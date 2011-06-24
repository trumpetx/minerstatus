package me.davidgreene.minerstatus.beans;

import java.io.Serializable;

public class ExchangeBitcoins implements Serializable, Exchange {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6550650898177013901L;
	private GenericTicker ticker;

	public GenericTicker getTicker() {
		return ticker;
	}

	public void setTicker(GenericTicker ticker) {
		this.ticker = ticker;
	}
}
