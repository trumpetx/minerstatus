package me.davidgreene.minerstatus.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Map;

public class ABCStatus implements Serializable, Status {

	/**
	 * {"confirmed_rewards":0.05043161686,"hashrate":628,"payout_history":0,
     * "workers":{"example.test":{"alive":true,"hashrate":628}}}
	 */
	private static final long serialVersionUID = 5089947276893311846L;
	private String apiKey;
	private String confirmed_rewards;
	private String hashrate;
	private String payout_history;
	private Map<String, ABCWorker> workers;
	
	@Override
	public String getUsername() {
		return "Worker(s)";
	}

	@Override
	public String getDisplayCol1() {
		try{
			return new BigDecimal(getConfirmed_rewards()).setScale(2, BigDecimal.ROUND_HALF_UP).toString();	
		} catch (Exception e){
			return "0";
		}
		
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
	public BigDecimal getTotalHashrate() {
		try{
			return new BigDecimal(getHashrate()).setScale(2, BigDecimal.ROUND_HALF_UP);
		} catch (Exception e){
			return BigDecimal.ZERO;
		}
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public String getConfirmed_rewards() {
		return confirmed_rewards == null ? "" : confirmed_rewards;
	}

	public void setConfirmed_rewards(String confirmed_rewards) {
		this.confirmed_rewards = confirmed_rewards;
	}

	public String getHashrate() {
		return hashrate== null ? "" : hashrate;
	}

	public void setHashrate(String hashrate) {
		this.hashrate = hashrate;
	}

	public String getPayout_history() {
		return payout_history== null ? "" : payout_history;
	}

	public void setPayout_history(String payout_history) {
		this.payout_history = payout_history;
	}

	public Map<String, ABCWorker> getWorkers() {
		return workers;
	}

	public void setWorkers(Map<String, ABCWorker> workers) {
		this.workers = workers;
	}

}
