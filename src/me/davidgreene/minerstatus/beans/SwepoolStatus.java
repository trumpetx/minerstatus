package me.davidgreene.minerstatus.beans;

import java.io.Serializable;
import java.math.BigDecimal;

public class SwepoolStatus implements Status, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -494004302693490738L;

	private String balance;
	private String address;
	private BigDecimal paypershare;
	private String pool_speed;
	private SwepoolWorker[] workers;
	private String apiKey;
	
	@Override
	public BigDecimal getTotalHashrate(){
		try{
			BigDecimal totalHashRate = BigDecimal.ZERO;
			if (workers != null){
				for (SwepoolWorker worker : workers){
					totalHashRate.add(new BigDecimal(worker.getHashspeed()));
				}
			}
			return totalHashRate.setScale(2, BigDecimal.ROUND_HALF_UP);
		} catch (Exception e){
			return BigDecimal.ZERO;
		}
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
		if (workers == null){
			return "No workers";
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
		return "Balance";
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

	public String getBalance() {
		BigDecimal balanceDecimal = new BigDecimal(balance).setScale(6, BigDecimal.ROUND_HALF_UP);
		return (balanceDecimal == null) ? "0" : balanceDecimal.toString();
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public BigDecimal getPaypershare() {
		return paypershare;
	}

	public void setPaypershare(BigDecimal paypershare) {
		this.paypershare = paypershare;
	}

	public String getPool_speed() {
		return pool_speed;
	}

	public void setPool_speed(String pool_speed) {
		this.pool_speed = pool_speed;
	}

	public SwepoolWorker[] getWorkers() {
		return workers;
	}

	public void setWorkers(SwepoolWorker[] workers) {
		this.workers = workers;
	}

}
