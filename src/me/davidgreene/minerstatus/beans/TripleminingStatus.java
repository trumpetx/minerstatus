package me.davidgreene.minerstatus.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import me.davidgreene.minerstatus.R;
import me.davidgreene.minerstatus.ViewMinerActivity;
import android.widget.TableLayout;

public class TripleminingStatus implements Status, Serializable, Renderable  {

	/**
	 * {
	 * "confirmed_reward":"0.00000000",HASHRATE_DISPLAY_COL_2_LABEL:"0",
	 * "workers":{"trumpetx_nm":{"shares":"0","alive":"false","stales":"0"}},
	 * "total_shares":"335059","estimated_payout":"0.00000000"
	 * }
	 */
	private static final long serialVersionUID = 9105053716084690211L;

	private BigDecimal confirmed_reward;
	private BigDecimal hashrate;
	private Integer total_shares;
	private BigDecimal estimated_payout;
	private String apiKey;
	private Map<String, TripleMiningWorker> workers = new HashMap<String, TripleMiningWorker>();
	
	@Override
	public String getUsername() {
		return DEFAULT_USERNAME;
	}

	@Override
	public String getDisplayCol1() {
		return confirmed_reward.toString();
	}

	@Override
	public String getDisplayCol2() {
		return hashrate.toString();
	}

	@Override
	public String getUsernameLabel() {
		return "";
	}

	@Override
	public String getDisplayCol1Label() {
		return CONFIRMED_REWARD_COL_1_LABEL;
	}

	@Override
	public String getDisplayCol2Label() {
		return HASHRATE_DISPLAY_COL_2_LABEL;
	}

	public BigDecimal getConfirmed_reward() {
		return confirmed_reward;
	}

	public void setConfirmed_reward(BigDecimal confirmed_reward) {
		this.confirmed_reward = confirmed_reward;
	}

	public BigDecimal getHashrate() {
		return hashrate == null ? BigDecimal.ZERO : hashrate;
	}

	public void setHashrate(BigDecimal hashrate) {
		this.hashrate = hashrate;
	}

	public Integer getTotal_shares() {
		return total_shares == null ? 0 : total_shares;
	}

	public void setTotal_shares(Integer total_shares) {
		this.total_shares = total_shares;
	}

	public BigDecimal getEstimated_payout() {
		return estimated_payout == null ? BigDecimal.ZERO : estimated_payout;
	}

	public void setEstimated_payout(BigDecimal estimated_payout) {
		this.estimated_payout = estimated_payout;
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	@Override
	public BigDecimal getTotalHashrate() {
		return getHashrate();
	}

	public Map<String, TripleMiningWorker> getWorkers() {
		return workers;
	}

	public void setWorkers(Map<String, TripleMiningWorker> workers) {
		this.workers = workers;
	}

	public void render(ViewMinerActivity activity) {
		TableLayout tl = (TableLayout) activity.findViewById(R.id.detailedView);
		tl.addView(activity.renderRow(HASHRATE_DISPLAY_COL_2_LABEL, getHashrate().toString()));
		tl.addView(activity.renderRow(CONFIRMED_REWARD_COL_1_LABEL, getConfirmed_reward().toString()));
		tl.addView(activity.renderRow("Estimated Payout", getEstimated_payout().toString()));
		tl.addView(activity.renderRow("Total Shares", getTotal_shares().toString()));
		tl.addView(activity.renderRow(DEFAULT_USERNAME + ":",""));
		if(getWorkers() != null){
		    for( String key : getWorkers().keySet() ){
		    	TripleMiningWorker workerStatus = getWorkers().get(key);
		    	tl.addView(activity.renderRow("",key));
		    	tl.addView(activity.renderRow("Alive",workerStatus.getAlive().toString()));
		    	tl.addView(activity.renderRow("Shares",workerStatus.getShares().toString()));
		    	tl.addView(activity.renderRow("Stales",workerStatus.getStales().toString()));
		    	tl.addView(activity.renderRow("",""));
		    }
		}
		tl.addView(activity.renderRow("",""));
	}
}
