package me.davidgreene.minerstatus.beans;

import java.io.Serializable;

public class RfcWorker implements Serializable {

	/**
	 * {"workers":[{"login":"trumpetx_worker","shares":{"this_round":0,"all_time":0,"last":null},
	 * "stales":{"this_round":0,"all_time":0,"last":null}}]}
	 */
	private static final long serialVersionUID = -9093227205062545099L;
	
	private String login;
	private String hashrate;
	private RfcShares shares;
	private RfcShares stales;
	public String getLogin() {
		return login == null ? "" : login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public RfcShares getShares() {
		return shares == null ? new RfcShares() : shares;
	}
	public void setShares(RfcShares shares) {
		this.shares = shares;
	}
	public RfcShares getStales() {
		return stales == null ? new RfcShares() : stales;
	}
	public void setStales(RfcShares stales) {
		this.stales = stales;
	}
	public String getHashrate() {
		return hashrate == null ? "0" : hashrate;
	}
	public void setHashrate(String hashrate) {
		this.hashrate = hashrate;
	}
}
