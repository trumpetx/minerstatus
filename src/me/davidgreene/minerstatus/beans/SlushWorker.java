package me.davidgreene.minerstatus.beans;

import java.io.Serializable;
import java.math.BigDecimal;

public class SlushWorker implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6908308730427306282L;
	private Integer last_share;
	private String score;
	private BigDecimal hashrate;
	private Integer shares;
	private Boolean alive;
	public Integer getLast_share() {
		return last_share == null ? 0 : last_share;
	}
	public void setLast_share(Integer last_share) {
		this.last_share = last_share;
	}
	public String getScore() {
		return score == null ? "" : score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public BigDecimal getHashrate() {
		return hashrate == null ? BigDecimal.ZERO : hashrate;
	}
	public void setHashrate(BigDecimal hashrate) {
		this.hashrate = hashrate;
	}
	public Integer getShares() {
		return shares == null ? 0 : shares;
	}
	public void setShares(Integer shares) {
		this.shares = shares;
	}
	public Boolean getAlive() {
		return (alive == null) ? Boolean.FALSE : alive;
	}
	public void setAlive(Boolean alive) {
		this.alive = alive;
	}
}
