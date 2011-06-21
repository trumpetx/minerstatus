package me.davidgreene.minerstatus.util;

import java.util.HashMap;
import java.util.Map;

public class MinerStatusConstants {
	
	public static String SEKRET_MTGOX_KEY = "J5ACiar7uDiEsoEt";
	public static String SEKRET_TRADEHILL_KEY = "4Fi9bSOQm34Aa2pw";
	public static String MT_GOX_PUBLIC = "https://mtgox.com/code/data/ticker.php";
	public static String TRADEHILL_PUBLIC = "https://api.tradehill.com/APIv1/USD/Ticker";
	public static int MAX_ERRORS = 10;
	
	public static final Map<String,String[]> POOL_URLS = new HashMap<String,String[]>(8);
	static{
		POOL_URLS.put("bitcoinpool", new String[]{"http://www.bitcoinpool.com/user.php?json=1&u=%MINER%"});
		POOL_URLS.put("slush", new String[]{"https://mining.bitcoin.cz/accounts/profile/json/%MINER%"});
		POOL_URLS.put("deepbit", new String[]{"https://deepbit.net/api/%MINER%"});
		POOL_URLS.put("btcmine", new String[]{"http://btcmine.com/api/getstats/%MINER%/", "http://btcmine.com/api/getminerstats/%MINER%/"});
		POOL_URLS.put("btcguild", new String[]{"http://www.btcguild.com/api.php?api_key=%MINER%"});
		POOL_URLS.put("bitclockers", new String[]{"http://bitclockers.com/?action=api&cmd=%MINER%"});
		POOL_URLS.put("swepool", new String[]{"https://swepool.net/json?key=%MINER%"});
		POOL_URLS.put("mtred", new String[]{"https://mtred.com/api/user/key/%MINER%"});
	}
	public static final Map<String,String> POOL_LABELS = new HashMap<String,String>(8);
	static{
		POOL_LABELS.put("bitcoinpool", "Bitcoin Pool");
		POOL_LABELS.put("slush", "Slush's Pool");
		POOL_LABELS.put("deepbit", "Deepbit.net");
		POOL_LABELS.put("btcmine", "BtcMine");
		POOL_LABELS.put("btcguild", "BTC Guild");
		POOL_LABELS.put("bitclockers", "Bitclockers");
		POOL_LABELS.put("swepool", "Swepool");
		POOL_LABELS.put("mtred", "Mt.Red");
	}
	public static final Map<String,String> POOL_DIRECTIONS = new HashMap<String,String>(8);
	static{
		POOL_DIRECTIONS.put("bitcoinpool", "Your miner's name is the username you created when you opened an account with\nhttp://www.bitcoinpool.com");
		POOL_DIRECTIONS.put("slush", "Slush's Pool provides an API key which you can use to access your data semi-privately (security through obscurity.)  You can get your API key (and generate new ones) in your account settings at\nhttp://mining.bitcoin.cz");
		POOL_DIRECTIONS.put("deepbit", "Deepbit.net provides an API key which you can use to access your data semi-privately (security through obscurity.)  You can get your API key (and generate new ones) on the JSON settings page at\nhttp://deepbit.net/settings");
		POOL_DIRECTIONS.put("btcmine", "BtcMine provides an API key which you can use to access your data semi-privately (security through obscurity.)  You can get your API key (and generate new ones) on your profile page at\nhttp://btcmine.com/user/profile/");
		POOL_DIRECTIONS.put("btcguild", "BTC Guild provides an API key which you can use to access your data semi-privately (security through obscurity.)  You can get your API key on your profile page at\nhttp://www.btcguild.com/my_api.php");
		POOL_DIRECTIONS.put("bitclockers", "Bitclockers provides an API key which you can use to access your data semi-privately (security through obscurity.)  You can get your API key on your dashboard page at\nhttp://bitclockers.com/?action=dashboard");
		POOL_DIRECTIONS.put("swepool", "Swepool provides an API key which you can use to access your data semi-privately (security through obscurity.)  You can get your API key on your settings page at\nhttp://swepool.net/settings");
		POOL_DIRECTIONS.put("mtred", "Mt. Red provides an API key which you can use to access your data semi-privately (security through obscurity.)  You can get your API key on your profile page at\nhttps://mtred.com/user/profile.html");
		
	}	
	public static final Map<String,String>THEME_LIST = new HashMap<String,String>(2);
	static{
		THEME_LIST.put("dark", "Dark Theme");
		THEME_LIST.put("light", "Light Theme");
	}
}
