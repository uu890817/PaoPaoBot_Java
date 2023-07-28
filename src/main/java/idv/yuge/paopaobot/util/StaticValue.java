package idv.yuge.paopaobot.util;

import idv.yuge.paopaobot.gson.BotConfigBean;
import net.dv8tion.jda.api.JDA;

public class StaticValue {
	private static BotConfigBean botConfig;
	private static JDA jda;
	private static String ip;
	private static int port;
	private static boolean serverOnline;
	private static String serverVersion;
	private static int onlinePlayer;
	private static int maxPlayer;
	
	public static BotConfigBean getBotConfig() {
		return botConfig;
	}
	public static void setBotConfig(BotConfigBean botConfig) {
		StaticValue.botConfig = botConfig;
	}
	public static JDA getJda() {
		return jda;
	}
	public static void setJda(JDA jda) {
		StaticValue.jda = jda;
	}
	public static String getIp() {
		return ip;
	}
	public static void setIp(String ip) {
		StaticValue.ip = ip;
	}
	public static int getPort() {
		return port;
	}
	public static void setPort(int port) {
		StaticValue.port = port;
	}
	public static boolean isServerOnline() {
		return serverOnline;
	}
	public static void setServerOnline(boolean serverOnline) {
		StaticValue.serverOnline = serverOnline;
	}
	public static String getServerVersion() {
		return serverVersion;
	}
	public static void setServerVersion(String serverVersion) {
		StaticValue.serverVersion = serverVersion;
	}
	public static int getOnlinePlayer() {
		return onlinePlayer;
	}
	public static void setOnlinePlayer(int onlinePlayer) {
		StaticValue.onlinePlayer = onlinePlayer;
	}
	public static int getMaxPlayer() {
		return maxPlayer;
	}
	public static void setMaxPlayer(int maxPlayer) {
		StaticValue.maxPlayer = maxPlayer;
	}
	

	
}
