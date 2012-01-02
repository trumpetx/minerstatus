package me.davidgreene.minerstatus.util;

import java.util.HashMap;
import java.util.Map;

public class MinerStatusConstants {
	
	public static int MAX_ERRORS = 10;
	public static int CONNECTION_TIMEOUT = 4000;
	public static int SOCKET_TIMEOUT = 0;
	
	public static final Map<String, String> EXCHANGE_LABELS = new HashMap<String,String>(4);
	static{
		EXCHANGE_LABELS.put("mtgox", "Mt. Gox");
		EXCHANGE_LABELS.put("tradehill", "Tradehill");
	}
	
	public static final Map<String, String> EXCHANGE_URLS = new HashMap<String,String>(4);
	static{
		EXCHANGE_URLS.put("mtgox", "https://mtgox.com/code/data/ticker.php");
		EXCHANGE_URLS.put("tradehill", "https://api.tradehill.com/APIv1/USD/Ticker");
	}
	
	public static final Map<String,String[]> POOL_URLS = new HashMap<String,String[]>(20);
	static{
		POOL_URLS.put("bitcoinpool", new String[]{"http://www.bitcoinpool.com/user.php?json=1&u=%MINER%"});
		POOL_URLS.put("slush", new String[]{"https://mining.bitcoin.cz/accounts/profile/json/%MINER%"});
		POOL_URLS.put("deepbit", new String[]{"https://deepbit.net/api/%MINER%"});
		POOL_URLS.put("btcmine", new String[]{"http://btcmine.com/api/getstats/%MINER%/", "http://btcmine.com/api/getminerstats/%MINER%/"});
		POOL_URLS.put("btcguild", new String[]{"https://www.btcguild.com/api.php?api_key=%MINER%"});
		POOL_URLS.put("bitclockers", new String[]{"http://bitclockers.com/?action=api&cmd=%MINER%"});
		POOL_URLS.put("swepool", new String[]{"https://swepool.net/json?key=%MINER%"});
		POOL_URLS.put("mtred", new String[]{"https://mtred.com/api/user/key/%MINER%"});
		POOL_URLS.put("ozcoin", new String[]{"https://ozco.in/api.php?api_key=%MINER%"});
		POOL_URLS.put("eligius", new String[]{"http://eligius.st/~luke-jr/hashrate.php?addr=%MINER%"});
		POOL_URLS.put("eclipsemc", new String[]{"https://eclipsemc.com/api.php?key=%MINER%&action=userstats"});
		POOL_URLS.put("ars", new String[] {"https://arsbitcoin.com/api.php?api_key=%MINER%"});
	}
	public static final Map<String,String> POOL_LABELS = new HashMap<String,String>(20);
	static{
		POOL_LABELS.put("bitcoinpool", "Bitcoin Pool");
		POOL_LABELS.put("slush", "Slush's Pool");
		POOL_LABELS.put("deepbit", "Deepbit.net");
		POOL_LABELS.put("btcmine", "BtcMine");
		POOL_LABELS.put("btcguild", "BTC Guild");
		POOL_LABELS.put("bitclockers", "Bitclockers");
		POOL_LABELS.put("swepool", "Swepool");
		POOL_LABELS.put("mtred", "Mt.Red");
		POOL_LABELS.put("ozcoin", "Ozco.in");
		POOL_LABELS.put("eligius", "Eligius");
		POOL_LABELS.put("eclipsemc", "EclipseMC");
		POOL_LABELS.put("ars","Ars Bitcoin");
	}
	public static final Map<String,String> POOL_DIRECTIONS = new HashMap<String,String>(20);
	static{
		POOL_DIRECTIONS.put("bitcoinpool", "Your miner's name is the username you created when you opened an account with\nhttp://www.bitcoinpool.com");
		POOL_DIRECTIONS.put("slush", "Slush's Pool provides an API key which you can use to access your data semi-privately (security through obscurity.)  You can get your API key (and generate new ones) in your account settings at\nhttp://mining.bitcoin.cz");
		POOL_DIRECTIONS.put("deepbit", "Deepbit.net provides an API key which you can use to access your data semi-privately (security through obscurity.)  You can get your API key (and generate new ones) on the JSON settings page at\nhttp://deepbit.net/settings");
		POOL_DIRECTIONS.put("btcmine", "BtcMine provides an API key which you can use to access your data semi-privately (security through obscurity.)  You can get your API key (and generate new ones) on your profile page at\nhttp://btcmine.com/user/profile/");
		POOL_DIRECTIONS.put("btcguild", "BTC Guild provides an API key which you can use to access your data semi-privately (security through obscurity.)  You can get your API key on your profile page at\nhttp://www.btcguild.com/my_api.php");
		POOL_DIRECTIONS.put("bitclockers", "Bitclockers provides an API key which you can use to access your data semi-privately (security through obscurity.)  You can get your API key on your dashboard page at\nhttp://bitclockers.com/?action=dashboard");
		POOL_DIRECTIONS.put("swepool", "Swepool provides an API key which you can use to access your data semi-privately (security through obscurity.)  You can get your API key on your settings page at\nhttp://swepool.net/settings");
		POOL_DIRECTIONS.put("mtred", "Mt. Red provides an API key which you can use to access your data semi-privately (security through obscurity.)  You can get your API key on your profile page at\nhttps://mtred.com/user/profile.html");
		POOL_DIRECTIONS.put("ozcoin", "Ozco.in provides an API key which you can use to access your data semi-privately (security through obscurity.)  You can get your API key on your account details page at\nhttps://ozco.in/accountdetails.php");
		POOL_DIRECTIONS.put("eligius", "Simply enter the BTC address you use to connect to the Eligius pool.");
		POOL_DIRECTIONS.put("eclipsemc", "EclipseMC provides an API key which you can use to access your data semi-privately (security through obscurity.)  You can get your API key on your account details page at\nhttps://eclipsemc.com/my_account.php");
		POOL_DIRECTIONS.put("ars", "Ars provides an API key which you can use to access your data semi-privately (security through obscurity.)  You can get your API key on your account details page at\nhttps://arsbitcoin.com/accountdetails.php");
	}	
	public static final Map<String,String>THEME_LIST = new HashMap<String,String>(2);
	static{
		THEME_LIST.put("dark", "Dark Theme");
		THEME_LIST.put("light", "Light Theme");
	}
}
