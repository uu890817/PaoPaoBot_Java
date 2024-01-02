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

public class PlayerChannel {
	JDA bot = DiscordBotFactory.getBot();
	Guild guild = StaticGuild.getGuild();
	
	public VoiceChannel getChannel() {
		VoiceChannel channel = guild.getVoiceChannelById(StaticChannelId.getPlayerChannelId());
		if(channel != null) {
			StaticChannelId.setPlayerChannelId(channel.getId());
			StaticChannelName.setPlayerChannelName(channel.getName());
			return channel;
		}
		InfoChannel infoChannel = new InfoChannel();
		return createChannel(infoChannel.getChannel());
	}
	
	public boolean isAlive() {
		VoiceChannel playerChannel = guild.getVoiceChannelById(StaticChannelId.getPlayerChannelId());
		if (playerChannel != null) {
			StaticChannelName.setIpChannelName(playerChannel.getName());
			return true;
		}
		return false;
	}
	
	public VoiceChannel createChannel(Category category) {
		McInfoBean serverInfo = McInfo.getInfo();
		String playerName;
		playerName = "線上人數 : " + "🔴離線";
		if (serverInfo != null && serverInfo.isOnline()) {
			playerName = "線上人數 : " + serverInfo.getOnlinePlayer() + "/" + serverInfo.getMaxPlayer();
		} 

		ChannelAction<VoiceChannel> playerAction = guild.createVoiceChannel(playerName, category);
		List<Permission> permission = new ArrayList<>();
		permission.add(Permission.VOICE_CONNECT);
		playerAction.addPermissionOverride(guild.getPublicRole(), null, permission);
		playerAction.setPosition(3);
		VoiceChannel playerChannel = playerAction.complete();
		
		SettingService ss = new SettingService();
		ss.updateSetting("Player_Channel_Id", playerChannel.getId());
		StaticChannelId.setPlayerChannelId(playerChannel.getId());
		StaticChannelName.setPlayerChannelName(playerChannel.getName());
		FormatPrint.timePrintln("建立Player頻道");
		return playerChannel;
	}
	
	public void renameChannel() {
		VoiceChannel channel = getChannel();
		if (channel != null) {
			channel.delete().complete();
			FormatPrint.timePrintln("刪除Player頻道");

		}
		InfoChannel infoChannel = new InfoChannel();
		createChannel(infoChannel.getChannel());
	}
}
