package idv.yuge.paopao.discordbot.channel;

import java.util.ArrayList;
import java.util.List;

import idv.yuge.paopao.discordbot.DiscordBotFactory;
import idv.yuge.paopao.sqlite.service.SettingService;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.channel.concrete.Category;
import net.dv8tion.jda.api.requests.restaction.ChannelAction;

public class InfoChannel {
	
	JDA bot = DiscordBotFactory.getBot();
	Guild guild = StaticGuild.getGuild();

	public Category getChannel() {
		Category channel = guild.getCategoryById(StaticChannelId.getInfoChannelId());
		if(channel != null) {
			return channel;
		}
		return createChannel();
	}
	
	public boolean isAlive() {
		Category infoChannel = guild.getCategoryById(StaticChannelId.getInfoChannelId());
		if (infoChannel != null) {
			StaticChannelName.setInfoChannelName(infoChannel.getName());
			return true;
		}
		return false;
	}

	public Category createChannel() {
		ChannelAction<Category> categoryAction = guild.createCategory("------------伺服器狀態------------");
		
		List<Permission> permission = new ArrayList<>();
		permission.add(Permission.VOICE_CONNECT);
		categoryAction.addPermissionOverride(guild.getPublicRole(), null, permission);
		categoryAction.setPosition(0);
		
		Category category = categoryAction.complete();
		SettingService ss = new SettingService();
		ss.updateSetting("Info_Channel_Id", category.getId());
		StaticChannelId.setInfoChannelId(category.getId());
		StaticChannelName.setInfoChannelName(category.getName());
		return category;
	}
	
	
	

	
}

