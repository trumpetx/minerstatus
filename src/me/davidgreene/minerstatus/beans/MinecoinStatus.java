package me.davidgreene.minerstatus.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Map;

public class MinecoinStatus implements Serializable, Status {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4665962070303489505L;

	private MinecoinUser user;
	private Map<String, MinecoinWorker> workers;
	private String apiKey;
	
	@Override
	public String getUsername() {
		return user == null ? "" : user.getUsername();
	}

	@Override
	public String getDisplayCol1() {
		return user == null ? "" : user.getTotal_reward().toString();
	}

	@Override
	public String getDisplayCol2() {
		return getTotalHashrate().toString();
	}

	@Override
	public String getUsernameLabel() {
		return null;
	}

	@Override
	public String getDisplayCol1Label() {
		return "Total Reward";
	}

	@Override
	public String getDisplayCol2Label() {
		return "Hashrate";
	}

	@Override
	public BigDecimal getTotalHashrate() {
		BigDecimal hashRate = BigDecimal.ZERO;
		if (workers != null){
			Iterator it = workers.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry pairs = (Map.Entry)it.next();
				MinecoinWorker worker = (MinecoinWorker) pairs.getValue();
				hashRate = hashRate.add(worker.getStats().getHash_rate());
			}
		}
		return hashRate.setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	@Override
	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	@Override
	public String getApiKey() {
		return apiKey;
	}

	public MinecoinUser getUser() {
		return user;
	}

	public void setUser(MinecoinUser user) {
		this.user = user;
	}

	public Map<String, MinecoinWorker> getWorkers() {
		return workers;
	}

	public void setWorkers(Map<String, MinecoinWorker> workers) {
		this.workers = workers;
	}

}
