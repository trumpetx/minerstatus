package me.davidgreene.minerstatus.beans;

import java.io.Serializable;
import java.math.BigDecimal;

public class EclipseMcUser implements Serializable {

	/**
	 * {"user":{"confirmed_rewards":"0.00007664","unconfirmed_rewards":"0.00000000",
	 * "estimated_rewards":2.6560171005005e-5,"total_payout":"0.00000000","blocks_found":"0"},
	 * "workers":[{"worker_name":"trumpetx_worker","hash_rate":"9.54 MH\/s","round_shares":"1",
	 * "reset_shares":"3","total_shares":"3","last_activity":"2011-07-17 13:02:49"}]} 
	 */
	private static final long serialVersionUID = -7401156353546163514L;

	private String confirmed_rewards;
	private String unconfirmed_rewards;
	private BigDecimal estimated_rewards;
	private String total_payout;
	private String blocks_found;
	public String getConfirmed_rewards() {
		return confirmed_rewards == null ? "" : confirmed_rewards;
	}
	public void setConfirmed_rewards(String confirmed_rewards) {
		this.confirmed_rewards = confirmed_rewards;
	}
	public String getUnconfirmed_rewards() {
		return unconfirmed_rewards == null ? "" : unconfirmed_rewards;
	}
	public void setUnconfirmed_rewards(String unconfirmed_rewards) {
		this.unconfirmed_rewards = unconfirmed_rewards;
	}
	public BigDecimal getEstimated_rewards() {
		return estimated_rewards == null ? BigDecimal.ZERO : estimated_rewards;
	}
	public void setEstimated_rewards(BigDecimal estimated_rewards) {
		this.estimated_rewards = estimated_rewards;
	}
	public String getTotal_payout() {
		return total_payout == null ? "" : total_payout;
	}
	public void setTotal_payout(String total_payout) {
		this.total_payout = total_payout;
	}
	public String getBlocks_found() {
		return blocks_found;
	}
	public void setBlocks_found(String blocks_found) {
		this.blocks_found = blocks_found;
	}

}
