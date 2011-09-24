package me.davidgreene.minerstatus.beans;

import java.io.Serializable;
import java.math.BigDecimal;

public class BitpWorker implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1339473104866621211L;

	private String label;
	private String password;
	private String username;
	private Integer blocks;
	private String rate;
	private BigDecimal score;
	public String getLabel() {
		return label==null ? "" : label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getPassword() {
		return password == null ? "" : password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUsername() {
		return username == null ? "" : username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Integer getBlocks() {
		return blocks == null ? 0 : blocks;
	}
	public void setBlocks(Integer blocks) {
		this.blocks = blocks;
	}
	public String getRate() {
		return rate == null ? "" : rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
	public BigDecimal getScore() {
		return score == null ? BigDecimal.ZERO : score;
	}
	public void setScore(BigDecimal score) {
		this.score = score;
	}
	
	
}
