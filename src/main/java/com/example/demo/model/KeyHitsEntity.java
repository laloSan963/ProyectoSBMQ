package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "SBMQ_TR_HIT_KEY")
public class KeyHitsEntity {
	
	@Id
	@Column(name = "ID_KEY")
	private String key;
	
	@Column(name = "HITS")
	private Long hits;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Long getHits() {
		return hits;
	}

	public void setHits(Long hits) {
		this.hits = hits;
	}

}
