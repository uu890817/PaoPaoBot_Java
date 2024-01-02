package idv.yuge.paopao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import idv.yuge.paopao.discordbot.DiscordBotFactory;
import idv.yuge.paopao.discordbot.channel.AllChannel;
import idv.yuge.paopao.discordbot.channel.StaticChannelId;
import idv.yuge.paopao.discordbot.channel.StaticGuild;
import idv.yuge.paopao.sqlite.SQLiteFactory;

public class PaoPao {

	private static final Logger logger = LoggerFactory.getLogger(PaoPao.class);

	public static void main(String[] args) throws InterruptedException {
		logger.info("啟動中...");

//		logger.error("err");
//		logger.warn("warn");
//		logger.info("info");
//		logger.debug("debug");

		
		SQLiteFactory.startCheck();
		StaticChannelId.getAllIdFromDB();

		DiscordBotFactory.startBot();
		StaticGuild.getGuildFromBot();

		AllChannel allChannel = new AllChannel();
		allChannel.getAllChannelInfo();

	
		
		
		
		
		
	}

}
