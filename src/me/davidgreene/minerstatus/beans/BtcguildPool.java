package me.davidgreene.minerstatus.beans;

import java.io.Serializable;
import java.math.BigDecimal;

public class BtcguildPool implements Serializable {

	/**
	 * {"pool_speed":1322.83,"pps_rate":0.00003451277408592010,"difficulty":1376302,"pps_rate_nmc":0.00008622739707625588,"difficulty_nmc":550869}
	 */
	private static final long serialVersionUID = -4258828827151924731L;
	
	private BigDecimal pool_speed;
	private BigDecimal pps_rate;
	private BigDecimal pps_rate_nmc;
	private Integer difficulty;
	private Integer difficulty_nmc;
	public BigDecimal getPool_speed() {
		return pool_speed == null ? BigDecimal.ZERO : pool_speed;
	}
	public void setPool_speed(BigDecimal pool_speed) {
		this.pool_speed = pool_speed;
	}
	public BigDecimal getPps_rate() {
		return pps_rate == null ? BigDecimal.ZERO : pps_rate;
	}
	public void setPps_rate(BigDecimal pps_rate) {
		this.pps_rate = pps_rate;
	}
	public BigDecimal getPps_rate_nmc() {
		return pps_rate_nmc == null ? BigDecimal.ZERO : pps_rate_nmc;
	}
	public void setPps_rate_nmc(BigDecimal pps_rate_nmc) {
		this.pps_rate_nmc = pps_rate_nmc;
	}
	public Integer getDifficulty() {
		return difficulty == null ? 0 : difficulty;
	}
	public void setDifficulty(Integer difficulty) {
		this.difficulty = difficulty;
	}
	public Integer getDifficulty_nmc() {
		return difficulty_nmc == null ? 0 : difficulty_nmc;
	}
	public void setDifficulty_nmc(Integer difficulty_nmc) {
		this.difficulty_nmc = difficulty_nmc;
	}
	

}
