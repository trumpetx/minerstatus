package me.davidgreene.minerstatus.util;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import me.davidgreene.minerstatus.beans.ABCStatus;
import me.davidgreene.minerstatus.beans.BitclockersStatus;
import me.davidgreene.minerstatus.beans.BitpoolStatus;
import me.davidgreene.minerstatus.beans.BtcMineStatus;
import me.davidgreene.minerstatus.beans.BtcguildStatus;
import me.davidgreene.minerstatus.beans.DeepbitStatus;
import me.davidgreene.minerstatus.beans.EclipseMcStatus;
import me.davidgreene.minerstatus.beans.EligiusStatus;
import me.davidgreene.minerstatus.beans.MtGox;
import me.davidgreene.minerstatus.beans.MtredStatus;
import me.davidgreene.minerstatus.beans.OzcoinStatus;
import me.davidgreene.minerstatus.beans.SimplecoinStatus;
import me.davidgreene.minerstatus.beans.SlushStatus;
import me.davidgreene.minerstatus.beans.TripleminingStatus;

public class MinerStatusConstants {
	
	public static int MAX_ERRORS = 10;
	public static int CONNECTION_TIMEOUT = 4000;
	public static int SOCKET_TIMEOUT = 0;
	
	public static final Map<String, String> EXCHANGE_LABELS = new HashMap<String,String>(1);
	static{
		EXCHANGE_LABELS.put("mtgox", "Mt. Gox");
	}
	
	public static final Map<String, String> EXCHANGE_URLS = new HashMap<String,String>(1);
	static{
		EXCHANGE_URLS.put("mtgox", "https://mtgox.com/code/data/ticker.php");
	}
	
	public static final Map<String,String[]> POOL_URLS = new HashMap<String,String[]>(20);
	static{
		POOL_URLS.put("bitcoinpool", new String[]{"http://www.bitcoinpool.com/user.php?json=1&u=%MINER%"});
		POOL_URLS.put("slush", new String[]{"https://mining.bitcoin.cz/accounts/profile/json/%MINER%"});
		POOL_URLS.put("deepbit", new String[]{"https://deepbit.net/api/%MINER%"});
		POOL_URLS.put("btcmine", new String[]{"http://btcmine.com/api/getstats/%MINER%/", "http://btcmine.com/api/getminerstats/%MINER%/"});
		POOL_URLS.put("btcguild", new String[]{"https://www.btcguild.com/api.php?api_key=%MINER%"});
		POOL_URLS.put("bitclockers", new String[]{"http://bitclockers.com/?action=api&cmd=%MINER%"});
		POOL_URLS.put("mtred", new String[]{"https://mtred.com/api/user/key/%MINER%"});
		POOL_URLS.put("ozcoin", new String[]{"https://ozco.in/api.php?api_key=%MINER%"});
		POOL_URLS.put("eligius", new String[]{"http://eligius.st/~luke-jr/hashrate.php?addr=%MINER%"});
		POOL_URLS.put("eclipsemc", new String[]{"https://eclipsemc.com/api.php?key=%MINER%&action=userstats"});
		POOL_URLS.put("abc", new String[] {"https://www.abcpool.co/api.php?api_key=%MINER%"});
		POOL_URLS.put("simplecoin", new String[] {"https://simplecoin.us/api.php?api_key=%MINER%"});
		POOL_URLS.put("triple", new String[] {"https://api.triplemining.com/json/%MINER%"});
		
	}
	public static final Map<String,String> POOL_LABELS = new TreeMap<String,String>();
	static{
		POOL_LABELS.put("bitcoinpool", "Bitcoin Pool");
		POOL_LABELS.put("slush", "Slush's Pool");
		POOL_LABELS.put("deepbit", "Deepbit.net");
		POOL_LABELS.put("btcmine", "BtcMine");
		POOL_LABELS.put("btcguild", "BTC Guild");
		POOL_LABELS.put("bitclockers", "Bitclockers");
		POOL_LABELS.put("mtred", "Mt.Red");
		POOL_LABELS.put("ozcoin", "Ozco.in");
		POOL_LABELS.put("eligius", "Eligius");
		POOL_LABELS.put("eclipsemc", "EclipseMC");
		POOL_LABELS.put("abc","ABCPool");
		POOL_LABELS.put("simplecoin","Simplecoin");
		POOL_LABELS.put("triple","Triple Mining");
	}
	
	private static final String COMMON_DIRECTIONS = " provides an API key which you can use to access your data semi-privately (security through obscurity.)  You can get your API key ";
	
	public static final Map<String,String> POOL_DIRECTIONS = new HashMap<String,String>(20);
	static{
		POOL_DIRECTIONS.put("bitcoinpool", "Your miner's name is the username you created when you opened an account with\nhttp://www.bitcoinpool.com");
		POOL_DIRECTIONS.put("slush", "Slush's Pool"+COMMON_DIRECTIONS+"(and generate new ones) in your account settings at\nhttp://mining.bitcoin.cz");
		POOL_DIRECTIONS.put("deepbit", "Deepbit.net"+COMMON_DIRECTIONS+"(and generate new ones) on the JSON settings page at\nhttp://deepbit.net/settings");
		POOL_DIRECTIONS.put("btcmine", "BtcMine"+COMMON_DIRECTIONS+"(and generate new ones) on your profile page at\nhttp://btcmine.com/user/profile/");
		POOL_DIRECTIONS.put("btcguild", "BTC Guild"+COMMON_DIRECTIONS+"on your profile page at\nhttp://www.btcguild.com/my_api.php");
		POOL_DIRECTIONS.put("bitclockers", "Bitclockers"+COMMON_DIRECTIONS+"on your dashboard page at\nhttp://bitclockers.com/?action=dashboard");
		POOL_DIRECTIONS.put("mtred", "Mt. Red"+COMMON_DIRECTIONS+"on your profile page at\nhttps://mtred.com/user/profile.html");
		POOL_DIRECTIONS.put("ozcoin", "Ozco.in"+COMMON_DIRECTIONS+"on your account details page at\nhttps://ozco.in/accountdetails.php");
		POOL_DIRECTIONS.put("eligius", "Simply enter the BTC address you use to connect to the Eligius pool.");
		POOL_DIRECTIONS.put("eclipsemc", "EclipseMC"+COMMON_DIRECTIONS+"on your account details page at\nhttps://eclipsemc.com/my_account.php");
		POOL_DIRECTIONS.put("abc", "ABCPool"+COMMON_DIRECTIONS+"on your account details page at\nhttps://www.abcpool.co/accountdetails.php");
		POOL_DIRECTIONS.put("simplecoin", "Simplecoin"+COMMON_DIRECTIONS+"on your account details page at\nhttps://simplecoin.us/accountdetails.php");
		POOL_DIRECTIONS.put("triple", "Triple Mining"+COMMON_DIRECTIONS+"on your workers page at\nhttps://www.triplemining.com/workers");
	}	
	
	public static final Map<String,Class<?>> GSON_FACTORY_OBJECTS = new HashMap<String,Class<?>>(20);
	static{
		GSON_FACTORY_OBJECTS.put("mtgox", MtGox.class);
		GSON_FACTORY_OBJECTS.put("bitcoinpool", BitpoolStatus.class);
		GSON_FACTORY_OBJECTS.put("slush", SlushStatus.class);
		GSON_FACTORY_OBJECTS.put("deepbit", DeepbitStatus.class);
		GSON_FACTORY_OBJECTS.put("btcmine", BtcMineStatus.class);
		GSON_FACTORY_OBJECTS.put("btcguild", BtcguildStatus.class);
		GSON_FACTORY_OBJECTS.put("bitclockers", BitclockersStatus.class);
		GSON_FACTORY_OBJECTS.put("mtred", MtredStatus.class);
		GSON_FACTORY_OBJECTS.put("ozcoin", OzcoinStatus.class);
		GSON_FACTORY_OBJECTS.put("eligius", EligiusStatus.class);
		GSON_FACTORY_OBJECTS.put("eclipsemc", EclipseMcStatus.class);
		GSON_FACTORY_OBJECTS.put("abc", ABCStatus.class);
		GSON_FACTORY_OBJECTS.put("simplecoin", SimplecoinStatus.class);
		GSON_FACTORY_OBJECTS.put("triple", TripleminingStatus.class);
	}	
	public static final Map<String,String>THEME_LIST = new HashMap<String,String>(2);
	static{
		THEME_LIST.put("dark", "Dark Theme");
		THEME_LIST.put("light", "Light Theme");
	}
}
