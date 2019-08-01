package com.zxw.pojo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Item {
	private String id;
	private String subjectId;
	private String optionId;

	@Id
	@Column(name = "id")
	@GeneratedValue(generator = "assigned")
	@GenericGenerator(name = "assigned", strategy = "assigned")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	@Basic
	@Column(name = "optionId")
	public String getOptionId() {
		return optionId;
	}

	public void setOptionId(String optionId) {
		this.optionId = optionId;
	}

	@Basic
	@Column(name = "subjectId")
	public String getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Item item = (Item) o;
		return Objects.equals(id, item.id) &&
				Objects.equals(subjectId, item.subjectId) &&
				Objects.equals(optionId, item.optionId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, subjectId, optionId);
	}
}
