package me.davidgreene.minerstatus.beans;

import java.io.Serializable;

public class RfcShares implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7543314459779133266L;
	private Integer this_round;
	private Integer all_time;
	private Long last;
	public Integer getThis_round() {
		return this_round == null ? 0 : this_round;
	}
	public void setThis_round(Integer this_round) {
		this.this_round = this_round;
	}
	public Integer getAll_time() {
		return all_time == null ? 0 : all_time;
	}
	public void setAll_time(Integer all_time) {
		this.all_time = all_time;
	}
	public Long getLast() {
		return last == null ? 0L : last;
	}
	public void setLast(Long last) {
		this.last = last;
	}
}
