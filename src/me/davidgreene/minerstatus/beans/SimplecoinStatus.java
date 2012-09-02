package me.davidgreene.minerstatus.beans;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import me.davidgreene.minerstatus.R;
import me.davidgreene.minerstatus.ViewMinerActivity;
import android.widget.TableLayout;

public class SimplecoinStatus implements Status, Renderable {

	/**
		{ "currencies" : { "BTC" : { "confirmed_rewards" : "0",
		          "estimated_rewards" : 0,
		          "payout_history" : "0"
		        },
		      "GG" : { "confirmed_rewards" : "0",
		          "estimated_rewards" : 0,
		          "payout_history" : "0"
		        },
		      "LTC" : { "confirmed_rewards" : "0",
		          "estimated_rewards" : 0,
		          "payout_history" : "0"
		        },
		      "NMC" : { "confirmed_rewards" : "0",
		          "estimated_rewards" : 0,
		          "payout_history" : "0"
		        },
		      "TBX" : { "confirmed_rewards" : "0",
		          "estimated_rewards" : 0,
		          "payout_history" : "0"
		        }
		    },
		  HASHRATE_DISPLAY_COL_2_LABEL : 0,
		  "workers" : { "trumpetx.1" : { "alive" : false,
		          HASHRATE_DISPLAY_COL_2_LABEL : 0,
		          "pool" : "BTC/NMC PPLNS"
		        } }
		}
	 */
	private static final long serialVersionUID = -6814310599079503273L;
	
	private Map<String, SimplecoinCurrency> currencies = new HashMap<String, SimplecoinCurrency>();
	private BigDecimal hashrate;
	private Map<String, SimplecoinWorker> workers = new HashMap<String, SimplecoinWorker>();
	private String apiKey;
	
	@Override
	public String getUsername() {
		return DEFAULT_USERNAME;
	}

	@Override
	public String getDisplayCol1() {
		SimplecoinCurrency currency = currencies.get("BTC");
		return currency == null ? "0" : currency.getConfirmed_rewards().setScale(2, BigDecimal.ROUND_HALF_UP).toString();
	}

	@Override
	public String getDisplayCol2() {
		return hashrate.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
	}

	@Override
	public String getUsernameLabel() {
		return "";
	}

	@Override
	public String getDisplayCol1Label() {
		return "BTC Confirmed Rewards";
	}

	@Override
	public String getDisplayCol2Label() {
		return HASHRATE_DISPLAY_COL_2_LABEL;
	}

	@Override
	public BigDecimal getTotalHashrate() {
		return getHashrate().setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	@Override
	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	@Override
	public String getApiKey() {
		return apiKey;
	}

	public Map<String, SimplecoinCurrency> getCurrencies() {
		return currencies;
	}

	public void setCurrencies(Map<String, SimplecoinCurrency> currencies) {
		this.currencies = currencies;
	}

	public BigDecimal getHashrate() {
		return hashrate == null ? BigDecimal.ZERO : hashrate;
	}

	public void setHashrate(BigDecimal hashrate) {
		this.hashrate = hashrate;
	}

	public Map<String, SimplecoinWorker> getWorkers() {
		return workers;
	}

	public void setWorkers(Map<String, SimplecoinWorker> workers) {
		this.workers = workers;
	}
	
	public void render(ViewMinerActivity activity) {
		TableLayout tl = (TableLayout) activity.findViewById(R.id.detailedView);
		tl.addView(activity.renderRow(HASHRATE_DISPLAY_COL_2_LABEL, getTotalHashrate().toString()));
		tl.addView(activity.renderRow("Currencies", ""));
		
		for(String key : getCurrencies().keySet()){
			SimplecoinCurrency currency = getCurrencies().get(key);
			if("ltc".equals(key.toLowerCase())){
				currency.setScale(3);
			}
			tl.addView(activity.renderRow("", key));
			tl.addView(activity.renderRow("Confirmed Rewards", currency.getConfirmed_rewards().toString()));
			tl.addView(activity.renderRow("Estimated Rewards", currency.getEstimated_rewards().toString()));
			tl.addView(activity.renderRow("Payout History", currency.getPayout_history().toString()));
		}
		tl.addView(activity.renderRow(DEFAULT_USERNAME + ":",""));
		for(String key : getWorkers().keySet()){
			SimplecoinWorker worker = getWorkers().get(key);
			tl.addView(activity.renderRow("", key));
			tl.addView(activity.renderRow("Alive", worker.getAlive().toString()));
			tl.addView(activity.renderRow(HASHRATE_DISPLAY_COL_2_LABEL, worker.getHashrate().toString()));
			tl.addView(activity.renderRow("Pool", worker.getPool()));
		}
		
		tl.addView(activity.renderRow("",""));
	}

}
