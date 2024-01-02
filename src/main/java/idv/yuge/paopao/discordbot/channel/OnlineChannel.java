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

public class OnlineChannel {
	JDA bot = DiscordBotFactory.getBot();
	Guild guild = StaticGuild.getGuild();

	public VoiceChannel getChannel() {
		VoiceChannel channel = guild.getVoiceChannelById(StaticChannelId.getOnlineChannelId());
		if(channel != null) {
			StaticChannelId.setOnlineChannelId(channel.getId());
			StaticChannelName.setOnlineChannelName(channel.getName());
			return channel;
		}
		InfoChannel infoChannel = new InfoChannel();
		return createChannel(infoChannel.getChannel());
	}
	
	public boolean isAlive() {
		VoiceChannel onlineChannel = guild.getVoiceChannelById(StaticChannelId.getOnlineChannelId());
		if (onlineChannel != null) {
			StaticChannelName.setOnlineChannelName(null);
			return true;
		}
		return false;
	}

	public VoiceChannel createChannel(Category category) {
		McInfoBean serverInfo = McInfo.getInfo();
		String onlineName;
		onlineName = "伺服器狀態 : " + "🔴離線";
		if (serverInfo != null && serverInfo.isOnline()) {
			onlineName = "伺服器狀態 : " + "🟢線上";
		} 

		ChannelAction<VoiceChannel> onlineAction = guild.createVoiceChannel(onlineName, category);
		List<Permission> permission = new ArrayList<>();
		permission.add(Permission.VOICE_CONNECT);
		onlineAction.addPermissionOverride(guild.getPublicRole(), null, permission);
		onlineAction.setPosition(2);
		
		VoiceChannel onlineChannel = onlineAction.complete();
		
		SettingService ss = new SettingService();
		ss.updateSetting("Online_Channel_Id", onlineChannel.getId());
		StaticChannelId.setOnlineChannelId(onlineChannel.getId());
		StaticChannelName.setOnlineChannelName(onlineChannel.getName());
		FormatPrint.timePrintln("建立Online頻道");
		return onlineChannel;
	}
	
	public void renameChannel() {
		VoiceChannel channel = getChannel();
		if (channel != null) {
			channel.delete().complete();
			FormatPrint.timePrintln("刪除Online頻道");
		}
		InfoChannel infoChannel = new InfoChannel();
		createChannel(infoChannel.getChannel());
	}

	
	
}
