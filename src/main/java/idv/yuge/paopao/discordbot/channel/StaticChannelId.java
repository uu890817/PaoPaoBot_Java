package idv.yuge.paopao.discordbot.channel;

import idv.yuge.paopao.sqlite.service.SettingService;
import lombok.Data;

@Data
public class StaticChannelId {
	private static String guildId;
	private static String infoChannelId;
	private static String ipChannelId;
	private static String portChannelId;
	private static String onlineChannelId;
	private static String playerChannelId;
	private static String versionChannelId;
	private static String messageChannelId;

	
	
	private StaticChannelId() {};
	
	public static void getAllIdFromDB() {
		SettingService ss = new SettingService();

		guildId = ss.selectByKey("Guild_Id").getValue();

		infoChannelId = ss.selectByKey("Info_Channel_Id").getValue();
		ipChannelId = ss.selectByKey("IP_Channel_Id").getValue();
		portChannelId = ss.selectByKey("Port_Channel_Id").getValue();
		onlineChannelId = ss.selectByKey("Online_Channel_Id").getValue();
		playerChannelId = ss.selectByKey("Player_Channel_Id").getValue();
		versionChannelId = ss.selectByKey("Version_Channel_Id").getValue();
		messageChannelId = ss.selectByKey("Message_Channel_Id").getValue();
	}

	public static void printAll() {
		System.out.println(infoChannelId);
		System.out.println(ipChannelId);
		System.out.println(portChannelId);
		System.out.println(infoChannelId);
		System.out.println(onlineChannelId);
		System.out.println(playerChannelId);
		System.out.println(versionChannelId);
		System.out.println(messageChannelId);
	}

	public static String getGuildId() {
		return guildId;
	}

	public static void setGuildId(String guildId) {
		StaticChannelId.guildId = guildId;
	}

	public static String getInfoChannelId() {
		return infoChannelId;
	}

	public static void setInfoChannelId(String infoChannelId) {
		StaticChannelId.infoChannelId = infoChannelId;
	}

	public static String getIpChannelId() {
		return ipChannelId;
	}

	public static void setIpChannelId(String ipChannelId) {
		StaticChannelId.ipChannelId = ipChannelId;
	}

	public static String getPortChannelId() {
		return portChannelId;
	}

	public static void setPortChannelId(String portChannelId) {
		StaticChannelId.portChannelId = portChannelId;
	}

	public static String getOnlineChannelId() {
		return onlineChannelId;
	}

	public static void setOnlineChannelId(String onlineChannelId) {
		StaticChannelId.onlineChannelId = onlineChannelId;
	}

	public static String getPlayerChannelId() {
		return playerChannelId;
	}

	public static void setPlayerChannelId(String playerChannelId) {
		StaticChannelId.playerChannelId = playerChannelId;
	}

	public static String getVersionChannelId() {
		return versionChannelId;
	}

	public static void setVersionChannelId(String versionChannelId) {
		StaticChannelId.versionChannelId = versionChannelId;
	}

	public static String getMessageChannelId() {
		return messageChannelId;
	}

	public static void setMessageChannelId(String messageChannelId) {
		StaticChannelId.messageChannelId = messageChannelId;
	}

	
}
