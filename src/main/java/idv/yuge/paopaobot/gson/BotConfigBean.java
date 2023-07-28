package idv.yuge.paopaobot.gson;

import java.util.ArrayList;
import java.util.Map;

public class BotConfigBean {
    private String BotToken;
    private long GuildId;
    private long BotOwner;
    private ArrayList<Long> Master;
    private Map<String, Long> ChannelId;
    private String Ip;
    private int Port;
    
	public String getIp() {
		return Ip;
	}
	public void setIp(String ip) {
		Ip = ip;
	}
	public int getPort() {
		return Port;
	}
	public void setPort(int port) {
		Port = port;
	}
	public String getBotToken() {
		return BotToken;
	}
	public void setBotToken(String botToken) {
		BotToken = botToken;
	}
	public long getGuildId() {
		return GuildId;
	}
	public void setGuildId(long guildId) {
		GuildId = guildId;
	}
	public long getBotOwner() {
		return BotOwner;
	}
	public void setBotOwner(long botOwner) {
		BotOwner = botOwner;
	}
	public ArrayList<Long> getMaster() {
		return Master;
	}
	public void setMaster(ArrayList<Long> master) {
		Master = master;
	}
	public Map<String, Long> getChannelId() {
		return ChannelId;
	}
	public void setChannelId(Map<String, Long> channelId) {
		ChannelId = channelId;
	}

    
}
