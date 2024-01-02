package idv.yuge.paopao.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FormatPrint {

	private static String CurrentTime() {
        // 獲取當前時間
        LocalDateTime currentTime = LocalDateTime.now();
        // 定義日期時間格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        // 格式化時間並返回結果
        return "[" + currentTime.format(formatter) + "]";
    }
	
	public static void timePrintln(String text) {
		System.out.println(CurrentTime() + " - " + text);
	}
	public static void timePrintlnErr(String text) {
		System.err.println(CurrentTime() + " - " + text);
	}
	
}
