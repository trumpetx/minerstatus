package me.davidgreene.minerstatus.beans;

import java.io.Serializable;
import java.math.BigDecimal;

public class EclipseMcStatus implements Status, Serializable {

	/**
	 * {"user":{"confirmed_rewards":"0.00007664","unconfirmed_rewards":"0.00000000",
	 * "estimated_rewards":2.6560171005005e-5,"total_payout":"0.00000000","blocks_found":"0"},
	 * "workers":[{"worker_name":"trumpetx_worker","hash_rate":"9.54 MH\/s","round_shares":"1",
	 * "reset_shares":"3","total_shares":"3","last_activity":"2011-07-17 13:02:49"}]}
	 */
	private static final long serialVersionUID = 3330095142253113379L;
	private String apiKey;
	private EclipseMcUser user;
	private EclipseMcWorker[] workers;

	@Override
	public String getUsername() {
		return "Worker(s)";
	}

	@Override
	public String getDisplayCol1() {
		return getUser() == null ? "" : getUser().getTotal_payout();
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
		return "Total Payout";
	}

	@Override
	public String getDisplayCol2Label() {
		return "Hashrate";
	}

	@Override
	public BigDecimal getTotalHashrate() {
		BigDecimal totalHashrate = BigDecimal.ZERO;
		try{
			for(EclipseMcWorker worker : workers){
				StringBuffer sb = new StringBuffer();
				for(Character c : worker.getHash_rate().toCharArray()){
					if (Character.isDigit(c)){
						sb.append(c);
					} else {
						break;
					}
				}
				totalHashrate = totalHashrate.add(new BigDecimal(sb.toString()));
			}
		} catch (Exception e){ /* Just return Zero */ }
		
		return totalHashrate.setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public EclipseMcUser getUser() {
		return user;
	}

	public void setUser(EclipseMcUser user) {
		this.user = user;
	}

	public EclipseMcWorker[] getWorkers() {
		return workers;
	}

	public void setWorkers(EclipseMcWorker[] workers) {
		this.workers = workers;
	}

}
