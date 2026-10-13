package com.company;

public class HttpClient {

	public HttpClient(HttpClientBuilder httpClientBuilder) {
		this.method = httpClientBuilder.method;
		this.url = httpClientBuilder.url;
		this.userName = httpClientBuilder.userName;
		this.password = httpClientBuilder.password;
		this.headers = httpClientBuilder.headers;
		this.body = httpClientBuilder.body;

	}

	private String method;
	private String url;
	private String userName;
	private String password;
	private String headers;
	private String body;
	
	public static class HttpClientBuilder{
		private String method;
		private String url;
		private String userName;
		private String password;
		private String headers;
		private String body;
		
		public HttpClientBuilder method(String method) {
			this.method = method;
			return this;
			
		}
		
		public HttpClientBuilder url(String url) {
			this.url = method;
			return this;
			
		}
		
		public HttpClientBuilder secure(String userName, String password) {
			this.userName = userName;
			this.password = password;
			return this;
		}
		
		public HttpClientBuilder headers(String headers) {
			this.headers = headers;
			return this;
		}
		
		public HttpClientBuilder body(String body) {
			this.body = body;
			return this;
		}
		
		public HttpClient build() {
			return new HttpClient(this);
			
		}
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getHeaders() {
		return headers;
	}

	public void setHeaders(String headers) {
		this.headers = headers;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	@Override
	public String toString() {
		return "HttpClient [method=" + method + ", url=" + url + ", userName=" + userName + ", password=" + password
				+ ", headers=" + headers + ", body=" + body + "]";
	}
	
}
