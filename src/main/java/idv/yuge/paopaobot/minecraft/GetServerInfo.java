package idv.yuge.paopaobot.minecraft;

import java.io.IOException;

import br.com.azalim.mcserverping.MCPing;
import br.com.azalim.mcserverping.MCPingOptions;
import br.com.azalim.mcserverping.MCPingResponse;
import br.com.azalim.mcserverping.MCPingResponse.Players;
import br.com.azalim.mcserverping.MCPingResponse.Version;
import idv.yuge.paopaobot.util.ConsoleColors;
import idv.yuge.paopaobot.util.PrintOut;
import idv.yuge.paopaobot.util.StaticValue;

public class GetServerInfo {
	public static void get() {
		GetServerInfo getServerInfo = new GetServerInfo();
		getServerInfo.sleep();
		
		String serverIP = StaticValue.getIp();
		int serverPort = StaticValue.getPort(); // 默认为25565
		
		System.out.println(serverIP);
		MCPingOptions options = MCPingOptions.builder().hostname(serverIP).port(serverPort).build();

		MCPingResponse reply;

		try {
			reply = MCPing.getPing(options);
			PrintOut.timePrintln(ConsoleColors.RED_BOLD_BRIGHT, "在線上");
			StaticValue.setServerOnline(true);
			;
		} catch (IOException ex) {
			PrintOut.timePrintln(ConsoleColors.RED_BOLD_BRIGHT, "不在線上");
			StaticValue.setServerOnline(false);
			return;
		}

//        System.out.println(String.format("Full response from %s:", options.getHostname()));
//        System.out.println();

//        Description description = reply.getDescription();

//        System.out.println("Description:");
//        System.out.println("    Raw: " + description.getText());
//        System.out.println("    No color codes: " + description.getStrippedText());
//        System.out.println();

		Players players = reply.getPlayers();

//        System.out.println("Players: ");
//        System.out.println("    Online count: " + players.getOnline());
//        System.out.println("    Max players: " + players.getMax());
//        System.out.println();

		StaticValue.setOnlinePlayer(players.getOnline());
		StaticValue.setMaxPlayer(players.getMax());
		// Can be null depending on the server
//        List<Player> sample = players.getSample();
//
//        if (sample != null) {
//            System.out.println("    Players: " + players.getSample().stream()
//                    .map(player -> String.format("%s@%s", player.getName(), player.getId()))
//                    .collect(Collectors.joining(", "))
//            );
//            System.out.println();
//        }

		Version version = reply.getVersion();

//        System.out.println("Version: ");
//
//        // The protocol is the version number: http://wiki.vg/Protocol_version_numbers
//        System.out.println("    Protocol: " + version.getProtocol());
//        System.out.println("    Name: " + version.getName());
//        System.out.println();

		StaticValue.setServerVersion(version.getName());

//        System.out.println(String.format("Favicon: %s", reply.getFavicon()));
	}

	public void sleep() {

		if (StaticValue.getIp() == null) {
			try {
				Thread.sleep(1000);
				System.out.println("sleep");
				sleep();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
