package me.davidgreene.minerstatus.beans;

import java.io.Serializable;
import java.math.BigDecimal;

import me.davidgreene.minerstatus.R;
import me.davidgreene.minerstatus.ViewMinerActivity;
import android.widget.TableLayout;

public class BitpoolStatus implements Status, Serializable, Renderable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4757193380729099732L;
	private BitpoolUser User;
	private BitpoolPool Pool;
	private String apiKey;
	
	@Override
	public BigDecimal getTotalHashrate(){
		//"currSpeed":"0 MH/s"
		try{
			StringBuffer sb = new StringBuffer();
			for (Character c : getUser().getCurrSpeed().toCharArray()){
				if (Character.isDigit(c)){
					sb.append(c);
				}
			}
			return new BigDecimal(sb.toString()).setScale(2, BigDecimal.ROUND_HALF_UP);
		} catch (Exception e){
			return BigDecimal.ZERO;
		}
	}
	
	@Override
	public String getUsername() {
		return getUser().getUsername();
	}
	@Override
	public String getDisplayCol2() {
		return getUser().getCurrSpeed();
	}
	@Override
	public String getDisplayCol1() {
		return getUser().getHistorical();
	}
	
	public BitpoolUser getUser() {
		return User;
	}
	public void setUser(BitpoolUser user) {
		this.User = user;
	}
	public BitpoolPool getPool() {
		return Pool;
	}
	public void setPool(BitpoolPool pool) {
		this.Pool = pool;
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
	public String getDisplayCol2Label() {
		return "Current Speed";
	}
	@Override
	public String getDisplayCol1Label() {
		return "Historical Payout";
	}

	public void render(ViewMinerActivity activity) {
		TableLayout tl = (TableLayout) activity.findViewById(R.id.detailedView);
		tl.addView(activity.renderRow("Username", getUsername()));
		tl.addView(activity.renderRow("Status", getUser().getStatus()));
		tl.addView(activity.renderRow("Current Speed", getUser().getCurrSpeed()));
		tl.addView(activity.renderRow("Curr. Pool Speed", getPool().getCurrentSpeed()));
		tl.addView(activity.renderRow("Currrent Round", getPool().getCurrentRound()));
		tl.addView(activity.renderRow("Join Date", getUser().getJoinDt()));
		tl.addView(activity.renderRow("Last Seen", getUser().getLastSeen()));
		tl.addView(activity.renderRow("Active", getUser().getActive()));
		tl.addView(activity.renderRow("Estimated Earnings", getUser().getEstimated()));
		tl.addView(activity.renderRow("Unconfirmed", getUser().getUnconfirmed()));
		tl.addView(activity.renderRow("Historical", getUser().getHistorical()));
		tl.addView(activity.renderRow("Unpaid", getUser().getUnpaid()));
		StringBuffer sb = new StringBuffer();
		if (getUser().getSolvedBlocks() != null){
			for(int i=0;i<getUser().getSolvedBlocks().length; i++){
				sb.append(getUser().getSolvedBlocks()[i]);
				if(i < getUser().getSolvedBlocks().length-1){
					sb.append(',');
				}
			}
		}
		tl.addView(activity.renderRow("Solved Blocks", sb.toString()));
		tl.addView(activity.renderRow("Requested", getUser().getRequested().toString()));
		tl.addView(activity.renderRow("Submitted", getUser().getSubmitted().toString()));
		tl.addView(activity.renderRow("Efficiency", getUser().getEfficiency()));
		tl.addView(activity.renderRow("",""));
	}
}
