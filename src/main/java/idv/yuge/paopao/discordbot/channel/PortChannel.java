package idv.yuge.paopao.discordbot.channel;

import java.util.ArrayList;
import java.util.List;

import idv.yuge.paopao.discordbot.DiscordBotFactory;
import idv.yuge.paopao.sqlite.service.SettingService;
import idv.yuge.paopao.util.FormatPrint;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.channel.concrete.Category;
import net.dv8tion.jda.api.entities.channel.concrete.VoiceChannel;
import net.dv8tion.jda.api.requests.restaction.ChannelAction;

public class PortChannel {
	JDA bot = DiscordBotFactory.getBot();
	Guild guild = StaticGuild.getGuild();
	
	public VoiceChannel getChannel() {
		VoiceChannel channel = guild.getVoiceChannelById(StaticChannelId.getPortChannelId());
		if(channel != null) {
			StaticChannelId.setPortChannelId(channel.getId());
			StaticChannelName.setPortChannelName(channel.getName());
			return channel;
		}
		InfoChannel infoChannel = new InfoChannel();
		return createChannel(infoChannel.getChannel());
	}
	
	public boolean isAlive() {
		VoiceChannel portChannel = guild.getVoiceChannelById(StaticChannelId.getPortChannelId());
		if (portChannel != null) {
			StaticChannelName.setIpChannelName(portChannel.getName());
			return true;
		}
		return false;
	}
	
	public VoiceChannel createChannel(Category category) {
		SettingService ss = new SettingService();
		String portName = "Port : " + ss.selectByKey("Ip_Port").getValue();
		ChannelAction<VoiceChannel> portAction = guild.createVoiceChannel(portName, category);
		List<Permission> permission = new ArrayList<>();
		permission.add(Permission.VOICE_CONNECT);
		portAction.addPermissionOverride(guild.getPublicRole(), null, permission);
		portAction.setPosition(1);
		
		VoiceChannel portChannel = portAction.complete();
		
		ss.updateSetting("Port_Channel_Id", portChannel.getId());
		StaticChannelId.setPortChannelId(portChannel.getId());
		StaticChannelName.setPortChannelName(portChannel.getName());
		FormatPrint.timePrintln("建立Port頻道");

		return portChannel;
	}
	
	public void renameChannel() {
		VoiceChannel channel = getChannel();
		if (channel != null) {
			channel.delete().complete();
			FormatPrint.timePrintln("刪除Port頻道");

		}
		InfoChannel infoChannel = new InfoChannel();
		createChannel(infoChannel.getChannel());
	}
}
