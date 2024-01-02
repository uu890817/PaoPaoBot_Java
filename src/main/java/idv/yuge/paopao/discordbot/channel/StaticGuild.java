package idv.yuge.paopao.discordbot.channel;

import idv.yuge.paopao.discordbot.DiscordBotFactory;
import idv.yuge.paopao.util.FormatPrint;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;

public class StaticGuild {
	private static Guild guild;
	private static TextChannel messageChannel;
	
	private StaticGuild() {};
	
	public static void getGuildFromBot() {
		JDA bot = DiscordBotFactory.getBot();
		
		guild = bot.getGuildById(StaticChannelId.getGuildId());
		FormatPrint.timePrintln("機器人作用伺服器為:" + guild.getName());
		messageChannel = bot.getTextChannelById(StaticChannelId.getMessageChannelId());
		FormatPrint.timePrintln("機器人發話頻道為:" + messageChannel.getName());
	}

	public static Guild getGuild() {
		return guild;
	}

	public static TextChannel getMessageChannel() {
		return messageChannel;
	}


}
