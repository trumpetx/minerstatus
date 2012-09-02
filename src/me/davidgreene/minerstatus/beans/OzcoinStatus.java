package me.davidgreene.minerstatus.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import me.davidgreene.minerstatus.R;
import me.davidgreene.minerstatus.ViewMinerActivity;
import android.widget.TableLayout;

public class OzcoinStatus implements Serializable, Status, Renderable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6915687432225003232L;


	private OzcoinUser user;
	private Map<String, OzcoinWorker> worker = new HashMap<String, OzcoinWorker>();
	private String apiKey;
	
	public void render(ViewMinerActivity activity) {
		TableLayout tl = (TableLayout) activity.findViewById(R.id.detailedView);
		tl.addView(activity.renderRow("GHashrate:", getTotalHashrate()));
		tl.addView(activity.renderRow("Completed Payout (BTC):", getUser().getCompleted_payout()));
		tl.addView(activity.renderRow("Estimated Payout (BTC):", getUser().getEstimated_payout()));
		tl.addView(activity.renderRow("Completed Payout (NMC):", getUser().getCompleted_payout_namecoin()));
		tl.addView(activity.renderRow("Estimated Payout (NMC):", getUser().getEstimated_payout_namecoin()));
		tl.addView(activity.renderRow("PPS Fee:", getUser().getPps_fee()));
		tl.addView(activity.renderRow("PPS Value:", getUser().getPps_value()));
		tl.addView(activity.renderRow(DEFAULT_USERNAME + ":",""));
		if(getWorker() != null){
		    for( Map.Entry<String, OzcoinWorker> entry : getWorker().entrySet() ){
		    	OzcoinWorker workerStatus = entry.getValue();
		    	tl.addView(activity.renderRow("",workerStatus.getUsername()));
		    	if(workerStatus.getIdle_since() != ""){
		    		tl.addView(activity.renderRow("Idle Since:",workerStatus.getIdle_since()));	
		    	}
		    	tl.addView(activity.renderRow(HASHRATE_DISPLAY_COL_2_LABEL + ":",workerStatus.getCurrent_speed()));
		    	if(!workerStatus.getValid_shares().equals(BigDecimal.ZERO) || !workerStatus.getInvalid_shares().equals(BigInteger.ZERO)){
		    		tl.addView(activity.renderRow("Efficiency: ",workerStatus.getEfficiency() + " %"));
		    	}
		    	tl.addView(activity.renderRow("Valid Shares:",workerStatus.getValid_shares()));
		    	tl.addView(activity.renderRow("Invalid Shares:",workerStatus.getInvalid_shares()));
		    	tl.addView(activity.renderRow("Total Valid Shares:",workerStatus.getValid_shares_lifetime()));
		    	tl.addView(activity.renderRow("Total Invalid Shares",workerStatus.getInvalid_shares_lifetime()));
		    	tl.addView(activity.renderRow("",""));	
		    }
		}
		tl.addView(activity.renderRow("",""));
	}

	public OzcoinUser getUser() {
		return user;
	}

	public void setUser(OzcoinUser user) {
		this.user = user;
	}

	public Map<String, OzcoinWorker> getWorker() {
		return worker;
	}

	public void setWorker(Map<String, OzcoinWorker> worker) {
		this.worker = worker;
	}

	@Override
	public String getUsername() {
		return DEFAULT_USERNAME;
	}

	@Override
	public String getDisplayCol1() {
		return getUser() == null ? "" : getUser().getCompleted_payout().setScale(2, BigDecimal.ROUND_HALF_UP).toString();
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
		return "Est. Payout";
	}

	@Override
	public String getDisplayCol2Label() {
		return "G/Hash";
	}

	private static final BigDecimal ONE_THOUSAND = new BigDecimal("1000");
	
	@Override
	public BigDecimal getTotalHashrate() {
		return getUser() == null ? BigDecimal.ZERO : getUser().getHashrate_raw().divide(ONE_THOUSAND).setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	@Override
	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public String getApiKey() {
		return apiKey;
	}
	
}
