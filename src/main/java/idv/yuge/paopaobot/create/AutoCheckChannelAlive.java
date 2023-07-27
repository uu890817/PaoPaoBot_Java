package idv.yuge.paopaobot.create;

import java.util.Map;

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
		}
		VoiceChannel ipChannel = guild.getChannelById(VoiceChannel.class, channelId.get("IP"));
		if(ipChannel == null) {
			PrintOut.timePrintln(ConsoleColors.YELLOW_BRIGHT, "ipChannel不存在");
		}
		
		
		
	}

	@Override
	public void run() {
		this.check();
		
	}
	
	
	
	
	
	
}
