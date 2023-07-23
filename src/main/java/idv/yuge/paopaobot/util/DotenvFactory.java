package idv.yuge.paopaobot.util;

import io.github.cdimascio.dotenv.Dotenv;

public class DotenvFactory {
	
	public static String getToken() {
		Dotenv env = Dotenv.load();
		return env.get("TOKEN");
	}
	
	
	
	
	
	
}
