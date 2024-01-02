package idv.yuge.paopao.discordbot.channel;

import java.util.ArrayList;
import java.util.List;

import idv.yuge.paopao.McInfo.GetIpAddr;
import idv.yuge.paopao.discordbot.DiscordBotFactory;
import idv.yuge.paopao.sqlite.service.SettingService;
import idv.yuge.paopao.util.FormatPrint;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.channel.concrete.Category;
import net.dv8tion.jda.api.entities.channel.concrete.VoiceChannel;
import net.dv8tion.jda.api.requests.restaction.ChannelAction;

public class IpChannel {

	JDA bot = DiscordBotFactory.getBot();
	Guild guild = StaticGuild.getGuild();

	public VoiceChannel getChannel() {
		VoiceChannel channel = guild.getVoiceChannelById(StaticChannelId.getIpChannelId());
		
		if(channel != null) {
			StaticChannelId.setIpChannelId(channel.getId());
			StaticChannelName.setIpChannelName(channel.getName());
			return channel;
		}
		InfoChannel infoChannel = new InfoChannel();
		return createChannel(infoChannel.getChannel());
	}

	public boolean isAlive() {
		VoiceChannel ipChannel = guild.getVoiceChannelById(StaticChannelId.getInfoChannelId());
		if (ipChannel != null) {
			StaticChannelName.setIpChannelName(ipChannel.getName());
			return true;
		}
		return false;
	}

	public VoiceChannel createChannel(Category category) {
		String ipName;
		if (GetIpAddr.getIp() != null) {
			ipName = "IP : " + GetIpAddr.getIp();
		} else {
			ipName = "IP : " + "尚未取得IP";
		}
		ChannelAction<VoiceChannel> ipAction = guild.createVoiceChannel(ipName, category);
		List<Permission> permission = new ArrayList<>();
		permission.add(Permission.VOICE_CONNECT);
		ipAction.addPermissionOverride(guild.getPublicRole(), null, permission);
		ipAction.setPosition(0);
		
		VoiceChannel ipChannel = ipAction.complete();
		
		SettingService ss = new SettingService();
		ss.updateSetting("IP_Channel_Id", ipChannel.getId());
		StaticChannelId.setIpChannelId(ipChannel.getId());
		StaticChannelName.setIpChannelName(ipChannel.getName());
		FormatPrint.timePrintln("建立IP頻道");

		return ipChannel;
	}

	public void renameChannel() {
		VoiceChannel channel = getChannel();
		if (channel != null) {
			channel.delete().complete();
			FormatPrint.timePrintln("刪除IP頻道");
		}
		InfoChannel infoChannel = new InfoChannel();
		createChannel(infoChannel.getChannel());
	}

}
