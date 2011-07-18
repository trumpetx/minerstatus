package me.davidgreene.minerstatus.beans;

import java.io.Serializable;
import java.math.BigDecimal;

public class MinecoinUser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2587900361054137456L;

	private String username;
	private String estimated_reward_this_round;
	private BigDecimal confirmed_reward;
	private BigDecimal unconfirmed_reward;
	private BigDecimal total_reward;
	public String getUsername() {
		return username == null ? "" : username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEstimated_reward_this_round() {
		return estimated_reward_this_round == null ? "" : estimated_reward_this_round;
	}
	public void setEstimated_reward_this_round(String estimated_reward_this_round) {
		this.estimated_reward_this_round = estimated_reward_this_round;
	}
	public BigDecimal getConfirmed_reward() {
		return confirmed_reward == null ? BigDecimal.ZERO : confirmed_reward.setScale(2, BigDecimal.ROUND_HALF_UP);
	}
	public void setConfirmed_reward(BigDecimal confirmed_reward) {
		this.confirmed_reward = confirmed_reward;
	}
	public BigDecimal getUnconfirmed_reward() {
		return unconfirmed_reward == null ? BigDecimal.ZERO : unconfirmed_reward.setScale(2, BigDecimal.ROUND_HALF_UP);
	}
	public void setUnconfirmed_reward(BigDecimal unconfirmed_reward) {
		this.unconfirmed_reward = unconfirmed_reward;
	}
	public BigDecimal getTotal_reward() {
		return total_reward == null ? BigDecimal.ZERO : total_reward.setScale(2, BigDecimal.ROUND_HALF_UP);
	}
	public void setTotal_reward(BigDecimal total_reward) {
		this.total_reward = total_reward;
	}
	
}
