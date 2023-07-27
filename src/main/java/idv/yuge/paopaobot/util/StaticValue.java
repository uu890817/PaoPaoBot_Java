package idv.yuge.paopaobot.util;

import idv.yuge.paopaobot.gson.BotConfigBean;
import net.dv8tion.jda.api.JDA;

public class StaticValue {
	private static BotConfigBean botConfig;
	private static JDA jda;
	private static String ip;
	
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

	
	

	
	
	
	
	
	
}
