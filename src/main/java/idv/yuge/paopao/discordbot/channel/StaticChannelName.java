package idv.yuge.paopao.discordbot.channel;

import idv.yuge.paopao.sqlite.service.SettingService;


public class StaticChannelName {

	private static String infoChannelName;
	private static String ipChannelName;
	private static String portChannelName;
	private static String onlineChannelName;
	private static String playerChannelName;
	private static String versionChannelName;

	private StaticChannelName() {};
	
	public static void getAllIdFromDB() {
		SettingService ss = new SettingService();
		infoChannelName = ss.selectByKey("Info_Channel_Name").getValue();
		ipChannelName = ss.selectByKey("IP_Channel_Name").getValue();
		portChannelName = ss.selectByKey("Port_Channel_Name").getValue();
		onlineChannelName = ss.selectByKey("Online_Channel_Name").getValue();
		playerChannelName = ss.selectByKey("Player_Channel_Name").getValue();
		versionChannelName = ss.selectByKey("Version_Channel_Name").getValue();

	}

	public static void printAll() {
		System.out.println(infoChannelName);
		System.out.println(ipChannelName);
		System.out.println(portChannelName);
		System.out.println(onlineChannelName);
		System.out.println(playerChannelName);
		System.out.println(versionChannelName);
	}

	public static String getInfoChannelName() {
		return infoChannelName;
	}

	public static void setInfoChannelName(String infoChannelName) {
		StaticChannelName.infoChannelName = infoChannelName;
	}

	public static String getIpChannelName() {
		return ipChannelName;
	}

	public static void setIpChannelName(String ipChannelName) {
		StaticChannelName.ipChannelName = ipChannelName;
	}

	public static String getPortChannelName() {
		return portChannelName;
	}

	public static void setPortChannelName(String portChannelName) {
		StaticChannelName.portChannelName = portChannelName;
	}

	public static String getOnlineChannelName() {
		return onlineChannelName;
	}

	public static void setOnlineChannelName(String onlineChannelName) {
		StaticChannelName.onlineChannelName = onlineChannelName;
	}

	public static String getPlayerChannelName() {
		return playerChannelName;
	}

	public static void setPlayerChannelName(String playerChannelName) {
		StaticChannelName.playerChannelName = playerChannelName;
	}

	public static String getVersionChannelName() {
		return versionChannelName;
	}

	public static void setVersionChannelName(String versionChannelName) {
		StaticChannelName.versionChannelName = versionChannelName;
	}



	
	
	
	
}
