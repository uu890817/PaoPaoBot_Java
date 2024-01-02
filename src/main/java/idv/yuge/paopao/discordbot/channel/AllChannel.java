package idv.yuge.paopao.discordbot.channel;

import idv.yuge.paopao.util.FormatPrint;

public class AllChannel {

	InfoChannel info = new InfoChannel();
	IpChannel ip = new IpChannel();
	PortChannel port = new PortChannel();
	OnlineChannel online = new OnlineChannel();
	PlayerChannel player = new PlayerChannel();
	VersionChannel version = new VersionChannel();
	
	public void getAllChannelInfo() {
		info.getChannel();
		ip.getChannel();
		port.getChannel();
		online.getChannel();
		player.getChannel();
		version.getChannel();
		FormatPrint.timePrintln("初始化頻道資訊完成");
	}
	
}
