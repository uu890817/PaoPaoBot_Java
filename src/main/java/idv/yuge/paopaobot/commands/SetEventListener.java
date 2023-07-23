package idv.yuge.paopaobot.commands;

import net.dv8tion.jda.api.JDA;

public class SetEventListener {
	
	private JDA bot = null;
	
	public SetEventListener(JDA bot) {
		this.bot = bot;
	}

	public void setAllCommand() {
		bot.addEventListener(new Ping());
	}
	
	
	
	

}
