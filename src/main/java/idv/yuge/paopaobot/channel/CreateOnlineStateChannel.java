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

public class CreateOnlineStateChannel {

	public static VoiceChannel create(Long categoryId){
		JDA jda = StaticValue.getJda();
		Guild guild = jda.getGuildById(StaticValue.getBotConfig().getGuildId());
		
		String name;
		if(StaticValue.isServerOnline()) {
			name = "伺服器狀態 : " + "🟢線上" ;
		}else {
			name = "伺服器狀態 : " + "🔴離線" ;
		}
		
		ChannelAction<VoiceChannel> ipChannel = guild.createVoiceChannel(name, guild.getCategoryById(categoryId));
//		List<Permission> allow = new ArrayList<>();
		List<Permission> deny = new ArrayList<>();
		deny.add(Permission.VOICE_CONNECT);
		ipChannel.addPermissionOverride(guild.getPublicRole(), null, deny);
		
		VoiceChannel createdIpChannel = ipChannel.setPosition(3).complete();
		
		PrintOut.timePrintln(ConsoleColors.YELLOW_BOLD_BRIGHT, "已建立\"伺服器狀態\"頻道");
		
		
		return createdIpChannel;
	}
	
	
	
}
