package me.davidgreene.minerstatus.beans;

import java.io.Serializable;
import java.math.BigDecimal;

public class MtredServer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7540124975445016643L;

	private BigDecimal hashrate;
	private Integer workers;
	private Integer roundshares;
	private Integer foundblock;
	public BigDecimal getHashrate() {
		return hashrate;
	}
	public void setHashrate(BigDecimal hashrate) {
		this.hashrate = hashrate;
	}
	public Integer getWorkers() {
		return workers;
	}
	public void setWorkers(Integer workers) {
		this.workers = workers;
	}
	public Integer getRoundshares() {
		return roundshares;
	}
	public void setRoundshares(Integer roundshares) {
		this.roundshares = roundshares;
	}
	public Integer getFoundblock() {
		return foundblock;
	}
	public void setFoundblock(Integer foundblock) {
		this.foundblock = foundblock;
	}

}
