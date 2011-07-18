package me.davidgreene.minerstatus.beans;

import java.io.Serializable;

public class MinecoinWorker implements Serializable {

	//
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -708021592795206217L;

	private String password;
	private String last_share_at;
	private MinecoinStats stats;
	public String getPassword() {
		return password == null ? "" : password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getLast_share_at() {
		return last_share_at == null ? "" : last_share_at;
	}
	public void setLast_share_at(String last_share_at) {
		this.last_share_at = last_share_at;
	}
	public MinecoinStats getStats() {
		return stats;
	}
	public void setStats(MinecoinStats stats) {
		this.stats = stats;
	}
}
