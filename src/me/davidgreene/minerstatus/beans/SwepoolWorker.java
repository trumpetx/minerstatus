package me.davidgreene.minerstatus.beans;

import java.io.Serializable;

public class SwepoolWorker implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7735371392800954116L;
	
	private String worker;
	private String password;
	private String shares;
	private String stales;
	private String hashspeed;
	
	public String getWorker() {
		return worker;
	}
	public void setWorker(String worker) {
		this.worker = worker;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getShares() {
		return (shares == null) ? "0" : shares;
	}
	public void setShares(String shares) {
		this.shares = shares;
	}
	public String getStales() {
		return (stales == null) ? "0" : stales;
	}
	public void setStales(String stales) {
		this.stales = stales;
	}
	public String getHashspeed() {
		return (hashspeed == null) ? "0" : hashspeed;
	}
	public void setHashspeed(String hashspeed) {
		this.hashspeed = hashspeed;
	}
	
	
	

}
