package me.davidgreene.minerstatus.beans;

import java.io.Serializable;
import java.math.BigDecimal;

import me.davidgreene.minerstatus.R;
import me.davidgreene.minerstatus.ViewMinerActivity;
import android.widget.TableLayout;

public class EligiusStatus implements Status, Serializable, Renderable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3762851881996168805L;

	private String apiKey;
	private Integer timedelta;
	private Integer shares;
	private BigDecimal hashrate;
	
	@Override
	public String getUsername() {
		return DEFAULT_USERNAME;
	}

	@Override
	public String getDisplayCol1() {
		return getShares().toString();
	}

	@Override
	public String getDisplayCol2() {
		return getTotalHashrate().toString();
	}

	@Override
	public String getUsernameLabel() {
		return "";
	}

	@Override
	public String getDisplayCol1Label() {
		return "Shares";
	}

	@Override
	public String getDisplayCol2Label() {
		return HASHRATE_DISPLAY_COL_2_LABEL;
	}

	@Override
	public BigDecimal getTotalHashrate() {
		return getHashrate().setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public Integer getTimedelta() {
		return timedelta == null ? 0 : timedelta;
	}

	public void setTimedelta(Integer timedelta) {
		this.timedelta = timedelta;
	}

	public Integer getShares() {
		return shares == null ? 0 : shares;
	}

	public void setShares(Integer shares) {
		this.shares = shares;
	}

	public BigDecimal getHashrate() {
		return hashrate == null ? BigDecimal.ZERO : hashrate;
	}

	public void setHashrate(BigDecimal hashrate) {
		this.hashrate = hashrate;
	}
	
	
	public void render(ViewMinerActivity activity) {
		TableLayout tl = (TableLayout) activity.findViewById(R.id.detailedView);
		tl.addView(activity.renderRow("Time Delta", getTimedelta().toString()));
		tl.addView(activity.renderRow("Shares", getShares().toString()));
		tl.addView(activity.renderRow(HASHRATE_DISPLAY_COL_2_LABEL, getHashrate().toString()));
		tl.addView(activity.renderRow("",""));
	}

}
