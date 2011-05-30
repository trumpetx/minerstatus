package me.davidgreene.minerstatus.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Map;

public class MtredStatus implements Status, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2672499038173236256L;

	private String balance;
	private String rsolved;
	private MtredServer server;
	private Map<String, MtredWorker> workers;
	private String apiKey;
	
	
	
	@Override
	public String getUsername() {
		return "Worker(s)";
	}

	@Override
	public String getDisplayCol1() {
		return getBalance();
	}

	@Override
	public String getDisplayCol2() {
		BigDecimal hashRate = new BigDecimal("0");
		Iterator it = workers.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pairs = (Map.Entry)it.next();
			pairs.getKey();
			MtredWorker worker = (MtredWorker) pairs.getValue();
			hashRate = hashRate.add(worker.getMhash().setScale(2, BigDecimal.ROUND_HALF_UP));
		}
		return hashRate.toString();
	}

	@Override
	public String getUsernameLabel() {
		return "";
	}

	@Override
	public String getDisplayCol1Label() {
		return "Balance";
	}

	@Override
	public String getDisplayCol2Label() {
		return "Hashrate";
	}

	public String getBalance() {
		return (balance != null) ? new BigDecimal(balance).setScale(6, BigDecimal.ROUND_HALF_UP).toString() : null;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public String getRsolved() {
		return rsolved;
	}

	public void setRsolved(String rsolved) {
		this.rsolved = rsolved;
	}

	public MtredServer getServer() {
		return server;
	}

	public void setServer(MtredServer server) {
		this.server = server;
	}

	public Map<String, MtredWorker> getWorkers() {
		return workers;
	}

	public void setWorkers(Map<String, MtredWorker> workers) {
		this.workers = workers;
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

}
