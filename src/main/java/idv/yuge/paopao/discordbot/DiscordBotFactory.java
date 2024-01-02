package idv.yuge.paopao.discordbot;

import idv.yuge.paopao.sqlite.service.SettingService;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

public class DiscordBotFactory {
	private static JDA bot = null;
	
	// 私有建構子，防止外部實例化
	private DiscordBotFactory() {
    }
	
	public static void startBot() throws InterruptedException {
		SettingService ss = new SettingService();
		
		JDABuilder jdaBuilder = JDABuilder.createDefault(ss.selectByKey("Token").getValue()).setActivity(Activity.watching("Minecraft Server")).setAutoReconnect(true);
		
		jdaBuilder.addEventListeners(new BotListener());
		jdaBuilder.addEventListeners(new SlashCommands());
		bot = jdaBuilder.build();
	
		bot.awaitReady();
		
		SlashCommands slashCommands = new SlashCommands();
		slashCommands.setSlachCommands();
		
	}
	
	public static JDA getBot() {
		return bot;
	}
	
}
