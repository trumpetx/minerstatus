package me.davidgreene.minerstatus.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Map;

import me.davidgreene.minerstatus.R;
import me.davidgreene.minerstatus.ViewMinerActivity;
import me.davidgreene.minerstatus.util.Renderable;
import android.widget.TableLayout;

public class MtredStatus implements Status, Serializable, Renderable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2672499038173236256L;

	private String balance;
	private String rsolved;
	private MtredServer server;
	private Map<String, MtredWorker> workers;
	private String apiKey;
	
	@Override
	public BigDecimal getTotalHashrate(){
		BigDecimal hashRate = BigDecimal.ZERO;
		if (workers != null){
			Iterator it = workers.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry pairs = (Map.Entry)it.next();
				pairs.getKey();
				MtredWorker worker = (MtredWorker) pairs.getValue();
				hashRate = hashRate.add(worker.getMhash());
			}
		}
		return hashRate.setScale(2, BigDecimal.ROUND_HALF_UP);
	}
	
	@Override
	public String getUsername() {
		return "Worker(s)";
	}

	@Override
	public String getDisplayCol1() {
		return getBalance();
	}

	@Override
	public String getDisplayCol2() {
		return getTotalHashrate().toString();
	}

	@Override
	public String getUsernameLabel() {
		return "";
	}

	@Override
	public String getDisplayCol1Label() {
		return "Balance";
	}

	@Override
	public String getDisplayCol2Label() {
		return "Hashrate";
	}

	public String getBalance() {
		return (balance != null) ? new BigDecimal(balance).setScale(6, BigDecimal.ROUND_HALF_UP).toString() : "";
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public String getRsolved() {
		return (rsolved == null) ? "" : rsolved;
	}

	public void setRsolved(String rsolved) {
		this.rsolved = rsolved;
	}

	public MtredServer getServer() {
		return server;
	}

	public void setServer(MtredServer server) {
		this.server = server;
	}

	public Map<String, MtredWorker> getWorkers() {
		return workers;
	}

	public void setWorkers(Map<String, MtredWorker> workers) {
		this.workers = workers;
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}
	
	public void render(ViewMinerActivity activity){
		TableLayout tl = (TableLayout) activity.findViewById(R.id.detailedView);
		tl.addView(activity.renderRow("Balance", getBalance()));
		tl.addView(activity.renderRow("User Round Solved", getRsolved()));
		
		BigDecimal rsolved = new BigDecimal(getRsolved());
		BigDecimal roundShares = new BigDecimal(getServer().getRoundshares());
		BigDecimal blockValue = new BigDecimal(50);
		BigDecimal estimatedReward = BigDecimal.ZERO;
		if (!roundShares.equals(BigDecimal.ZERO)){
			estimatedReward = blockValue.multiply(rsolved).divide(roundShares, 4, BigDecimal.ROUND_HALF_UP).setScale(4, BigDecimal.ROUND_HALF_UP);		
		}
		tl.addView(activity.renderRow("Estimated Payout", estimatedReward.toString()));
		tl.addView(activity.renderRow("Server Hashrate", getServer().getHashrate().toString()));
		tl.addView(activity.renderRow("Server Round Shares", getServer().getRoundshares().toString()));
		tl.addView(activity.renderRow("Server Found Block", getServer().getFoundblock().toString()));
		tl.addView(activity.renderRow("Server Workers", getServer().getWorkers().toString()));
		if (getWorkers() != null){
		    for( String key : getWorkers().keySet() ){
		    	MtredWorker worker = getWorkers().get(key);
		    	tl.addView(activity.renderRow("",key));
		    	tl.addView(activity.renderRow("Round Solved",worker.getRsolved().toString()));
		    	tl.addView(activity.renderRow("Hashrate",worker.getMhash().toString()));
		    	tl.addView(activity.renderRow("",""));
		    }		
		}
		tl.addView(activity.renderRow("",""));
	}	

}
