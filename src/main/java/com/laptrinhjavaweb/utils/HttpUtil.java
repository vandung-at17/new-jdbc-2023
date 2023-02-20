package com.laptrinhjavaweb.utils;

import java.io.BufferedReader;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class HttpUtil {
	
	private String value;

	public HttpUtil() {

	}

	public HttpUtil(String value) {
		this.value = value;
	}

	public <T> T toModel(Class<T> tClass) {
		try {
			return new ObjectMapper().readValue(value, tClass);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Lỗi ở phần HttpUtil toModel");
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	public static HttpUtil of (BufferedReader reader) {
		StringBuilder sb = new StringBuilder();
		try {
			String line ;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			// TODO: handle exception
			System.out.println("Lỗi ở phần HttpUtil of");
			System.out.println(e.getMessage());
		}
		return new HttpUtil(sb.toString());
	}
}
