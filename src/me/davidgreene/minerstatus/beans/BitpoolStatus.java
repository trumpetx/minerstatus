package me.davidgreene.minerstatus.beans;

import java.io.Serializable;
import java.math.BigDecimal;

public class BitpoolStatus implements Status, Serializable{
	
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


}
