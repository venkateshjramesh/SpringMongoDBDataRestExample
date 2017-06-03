package springmongodbdatarest.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document
public class Data {
	
	@Id
	private String id;
	
	private String text;
	private List<Scrap> scrap;
	private Date modifiedDate;
	private Date createdDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public List<Scrap> getScrap() {
		return scrap;
	}

	public void setScrap(List<Scrap> scrap) {
		this.scrap = scrap;
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

	public String toString() {
		return "Data{" +
				"id='" + id + '\'' +
				", text='" + text + '\'' +
				", scrap=" + scrap +
				", modifiedDate=" + modifiedDate +
				", createdDate=" + createdDate +
				'}';
	}
}
