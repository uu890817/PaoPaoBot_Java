package idv.yuge.paopaobot;

import idv.yuge.paopaobot.commands.CommandManager;
import idv.yuge.paopaobot.create.AutoCheckChannelAlive;
import idv.yuge.paopaobot.create.CreateCategory;
import idv.yuge.paopaobot.gson.BotConfig;
import idv.yuge.paopaobot.runnable.RunnableIp;
import idv.yuge.paopaobot.util.PaoPaoFactory;
import idv.yuge.paopaobot.util.StaticValue;

public class PaoPaoBot {

	public static void main(String[] args) {
		BotConfig.read();
		
		RunnableIp.autoGetIp();

		PaoPaoFactory.getBot();
		CommandManager.addCommand();
		
		AutoCheckChannelAlive a = new AutoCheckChannelAlive();
		a.check();
//		CreateCategory c = new CreateCategory();
//		c.createCategory();
	}

}
