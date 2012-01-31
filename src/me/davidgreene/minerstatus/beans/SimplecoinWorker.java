package me.davidgreene.minerstatus.beans;

import java.io.Serializable;
import java.math.BigDecimal;

public class SimplecoinWorker implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1880931194233899718L;

	private Boolean alive;
	private BigDecimal hashrate;
	private String pool;
	public Boolean getAlive() {
		return alive == null ? Boolean.FALSE : alive;
	}
	public void setAlive(Boolean alive) {
		this.alive = alive;
	}
	public BigDecimal getHashrate() {
		return hashrate == null ? BigDecimal.ZERO : hashrate.setScale(2, BigDecimal.ROUND_HALF_UP);
	}
	public void setHashrate(BigDecimal hashrate) {
		this.hashrate = hashrate;
	}
	public String getPool() {
		return pool == null ? "" : pool;
	}
	public void setPool(String pool) {
		this.pool = pool;
	}
	
}
