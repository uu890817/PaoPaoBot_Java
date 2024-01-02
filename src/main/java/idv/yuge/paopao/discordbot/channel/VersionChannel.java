package idv.yuge.paopao.discordbot.channel;

import java.util.ArrayList;
import java.util.List;

import idv.yuge.paopao.McInfo.McInfo;
import idv.yuge.paopao.McInfo.McInfoBean;
import idv.yuge.paopao.discordbot.DiscordBotFactory;
import idv.yuge.paopao.sqlite.service.SettingService;
import idv.yuge.paopao.util.FormatPrint;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.channel.concrete.Category;
import net.dv8tion.jda.api.entities.channel.concrete.VoiceChannel;
import net.dv8tion.jda.api.requests.restaction.ChannelAction;

public class VersionChannel {
	JDA bot = DiscordBotFactory.getBot();
	Guild guild = StaticGuild.getGuild();
	
	public VoiceChannel getChannel() {
		VoiceChannel channel = guild.getVoiceChannelById(StaticChannelId.getVersionChannelId());
		if(channel != null) {
			StaticChannelId.setVersionChannelId(channel.getId());
			StaticChannelName.setVersionChannelName(channel.getName());
			return channel;
		}
		InfoChannel infoChannel = new InfoChannel();
		return createChannel(infoChannel.getChannel());
	}
	
	public boolean isAlive() {
		VoiceChannel versionChannel = guild.getVoiceChannelById(StaticChannelId.getVersionChannelId());
		if (versionChannel != null) {
			StaticChannelName.setIpChannelName(versionChannel.getName());
			return true;
		}
		return false;
	}
	
	public VoiceChannel createChannel(Category category) {
		McInfoBean serverInfo = McInfo.getInfo();
		String versionName;
		versionName = "遊戲版本 : " + "🔴離線";
		if (serverInfo != null && serverInfo.isOnline()) {
			versionName = "遊戲版本 : " + serverInfo.getGameVersion();
		}

		ChannelAction<VoiceChannel> versionAction = guild.createVoiceChannel(versionName, category);
		List<Permission> permission = new ArrayList<>();
		permission.add(Permission.VOICE_CONNECT);
		versionAction.addPermissionOverride(guild.getPublicRole(), null, permission);
		versionAction.setPosition(4);
		
		VoiceChannel versionChannel = versionAction.complete();
		SettingService ss = new SettingService();
		ss.updateSetting("Version_Channel_Id", versionChannel.getId());
		StaticChannelId.setVersionChannelId(versionChannel.getId());
		StaticChannelName.setVersionChannelName(versionChannel.getName());
		FormatPrint.timePrintln("建立Version頻道");

		return versionChannel;
	}
	
	public void renameChannel() {
		VoiceChannel channel = getChannel();
		if (channel != null) {
			channel.delete().complete();
			FormatPrint.timePrintln("刪除Version頻道");

		}
		InfoChannel infoChannel = new InfoChannel();
		createChannel(infoChannel.getChannel());
	}
}
