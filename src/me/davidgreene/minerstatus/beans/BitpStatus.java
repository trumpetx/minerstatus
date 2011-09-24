package me.davidgreene.minerstatus.beans;

import java.io.Serializable;
import java.math.BigDecimal;

public class BitpStatus implements Status, Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 7125942808625926972L;

	private String apiKey;
	private Integer shares;
	private String score;
	private String estimated_reward;
	private String unconfirmed_balance;
	private String confirmed_balance;
	private String rate;
	private BitpWorker[] workers;
	
	@Override
	public String getUsername() {
		return "Worker(s)";
	}

	@Override
	public String getDisplayCol1() {
		return getConfirmed_balance();
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
		return "Balance";
	}

	@Override
	public String getDisplayCol2Label() {
		return "Hashrate";
	}

	@Override
	public BigDecimal getTotalHashrate() {
		StringBuffer sb = new StringBuffer();
		for (Character c : getRate().toCharArray()){
			if (Character.isDigit(c)){
				sb.append(c);
			}
		}
		return new BigDecimal((sb.length() == 0) ? "0" : sb.toString()).setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public Integer getShares() {
		return shares == null ? 0 : shares;
	}

	public void setShares(Integer shares) {
		this.shares = shares;
	}

	public String getScore() {
		return score == null ? "" : score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getEstimated_reward() {
		return estimated_reward == null ? "" : estimated_reward;
	}

	public void setEstimated_reward(String estimated_reward) {
		this.estimated_reward = estimated_reward;
	}

	public String getUnconfirmed_balance() {
		return unconfirmed_balance == null ? "" : unconfirmed_balance;
	}

	public void setUnconfirmed_balance(String unconfirmed_balance) {
		this.unconfirmed_balance = unconfirmed_balance;
	}

	public String getConfirmed_balance() {
		return confirmed_balance == null ? "" : confirmed_balance;
	}

	public void setConfirmed_balance(String confirmed_balance) {
		this.confirmed_balance = confirmed_balance;
	}

	public String getRate() {
		return rate == null ? "" : rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public BitpWorker[] getWorkers() {
		return workers;
	}

	public void setWorkers(BitpWorker[] workers) {
		this.workers = workers;
	}


}
