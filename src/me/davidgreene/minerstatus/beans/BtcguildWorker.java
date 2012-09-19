package me.davidgreene.minerstatus.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

public class BtcguildWorker implements Serializable {

	/**
	 * "5" : { "dupe_shares" : 0,
          "dupe_shares_nmc" : 0,
          "dupe_shares_nmc_since_reset" : 0,
          "dupe_shares_since_reset" : 0,
          "hash_rate" : 0.0,
          "last_share" : 0,
          "stale_shares" : 0,
          "stale_shares_nmc" : 0,
          "stale_shares_nmc_since_reset" : 0,
          "stale_shares_since_reset" : 0,
          "unknown_shares" : 0,
          "unknown_shares_nmc" : 0,
          "unknown_shares_nmc_since_reset" : 0,
          "unknown_shares_since_reset" : 0,
          "valid_shares" : 0,
          "valid_shares_nmc" : 0,
          "valid_shares_nmc_since_reset" : 0,
          "valid_shares_since_reset" : 0,
          "worker_name" : "trumpetx_other"
        }
	 */
	private static final long serialVersionUID = -6917436321008955505L;
	
	private String worker_name;
	private BigDecimal hash_rate;
	private BigInteger stale_shares;
	private BigInteger valid_shares;
	private BigInteger dupe_shares;
	private BigInteger unknown_shares;
	private BigInteger valid_shares_since_reset;
	private BigInteger stale_shares_since_reset;
	private BigInteger dupe_shares_since_reset;
	private BigInteger unknown_shares_since_reset;
	private BigInteger valid_shares_nmc;
	private BigInteger stale_shares_nmc;
	private BigInteger dupe_shares_nmc;
	private BigInteger unknown_shares_nmc;
	private BigInteger valid_shares_nmc_since_reset;
	private BigInteger stale_shares_nmc_since_reset;
	private BigInteger dupe_shares_nmc_since_reset;
	private BigInteger unknown_shares_nmc_since_reset;
	private BigInteger last_share;
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
	public BigInteger getStale_shares() {
		return stale_shares == null ? BigInteger.ZERO : stale_shares;
	}
	public void setStale_shares(BigInteger stale_shares) {
		this.stale_shares = stale_shares;
	}
	public BigInteger getValid_shares() {
		return valid_shares == null ? BigInteger.ZERO : valid_shares;
	}
	public void setValid_shares(BigInteger valid_shares) {
		this.valid_shares = valid_shares;
	}
	public BigInteger getDupe_shares() {
		return dupe_shares == null ? BigInteger.ZERO : dupe_shares;
	}
	public void setDupe_shares(BigInteger dupe_shares) {
		this.dupe_shares = dupe_shares;
	}
	public BigInteger getUnknown_shares() {
		return unknown_shares == null ? BigInteger.ZERO : unknown_shares;
	}
	public void setUnknown_shares(BigInteger unknown_shares) {
		this.unknown_shares = unknown_shares;
	}
	public BigInteger getValid_shares_since_reset() {
		return valid_shares_since_reset == null ? BigInteger.ZERO : valid_shares_since_reset;
	}
	public void setValid_shares_since_reset(BigInteger valid_shares_since_reset) {
		this.valid_shares_since_reset = valid_shares_since_reset;
	}
	public BigInteger getStale_shares_since_reset() {
		return stale_shares_since_reset == null ? BigInteger.ZERO : stale_shares_since_reset;
	}
	public void setStale_shares_since_reset(BigInteger stale_shares_since_reset) {
		this.stale_shares_since_reset = stale_shares_since_reset;
	}
	public BigInteger getDupe_shares_since_reset() {
		return dupe_shares_since_reset == null ? BigInteger.ZERO : dupe_shares_since_reset;
	}
	public void setDupe_shares_since_reset(BigInteger dupe_shares_since_reset) {
		this.dupe_shares_since_reset = dupe_shares_since_reset;
	}
	public BigInteger getUnknown_shares_since_reset() {
		return unknown_shares_since_reset == null ? BigInteger.ZERO : unknown_shares_since_reset;
	}
	public void setUnknown_shares_since_reset(BigInteger unknown_shares_since_reset) {
		this.unknown_shares_since_reset = unknown_shares_since_reset;
	}
	public BigInteger getValid_shares_nmc() {
		return valid_shares_nmc == null ? BigInteger.ZERO : valid_shares_nmc;
	}
	public void setValid_shares_nmc(BigInteger valid_shares_nmc) {
		this.valid_shares_nmc = valid_shares_nmc;
	}
	public BigInteger getStale_shares_nmc() {
		return stale_shares_nmc == null ? BigInteger.ZERO : stale_shares_nmc;
	}
	public void setStale_shares_nmc(BigInteger stale_shares_nmc) {
		this.stale_shares_nmc = stale_shares_nmc;
	}
	public BigInteger getDupe_shares_nmc() {
		return dupe_shares_nmc == null ? BigInteger.ZERO : dupe_shares_nmc;
	}
	public void setDupe_shares_nmc(BigInteger dupe_shares_nmc) {
		this.dupe_shares_nmc = dupe_shares_nmc;
	}
	public BigInteger getUnknown_shares_nmc() {
		return unknown_shares_nmc == null ? BigInteger.ZERO : unknown_shares_nmc;
	}
	public void setUnknown_shares_nmc(BigInteger unknown_shares_nmc) {
		this.unknown_shares_nmc = unknown_shares_nmc;
	}
	public BigInteger getValid_shares_nmc_since_reset() {
		return valid_shares_nmc_since_reset == null ? BigInteger.ZERO : valid_shares_nmc_since_reset;
	}
	public void setValid_shares_nmc_since_reset(BigInteger valid_shares_nmc_since_reset) {
		this.valid_shares_nmc_since_reset = valid_shares_nmc_since_reset;
	}
	public BigInteger getStale_shares_nmc_since_reset() {
		return stale_shares_nmc_since_reset == null ? BigInteger.ZERO : stale_shares_nmc_since_reset;
	}
	public void setStale_shares_nmc_since_reset(BigInteger stale_shares_nmc_since_reset) {
		this.stale_shares_nmc_since_reset = stale_shares_nmc_since_reset;
	}
	public BigInteger getDupe_shares_nmc_since_reset() {
		return dupe_shares_nmc_since_reset == null ? BigInteger.ZERO : dupe_shares_nmc_since_reset;
	}
	public void setDupe_shares_nmc_since_reset(BigInteger dupe_shares_nmc_since_reset) {
		this.dupe_shares_nmc_since_reset = dupe_shares_nmc_since_reset;
	}
	public BigInteger getUnknown_shares_nmc_since_reset() {
		return unknown_shares_nmc_since_reset == null ? BigInteger.ZERO : unknown_shares_nmc_since_reset;
	}
	public void setUnknown_shares_nmc_since_reset(
			BigInteger unknown_shares_nmc_since_reset) {
		this.unknown_shares_nmc_since_reset = unknown_shares_nmc_since_reset;
	}
	public BigInteger getLast_share() {
		return last_share == null ? BigInteger.ZERO : last_share;
	}
	public void setLast_share(BigInteger last_share) {
		this.last_share = last_share;
	}

}
