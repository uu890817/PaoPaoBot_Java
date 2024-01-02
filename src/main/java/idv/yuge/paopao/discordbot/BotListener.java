package idv.yuge.paopao.discordbot;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import idv.yuge.paopao.McInfo.GetIpAddr;
import idv.yuge.paopao.McInfo.McInfo;
import idv.yuge.paopao.util.FormatPrint;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.events.session.SessionDisconnectEvent;
import net.dv8tion.jda.api.events.session.SessionResumeEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class BotListener extends ListenerAdapter {

	ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);
	public ScheduledFuture<?> ipFuture;
	public ScheduledFuture<?> mcInfoFuture;
	
	public void startIpFuture() {
		FormatPrint.timePrintln("設定每60秒抓取IP");
		ipFuture = scheduler.scheduleAtFixedRate(() -> {
			CompletableFuture<String> getIpFuture = GetIpAddr.getIpAsync();

			getIpFuture.thenAccept(externalIp -> {
				FormatPrint.timePrintln("取得IP: " + externalIp);

			}).join();

		}, 0, 60, TimeUnit.SECONDS);
	}
	
	
	public void startMcInfoFuture() {
		FormatPrint.timePrintln("設定每30秒抓取伺服器資訊");

		mcInfoFuture = scheduler.scheduleAtFixedRate(() -> {
			McInfo.getServerInfo();

		}, 10, 30, TimeUnit.SECONDS);
	}
	
	
	@Override
	public void onReady(ReadyEvent event) {
		FormatPrint.timePrintln(event.getJDA().getSelfUser().getName() + "已上線!!!");
		startIpFuture();
		startMcInfoFuture();
		

		

	}

	@Override
	public void onSessionDisconnect(SessionDisconnectEvent event) {
		FormatPrint.timePrintlnErr("機器人斷線");
		ipFuture.cancel(true);
		ipFuture=null;
		mcInfoFuture.cancel(true);
		mcInfoFuture = null;
	}

	@Override
	public void onSessionResume(SessionResumeEvent event) {
		FormatPrint.timePrintlnErr("機器人恢復連線");
		startIpFuture();
		startMcInfoFuture();
	}

}
