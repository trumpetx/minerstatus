package me.davidgreene.minerstatus.util;

import me.davidgreene.minerstatus.beans.Exchange;
import me.davidgreene.minerstatus.beans.ExchangeBitcoins;
import me.davidgreene.minerstatus.beans.MtGox;
import me.davidgreene.minerstatus.beans.Tradehill;

import com.google.gson.Gson;

public class ExchangeObjectFactory {
	
	public static Exchange getStatusObject(String result, String identifier){
		if (identifier == null){
			return null;
		}
		Gson gson = new Gson();
		if (identifier.equals("mtgox")){
			return gson.fromJson(result, MtGox.class);
		} else if (identifier.equals("tradehill")){
			return gson.fromJson(result, Tradehill.class);
		} else if (identifier.equals("exchange_bitcoins")){
			return gson.fromJson(result, ExchangeBitcoins.class);
		}
		return null;
	}
	
	
}
