package idv.yuge.paopao.McInfo;

import br.com.azalim.mcserverping.MCPing;
import br.com.azalim.mcserverping.MCPingOptions;
import br.com.azalim.mcserverping.MCPingResponse;
import idv.yuge.paopao.discordbot.channel.OnlineChannel;
import idv.yuge.paopao.discordbot.channel.PlayerChannel;
import idv.yuge.paopao.discordbot.channel.StaticChannelName;
import idv.yuge.paopao.discordbot.channel.StaticGuild;
import idv.yuge.paopao.discordbot.channel.VersionChannel;
import idv.yuge.paopao.sqlite.service.SettingService;
import idv.yuge.paopao.util.FormatPrint;

public class McInfo {

	private static McInfoBean info;
	private static int offlineCount = 0;

	public static McInfoBean getInfo() {
		return info;
	}

	public static McInfoBean getServerInfo() {
		FormatPrint.timePrintln("讀取伺服器狀態中...");
		
		String ip = GetIpAddr.getIp();
		if (ip == null) {
			FormatPrint.timePrintlnErr("尚未取得IP");
			return null;
		}
//		ip = "114.27.136.47";
//		"114.27.136.47"
		SettingService ss = new SettingService();
		String portStr = ss.selectByKey("Ip_Port").getValue();
		int parseInt = Integer.parseInt(portStr);

		try {
			MCPingOptions options = MCPingOptions.builder().hostname(ip).port(parseInt).timeout(1000).build();
			McInfoBean newInfo = new McInfoBean();
			MCPingResponse data = null;

			data = MCPing.getPing(options);
//				System.out.println(data.getDescription().getStrippedText() + "  --  " + data.getPlayers().getOnline() + "/"
//						+ data.getPlayers().getMax() + "  --  " + data.getVersion().getName());
			FormatPrint.timePrintln("伺服器在線上");

			newInfo.setOnline(true);
			newInfo.setOnlinePlayer(data.getPlayers().getOnline());
			newInfo.setMaxPlayer(data.getPlayers().getMax());
			newInfo.setGameVersion(data.getVersion().getName());
			info = newInfo;

			if (!StaticChannelName.getOnlineChannelName().equals("伺服器狀態 : " + "🟢線上")) {
				new OnlineChannel().renameChannel();
			}

			if (!StaticChannelName.getPlayerChannelName()
					.equals("線上人數 : " + data.getPlayers().getOnline() + "/" + data.getPlayers().getMax())) {
				new PlayerChannel().renameChannel();
			}

			if (!StaticChannelName.getVersionChannelName().equals("遊戲版本 : " + data.getVersion().getName())) {
				new VersionChannel().renameChannel();
			}

			offlineCount = 0;
			return info;

		} catch (Exception e) {
			FormatPrint.timePrintlnErr("讀取伺服器狀態超時，將設定為離線狀態");
			McInfoBean newInfo = new McInfoBean();
			
			newInfo.setOnline(false);
			info = newInfo;	
			offlineCount++;
			if (offlineCount >= 2) {
				if (!StaticChannelName.getOnlineChannelName().equals("伺服器狀態 : " + "🔴離線")) {
					new OnlineChannel().renameChannel();
				}

				if (!StaticChannelName.getPlayerChannelName().equals("線上人數 : " + "🔴離線")) {
					new PlayerChannel().renameChannel();
				}

				if (!StaticChannelName.getVersionChannelName().equals("遊戲版本 : " + "🔴離線")) {
					new VersionChannel().renameChannel();
				}

			}

			if (offlineCount == 4) {
				StaticGuild.getMessageChannel().sendMessage("<@431253739391942656> " + "<@552083728147415056> " + "<@1066552829642276975> " + "伺服器斷線!").queue();
			}

			
			return info;
		}

	}

}
