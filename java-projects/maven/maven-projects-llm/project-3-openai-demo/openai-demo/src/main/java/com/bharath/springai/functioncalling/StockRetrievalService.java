package com.bharath.springai.functioncalling;

import java.util.function.Function;
import com.bharath.springai.functioncalling.StockRetrievalService.Request;
import com.bharath.springai.functioncalling.StockRetrievalService.Response;


public class StockRetrievalService implements Function<Request, Response> {
	
	public record Request(String symbol) {
		
	}
	
	public record Response(Double price) {
		
	}

	@Override
	public Response apply(Request request) {
		return new Response(5000D);
	}

}
