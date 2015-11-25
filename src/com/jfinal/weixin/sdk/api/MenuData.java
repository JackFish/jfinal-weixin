package com.jfinal.weixin.sdk.api;

import java.util.List;

public class MenuData {
	
	public List<Button> button;
	
	public class Button {
		String name;
		List<SubButton> sub_button;
	}
	
	public class SubButton {
		private String type;
		private String name;
		private String key;
		private String url;
	}
	
}
