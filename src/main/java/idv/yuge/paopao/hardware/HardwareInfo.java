package idv.yuge.paopao.hardware;

import java.text.DecimalFormat;

import oshi.SystemInfo;
import oshi.hardware.GlobalMemory;

public class HardwareInfo {
	public static String getMem() {
		 // 建立 SystemInfo 物件
        SystemInfo systemInfo = new SystemInfo();

        // 取得 GlobalMemory 物件
        GlobalMemory globalMemory = systemInfo.getHardware().getMemory();

        // 取得 CentralProcessor 物件

        // 取得目前記憶體使用量和總記憶體
        long usedMemory = globalMemory.getTotal() - globalMemory.getAvailable();
        long totalMemory = globalMemory.getTotal();
        
        // 顯示結果
        DecimalFormat df = new DecimalFormat("#0.0");

        return "目前記憶體使用量: " + df.format(usedMemory / (1024.0 * 1024.0 * 1024.0)) + " GB / " + df.format(totalMemory / (1024.0 * 1024.0 * 1024.0)) + " GB";
	}
}
