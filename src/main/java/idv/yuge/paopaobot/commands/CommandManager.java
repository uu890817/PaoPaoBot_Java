package idv.yuge.paopaobot.commands;

import idv.yuge.paopaobot.util.ConsoleColors;
import idv.yuge.paopaobot.util.PrintOut;
import idv.yuge.paopaobot.util.StaticValue;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class CommandManager extends ListenerAdapter{
	
	public static void addCommand() {
		JDA jda = StaticValue.getJda();
		
		jda.addEventListener(new RefreshIp());
		
		
		
		PrintOut.timePrintln(ConsoleColors.GREEN_UNDERLINED, "指令註冊完成");
	}
	
	
	
	
	
	
	
	
}
