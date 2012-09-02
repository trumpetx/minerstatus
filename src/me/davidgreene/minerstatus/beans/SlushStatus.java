package me.davidgreene.minerstatus.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Map;

import me.davidgreene.minerstatus.R;
import me.davidgreene.minerstatus.ViewMinerActivity;
import android.widget.TableLayout;

public class SlushStatus implements Status, Serializable, Renderable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -4156611246722285882L;
	private String username;
	private String unconfirmed_reward;
	private String send_threshold;
	private String confirmed_reward;
	private String wallet;
	private String estimated_reward;
	private Map<String, SlushWorker> workers;
	private String apiKey;
	
	@Override
	public BigDecimal getTotalHashrate(){
		BigDecimal hashRate = BigDecimal.ZERO;
		if (workers != null){
			Iterator it = workers.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry pairs = (Map.Entry)it.next();
				SlushWorker worker = (SlushWorker) pairs.getValue();
				hashRate = hashRate.add(worker.getHashrate());
			}
		}
		return hashRate.setScale(2, BigDecimal.ROUND_HALF_UP);
	}
	
	@Override
	public String getDisplayCol1() {
		return (confirmed_reward == null) ? "" : confirmed_reward;
	}
	@Override
	public String getDisplayCol2() {
		if (workers == null){
			return "No Workers";
		} else {
			return getTotalHashrate().toString();
		}
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUnconfirmed_reward() {
		return unconfirmed_reward;
	}
	public void setUnconfirmed_reward(String unconfirmed_reward) {
		this.unconfirmed_reward = unconfirmed_reward;
	}
	public String getSend_threshold() {
		return send_threshold;
	}
	public void setSend_threshold(String send_threshold) {
		this.send_threshold = send_threshold;
	}
	public String getConfirmed_reward() {
		return confirmed_reward;
	}
	public void setConfirmed_reward(String confirmed_reward) {
		this.confirmed_reward = confirmed_reward;
	}
	public String getWallet() {
		return wallet;
	}
	public void setWallet(String wallet) {
		this.wallet = wallet;
	}
	public String getEstimated_reward() {
		return estimated_reward;
	}
	public void setEstimated_reward(String estimated_reward) {
		this.estimated_reward = estimated_reward;
	}
	public String getApiKey() {
		return apiKey;
	}
	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
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
	public Map<String, SlushWorker> getWorkers() {
		return workers;
	}
	public void setWorkers(Map<String, SlushWorker> workers) {
		this.workers = workers;
	}

	public void render(ViewMinerActivity activity) {
		TableLayout tl = (TableLayout) activity.findViewById(R.id.detailedView);
		tl.addView(activity.renderRow("Username", getUsername()));
		tl.addView(activity.renderRow("Send Threshold", getSend_threshold()));
		tl.addView(activity.renderRow("Estimated", getEstimated_reward()));
		tl.addView(activity.renderRow("Unconfirmed", getUnconfirmed_reward()));
		tl.addView(activity.renderRow("Confirmed", getConfirmed_reward()));
		tl.addView(activity.renderRow("Wallet", getWallet()));
		if (getWorkers() != null){
		    for( String key : getWorkers().keySet() ){
		    	SlushWorker worker = getWorkers().get(key);
		    	tl.addView(activity.renderRow("",key));
		    	tl.addView(activity.renderRow(HASHRATE_DISPLAY_COL_2_LABEL,worker.getHashrate().toString()));
		    	tl.addView(activity.renderRow("Last Share",worker.getLast_share().toString()));
		    	tl.addView(activity.renderRow("Shares",worker.getShares().toString()));
		    	tl.addView(activity.renderRow("Score",worker.getScore()));
		    	tl.addView(activity.renderRow("Alive",worker.getAlive().toString()));
		    	tl.addView(activity.renderRow("",""));
		    }		
		}		
		tl.addView(activity.renderRow("",""));
	}
	
}
