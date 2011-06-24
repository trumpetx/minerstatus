package me.davidgreene.minerstatus.beans;

import java.io.Serializable;
import java.math.BigDecimal;

public class GenericTicker implements Serializable, Ticker {

	/**
	 * 
	 */
	private static final long serialVersionUID = -289439365080216663L;
	private BigDecimal high;
	private BigDecimal low;
	private Integer vol;
	private BigDecimal buy;
	private BigDecimal sell;
	private BigDecimal last;
	public BigDecimal getHigh() {
		return high == null ? BigDecimal.ZERO : high;
	}
	public void setHigh(BigDecimal high) {
		this.high = high;
	}
	public BigDecimal getLow() {
		return low == null ? BigDecimal.ZERO : low;
	}
	public void setLow(BigDecimal low) {
		this.low = low;
	}
	public Integer getVol() {
		return vol == null ? 0 : vol;
	}
	public void setVol(Integer vol) {
		this.vol = vol;
	}
	public BigDecimal getBuy() {
		return buy == null ? BigDecimal.ZERO : buy;
	}
	public void setBuy(BigDecimal buy) {
		this.buy = buy;
	}
	public BigDecimal getSell() {
		return sell == null ? BigDecimal.ZERO : sell;
	}
	public void setSell(BigDecimal sell) {
		this.sell = sell;
	}
	public BigDecimal getLast() {
		return last == null ? BigDecimal.ZERO : last;
	}
	public void setLast(BigDecimal last) {
		this.last = last;
	}
	@Override
	public String getLastString() {
		return getLast().setScale(2, BigDecimal.ROUND_HALF_UP).toString();
	}
	@Override
	public String getHighString() {
		return getHigh().setScale(2, BigDecimal.ROUND_HALF_UP).toString();
	}
	@Override
	public String getLowString() {
		return getLow().setScale(2, BigDecimal.ROUND_HALF_UP).toString();
	}
	@Override
	public String getBuyString() {
		return getBuy().setScale(2, BigDecimal.ROUND_HALF_UP).toString();
	}
	@Override
	public String getSellString() {
		return getSell().setScale(2, BigDecimal.ROUND_HALF_UP).toString();
	}
	@Override
	public String getVolString() {
		return getVol().toString();
	}
}
