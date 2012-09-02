package me.davidgreene.minerstatus.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

public class OzcoinWorker implements Serializable {

	/**
     "8" : { "comment" : null,
          "current_speed" : "587.00",
          "efficiency" : "99.74",
          "idle_since" : null,
          "idle_since_unix" : null,
          "invalid_shares" : "2",
          "invalid_shares_lifetime" : "2860",
          "username" : "graet.g5",
          "valid_shares" : "779",
          "valid_shares_lifetime" : "544606"
        },
      "9" : { "comment" : null,
          "current_speed" : "0.00",
          "efficiency" : null,
          "idle_since" : "2012-01-26 10:31:02",
          "idle_since_unix" : "1327573862",
          "invalid_shares" : "0",
          "invalid_shares_lifetime" : "193",
          "username" : "graet.g11",
          "valid_shares" : "0",
          "valid_shares_lifetime" : "57465"
        }
	 */
	private static final long serialVersionUID = -708021592795206217L;

	private String username;
    private BigDecimal current_speed;
	private BigDecimal efficiency;
    private String idle_since;
    private BigInteger invalid_shares;
    private BigInteger invalid_shares_lifetime;
    private BigInteger valid_shares;
    private BigInteger valid_shares_lifetime;
	public String getUsername() {
		return username == null ? "" : username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public BigDecimal getCurrent_speed() {
		return current_speed == null ? BigDecimal.ZERO : current_speed.setScale(2, BigDecimal.ROUND_HALF_UP);
	}
	public void setCurrent_speed(BigDecimal current_speed) {
		this.current_speed = current_speed;
	}
	public BigDecimal getEfficiency() {
		return efficiency == null ? new BigDecimal(100) : efficiency.setScale(2, BigDecimal.ROUND_HALF_UP);
	}
	public void setEfficiency(BigDecimal efficiency) {
		this.efficiency = efficiency;
	}
	public String getIdle_since() {
		return idle_since == null ? "" : idle_since;
	}
	public void setIdle_since(String idle_since) {
		this.idle_since = idle_since;
	}
	public BigInteger getInvalid_shares() {
		return invalid_shares == null ? BigInteger.ZERO : invalid_shares;
	}
	public void setInvalid_shares(BigInteger invalid_shares) {
		this.invalid_shares = invalid_shares;
	}
	public BigInteger getInvalid_shares_lifetime() {
		return invalid_shares_lifetime == null ? BigInteger.ZERO : invalid_shares_lifetime;
	}
	public void setInvalid_shares_lifetime(BigInteger invalid_shares_lifetime) {
		this.invalid_shares_lifetime = invalid_shares_lifetime;
	}
	public BigInteger getValid_shares() {
		return valid_shares == null ? BigInteger.ZERO : valid_shares;
	}
	public void setValid_shares(BigInteger valid_shares) {
		this.valid_shares = valid_shares;
	}
	public BigInteger getValid_shares_lifetime() {
		return valid_shares_lifetime == null ? BigInteger.ZERO : valid_shares_lifetime;
	}
	public void setValid_shares_lifetime(BigInteger valid_shares_lifetime) {
		this.valid_shares_lifetime = valid_shares_lifetime;
	}
}
