package me.davidgreene.minerstatus.beans;

import java.io.Serializable;

public class BitclockersWorker implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1986573230275836794L;
	private Integer stale;
	private Integer shares;
	private String hashrate;
	public Integer getStale() {
		return stale;
	}
	public void setStales(Integer stale) {
		this.stale = stale;
	}
	public Integer getShares() {
		return shares;
	}
	public void setShares(Integer shares) {
		this.shares = shares;
	}
	public String getHashrate() {
		return hashrate;
	}
	public void setHashrate(String hashrate) {
		this.hashrate = hashrate;
	}
	
	

}
