package me.davidgreene.minerstatus.beans;

import java.io.Serializable;
import java.math.BigDecimal;

public class MinecoinStats implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2048435662277392686L;

	private String created_at;
	private BigDecimal hash_rate;
	private Integer share_count;
	public String getCreated_at() {
		return created_at == null ? "" : created_at;
	}
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
	public BigDecimal getHash_rate() {
		return hash_rate == null ? BigDecimal.ZERO : hash_rate;
	}
	public void setHash_rate(BigDecimal hash_rate) {
		this.hash_rate = hash_rate;
	}
	public Integer getShare_count() {
		return share_count == null ? 0 : share_count;
	}
	public void setShare_count(Integer share_count) {
		this.share_count = share_count;
	}
}
