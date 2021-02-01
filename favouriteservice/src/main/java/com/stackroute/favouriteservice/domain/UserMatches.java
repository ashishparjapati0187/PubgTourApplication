package com.stackroute.favouriteservice.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pubg")
public class UserMatches {

	
	/**
	 Id for the movie
	 */
	@Id
	@Column(name = "id")
    private String id;

	
	/**
	 title for the movie
	 */
	@Column(name="match_id")
	private String matchId;
	
	@Column(name = "title")
	private String title;
	/**
	  field for Comments of the movie
	 */
	
	@Column(name="user_id")
	private String userId;
	
	@Column(name="created_at")
	private String createdAt;

	public String getId() {
		return id;
	}

	public void setId(String id) {

		this.id = id;
	}

	public String getMatchId() {
		return matchId;
	}

	public void setMatchId(String matchId) {
		this.matchId = matchId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return "UserMatches [id=" + id + ", matchId=" + matchId + ", title=" + title + ", userId=" + userId
				+ ", createdAt=" + createdAt + "]";
	}

	public UserMatches(String id, String matchId, String title, String userId, String createdAt) {
		super();
		this.id = id;
		this.matchId = matchId;
		this.title = title;
		this.userId = userId;
		this.createdAt = createdAt;
	}
	public UserMatches() {
		
	}


}
