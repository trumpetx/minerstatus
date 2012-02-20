package me.davidgreene.minerstatus.beans;

import java.io.Serializable;
import java.math.BigDecimal;

public class BtcguildWorker implements Serializable {

	/**
	 * {"worker_name":"trumpetx_57701","hash_rate":0.00,"valid_shares":0,"stale_shares":0,"dupe_shares":0,"unknown_shares":0,
	 * "valid_shares_since_reset":0,"stale_shares_since_reset":0,"dupe_shares_since_reset":0,"unknown_shares_since_reset":0,
	 * "valid_shares_nmc":0,"stale_shares_nmc":0,"dupe_shares_nmc":0,"unknown_shares_nmc":0,"valid_shares_nmc_since_reset":0,
	 * "stale_shares_nmc_since_reset":0,"dupe_shares_nmc_since_reset":0,"unknown_shares_nmc_since_reset":0,"last_share":0}
	 */
	private static final long serialVersionUID = -6917436321008955505L;
	
	private String worker_name;
	private BigDecimal hash_rate;
	private Integer stale_shares;
	private Integer valid_shares;
	private Integer dupe_shares;
	private Integer unknown_shares;
	private Integer valid_shares_since_reset;
	private Integer stale_shares_since_reset;
	private Integer dupe_shares_since_reset;
	private Integer unknown_shares_since_reset;
	private Integer valid_shares_nmc;
	private Integer stale_shares_nmc;
	private Integer dupe_shares_nmc;
	private Integer unknown_shares_nmc;
	private Integer valid_shares_nmc_since_reset;
	private Integer stale_shares_nmc_since_reset;
	private Integer dupe_shares_nmc_since_reset;
	private Integer unknown_shares_nmc_since_reset;
	private Integer last_share;
	public String getWorker_name() {
		return worker_name == null ? "" : worker_name;
	}
	public void setWorker_name(String worker_name) {
		this.worker_name = worker_name;
	}
	public BigDecimal getHash_rate() {
		return hash_rate == null ? BigDecimal.ZERO : hash_rate;
	}
	public void setHash_rate(BigDecimal hash_rate) {
		this.hash_rate = hash_rate;
	}
	public Integer getStale_shares() {
		return stale_shares == null ? 0 : stale_shares;
	}
	public void setStale_shares(Integer stale_shares) {
		this.stale_shares = stale_shares;
	}
	public Integer getValid_shares() {
		return valid_shares == null ? 0 : valid_shares;
	}
	public void setValid_shares(Integer valid_shares) {
		this.valid_shares = valid_shares;
	}
	public Integer getDupe_shares() {
		return dupe_shares == null ? 0 : dupe_shares;
	}
	public void setDupe_shares(Integer dupe_shares) {
		this.dupe_shares = dupe_shares;
	}
	public Integer getUnknown_shares() {
		return unknown_shares == null ? 0 : unknown_shares;
	}
	public void setUnknown_shares(Integer unknown_shares) {
		this.unknown_shares = unknown_shares;
	}
	public Integer getValid_shares_since_reset() {
		return valid_shares_since_reset == null ? 0 : valid_shares_since_reset;
	}
	public void setValid_shares_since_reset(Integer valid_shares_since_reset) {
		this.valid_shares_since_reset = valid_shares_since_reset;
	}
	public Integer getStale_shares_since_reset() {
		return stale_shares_since_reset == null ? 0 : stale_shares_since_reset;
	}
	public void setStale_shares_since_reset(Integer stale_shares_since_reset) {
		this.stale_shares_since_reset = stale_shares_since_reset;
	}
	public Integer getDupe_shares_since_reset() {
		return dupe_shares_since_reset == null ? 0 : dupe_shares_since_reset;
	}
	public void setDupe_shares_since_reset(Integer dupe_shares_since_reset) {
		this.dupe_shares_since_reset = dupe_shares_since_reset;
	}
	public Integer getUnknown_shares_since_reset() {
		return unknown_shares_since_reset == null ? 0 : unknown_shares_since_reset;
	}
	public void setUnknown_shares_since_reset(Integer unknown_shares_since_reset) {
		this.unknown_shares_since_reset = unknown_shares_since_reset;
	}
	public Integer getValid_shares_nmc() {
		return valid_shares_nmc == null ? 0 : valid_shares_nmc;
	}
	public void setValid_shares_nmc(Integer valid_shares_nmc) {
		this.valid_shares_nmc = valid_shares_nmc;
	}
	public Integer getStale_shares_nmc() {
		return stale_shares_nmc == null ? 0 : stale_shares_nmc;
	}
	public void setStale_shares_nmc(Integer stale_shares_nmc) {
		this.stale_shares_nmc = stale_shares_nmc;
	}
	public Integer getDupe_shares_nmc() {
		return dupe_shares_nmc == null ? 0 : dupe_shares_nmc;
	}
	public void setDupe_shares_nmc(Integer dupe_shares_nmc) {
		this.dupe_shares_nmc = dupe_shares_nmc;
	}
	public Integer getUnknown_shares_nmc() {
		return unknown_shares_nmc == null ? 0 : unknown_shares_nmc;
	}
	public void setUnknown_shares_nmc(Integer unknown_shares_nmc) {
		this.unknown_shares_nmc = unknown_shares_nmc;
	}
	public Integer getValid_shares_nmc_since_reset() {
		return valid_shares_nmc_since_reset == null ? 0 : valid_shares_nmc_since_reset;
	}
	public void setValid_shares_nmc_since_reset(Integer valid_shares_nmc_since_reset) {
		this.valid_shares_nmc_since_reset = valid_shares_nmc_since_reset;
	}
	public Integer getStale_shares_nmc_since_reset() {
		return stale_shares_nmc_since_reset == null ? 0 : stale_shares_nmc_since_reset;
	}
	public void setStale_shares_nmc_since_reset(Integer stale_shares_nmc_since_reset) {
		this.stale_shares_nmc_since_reset = stale_shares_nmc_since_reset;
	}
	public Integer getDupe_shares_nmc_since_reset() {
		return dupe_shares_nmc_since_reset == null ? 0 : dupe_shares_nmc_since_reset;
	}
	public void setDupe_shares_nmc_since_reset(Integer dupe_shares_nmc_since_reset) {
		this.dupe_shares_nmc_since_reset = dupe_shares_nmc_since_reset;
	}
	public Integer getUnknown_shares_nmc_since_reset() {
		return unknown_shares_nmc_since_reset == null ? 0 : unknown_shares_nmc_since_reset;
	}
	public void setUnknown_shares_nmc_since_reset(
			Integer unknown_shares_nmc_since_reset) {
		this.unknown_shares_nmc_since_reset = unknown_shares_nmc_since_reset;
	}
	public Integer getLast_share() {
		return last_share == null ? 0 : last_share;
	}
	public void setLast_share(Integer last_share) {
		this.last_share = last_share;
	}

}
