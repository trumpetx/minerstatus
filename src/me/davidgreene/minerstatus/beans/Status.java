package me.davidgreene.minerstatus.beans;

import java.io.Serializable;
import java.math.BigDecimal;


public interface Status extends Serializable {
	public String getUsername();
	public String getDisplayCol1();
	public String getDisplayCol2();
	public String getUsernameLabel();
	public String getDisplayCol1Label();
	public String getDisplayCol2Label();
	public BigDecimal getTotalHashrate();
	public void setApiKey(String apiKey);
	public String getApiKey();
}
