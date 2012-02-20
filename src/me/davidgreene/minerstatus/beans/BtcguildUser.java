package me.davidgreene.minerstatus.beans;

import java.io.Serializable;
import java.math.BigDecimal;

public class BtcguildUser implements Serializable {

	/**
	 * {"user_id":759,"total_rewards":0.00000000,"paid_rewards":0.00000000,"unpaid_rewards":0.00000000,"past_24h_rewards":0.00000000,
	 * "total_rewards_nmc":0.00000000,"paid_rewards_nmc":0.00000000,"unpaid_rewards_nmc":0.00000000,"past_24h_rewards_nmc":0.00000000}
	 */
	private static final long serialVersionUID = 7805430620398782585L;
	
	private Integer user_id;
	private BigDecimal total_rewards;
	private BigDecimal paid_rewards;
	private BigDecimal unpaid_rewards;
	private BigDecimal past_24h_rewards;
	private BigDecimal total_rewards_nmc;
	private BigDecimal paid_rewards_nmc;
	private BigDecimal unpaid_rewards_nmc;
	private BigDecimal past_24h_rewards_nmc;
	
	public Integer getUser_id() {
		return user_id == null ? 0 : user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public BigDecimal getTotal_rewards() {
		return total_rewards == null ? BigDecimal.ZERO : total_rewards;
	}
	public void setTotal_rewards(BigDecimal total_rewards) {
		this.total_rewards = total_rewards;
	}
	public BigDecimal getPaid_rewards() {
		return paid_rewards == null ? BigDecimal.ZERO : paid_rewards;
	}
	public void setPaid_rewards(BigDecimal paid_rewards) {
		this.paid_rewards = paid_rewards;
	}
	public BigDecimal getUnpaid_rewards() {
		return unpaid_rewards == null ? BigDecimal.ZERO : unpaid_rewards;
	}
	public void setUnpaid_rewards(BigDecimal unpaid_rewards) {
		this.unpaid_rewards = unpaid_rewards;
	}
	public BigDecimal getPast_24h_rewards() {
		return past_24h_rewards == null ? BigDecimal.ZERO : past_24h_rewards;
	}
	public void setPast_24h_rewards(BigDecimal past_24h_rewards) {
		this.past_24h_rewards = past_24h_rewards;
	}
	public BigDecimal getTotal_rewards_nmc() {
		return total_rewards_nmc == null ? BigDecimal.ZERO : total_rewards_nmc;
	}
	public void setTotal_rewards_nmc(BigDecimal total_rewards_nmc) {
		this.total_rewards_nmc = total_rewards_nmc;
	}
	public BigDecimal getPaid_rewards_nmc() {
		return paid_rewards_nmc == null ? BigDecimal.ZERO : paid_rewards_nmc;
	}
	public void setPaid_rewards_nmc(BigDecimal paid_rewards_nmc) {
		this.paid_rewards_nmc = paid_rewards_nmc;
	}
	public BigDecimal getUnpaid_rewards_nmc() {
		return unpaid_rewards_nmc == null ? BigDecimal.ZERO : unpaid_rewards_nmc;
	}
	public void setUnpaid_rewards_nmc(BigDecimal unpaid_rewards_nmc) {
		this.unpaid_rewards_nmc = unpaid_rewards_nmc;
	}
	public BigDecimal getPast_24h_rewards_nmc() {
		return past_24h_rewards_nmc == null ? BigDecimal.ZERO : past_24h_rewards_nmc;
	}
	public void setPast_24h_rewards_nmc(BigDecimal past_24h_rewards_nmc) {
		this.past_24h_rewards_nmc = past_24h_rewards_nmc;
	}

	
	
}
