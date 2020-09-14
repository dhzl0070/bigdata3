package kr.mem.controller;

import java.util.HashMap;

import kr.mem.pojo.Controller;
import kr.mem.pojo.MemberContentController;
import kr.mem.pojo.MemberDeleteController;
import kr.mem.pojo.MemberInsertCtroller;
import kr.mem.pojo.MemberInsertFormController;
import kr.mem.pojo.MemberListController;
import kr.mem.pojo.MemberUpdateController;

public class HandlerMapping {
	// key, value
	
	private HashMap<String, Controller> mappings;
	public HandlerMapping() {
		mappings=new HashMap<String, Controller>();
		initMap();
	}
	//중요!!
	private void initMap() {// /list.do가 key값, MemberListController가 value값
		try {
		mappings.put("/list.do", new MemberListController());
		mappings.put("/insert.do", new MemberInsertCtroller());
		mappings.put("/insertForm.do", new MemberInsertFormController());
		mappings.put("/delete.do", new MemberDeleteController());
		mappings.put("/content.do", new MemberContentController());
		mappings.put("/update.do", new MemberUpdateController());
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public Controller getController(String key) {//key값은 FC(command)가 넘겨주면 value값을 보낸다.
		return mappings.get(key);
	}
}
