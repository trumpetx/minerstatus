package me.davidgreene.minerstatus.beans;

import java.io.Serializable;

public class OzcoinWorker implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -708021592795206217L;

	private String hashrate;
	private String alive;
	public String getHashrate() {
		return hashrate == null ? "" : hashrate;
	}
	public void setHashrate(String hashrate) {
		this.hashrate = hashrate;
	}
	public String getAlive() {
		return alive == null ? "false" : (alive.equals("0") ? "false" : "true");
	}
	public void setAlive(String alive) {
		this.alive = alive;
	}
	
}
