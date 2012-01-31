package me.davidgreene.minerstatus.beans;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class SimplecoinStatus implements Status {

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
		  "hashrate" : 0,
		  "workers" : { "trumpetx.1" : { "alive" : false,
		          "hashrate" : 0,
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
		return "Worker(s)";
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
		return "Hashrate";
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

}
