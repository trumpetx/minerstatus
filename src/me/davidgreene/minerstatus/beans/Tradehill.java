package me.davidgreene.minerstatus.beans;

import java.io.Serializable;

public class Tradehill implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7846787595803199600L;
	private TradehillTicker ticker;

	public TradehillTicker getTicker() {
		return ticker;
	}

	public void setTicker(TradehillTicker ticker) {
		this.ticker = ticker;
	}
	
}
