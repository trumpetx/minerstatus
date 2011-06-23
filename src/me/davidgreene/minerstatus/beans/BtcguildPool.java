package me.davidgreene.minerstatus.beans;

import java.io.Serializable;
import java.math.BigDecimal;

public class BtcguildPool implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4258828827151924731L;
	
	private BigDecimal hash_rate;
	private Integer active_workers;
	private Integer round_shares;
	private String round_time;
	
	public BigDecimal getHash_rate() {
		return hash_rate;
	}
	public void setHash_rate(BigDecimal hash_rate) {
		this.hash_rate = hash_rate;
	}
	public Integer getActive_workers() {
		return active_workers;
	}
	public void setActive_workers(Integer active_workers) {
		this.active_workers = active_workers;
	}
	public Integer getActive_shares() {
		return round_shares;
	}
	public void setActive_shares(Integer round_shares) {
		this.round_shares = round_shares;
	}
	public String getRound_time() {
		return round_time;
	}
	public void setRound_time(String round_time) {
		this.round_time = round_time;
	}

}
