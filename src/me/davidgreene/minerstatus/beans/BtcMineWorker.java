package me.davidgreene.minerstatus.beans;

import java.io.Serializable;

public class BtcMineWorker implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3540379237186520290L;

	private String name;
	private String date_connected;
	private Boolean online_status;
	private Integer solved_shares;
	private Integer solved_blocks;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDate_connected() {
		return date_connected;
	}
	public void setDate_connected(String date_connected) {
		this.date_connected = date_connected;
	}
	public Boolean getOnline_status() {
		return online_status;
	}
	public void setOnline_status(Boolean online_status) {
		this.online_status = online_status;
	}
	public Integer getSolved_shares() {
		return solved_shares;
	}
	public void setSolved_shares(Integer solved_shares) {
		this.solved_shares = solved_shares;
	}
	public Integer getSolved_blocks() {
		return solved_blocks;
	}
	public void setSolved_blocks(Integer solved_blocks) {
		this.solved_blocks = solved_blocks;
	}
	
}
