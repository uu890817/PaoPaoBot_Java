package idv.yuge.paopao.sqlite;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

import idv.yuge.paopao.sqlite.bean.SettingBean;
import idv.yuge.paopao.sqlite.service.SettingService;
import idv.yuge.paopao.util.FormatPrint;

public class SQLiteFactory {
	private static Connection conn = null;

	private SQLiteFactory() {
	}

	private static boolean isDataBaseExistence() {
		Path path = Paths.get("PaoPaoData.db");
		return Files.exists(path);
	}

	private static Connection newConnection() {
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:PaoPaoData.db");
			FormatPrint.timePrintln("資料庫連結成功");
			return conn;
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		FormatPrint.timePrintlnErr("資料庫連結錯誤");
		return null;
	}

	public static Connection getConnection() {
		if (conn != null) {
			return conn;
		}

		newConnection();
		return conn;
	};

	public static void startCheck() {
		if (isDataBaseExistence()) {
			FormatPrint.timePrintln("偵測到資料庫");
		} else {
			FormatPrint.timePrintlnErr("未偵測到資料庫，將建立新的資料庫...");

			SettingService ss = new SettingService();
			ss.createTable();

			// -----------------------------------------------------------------------------
			Scanner scanner = new Scanner(System.in);
			String discordBotToken;
			do {
				// 提示用戶輸入
				FormatPrint.timePrintlnErr("請輸入 Discord Bot Token: ");

				// 讀取用戶輸入的文字
				discordBotToken = scanner.nextLine();

				SettingBean sb = new SettingBean("Token", discordBotToken);
				// 檢查輸入是否為空或不是指定長度
				if (!discordBotToken.isEmpty() || discordBotToken.length() > 59) {
					ss.insertInto(sb);
				} else {
					FormatPrint.timePrintlnErr("輸入格式不正確，請重新輸入。");
				}

			} while (discordBotToken.isEmpty() || discordBotToken.length() <= 59);

			{
				SettingBean sb = new SettingBean("Ip_Port", "40507");
				ss.insertInto(sb);
			}
			// -----------------------------------------------------------------------------
			String guildId;
			do {
				// 提示用戶輸入
				FormatPrint.timePrintlnErr("請輸入Discord Bot要作用的伺服器ID:");
				// 讀取用戶輸入的文字
				guildId = scanner.nextLine();

				SettingBean sb = new SettingBean("Guild_Id", guildId);
				// 檢查輸入是否為空或不是指定長度
				if (!guildId.isEmpty() || guildId.length() == 19) {
					ss.insertInto(sb);
				} else {
					FormatPrint.timePrintlnErr("輸入格式不正確，請重新輸入。");
				}

			} while (guildId.isEmpty() || guildId.length() != 19);
			// 關閉 Scanner
			// -----------------------------------------------------------------------------
			String infoId;
			do {
				// 提示用戶輸入
				FormatPrint.timePrintlnErr("請輸入 ------------伺服器狀態------------ 頻道ID:");
				// 讀取用戶輸入的文字
				infoId = scanner.nextLine();

				SettingBean sb = new SettingBean("Info_Channel_Id", infoId);
				// 檢查輸入是否為空或不是指定長度
				if (!infoId.isEmpty() || infoId.length() == 19) {
					ss.insertInto(sb);
				} else {
					FormatPrint.timePrintlnErr("輸入格式不正確，請重新輸入。");
				}

			} while (infoId.isEmpty() || infoId.length() != 19);

			// -----------------------------------------------------------------------------
			String ipId;
			do {
				// 提示用戶輸入
				FormatPrint.timePrintlnErr("請輸入 IP 頻道ID:");
				// 讀取用戶輸入的文字
				ipId = scanner.nextLine();

				SettingBean sb = new SettingBean("IP_Channel_Id", ipId);
				// 檢查輸入是否為空或不是指定長度
				if (!ipId.isEmpty() || ipId.length() == 19) {
					ss.insertInto(sb);
				} else {
					FormatPrint.timePrintlnErr("輸入格式不正確，請重新輸入。");
				}

			} while (ipId.isEmpty() || ipId.length() != 19);

			// -----------------------------------------------------------------------------

			String portId;
			do {
				// 提示用戶輸入
				FormatPrint.timePrintlnErr("請輸入 Port 頻道ID:");
				// 讀取用戶輸入的文字
				portId = scanner.nextLine();

				SettingBean sb = new SettingBean("Port_Channel_Id", portId);
				// 檢查輸入是否為空或不是指定長度
				if (!portId.isEmpty() || portId.length() == 19) {
					ss.insertInto(sb);
				} else {
					FormatPrint.timePrintlnErr("輸入格式不正確，請重新輸入。");
				}

			} while (portId.isEmpty() || portId.length() != 19);

			// -----------------------------------------------------------------------------
			String onlineId;
			do {
				// 提示用戶輸入
				FormatPrint.timePrintlnErr("請輸入 伺服器狀態 頻道ID:");
				// 讀取用戶輸入的文字
				onlineId = scanner.nextLine();

				SettingBean sb = new SettingBean("Online_Channel_Id", onlineId);
				// 檢查輸入是否為空或不是指定長度
				if (!onlineId.isEmpty() || onlineId.length() == 19) {
					ss.insertInto(sb);
				} else {
					FormatPrint.timePrintlnErr("輸入格式不正確，請重新輸入。");
				}

			} while (onlineId.isEmpty() || onlineId.length() != 19);

			// -----------------------------------------------------------------------------
			String playerId;
			do {
				// 提示用戶輸入
				FormatPrint.timePrintlnErr("請輸入 線上人數 頻道ID:");
				// 讀取用戶輸入的文字
				playerId = scanner.nextLine();

				SettingBean sb = new SettingBean("Player_Channel_Id", playerId);
				// 檢查輸入是否為空或不是指定長度
				if (!playerId.isEmpty() || playerId.length() == 19) {
					ss.insertInto(sb);
				} else {
					FormatPrint.timePrintlnErr("輸入格式不正確，請重新輸入。");
				}

			} while (playerId.isEmpty() || playerId.length() != 19);

			// -----------------------------------------------------------------------------
			String versionId;
			do {
				// 提示用戶輸入
				FormatPrint.timePrintlnErr("請輸入 遊戲版本 頻道ID:");
				// 讀取用戶輸入的文字
				versionId = scanner.nextLine();

				SettingBean sb = new SettingBean("Version_Channel_Id", versionId);
				// 檢查輸入是否為空或不是指定長度
				if (!versionId.isEmpty() || versionId.length() == 19) {
					ss.insertInto(sb);
				} else {
					FormatPrint.timePrintlnErr("輸入格式不正確，請重新輸入。");
				}

			} while (versionId.isEmpty() || versionId.length() != 19);

			// -----------------------------------------------------------------------------
			String messageId;
			do {
				// 提示用戶輸入
				FormatPrint.timePrintlnErr("請輸入 機器人要發送消息的 頻道ID:");
				// 讀取用戶輸入的文字
				messageId = scanner.nextLine();

				SettingBean sb = new SettingBean("Message_Channel_Id", messageId);
				// 檢查輸入是否為空或不是指定長度
				if (!versionId.isEmpty() || versionId.length() == 19) {
					ss.insertInto(sb);
				} else {
					FormatPrint.timePrintlnErr("輸入格式不正確，請重新輸入。");
				}

			} while (versionId.isEmpty() || versionId.length() != 19);

			scanner.close();
		}
	}

}
