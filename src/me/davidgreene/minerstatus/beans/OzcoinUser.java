package me.davidgreene.minerstatus.beans;

import java.io.Serializable;
import java.math.BigDecimal;

public class OzcoinUser implements Serializable{

	/**
		  "user" : { "completed_payout" : "0.00000000",
		      "completed_payout_namecoin" : "0.00000000",
		      "estimated_payout" : "0.00000000",
		      "estimated_payout_namecoin" : "0.00000000",
		      "estimated_payout_pps" : "0.00000000",
		      "hashrate" : "0 MHash/s",
		      "hashrate_raw" : "0",
		      "pending_payout" : "0.00000000",
		      "pps_fee" : "0.05",
		      "pps_value" : "0.00001911"
		    }, 
	 */
	private static final long serialVersionUID = 726434297655136450L;

	private BigDecimal completed_payout;
	private BigDecimal completed_payout_namecoin;
	private BigDecimal estimated_payout;
	private BigDecimal estimated_payout_namecoin;
	private BigDecimal estimated_payout_pps;
    private BigDecimal hashrate_raw;
    private BigDecimal pending_payout;
    private BigDecimal pps_fee;
    private BigDecimal pps_value;
	public BigDecimal getCompleted_payout() {
		return completed_payout == null ? BigDecimal.ZERO : completed_payout.setScale(6, BigDecimal.ROUND_HALF_UP);
	}
	public void setCompleted_payout(BigDecimal completed_payout) {
		this.completed_payout = completed_payout;
	}
	public BigDecimal getCompleted_payout_namecoin() {
		return completed_payout_namecoin == null ? BigDecimal.ZERO : completed_payout_namecoin.setScale(6, BigDecimal.ROUND_HALF_UP);
	}
	public void setCompleted_payout_namecoin(BigDecimal completed_payout_namecoin) {
		this.completed_payout_namecoin = completed_payout_namecoin;
	}
	public BigDecimal getEstimated_payout() {
		return estimated_payout == null ? BigDecimal.ZERO : estimated_payout.setScale(6, BigDecimal.ROUND_HALF_UP);
	}
	public void setEstimated_payout(BigDecimal estimated_payout) {
		this.estimated_payout = estimated_payout;
	}
	public BigDecimal getEstimated_payout_namecoin() {
		return estimated_payout_namecoin == null ? BigDecimal.ZERO : estimated_payout_namecoin.setScale(6, BigDecimal.ROUND_HALF_UP);
	}
	public void setEstimated_payout_namecoin(BigDecimal estimated_payout_namecoin) {
		this.estimated_payout_namecoin = estimated_payout_namecoin;
	}
	public BigDecimal getEstimated_payout_pps() {
		return estimated_payout_pps == null ? BigDecimal.ZERO : estimated_payout_pps.setScale(6, BigDecimal.ROUND_HALF_UP);
	}
	public void setEstimated_payout_pps(BigDecimal estimated_payout_pps) {
		this.estimated_payout_pps = estimated_payout_pps;
	}
	public BigDecimal getHashrate_raw() {
		return hashrate_raw == null ? BigDecimal.ZERO : hashrate_raw;
	}
	public void setHashrate_raw(BigDecimal hashrate_raw) {
		this.hashrate_raw = hashrate_raw;
	}
	public BigDecimal getPending_payout() {
		return pending_payout == null ? BigDecimal.ZERO : pending_payout.setScale(6, BigDecimal.ROUND_HALF_UP);
	}
	public void setPending_payout(BigDecimal pending_payout) {
		this.pending_payout = pending_payout;
	}
	public BigDecimal getPps_fee() {
		return pps_fee == null ? BigDecimal.ZERO : pps_fee.setScale(3, BigDecimal.ROUND_HALF_UP);
	}
	public void setPps_fee(BigDecimal pps_fee) {
		this.pps_fee = pps_fee;
	}
	public BigDecimal getPps_value() {
		return pps_value == null ? BigDecimal.ZERO : pps_value.setScale(8, BigDecimal.ROUND_HALF_UP);
	}
	public void setPps_value(BigDecimal pps_value) {
		this.pps_value = pps_value;
	}
	
}
