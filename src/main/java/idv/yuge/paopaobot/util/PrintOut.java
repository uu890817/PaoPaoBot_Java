package idv.yuge.paopaobot.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PrintOut {

	public static void println(String colors, String text) {
		System.out.println(colors + text + ConsoleColors.RESET);
	}
	
	public static void println(String text) {
		System.out.println(text + ConsoleColors.RESET);
	}
	
	public static void timePrintln(String colors, String text) {
		LocalDateTime now = LocalDateTime.now();
        // 定義您想要的日期時間格式
        String pattern = "yyyy/MM/dd HH:mm:ss";
        // 使用 DateTimeFormatter 來格式化 LocalDateTime
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        String time = now.format(formatter);
		System.out.println(ConsoleColors.CYAN_BOLD_BRIGHT + "[" + time + "] -> " + colors + text + ConsoleColors.RESET);
	}
	
	public static void print(String colors, String text) {
		System.out.println(colors + text + ConsoleColors.RESET);
	}
	

}
