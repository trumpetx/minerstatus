package me.davidgreene.minerstatus.beans;

import java.io.Serializable;

public class EclipseMcWorker implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 5031534666304493052L;
	private String worker_name;
	private String hash_rate;
	private String round_shares;
	private String reset_shares;
	private String total_shares;
	private String last_activity;
	public String getWorker_name() {
		return worker_name;
	}
	public void setWorker_name(String worker_name) {
		this.worker_name = worker_name;
	}
	public String getHash_rate() {
		return hash_rate;
	}
	public void setHash_rate(String hash_rate) {
		this.hash_rate = hash_rate;
	}
	public String getRound_shares() {
		return round_shares;
	}
	public void setRound_shares(String round_shares) {
		this.round_shares = round_shares;
	}
	public String getReset_shares() {
		return reset_shares;
	}
	public void setReset_shares(String reset_shares) {
		this.reset_shares = reset_shares;
	}
	public String getTotal_shares() {
		return total_shares;
	}
	public void setTotal_shares(String total_shares) {
		this.total_shares = total_shares;
	}
	public String getLast_activity() {
		return last_activity;
	}
	public void setLast_activity(String last_activity) {
		this.last_activity = last_activity;
	}
}
