package idv.yuge.paopao.mcservercontroller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import idv.yuge.paopao.util.FormatPrint;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class ServerBackup {

	public void start(SlashCommandInteractionEvent event) {
		Thread thread = new Thread(() -> {
			startBackupServer(event);
			
        });
		thread.start();
	}
	
	private boolean startBackupServer(SlashCommandInteractionEvent event) {
		ServerController.shutdownServer();
		if(ServerController.getServerProcess() != null) {
			event.getHook().editOriginal("⌛ 關閉伺服器中...").queue();
		}
		event.getHook().editOriginal("⌛ 沒有管控的伺服器").queue();
		
		String sourceDirectory = "./server/world";

		File serverFile = new File(sourceDirectory);
		if(!serverFile.exists()) {
			event.getHook().editOriginal("❌ 沒有可備份的資料夾").queue();
			return false;
		}
		// 創建目標目錄
		File backupDirectory = new File("./backup");
		if (!backupDirectory.exists()) {
		    backupDirectory.mkdirs(); // 創建目錄及其上層目錄（如果不存在）
		}
		// 目標 ZIP 檔案
		String zipFileName = "world-" + getFormattedDateTime() + ".zip";
		String zipFilePath = "./backup/" + zipFileName;

		try {
			// 創建 ZIP 檔案
			event.getHook().editOriginal("⌛ 伺服器備份中...").queue();
			createZipFile(sourceDirectory, zipFilePath);
			event.getHook().editOriginal("✅ 伺服器備份成功").queue();
			FormatPrint.timePrintln("備份檔案已創建成功！");
		} catch (IOException e) {
			e.printStackTrace();
			event.getHook().editOriginal("❌ 備份失敗").queue();
			FormatPrint.timePrintlnErr("備份檔案創建失敗！");
			return false;
		}
		
		ServerController.startServer();
		event.getHook().editOriginal("✅ 備份完成，重新啟動伺服器中，請過幾分鐘後再進入遊戲。").queue();
		return true;

	}

	private static String getFormattedDateTime() {
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm");
		return now.format(formatter);
	}

	private static void createZipFile(String sourceDirectory, String zipFilePath) throws IOException {
		// 創建輸出流
		FileOutputStream fos = new FileOutputStream(zipFilePath);
		ZipOutputStream zipOut = new ZipOutputStream(fos);

		// 壓縮目錄
		File fileToZip = new File(sourceDirectory);
		zipFile(fileToZip, fileToZip.getName(), zipOut);

		// 關閉流
		zipOut.close();
		fos.close();
	}

	private static void zipFile(File fileToZip, String fileName, ZipOutputStream zipOut) throws IOException {
		if (fileToZip.isHidden()) {
			return;
		}
		if (fileToZip.isDirectory()) {
			File[] children = fileToZip.listFiles();
			for (File childFile : children) {
				zipFile(childFile, fileName + "/" + childFile.getName(), zipOut);
			}
		} else {
			FileInputStream fis = new FileInputStream(fileToZip);
			ZipEntry zipEntry = new ZipEntry(fileName);
			zipOut.putNextEntry(zipEntry);

			byte[] bytes = new byte[1024];
			int length;
			while ((length = fis.read(bytes)) >= 0) {
				zipOut.write(bytes, 0, length);
			}

			fis.close();
		}
	}
}
