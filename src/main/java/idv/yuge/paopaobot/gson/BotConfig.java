package idv.yuge.paopaobot.gson;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import idv.yuge.paopaobot.util.ConsoleColors;
import idv.yuge.paopaobot.util.PrintOut;
import idv.yuge.paopaobot.util.StaticValue;

public class BotConfig {
	public static void read() {
		PrintOut.timePrintln(ConsoleColors.GREEN_UNDERLINED, "設定檔讀取中");
		
		try(FileReader fileReader = new FileReader("./config.json")){
			
		}catch(IOException e) {
			PrintOut.timePrintln(ConsoleColors.RED_BOLD_BRIGHT, "找不到設定檔");
		}
		
		
		
		
        try (FileReader fileReader = new FileReader("./config.json")) {
            // 初始化 Gson 物件
            Gson gson = new Gson();

            // 使用 Gson 將 JSON 資料轉換為 Config 物件
            BotConfigBean config = gson.fromJson(fileReader, BotConfigBean.class);

            
            
            StaticValue.setBotConfig(config);
            
            
            // 使用讀取到的 Config 物件進行後續操作
            System.out.println("BotToken: " + config.getBotToken());
            System.out.println("GuildId: " + config.getGuildId());
            System.out.println("BotOwner: " + config.getBotOwner());
            System.out.println("Master: " + config.getMaster());
            System.out.println("ChannelId: " + config.getChannelId());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JsonSyntaxException e) {
            System.err.println("JSON 格式錯誤：" + e.getMessage());
        }
	}
	public static void write() {
		try{

            // 使用 Gson 將 JSON 資料轉換為 Config 物件
            BotConfigBean config = StaticValue.getBotConfig();

            // 使用 GsonBuilder 創建 Gson 物件，以便更美觀地輸出 JSON
            Gson gsonWithPrettyPrinting = new GsonBuilder().setPrettyPrinting().create();

            // 將修改後的 Config 物件轉換回 JSON 格式
            String updatedJson = gsonWithPrettyPrinting.toJson(config);

            // 將 JSON 寫回原來的檔案
            try (FileWriter fileWriter = new FileWriter("./config.json")) {
                fileWriter.write(updatedJson);
            }

            System.out.println("JSON 檔案已更新！");
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	
	public static void createNewConfigFile() {
		try{
			
			BotConfigBean config = new BotConfigBean();

            // 使用 GsonBuilder 創建 Gson 物件，以便更美觀地輸出 JSON
            Gson gsonWithPrettyPrinting = new GsonBuilder().setPrettyPrinting().create();

            // 將修改後的 Config 物件轉換回 JSON 格式
            String updatedJson = gsonWithPrettyPrinting.toJson(config);

            // 將 JSON 寫回原來的檔案
            try (FileWriter fileWriter = new FileWriter("./config.json")) {
                fileWriter.write(updatedJson);
            }

            System.out.println("JSON 檔案已更新！");
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}

