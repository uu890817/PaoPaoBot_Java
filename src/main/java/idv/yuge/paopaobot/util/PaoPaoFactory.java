package idv.yuge.paopaobot.util;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class PaoPaoFactory {

	public static JDA getBot() {
		
		if (StaticValue.getBotConfig().getBotToken() == null) {
			getBot();
		}
		
		JDABuilder bot = JDABuilder.createDefault(StaticValue.getBotConfig().getBotToken());
		
			bot.setStatus(OnlineStatus.ONLINE);
		bot.setActivity(Activity.watching("Minecraft Server"));

		bot.enableIntents(GatewayIntent.DIRECT_MESSAGES, GatewayIntent.MESSAGE_CONTENT);

		JDA jda = null;
		try {
			jda = bot.build().awaitReady();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		StaticValue.setJda(jda);
		PrintOut.timePrintln(ConsoleColors.GREEN_UNDERLINED, "機器人啟動");
		return jda;
	}

}
