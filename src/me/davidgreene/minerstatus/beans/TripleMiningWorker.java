package me.davidgreene.minerstatus.beans;

import java.io.Serializable;

public class TripleMiningWorker implements Serializable {

	/**
	 * {"shares":"0","alive":"false","stales":"0"}
	 */
	private static final long serialVersionUID = 2684794105516033450L;

	private Integer shares;
	private Boolean alive;
	private Integer stales;
	public Integer getShares() {
		return shares == null ? 0 : shares;
	}
	public void setShares(Integer shares) {
		this.shares = shares;
	}
	public Boolean getAlive() {
		return alive == null ? false : alive;
	}
	public void setAlive(Boolean alive) {
		this.alive = alive;
	}
	public Integer getStales() {
		return stales == null ? 0 : stales;
	}
	public void setStales(Integer stales) {
		this.stales = stales;
	}
	
}
