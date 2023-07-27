package idv.yuge.paopaobot.create;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import idv.yuge.paopaobot.gson.BotConfig;
import idv.yuge.paopaobot.gson.BotConfigBean;
import idv.yuge.paopaobot.util.PrintOut;
import idv.yuge.paopaobot.util.StaticValue;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.channel.concrete.Category;
import net.dv8tion.jda.api.requests.restaction.ChannelAction;

public class CreateIpChannel {
//
//	public Category createIpChannel(){
//		JDA jda = StaticValue.getJda();
//		Guild guild = jda.getGuildById(StaticValue.getBotConfig().getGuildId());
//
//		ChannelAction<Category> category = guild.createVoiceChannel(null, null);
//		
////		List<Permission> allow = new ArrayList<>();
//		List<Permission> deny = new ArrayList<>();
//		deny.add(Permission.VOICE_CONNECT);
//		category.addPermissionOverride(guild.getPublicRole(), null, deny);
//		
//		Category createdCategory = category.complete();
//		
//		PrintOut.timePrintln(categoryName, "已建立\"伺服器狀態\"分類");
//		
//		BotConfigBean config = StaticValue.getBotConfig();
//		Map<String, Long> channelId = config.getChannelId();
//		channelId.put("Category", createdCategory.getIdLong());
//		StaticValue.setBotConfig(config);
//		BotConfig.write();
//		
//		return createdCategory;
//	}
}
