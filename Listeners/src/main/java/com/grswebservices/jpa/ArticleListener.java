package com.grswebservices.jpa;

import java.util.Date;

import jakarta.persistence.PostPersist;
import jakarta.persistence.PrePersist;

public class ArticleListener {
	
	@PrePersist
	public void beforePersist(Article article) {
		article.setDate(new Date());
		System.out.println("Before persisting the object...");
	}
	
	@PostPersist
	public void afterPersist(Article article) {
		System.out.println("After persisting the object...");
	}
}
