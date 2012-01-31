package me.davidgreene.minerstatus.beans;

import java.io.Serializable;
import java.math.BigDecimal;

public class SimplecoinCurrency implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5069239327837214946L;
	
	private BigDecimal confirmed_rewards;
	private BigDecimal estimated_rewards;
	private BigDecimal payout_history;
	public BigDecimal getConfirmed_rewards() {
		return confirmed_rewards == null ? BigDecimal.ZERO : confirmed_rewards.setScale(2, BigDecimal.ROUND_HALF_UP);
	}
	public void setConfirmed_rewards(BigDecimal confirmed_rewards) {
		this.confirmed_rewards = confirmed_rewards;
	}
	public BigDecimal getEstimated_rewards() {
		return estimated_rewards == null ? BigDecimal.ZERO : estimated_rewards.setScale(2, BigDecimal.ROUND_HALF_UP);
	}
	public void setEstimated_rewards(BigDecimal estimated_rewards) {
		this.estimated_rewards = estimated_rewards;
	}
	public BigDecimal getPayout_history() {
		return payout_history == null ? BigDecimal.ZERO : payout_history.setScale(2, BigDecimal.ROUND_HALF_UP);
	}
	public void setPayout_history(BigDecimal payout_history) {
		this.payout_history = payout_history;
	}
	
}
