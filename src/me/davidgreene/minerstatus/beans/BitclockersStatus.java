package me.davidgreene.minerstatus.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Map;

import me.davidgreene.minerstatus.R;
import me.davidgreene.minerstatus.ViewMinerActivity;
import me.davidgreene.minerstatus.util.Renderable;
import android.widget.TableLayout;

public class BitclockersStatus implements Serializable, Status, Renderable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 521022577604612057L;
	
	private BigDecimal balance;
	private String payout;
	private BigDecimal hashrate;
	private Map<String, BitclockersWorker> workers;
	private String apiKey;
	
	@Override
	public BigDecimal getTotalHashrate(){
		return getHashrate().setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	@Override
	public String getUsername() {
		return "Worker(s)";
	}

	@Override
	public String getDisplayCol1() {
		return getBalance().toString();
	}

	@Override
	public String getDisplayCol2() {
		return getHashrate().toString();
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

	public BigDecimal getBalance() {
		return balance == null ? BigDecimal.ZERO : balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public String getPayout() {
		return payout == null ? "" : payout;
	}

	public void setPayout(String payout) {
		this.payout = payout;
	}

	public BigDecimal getHashrate() {
		return hashrate == null ? BigDecimal.ZERO : hashrate;
	}

	public void setHashrate(BigDecimal hashrate) {
		this.hashrate = hashrate;
	}

	public Map<String, BitclockersWorker> getWorkers() {
		return workers;
	}

	public void setWorkers(Map<String, BitclockersWorker> workers) {
		this.workers = workers;
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public void render(ViewMinerActivity activity) {
		TableLayout tl = (TableLayout) activity.findViewById(R.id.detailedView);
		tl.addView(activity.renderRow("Balance", getBalance().toString()));
		tl.addView(activity.renderRow("Total Withdrawn", getPayout()));
		tl.addView(activity.renderRow("Hashrate", getHashrate().toString()));
		if (getWorkers() != null){
		    for( String key : getWorkers().keySet() ){
		    	BitclockersWorker worker = getWorkers().get(key);
		    	tl.addView(activity.renderRow("",key));
		    	tl.addView(activity.renderRow("Shares",worker.getShares().toString()));
		    	tl.addView(activity.renderRow("Stale",worker.getStale().toString()));
		    	tl.addView(activity.renderRow("Hashrate",worker.getHashrate()));
		    	tl.addView(activity.renderRow("",""));
		    }
		}
		tl.addView(activity.renderRow("",""));
	}
}
