package me.davidgreene.minerstatus.beans;

import java.io.Serializable;
import java.math.BigDecimal;


public interface Status extends Serializable {
	
	static final String DEFAULT_USERNAME = "Worker(s)";
	static final String HASHRATE_DISPLAY_COL_2_LABEL = "Hashrate";
	static final String CONFIRMED_REWARD_COL_1_LABEL = "Confirmed Reward";
	String getUsername();
	String getDisplayCol1();
	String getDisplayCol2();
	String getUsernameLabel();
	String getDisplayCol1Label();
	String getDisplayCol2Label();
	BigDecimal getTotalHashrate();
	void setApiKey(String apiKey);
	String getApiKey();
}
