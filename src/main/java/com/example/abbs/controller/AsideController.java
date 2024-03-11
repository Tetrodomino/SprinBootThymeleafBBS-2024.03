package com.example.abbs.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.abbs.util.asideUtil;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/aside")
public class AsideController {
	@Autowired private asideUtil asideUtil;

	@ResponseBody // 데이터 바로 보내주기
	@GetMapping("/stateMsg")
	public String changeStateMsg(String stateMsg, HttpSession session) {
		session.setAttribute("stateMsg", stateMsg);
		return "return message";
	}
	
	@ResponseBody
	@GetMapping("/weather")
	public String weather(HttpSession session) throws Exception {
		String location = (String) session.getAttribute("location") + "청";
		
		// 위치를 통해 도로명 주소를 받기
		String roadAddr = asideUtil.getRoadAddr(location);
		
		// 도로명 주소를 통해 위도와 경도 받기
		Map<String, String> map = asideUtil.getGeocode(roadAddr);
		
		// 위도와 경도를 통해 현재 날씨 받기
		String result = asideUtil.getWeather(map.get("lon"), map.get("lat"));
		
		return result;
	}
}
