package idv.yuge.paopaobot.util;


import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class PaoPaoFactory {	
	
	public static JDA getBot() {		
		JDABuilder bot = JDABuilder.createDefault(DotenvFactory.getToken());
		bot.setStatus(OnlineStatus.ONLINE);
		bot.setActivity(Activity.watching("Minecraft Server"));
		
		bot.enableIntents(
				GatewayIntent.DIRECT_MESSAGES,
				GatewayIntent.MESSAGE_CONTENT
				);
		
		

		return bot.build();
	}
	
}
