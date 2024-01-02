package idv.yuge.paopao.McInfo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.CompletableFuture;

import idv.yuge.paopao.discordbot.channel.IpChannel;
import idv.yuge.paopao.discordbot.channel.StaticChannelName;
import idv.yuge.paopao.util.FormatPrint;

public class GetIpAddr {
	private static String oldIp;
	
	public static CompletableFuture<String> getIpAsync() {
	    return CompletableFuture.supplyAsync(() -> {
	    	FormatPrint.timePrintln("讀取IP中...");
	        try {
	            URL url = new URL("https://api.ipify.org/");

	            // 開啟 HTTP 連線
	            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

	            // 設定連線超時時間（毫秒）
	            connection.setConnectTimeout(2000); // 這裡設定為2秒

	            // 設定讀取超時時間（毫秒）
	            connection.setReadTimeout(2000); // 這裡設定為2秒

	            connection.setRequestMethod("GET");

	            // 讀取 API 回傳的資料
	            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
	                StringBuilder response = new StringBuilder();
	                String line;

	                while ((line = reader.readLine()) != null) {
	                    response.append(line);
	                }
	                oldIp = response.toString();
	                String newChannelString = "IP : " + oldIp;
	                
	                if(!newChannelString.equals(StaticChannelName.getIpChannelName())) {
	                	IpChannel ipChannel = new IpChannel();
	                	ipChannel.renameChannel();
	                }
	                
	                return response.toString();
	            } finally {
	                connection.disconnect(); // 關閉連線
	            }

	        } catch (Exception e) {
	        	FormatPrint.timePrintlnErr("本次讀取IP超時，顯示的為上次讀取的IP");
	        }
			return oldIp;
	    });
	}
	
	
	
	
	public static String getIp() {
		return oldIp;
	}
	
}
