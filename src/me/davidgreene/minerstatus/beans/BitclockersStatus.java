package me.davidgreene.minerstatus.beans;

import java.io.Serializable;
import java.util.Map;

public class BitclockersStatus implements Serializable, Status {

	/**
	 * 
	 */
	private static final long serialVersionUID = 521022577604612057L;
	
	private Double balance;
	private String payout;
	private Double hashrate;
	private Map<String, BitclockersWorker> workers;
	private String apiKey;
	

	@Override
	public String getUsername() {
		return "Worker(s)";
	}

	@Override
	public String getDisplayCol1() {
		return balance.toString();
	}

	@Override
	public String getDisplayCol2() {
		return hashrate.toString();
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

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public String getPayout() {
		return payout;
	}

	public void setPayout(String payout) {
		this.payout = payout;
	}

	public Double getHashrate() {
		return hashrate;
	}

	public void setHashrate(Double hashrate) {
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


}
