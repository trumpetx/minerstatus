package me.davidgreene.minerstatus.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Map;

import me.davidgreene.minerstatus.R;
import me.davidgreene.minerstatus.ViewMinerActivity;
import android.widget.TableLayout;

public class BtcguildStatus implements Status, Serializable, Renderable  {

	/**
	 * { "pool" : { "difficulty" : 2694047,
      "difficulty_nmc" : 1209202,
      "pool_speed" : 2344.2199999999998,
      "pps_rate" : 0.00001763146671160525,
      "pps_rate_nmc" : 0.00003928210505771575
    },
  "user" : { "paid_rewards" : 0.0,
      "paid_rewards_nmc" : 0.0,
      "past_24h_rewards" : 0.0,
      "past_24h_rewards_nmc" : 0.0,
      "total_rewards" : 0.0,
      "total_rewards_nmc" : 0.0,
      "unpaid_rewards" : 0.0,
      "unpaid_rewards_nmc" : 0.0,
      "user_id" : 759
    },
  "workers" : { "1" : { "dupe_shares" : 0,
          "dupe_shares_nmc" : 0,
          "dupe_shares_nmc_since_reset" : 0,
          "dupe_shares_since_reset" : 0,
          "hash_rate" : 0.0,
          "last_share" : 0,
          "stale_shares" : 0,
          "stale_shares_nmc" : 0,
          "stale_shares_nmc_since_reset" : 0,
          "stale_shares_since_reset" : 0,
          "unknown_shares" : 0,
          "unknown_shares_nmc" : 0,
          "unknown_shares_nmc_since_reset" : 0,
          "unknown_shares_since_reset" : 0,
          "valid_shares" : 0,
          "valid_shares_nmc" : 0,
          "valid_shares_nmc_since_reset" : 0,
          "valid_shares_since_reset" : 0,
          "worker_name" : "trumpetx_57701"
        },
      "2" : { "dupe_shares" : 0,
          "dupe_shares_nmc" : 0,
          "dupe_shares_nmc_since_reset" : 0,
          "dupe_shares_since_reset" : 0,
          "hash_rate" : 0.0,
          "last_share" : 0,
          "stale_shares" : 0,
          "stale_shares_nmc" : 0,
          "stale_shares_nmc_since_reset" : 0,
          "stale_shares_since_reset" : 0,
          "unknown_shares" : 0,
          "unknown_shares_nmc" : 0,
          "unknown_shares_nmc_since_reset" : 0,
          "unknown_shares_since_reset" : 0,
          "valid_shares" : 0,
          "valid_shares_nmc" : 0,
          "valid_shares_nmc_since_reset" : 0,
          "valid_shares_since_reset" : 0,
          "worker_name" : "trumpetx_57702"
        },
      "3" : { "dupe_shares" : 0,
          "dupe_shares_nmc" : 0,
          "dupe_shares_nmc_since_reset" : 0,
          "dupe_shares_since_reset" : 0,
          "hash_rate" : 0.0,
          "last_share" : 0,
          "stale_shares" : 0,
          "stale_shares_nmc" : 0,
          "stale_shares_nmc_since_reset" : 0,
          "stale_shares_since_reset" : 0,
          "unknown_shares" : 0,
          "unknown_shares_nmc" : 0,
          "unknown_shares_nmc_since_reset" : 0,
          "unknown_shares_since_reset" : 0,
          "valid_shares" : 0,
          "valid_shares_nmc" : 0,
          "valid_shares_nmc_since_reset" : 0,
          "valid_shares_since_reset" : 0,
          "worker_name" : "trumpetx_58501"
        },
      "4" : { "dupe_shares" : 0,
          "dupe_shares_nmc" : 0,
          "dupe_shares_nmc_since_reset" : 0,
          "dupe_shares_since_reset" : 0,
          "hash_rate" : 0.0,
          "last_share" : 0,
          "stale_shares" : 0,
          "stale_shares_nmc" : 0,
          "stale_shares_nmc_since_reset" : 0,
          "stale_shares_since_reset" : 0,
          "unknown_shares" : 0,
          "unknown_shares_nmc" : 0,
          "unknown_shares_nmc_since_reset" : 0,
          "unknown_shares_since_reset" : 0,
          "valid_shares" : 0,
          "valid_shares_nmc" : 0,
          "valid_shares_nmc_since_reset" : 0,
          "valid_shares_since_reset" : 0,
          "worker_name" : "trumpetx_58502"
        },
      "5" : { "dupe_shares" : 0,
          "dupe_shares_nmc" : 0,
          "dupe_shares_nmc_since_reset" : 0,
          "dupe_shares_since_reset" : 0,
          "hash_rate" : 0.0,
          "last_share" : 0,
          "stale_shares" : 0,
          "stale_shares_nmc" : 0,
          "stale_shares_nmc_since_reset" : 0,
          "stale_shares_since_reset" : 0,
          "unknown_shares" : 0,
          "unknown_shares_nmc" : 0,
          "unknown_shares_nmc_since_reset" : 0,
          "unknown_shares_since_reset" : 0,
          "valid_shares" : 0,
          "valid_shares_nmc" : 0,
          "valid_shares_nmc_since_reset" : 0,
          "valid_shares_since_reset" : 0,
          "worker_name" : "trumpetx_other"
        }
    }
}
	 */
	private static final long serialVersionUID = 2399234390831522839L;

	private BtcguildUser user;
	private BtcguildPool pool;
	private Map<String, BtcguildWorker> workers;
	private String apiKey;
	
	@Override
	public BigDecimal getTotalHashrate(){
		BigDecimal hashRate = BigDecimal.ZERO;
		if (workers != null){
			for(String key : workers.keySet()) {
				BtcguildWorker worker = (BtcguildWorker) workers.get(key);
				hashRate = hashRate.add(worker.getHash_rate());
			}
		} 
		return hashRate.setScale(2, BigDecimal.ROUND_HALF_UP);
	}
	
	@Override
	public String getUsername() {
		return DEFAULT_USERNAME;
	}

	@Override
	public String getDisplayCol1() {
		if (user == null){
			return "";
		}
		return user.getPast_24h_rewards().toString();
	}

	@Override
	public String getDisplayCol2() {
		if (workers == null){
			return "No Workers";
		} else {
			return getTotalHashrate().toString();
		}
	}

	@Override
	public String getUsernameLabel() {
		return "";
	}

	@Override
	public String getDisplayCol1Label() {
		return "Past 24 Hrs Rewards";
	}

	@Override
	public String getDisplayCol2Label() {
		return "Hash Rate";
	}

	public BtcguildUser getUser() {
		return user;
	}

	public void setUser(BtcguildUser user) {
		this.user = user;
	}

	public BtcguildPool getPool() {
		return pool;
	}

	public void setPool(BtcguildPool pool) {
		this.pool = pool;
	}

	public Map<String, BtcguildWorker> getWorkers() {
		return workers;
	}

	public void setWorkers(Map<String, BtcguildWorker> workers) {
		this.workers = workers;
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public void render(ViewMinerActivity activity) {
		TableLayout tl = (TableLayout) activity.findViewById(R.id.detailedView);
		tl.addView(activity.renderRow("Paid Rewards", getUser().getPaid_rewards()));
		tl.addView(activity.renderRow("24h Rewards", getUser().getPast_24h_rewards()));
		tl.addView(activity.renderRow("Unpaid Rewards", getUser().getUnpaid_rewards()));
		tl.addView(activity.renderRow("NMC Paid Rewards", getUser().getPaid_rewards_nmc()));
		tl.addView(activity.renderRow("NMC 24h rewards", getUser().getPast_24h_rewards_nmc()));
		tl.addView(activity.renderRow("NMC Unpaid Rewards", getUser().getUnpaid_rewards_nmc()));

		tl.addView(activity.renderRow(DEFAULT_USERNAME + ":",""));
		if (getWorkers() != null){
		    for( String key : getWorkers().keySet() ){
		    	BtcguildWorker worker = getWorkers().get(key);
		    	tl.addView(activity.renderRow("",worker.getWorker_name()));
		    	tl.addView(activity.renderRow(HASHRATE_DISPLAY_COL_2_LABEL,worker.getHash_rate()));
		    	tl.addView(activity.renderRow("Valid Shares",worker.getValid_shares()));
		    	tl.addView(activity.renderRow("Dupe Shares",worker.getDupe_shares()));
		    	tl.addView(activity.renderRow("Stale Shares",worker.getStale_shares()));
		    	tl.addView(activity.renderRow("Valid NMC Shares",worker.getValid_shares_nmc()));
		    	tl.addView(activity.renderRow("Dupe NMC Shares",worker.getDupe_shares_nmc()));
		    	tl.addView(activity.renderRow("Stale NMC Shares",worker.getStale_shares_nmc()));
		    	tl.addView(activity.renderRow("",""));
		    }
		}
		tl.addView(activity.renderRow("",""));
	}	
	
}
