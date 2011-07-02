package me.davidgreene.minerstatus.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Map;

public class SlushStatus implements Status, Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = -4156611246722285882L;
	private String username;
	private String unconfirmed_reward;
	private String send_threshold;
	private String confirmed_reward;
	private String wallet;
	private String estimated_reward;
	private Map<String, SlushWorker> workers;
	private String apiKey;
	
	@Override
	public BigDecimal getTotalHashrate(){
		BigDecimal hashRate = BigDecimal.ZERO;
		if (workers != null){
			Iterator it = workers.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry pairs = (Map.Entry)it.next();
				pairs.getKey();
				SlushWorker worker = (SlushWorker) pairs.getValue();
				hashRate = hashRate.add(worker.getHashrate());
			}
		}
		return hashRate.setScale(2, BigDecimal.ROUND_HALF_UP);
	}
	
	@Override
	public String getDisplayCol1() {
		return (confirmed_reward == null) ? "" : confirmed_reward;
	}
	@Override
	public String getDisplayCol2() {
		if (workers == null){
			return "No Workers";
		} else {
			return getTotalHashrate().toString();
		}
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUnconfirmed_reward() {
		return unconfirmed_reward;
	}
	public void setUnconfirmed_reward(String unconfirmed_reward) {
		this.unconfirmed_reward = unconfirmed_reward;
	}
	public String getSend_threshold() {
		return send_threshold;
	}
	public void setSend_threshold(String send_threshold) {
		this.send_threshold = send_threshold;
	}
	public String getConfirmed_reward() {
		return confirmed_reward;
	}
	public void setConfirmed_reward(String confirmed_reward) {
		this.confirmed_reward = confirmed_reward;
	}
	public String getWallet() {
		return wallet;
	}
	public void setWallet(String wallet) {
		this.wallet = wallet;
	}
	public String getEstimated_reward() {
		return estimated_reward;
	}
	public void setEstimated_reward(String estimated_reward) {
		this.estimated_reward = estimated_reward;
	}
	public String getApiKey() {
		return apiKey;
	}
	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}
	@Override
	public String getUsernameLabel() {
		return "";
	}
	@Override
	public String getDisplayCol1Label() {
		return "Confirmed Reward";
	}
	@Override
	public String getDisplayCol2Label() {
		return "Hashrate";
	}
	public Map<String, SlushWorker> getWorkers() {
		return workers;
	}
	public void setWorkers(Map<String, SlushWorker> workers) {
		this.workers = workers;
	}

}
