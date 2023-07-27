package idv.yuge.paopaobot.runnable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import idv.yuge.paopaobot.util.ConsoleColors;
import idv.yuge.paopaobot.util.PrintOut;
import idv.yuge.paopaobot.util.StaticValue;

public class RunnableIp implements Runnable {

	
	public static String getIp() {
		try {
            // 發送 HTTP 請求並讀取回應
            URL url = new URL("https://api.ipify.org/");
            URLConnection connection = url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // 解析回應取得外網 IP
            String ip = response.toString();
            StaticValue.setIp(ip);
            if(ip != null) {
            	PrintOut.timePrintln(ConsoleColors.CYAN, "取得IP: " + ip);
            	return ip;
            }
            

        } catch (IOException e) {
            e.printStackTrace();
        }
		return null;
	}
	
	public static void autoGetIp() {
		ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(new RunnableIp(), 0, 5, TimeUnit.MINUTES); // 每隔 30秒10 分鐘執行一次

	}
	
	@Override
	public void run() {
		getIp();
	}

}
