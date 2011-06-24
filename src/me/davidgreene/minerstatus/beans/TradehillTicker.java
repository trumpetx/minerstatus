package me.davidgreene.minerstatus.beans;

import java.io.Serializable;
import java.math.BigDecimal;

public class TradehillTicker implements Serializable, Ticker {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1427979082480126977L;
	private String high;
	private String low;
	private String vol;
	private String buy;
	private String sell;
	private String last;
	public String getHigh() {
		return (high == null) ? "" : roundTwoDigits(high);
	}
	public void setHigh(String high) {
		this.high = high;
	}
	public String getLow() {
		return (low == null) ? "" : roundTwoDigits(low);
	}
	public void setLow(String low) {
		this.low = low;
	}
	public String getVol() {
		return (vol == null) ? "" : roundTwoDigits(vol);
	}
	public void setVol(String vol) {
		this.vol = vol;
	}
	public String getBuy() {
		return (buy == null) ? "" : roundTwoDigits(buy);
	}
	public void setBuy(String buy) {
		this.buy = buy;
	}
	public String getSell() {
		return (sell == null) ? "" : roundTwoDigits(sell);
	}
	public void setSell(String sell) {
		this.sell = sell;
	}
	public String getLast() {
		return (last == null) ? "" : roundTwoDigits(last);
	}
	public void setLast(String last) {
		this.last = last;
	}
	
	private String roundTwoDigits(String str){
		try{
			BigDecimal bigD = new BigDecimal(str);
			return bigD.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
		} catch (NumberFormatException e){
			return "";
		}
	}
	@Override
	public String getLastString() {
		return getLast();
	}
	@Override
	public String getHighString() {
		return getHigh();
	}
	@Override
	public String getLowString() {
		return getLow();
	}
	@Override
	public String getBuyString() {
		return getBuy();
	}
	@Override
	public String getSellString() {
		return getSell();
	}
	@Override
	public String getVolString() {
		return getVol();
	}
}
