package idv.yuge.paopaobot.channel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import idv.yuge.paopaobot.gson.BotConfig;
import idv.yuge.paopaobot.gson.BotConfigBean;
import idv.yuge.paopaobot.util.ConsoleColors;
import idv.yuge.paopaobot.util.PrintOut;
import idv.yuge.paopaobot.util.StaticValue;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.channel.concrete.Category;
import net.dv8tion.jda.api.entities.channel.concrete.VoiceChannel;
import net.dv8tion.jda.api.requests.restaction.ChannelAction;

public class CreatePortChannel {

	public static VoiceChannel create(Long categoryId){
		JDA jda = StaticValue.getJda();
		Guild guild = jda.getGuildById(StaticValue.getBotConfig().getGuildId());

		String name = "Port : " + StaticValue.getPort();
		ChannelAction<VoiceChannel> portChannel = guild.createVoiceChannel(name, guild.getCategoryById(categoryId));
//		List<Permission> allow = new ArrayList<>();
		List<Permission> deny = new ArrayList<>();
		deny.add(Permission.VOICE_CONNECT);
		portChannel.addPermissionOverride(guild.getPublicRole(), null, deny);
		
		VoiceChannel createdportChannel = portChannel.setPosition(2).complete();
		
		PrintOut.timePrintln(ConsoleColors.YELLOW_BOLD_BRIGHT, "已建立\"Port\"頻道");
		
		
		return createdportChannel;
	}
}
