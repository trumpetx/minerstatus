package me.davidgreene.minerstatus.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Map;

public class BtcguildStatus implements Status, Serializable {

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
			Iterator it = workers.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry pairs = (Map.Entry)it.next();
				pairs.getKey();
				BtcguildWorker worker = (BtcguildWorker) pairs.getValue();
				hashRate = hashRate.add(worker.getHash_rate());
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
		if (user == null || user.getConfirmed_rewards() == null){
			return "";
		}
		return user.getConfirmed_rewards().toString();
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
		return "Confirmed Rewards";
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

}
