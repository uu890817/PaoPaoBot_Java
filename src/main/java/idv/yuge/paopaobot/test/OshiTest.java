package idv.yuge.paopaobot.test;

import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.util.Util;

public class OshiTest {
	public OshiTest() {
	}
	
	public static void getCpuInfo() {
		SystemInfo si = new SystemInfo();
		HardwareAbstractionLayer hal = si.getHardware();
		CentralProcessor cpu = (hal).getProcessor();
		Util.sleep(1000);
		 // 獲取 CPU 的總使用率
//        double cpuUsage = ;
       
        
        // 印出 CPU 的總使用率
//        System.out.printf("CPU Usage: %.2f %% \n", cpu.getSystemLoadAverage());
        
        cpu = null;
        hal = null;
        si = null;
        
	}
	
	
	
	
	public void test() {
		
		
	}
	
	
}
