package me.davidgreene.minerstatus.beans;

import java.io.Serializable;

public class ArsWorker implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6618581272284739814L;
	private String alive;
	private String hashrate;
	public String getAlive() {
		return alive == null ? "" : alive;
	}
	public void setAlive(String alive) {
		this.alive = alive;
	}
	public String getHashrate() {
		return hashrate == null ? "" : hashrate;
	}
	public void setHashrate(String hashrate) {
		this.hashrate = hashrate;
	}
}
