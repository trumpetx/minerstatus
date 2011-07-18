package me.davidgreene.minerstatus.beans;

import java.io.Serializable;

public class DeepbitWorker implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1489566244233639810L;
	private Boolean alive;
	private Integer shares;
	private Integer stales;
	public Boolean getAlive() {
		return (alive == null) ? Boolean.FALSE : alive;
	}
	public void setAlive(Boolean alive) {
		this.alive = alive;
	}
	public Integer getShares() {
		return (shares == null) ? 0 : shares;
	}
	public void setShares(Integer shares) {
		this.shares = shares;
	}
	public Integer getStales() {
		return (stales == null) ? 0 : stales;
	}
	public void setStales(Integer stale) {
		this.stales = stale;
	}
	

}
