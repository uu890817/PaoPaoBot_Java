package idv.yuge.paopao.mcservercontroller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import idv.yuge.paopao.util.FormatPrint;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class ServerController {
	private static Process serverProcess;
	private static Thread serverThread;

	public static boolean startServer() {
		if (serverProcess != null) {
			return false;
		}
		// 設定工作目錄（伺服器檔案的所在目錄）
		File workingDirectory = new File("./server");
		// 設定 Minecraft 伺服器 JAR 檔案的路徑
		String minecraftServerPath = "server.jar";
		Path path = Paths.get("./server/server.jar");
		if (!Files.exists(path)) {
			return false;
		}
		// 設定 Java 的執行命令
		String javaCommand = "java";
		// 設定伺服器的最大內存和初始內存
		String maxMemory = "18G";
		String initialMemory = "18G";

		// 創建 ProcessBuilder
		ProcessBuilder processBuilder = new ProcessBuilder(javaCommand, "-Xmx" + maxMemory, "-Xms" + initialMemory,
				"-jar", minecraftServerPath, "nogui");

		processBuilder.directory(workingDirectory);

//        // 設定伺服器啟動的其他配置（例如，如果需要 EULA 同意，你可以在工作目錄中創建一個 eula.txt 文件）
        processBuilder.redirectInput(ProcessBuilder.Redirect.INHERIT);
        processBuilder.redirectOutput(ProcessBuilder.Redirect.INHERIT);
        processBuilder.redirectError(ProcessBuilder.Redirect.INHERIT);
		// 將伺服器輸出重新導向到 Java 程序
//		processBuilder.redirectOutput(ProcessBuilder.Redirect.PIPE);
//		processBuilder.redirectError(ProcessBuilder.Redirect.PIPE);
		
		serverThread = new Thread(() -> {
			try {
				serverProcess = processBuilder.start();
				// 等待伺服器結束

				int exitCode = serverProcess.waitFor();
				FormatPrint.timePrintlnErr("Minecraft伺服器關閉，關閉碼 : " + exitCode);
			} catch (InterruptedException | IOException e) {
				FormatPrint.timePrintlnErr("啟動Minecraft伺服器失敗");
			}
		});
		serverThread.start();
		return true;
	}

	private static void sendCommandToServer(String command) throws IOException {
		if(serverProcess!=null) {
			// 向伺服器進程發送指令
			serverProcess.getOutputStream().write((command + "\n").getBytes());
			serverProcess.getOutputStream().flush();
			return;
		}
	}
	public static void sendMsgToServer(SlashCommandInteractionEvent event,String command) throws IOException {
		if(serverProcess!=null) {
			// 向伺服器進程發送指令
			serverProcess.getOutputStream().write(("say " + command + "\n").getBytes());
			serverProcess.getOutputStream().flush();
			event.getHook().editOriginal("✅ 訊息發送成功。").queue();
			return;
		}
		event.getHook().editOriginal("❌ 目前沒有管控中的Minecraft伺服器").queue();
	}
	public static void sendCommandToServer(SlashCommandInteractionEvent event,String command) throws IOException {
		if(serverProcess!=null) {
			// 向伺服器進程發送指令
			serverProcess.getOutputStream().write((command + "\n").getBytes());
			serverProcess.getOutputStream().flush();
			event.getHook().editOriginal("✅ 指令發送成功。").queue();;
			return;
		}
		event.getHook().editOriginal("❌ 目前沒有管控中的Minecraft伺服器").queue();
	}
	public static void shutdownServer() {
		// 手動關閉伺服器進程
		if (serverProcess != null && serverProcess.isAlive()) {
			try {
				sendCommandToServer("save-all");
				sendCommandToServer("stop");
				while(serverProcess.isAlive()) {
					Thread.sleep(5000);
				}
					
				
				
				serverProcess = null;
			} catch (IOException | InterruptedException e) {
				e.printStackTrace();
			}
			FormatPrint.timePrintlnErr("Minecraft伺服器關閉");

		} else {
			FormatPrint.timePrintlnErr("Minecraft伺服器未啟動");
		}
	}
	
	public static void killServer() throws InterruptedException {
		// 手動關閉伺服器進程
		if (serverProcess != null && serverProcess.isAlive()) {
			serverProcess.destroyForcibly();

			serverProcess = null;
//			serverProcess.destroyForcibly();
			FormatPrint.timePrintlnErr("Minecraft伺服器關閉");
//			serverProcess = null;
//			serverThread.interrupt();
//			serverThread = null;
		} else {
			FormatPrint.timePrintlnErr("Minecraft伺服器未啟動");
		}
	}

	public static Process getServerProcess() {
		return serverProcess;
	}

	
	
}
