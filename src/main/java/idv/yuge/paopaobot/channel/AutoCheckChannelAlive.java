package idv.yuge.paopaobot.channel;

import java.util.Map;

import idv.yuge.paopaobot.gson.BotConfig;
import idv.yuge.paopaobot.gson.BotConfigBean;
import idv.yuge.paopaobot.util.ConsoleColors;
import idv.yuge.paopaobot.util.PrintOut;
import idv.yuge.paopaobot.util.StaticValue;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.channel.concrete.Category;
import net.dv8tion.jda.api.entities.channel.concrete.VoiceChannel;

public class AutoCheckChannelAlive implements Runnable{
	
	public void check() {
		
		JDA jda = StaticValue.getJda();
		Guild guild = jda.getGuildById(StaticValue.getBotConfig().getGuildId());
		BotConfigBean bConfig = StaticValue.getBotConfig();
		Map<String, Long> channelId = bConfig.getChannelId();
		
		Category category= guild.getCategoryById(channelId.get("Category"));
		if(category == null) {
			PrintOut.timePrintln(ConsoleColors.YELLOW_BRIGHT, "Category不存在");
			Category createdCategory = CreateCategory.create();
			channelId.put("Category", createdCategory.getIdLong());
		}
		
		VoiceChannel ipChannel = guild.getChannelById(VoiceChannel.class, channelId.get("IpChannel"));
		if(ipChannel == null) {
			PrintOut.timePrintln(ConsoleColors.YELLOW_BRIGHT, "ipChannel不存在");
			VoiceChannel createdVoiceChannel = CreateIpChannel.create(channelId.get("Category"));
			channelId.put("IpChannel", createdVoiceChannel.getIdLong());
		}
		
		VoiceChannel OnlinePortChannel = guild.getChannelById(VoiceChannel.class, channelId.get("PortChannel"));
		if(OnlinePortChannel == null) {
			PrintOut.timePrintln(ConsoleColors.YELLOW_BRIGHT, "PortChannel不存在");
			VoiceChannel createdOnlineStateChannel = CreatePortChannel.create(channelId.get("Category"));
			channelId.put("PortChannel", createdOnlineStateChannel.getIdLong());
		}
		
		VoiceChannel OnlineStateChannel = guild.getChannelById(VoiceChannel.class, channelId.get("ServerState"));
		if(OnlineStateChannel == null) {
			PrintOut.timePrintln(ConsoleColors.YELLOW_BRIGHT, "serverState不存在");
			VoiceChannel createdOnlineStateChannel = CreateOnlineStateChannel.create(channelId.get("Category"));
			channelId.put("ServerState", createdOnlineStateChannel.getIdLong());
		}
		
		VoiceChannel serverVersionChannel = guild.getChannelById(VoiceChannel.class, channelId.get("ServerVersion"));
		if(serverVersionChannel == null) {
			PrintOut.timePrintln(ConsoleColors.YELLOW_BRIGHT, "PortChannel不存在");
			VoiceChannel createdOnlineStateChannel = CreateVersionChannel.create(channelId.get("Category"));
			channelId.put("ServerVersion", createdOnlineStateChannel.getIdLong());
		}
		
		VoiceChannel playerCountChannel = guild.getChannelById(VoiceChannel.class, channelId.get("PlayerCount"));
		if(playerCountChannel == null) {
			PrintOut.timePrintln(ConsoleColors.YELLOW_BRIGHT, "playerCount不存在");
			VoiceChannel createdOnlineStateChannel = CreatePlayersChannel.create(channelId.get("Category"));
			channelId.put("PlayerCount", createdOnlineStateChannel.getIdLong());
		}
		
		
		
		
		bConfig.setChannelId(channelId);
		StaticValue.setBotConfig(bConfig);
		BotConfig.write();
		
		
		
	}

	@Override
	public void run() {
		this.check();
		
	}
	
	
	
	
	
	
}
