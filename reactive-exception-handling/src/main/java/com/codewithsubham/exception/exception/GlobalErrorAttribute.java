package com.codewithsubham.exception.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;


@Component
public class GlobalErrorAttribute extends DefaultErrorAttributes {
	
	
	@Override
	public Map<String, Object> getErrorAttributes(ServerRequest request, ErrorAttributeOptions options) {
		Map<String, Object> map = new HashMap<String, Object>();
		Throwable error = getError(request);
		map.put("message", error.getMessage());
		map.put("endPoint URL : ", request.path());
		return map;
	}

}
