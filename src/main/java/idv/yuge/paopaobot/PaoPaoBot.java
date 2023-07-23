package idv.yuge.paopaobot;

import idv.yuge.paopaobot.commands.SetEventListener;
import idv.yuge.paopaobot.util.IpFactory;
import idv.yuge.paopaobot.util.PaoPaoFactory;
import net.dv8tion.jda.api.JDA;

public class PaoPaoBot {

	public static void main(String[] args) {
		JDA bot = PaoPaoFactory.getBot();

		SetEventListener listener = new SetEventListener(bot);
		listener.setAllCommand();

		System.out.println(IpFactory.getIp());

	}

}
