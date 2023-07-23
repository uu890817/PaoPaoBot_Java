package idv.yuge.paopaobot.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class IpFactory {

    private static String ip = null;

    static {
        // 使用 ScheduledExecutorService 執行定時任務
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(new AutoGetIp(), 0, 10, TimeUnit.MINUTES); // 每隔 10 分鐘執行一次
    }
    public static String getIp() {
        return ip;
    }

    public static void setIp(String ip) {
        IpFactory.ip = ip;
    }

    private static class AutoGetIp implements Runnable {
        @Override
        public void run() {
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
                String json = response.toString();
                IpFactory.setIp(json);
                System.out.println("132" + json);
                
                System.out.println("456" + IpFactory.getIp());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
