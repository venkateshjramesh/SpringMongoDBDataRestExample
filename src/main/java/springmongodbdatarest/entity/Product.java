package springmongodbdatarest.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
public class Product {
	
	@Id
	private String id;
	
	private String email;
	private String text;
	private Integer emailAttempt;
	private Integer globalAttempt;
	private Date modifiedDate;
	private Date createdDate;
	private String score;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Integer getEmailAttempt() {
		return emailAttempt;
	}

	public void setEmailAttempt(Integer emailAttempt) {
		this.emailAttempt = emailAttempt;
	}

	public Integer getGlobalAttempt() {
		return globalAttempt;
	}

	public void setGlobalAttempt(Integer globalAttempt) {
		this.globalAttempt = globalAttempt;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "Product{" +
				"id='" + id + '\'' +
				", email='" + email + '\'' +
				", text='" + text + '\'' +
				", emailAttempt=" + emailAttempt +
				", globalAttempt=" + globalAttempt +
				", modifiedDate=" + modifiedDate +
				", createdDate=" + createdDate +
				", score='" + score + '\'' +
				'}';
	}
}
