package me.davidgreene.minerstatus.beans;

import java.io.Serializable;
import java.math.BigDecimal;

public class MtredWorker implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2957563025174721400L;
	
	private String rsolved;
	private BigDecimal mhash;
	public String getRsolved() {
		return rsolved;
	}
	public void setRsolved(String rsolved) {
		this.rsolved = rsolved;
	}
	public BigDecimal getMhash() {
		return mhash.setScale(2, BigDecimal.ROUND_HALF_UP);
	}
	public void setMhash(BigDecimal mhash) {
		this.mhash = mhash;
	}
	
}
