package me.davidgreene.minerstatus.beans;

import java.io.Serializable;
import java.math.BigDecimal;

public class BtcguildUser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7805430620398782585L;
	
	private BigDecimal confirmed_rewards;
	private BigDecimal unconfirmed_rewards;
	private BigDecimal estimated_rewards;
	private BigDecimal payouts;
	
	public BigDecimal getConfirmed_rewards() {
		return confirmed_rewards == null ? BigDecimal.ZERO : confirmed_rewards;
	}
	public void setConfirmed_rewards(BigDecimal confirmed_rewards) {
		this.confirmed_rewards = confirmed_rewards;
	}
	public BigDecimal getUnconfirmed_rewards() {
		return unconfirmed_rewards == null ? BigDecimal.ZERO : unconfirmed_rewards;
	}
	public void setUnconfirmed_rewards(BigDecimal unconfirmed_rewards) {
		this.unconfirmed_rewards = unconfirmed_rewards;
	}
	public BigDecimal getEstimated_rewards() {
		return estimated_rewards == null ? BigDecimal.ZERO : estimated_rewards;
	}
	public void setEstimated_rewards(BigDecimal estimated_rewards) {
		this.estimated_rewards = estimated_rewards;
	}
	public BigDecimal getPayouts() {
		return payouts == null ? BigDecimal.ZERO : payouts;
	}
	public void setPayouts(BigDecimal payouts) {
		this.payouts = payouts;
	}
	
}
