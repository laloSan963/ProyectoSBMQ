package com.example.demo.components;

public class CounterResponse {
	
	private Long hits;
	private String key;
	
	public Long getHits() {
		return hits;
	}
	
	public String getKey() {
		return key;
	}
	
	public static class Builder {
		private Long hits;
		private String key;
		
		public Builder hits(Long hits) {
			this.hits = hits;
			return this;
		}
		
		public Builder key(String key) {
			this.key = key;
			return this;
		}
		
		public CounterResponse build() {
			CounterResponse counterResponse = new CounterResponse();
			counterResponse.hits = this.hits;
			counterResponse.key = key;
			return counterResponse;
		}
	}

}
