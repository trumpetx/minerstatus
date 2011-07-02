package me.davidgreene.minerstatus.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Map;

public class OzcoinStatus implements Serializable, Status {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6915687432225003232L;

	//{"confirmed_rewards":"0","hashrate":"0","payout_history":"0","workers":{"trumpetx.1":{"alive":"0","hashrate":"0"}}}
	
	private String confirmed_rewards;
	private String hashrate;
	private String payout_history;
	private Map<String, OzcoinWorker> workers;
	private String apiKey;
	
	@Override
	public BigDecimal getTotalHashrate(){
		try{
			return new BigDecimal(getHashrate()).setScale(2, BigDecimal.ROUND_HALF_UP);
		} catch (Exception e){
			return BigDecimal.ZERO;
		}
	}
	
	@Override
	public String getUsername() {
		if (workers == null || workers.isEmpty()){
			return "No workers";
		}
		return "Worker(s)";
	}

	@Override
	public String getDisplayCol1() {
		return getConfirmed_rewards();
	}

	@Override
	public String getDisplayCol2() {
		return getHashrate();
	}

	@Override
	public String getUsernameLabel() {
		return "";
	}

	@Override
	public String getDisplayCol1Label() {
		return "Confirmed Rewards";
	}

	@Override
	public String getDisplayCol2Label() {
		return "Hashrate";
	}

	@Override
	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	@Override
	public String getApiKey() {
		return apiKey;
	}

	public String getConfirmed_rewards() {
		return confirmed_rewards == null ? "" : confirmed_rewards;
	}

	public void setConfirmed_rewards(String confirmed_rewards) {
		this.confirmed_rewards = confirmed_rewards;
	}

	public String getHashrate() {
		return hashrate;
	}

	public void setHashrate(String hashrate) {
		this.hashrate = hashrate;
	}

	public String getPayout_history() {
		return payout_history == null ? "" : payout_history;
	}

	public void setPayout_history(String payout_history) {
		this.payout_history = payout_history;
	}

	public Map<String, OzcoinWorker> getWorkers() {
		return workers;
	}

	public void setWorkers(Map<String, OzcoinWorker> workers) {
		this.workers = workers;
	}

}
