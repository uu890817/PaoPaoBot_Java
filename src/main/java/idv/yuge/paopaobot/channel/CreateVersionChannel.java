package idv.yuge.paopaobot.channel;

import java.util.ArrayList;
import java.util.List;

import idv.yuge.paopaobot.util.ConsoleColors;
import idv.yuge.paopaobot.util.PrintOut;
import idv.yuge.paopaobot.util.StaticValue;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.channel.concrete.VoiceChannel;
import net.dv8tion.jda.api.requests.restaction.ChannelAction;

public class CreateVersionChannel {

	public static VoiceChannel create(Long categoryId){
		JDA jda = StaticValue.getJda();
		Guild guild = jda.getGuildById(StaticValue.getBotConfig().getGuildId());
		
		String name;
		if(StaticValue.isServerOnline()) {
			 name = "遊戲版本 : " + StaticValue.getServerVersion();
		}else {
			name = "遊戲版本 : " + "🔴離線";
		}

		ChannelAction<VoiceChannel> versionChannel = guild.createVoiceChannel(name, guild.getCategoryById(categoryId));
//		List<Permission> allow = new ArrayList<>();
		List<Permission> deny = new ArrayList<>();
		deny.add(Permission.VOICE_CONNECT);
		versionChannel.addPermissionOverride(guild.getPublicRole(), null, deny);
		
		VoiceChannel createdVersionChannel = versionChannel.setPosition(5).complete();
		
		PrintOut.timePrintln(ConsoleColors.YELLOW_BOLD_BRIGHT, "已建立\"Version\"頻道");
		
		
		return createdVersionChannel;
	}
}
