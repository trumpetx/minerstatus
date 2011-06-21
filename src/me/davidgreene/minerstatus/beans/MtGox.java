package me.davidgreene.minerstatus.beans;

import java.io.Serializable;

public class MtGox implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3039167272598591252L;
	private MtGoxTicker ticker;

	public MtGoxTicker getTicker() {
		return ticker;
	}

	public void setTicker(MtGoxTicker ticker) {
		this.ticker = ticker;
	}
	
}
