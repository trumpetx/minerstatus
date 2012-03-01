package me.davidgreene.minerstatus.util;

import me.davidgreene.minerstatus.beans.Exchange;
import me.davidgreene.minerstatus.beans.Status;

import com.google.gson.Gson;

public class GsonObjectFactory {
	
	@SuppressWarnings("unchecked")
	public static Status getStatusObject(String result, String identifier){
		Class<Status> Clazz = (Class<Status>) MinerStatusConstants.GSON_FACTORY_OBJECTS.get(identifier);
		if (Clazz == null){
			return null;
		}
		return new Gson().fromJson(result, Clazz);
	}
	
	@SuppressWarnings("unchecked")
	public static Exchange getExchangeObject(String result, String identifier){
		Class<Exchange> Clazz = (Class<Exchange>) MinerStatusConstants.GSON_FACTORY_OBJECTS.get(identifier);
		if (Clazz == null){
			return null;
		}
		return new Gson().fromJson(result, Clazz);
	}
}
