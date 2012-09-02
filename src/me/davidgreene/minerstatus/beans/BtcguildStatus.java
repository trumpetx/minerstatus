package me.davidgreene.minerstatus.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Map;

import me.davidgreene.minerstatus.R;
import me.davidgreene.minerstatus.ViewMinerActivity;
import android.widget.TableLayout;

public class BtcguildStatus implements Status, Serializable, Renderable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2399234390831522839L;

	private BtcguildUser user;
	private BtcguildPool pool;
	private Map<String, BtcguildWorker> workers;
	private String apiKey;
	
	@Override
	public BigDecimal getTotalHashrate(){
		BigDecimal hashRate = BigDecimal.ZERO;
		if (workers != null){
			for(String key : workers.keySet()) {
				BtcguildWorker worker = (BtcguildWorker) workers.get(key);
				hashRate = hashRate.add(worker.getHash_rate());
			}
		} 
		return hashRate.setScale(2, BigDecimal.ROUND_HALF_UP);
	}
	
	@Override
	public String getUsername() {
		return DEFAULT_USERNAME;
	}

	@Override
	public String getDisplayCol1() {
		if (user == null){
			return "";
		}
		return user.getPast_24h_rewards().toString();
	}

	@Override
	public String getDisplayCol2() {
		if (workers == null){
			return "No Workers";
		} else {
			return getTotalHashrate().toString();
		}
	}

	@Override
	public String getUsernameLabel() {
		return "";
	}

	@Override
	public String getDisplayCol1Label() {
		return "Past 24 Hrs Rewards";
	}

	@Override
	public String getDisplayCol2Label() {
		return "Hash Rate";
	}

	public BtcguildUser getUser() {
		return user;
	}

	public void setUser(BtcguildUser user) {
		this.user = user;
	}

	public BtcguildPool getPool() {
		return pool;
	}

	public void setPool(BtcguildPool pool) {
		this.pool = pool;
	}

	public Map<String, BtcguildWorker> getWorkers() {
		return workers;
	}

	public void setWorkers(Map<String, BtcguildWorker> workers) {
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
		tl.addView(activity.renderRow("Paid Rewards", getUser().getPaid_rewards()));
		tl.addView(activity.renderRow("24h Rewards", getUser().getPast_24h_rewards()));
		tl.addView(activity.renderRow("Unpaid Rewards", getUser().getUnpaid_rewards()));
		tl.addView(activity.renderRow("NMC Paid Rewards", getUser().getPaid_rewards_nmc()));
		tl.addView(activity.renderRow("NMC 24h rewards", getUser().getPast_24h_rewards_nmc()));
		tl.addView(activity.renderRow("NMC Unpaid Rewards", getUser().getUnpaid_rewards_nmc()));

		tl.addView(activity.renderRow(DEFAULT_USERNAME + ":",""));
		if (getWorkers() != null){
		    for( String key : getWorkers().keySet() ){
		    	BtcguildWorker worker = getWorkers().get(key);
		    	tl.addView(activity.renderRow("",worker.getWorker_name()));
		    	tl.addView(activity.renderRow(HASHRATE_DISPLAY_COL_2_LABEL,worker.getHash_rate()));
		    	tl.addView(activity.renderRow("Valid Shares",worker.getValid_shares()));
		    	tl.addView(activity.renderRow("Dupe Shares",worker.getDupe_shares()));
		    	tl.addView(activity.renderRow("Stale Shares",worker.getStale_shares()));
		    	tl.addView(activity.renderRow("Valid NMC Shares",worker.getValid_shares_nmc()));
		    	tl.addView(activity.renderRow("Dupe NMC Shares",worker.getDupe_shares_nmc()));
		    	tl.addView(activity.renderRow("Stale NMC Shares",worker.getStale_shares_nmc()));
		    	tl.addView(activity.renderRow("",""));
		    }
		}
		tl.addView(activity.renderRow("",""));
	}	
	
}
