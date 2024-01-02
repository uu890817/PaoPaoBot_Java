package idv.yuge.paopao.sqlite.bean;

import lombok.Data;

@Data
public class SettingBean {
	
	private String key;
	private String value;
	
	public SettingBean(String key, String value) {
		this.key = key;
		this.value = value;
	};
	
}
