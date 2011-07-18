package me.davidgreene.minerstatus.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Map;

public class ArsStatus implements Serializable, Status {

	/**
	 * {"confirmed_rewards":"0.74339679320343","hashrate":"3278","payout_history":"18.0843209912012",
	 * "totalPPSWork":"18.20610384","paidPPSWork":"18.08432099","PPSDonated":"0.00000000",
	 * "PPSShares":"569133","workers":{"guggemand.default":{"alive":"0","hashrate":"0"},
	 * "guggemand.mine01":{"alive":"1","hashrate":"1095"},"guggemand.mine02":{"alive":"1",
	 * "hashrate":"759"},"guggemand.mine03":{"alive":"1","hashrate":"644"},"guggemand.mine04":
	 * {"alive":"1","hashrate":"673"},"guggemand.home":{"alive":"1","hashrate":"107"}}}
	 */
	private static final long serialVersionUID = 7734783437782732388L;
	private String apiKey;
	private String confirmed_rewards;
	private String hashrate;
	private String payout_history;
	private String totalPPSWork;
	private String paidPPSWork;
	private String PPSDonated;
	private String PPSShares;
	private Map<String, ArsWorker> workers;
	
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

	public String getTotalPPSWork() {
		return totalPPSWork== null ? "" : totalPPSWork;
	}

	public void setTotalPPSWork(String totalPPSWork) {
		this.totalPPSWork = totalPPSWork;
	}

	public String getPaidPPSWork() {
		return paidPPSWork== null ? "" : paidPPSWork;
	}

	public void setPaidPPSWork(String paidPPSWork) {
		this.paidPPSWork = paidPPSWork;
	}

	public String getPPSDonated() {
		return PPSDonated== null ? "" : PPSDonated;
	}

	public void setPPSDonated(String pPSDonated) {
		PPSDonated = pPSDonated;
	}

	public String getPPSShares() {
		return PPSShares== null ? "" : PPSShares;
	}

	public void setPPSShares(String pPSShares) {
		PPSShares = pPSShares;
	}

	public Map<String, ArsWorker> getWorkers() {
		return workers;
	}

	public void setWorkers(Map<String, ArsWorker> workers) {
		this.workers = workers;
	}

}
