package me.davidgreene.minerstatus.beans;

import java.io.Serializable;

public class MtGox implements Serializable, Exchange {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3039167272598591252L;
	private GenericTicker ticker;

	public GenericTicker getTicker() {
		return ticker;
	}

	public void setTicker(GenericTicker ticker) {
		this.ticker = ticker;
	}
	
}
