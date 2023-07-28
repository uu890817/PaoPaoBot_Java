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
import net.dv8tion.jda.api.requests.restaction.ChannelAction;

public class CreateCategory {
	
	public static Category create(){
		JDA jda = StaticValue.getJda();
		Guild guild = jda.getGuildById(StaticValue.getBotConfig().getGuildId());

		ChannelAction<Category> category = guild.createCategory("------------伺服器狀態------------");
		
//		List<Permission> allow = new ArrayList<>();
		List<Permission> deny = new ArrayList<>();
		deny.add(Permission.VOICE_CONNECT);
		category.addPermissionOverride(guild.getPublicRole(), null, deny);
		
		Category createdCategory = category.setPosition(0).complete();
		
		PrintOut.timePrintln(ConsoleColors.YELLOW_BOLD_BRIGHT, "已建立\"伺服器狀態\"分類");
		
		return createdCategory;
	}
}
