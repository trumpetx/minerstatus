package me.davidgreene.minerstatus.beans;

import java.io.Serializable;
import java.math.BigDecimal;

public class BtcMineStatus implements Serializable, Status, Mergable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4929377426083392967L;
	private String total_bounty;
	private String confirmed_bounty;
	private Integer solved_blocks;
	private Integer round_shares;
	private String estimated_bounty;
	private Integer solved_shares;
	private String unconfirmed_bounty;
	private String hashrate;
	private String total_payout;
	private BtcMineWorker[] miners;
	private String apiKey;
	private Boolean online_status;
	private String total_24h;
	
	@Override
	public BigDecimal getTotalHashrate(){
		try{
			return new BigDecimal((hashrate == null) ? "0" : hashrate).setScale(2, BigDecimal.ROUND_HALF_UP);
		} catch (Exception e){
			return BigDecimal.ZERO;
		}
	}
	
	@Override
	public String getUsername() {
		return "Worker(s)";
	}

	@Override
	public String getDisplayCol2() {
		return getTotalHashrate().toString();
	}

	@Override
	public String getDisplayCol1() {
		return (total_payout == null) ? "" : total_payout;
	}

	@Override
	public void setApiKey(String apiKey) {
		this.apiKey=apiKey;
	}

	@Override
	public String getApiKey() {
		return apiKey;
	}

	public String getTotal_bounty() {
		return (total_bounty == null) ? "" : total_bounty;
	}

	public void setTotal_bounty(String total_bounty) {
		this.total_bounty = total_bounty;
	}

	public String getConfirmed_bounty() {
		return (confirmed_bounty == null) ? "" : confirmed_bounty;
	}

	public void setConfirmed_bounty(String confirmed_bounty) {
		this.confirmed_bounty = confirmed_bounty;
	}

	public Integer getSolved_blocks() {
		return (solved_blocks == null) ? 0 : solved_blocks;
	}

	public void setSolved_blocks(Integer solved_blocks) {
		this.solved_blocks = solved_blocks;
	}

	public Integer getRound_shares() {
		return (round_shares == null) ? 0 : round_shares;
	}

	public void setRound_shares(Integer round_shares) {
		this.round_shares = round_shares;
	}

	public String getEstimated_bounty() {
		return (estimated_bounty == null) ? "" : estimated_bounty;
	}

	public void setEstimated_bounty(String estimated_bounty) {
		this.estimated_bounty = estimated_bounty;
	}

	public Integer getSolved_shares() {
		return (solved_shares == null) ? 0 : solved_shares;
	}

	public void setSolved_shares(Integer solved_shares) {
		this.solved_shares = solved_shares;
	}

	public String getUnconfirmed_bounty() {
		return (unconfirmed_bounty == null) ? "" : unconfirmed_bounty;
	}

	public void setUnconfirmed_bounty(String unconfirmed_bounty) {
		this.unconfirmed_bounty = unconfirmed_bounty;
	}

	public String getHashrate() {
		return (hashrate == null) ? "" : hashrate;
	}

	public void setHashrate(String hashrate) {
		this.hashrate = hashrate;
	}

	public String getTotal_payout() {
		return (total_payout == null) ? "" : total_payout;
	}

	public void setTotal_payout(String total_payout) {
		this.total_payout = total_payout;
	}

	@Override
	public String getUsernameLabel() {
		return "";
	}

	@Override
	public String getDisplayCol2Label() {
		return "Hashrate";
	}

	@Override
	public String getDisplayCol1Label() {
		return "Total Payout";
	}

	public BtcMineWorker[] getMiners() {
		return miners;
	}

	public void setMiners(BtcMineWorker[] miners) {
		this.miners = miners;
	}

	@Override
	public BtcMineStatus mergeWith(Mergable object) {
		if (object instanceof BtcMineStatus){
			BtcMineStatus btcMine = (BtcMineStatus) object;
			if (btcMine.total_bounty != null)
				this.total_bounty =  btcMine.total_bounty;
			if (btcMine.confirmed_bounty != null)
				this.confirmed_bounty =  btcMine.confirmed_bounty;
			if (btcMine.solved_blocks != null)
				this.solved_blocks =  btcMine.solved_blocks;
			if (btcMine.round_shares != null)
				this.round_shares =  btcMine.round_shares;
			if (btcMine.estimated_bounty != null)
				this.estimated_bounty =  btcMine.estimated_bounty;
			if (btcMine.solved_shares != null)
				this.solved_shares =  btcMine.solved_shares;
			if (btcMine.unconfirmed_bounty != null)
				this.unconfirmed_bounty =  btcMine.unconfirmed_bounty;
			if (btcMine.hashrate != null)
				this.hashrate =  btcMine.hashrate;
			if (btcMine.total_payout != null)
				this.total_payout =  btcMine.total_payout;
			if (btcMine.miners != null)
				this.miners =  btcMine.miners;
			if (btcMine.online_status != null)
				this.online_status =  btcMine.online_status;
			if (btcMine.total_24h != null)
				this.total_24h =  btcMine.total_24h;			
			return this;
		} else {
			throw new RuntimeException("Cannot merge these two objects.");
		}
	}

	public Boolean getOnline_status() {
		return (online_status == null) ? Boolean.FALSE : online_status;
	}

	public void setOnline_status(Boolean online_status) {
		this.online_status = online_status;
	}

	public String getTotal_24h() {
		return (total_24h == null) ? "" : total_24h;
	}

	public void setTotal_24h(String total_24h) {
		this.total_24h = total_24h;
	}

}
