package me.davidgreene.minerstatus.beans;

import java.io.Serializable;
import java.math.BigDecimal;

public class RfcStatus implements Serializable, Status {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8275174008083089359L;
	private String apiKey;
	private RfcWorker[] workers;

	@Override
	public String getUsername() {
		return "Worker(s)";
	}

	@Override
	public String getDisplayCol1() {
		Integer totalShares = 0;
		for (RfcWorker worker : workers){
			totalShares += worker.getShares().getThis_round();
		}
		return totalShares.toString();
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
		return "Shares";
	}

	@Override
	public String getDisplayCol2Label() {
		return "Hashrate";
	}

	@Override
	public BigDecimal getTotalHashrate() {
		BigDecimal totalHashrate = BigDecimal.ZERO;
		try{
			for(RfcWorker worker : workers){
				totalHashrate = totalHashrate.add(new BigDecimal(worker.getHashrate()));
			}
		} catch (Exception e){ /*Just return Zero*/ }
		return totalHashrate.setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public RfcWorker[] getWorkers() {
		return workers;
	}

	public void setWorkers(RfcWorker[] workers) {
		this.workers = workers;
	}


}
