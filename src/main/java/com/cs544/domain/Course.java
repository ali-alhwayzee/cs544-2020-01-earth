package com.cs544.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Course {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long Id;
	@Column(unique = true)
	@NotNull(message = "Required to fill this field")
	@Size(min = 3 , max = 7)
	private String courseID;
	@Lob
	@NotNull(message = "this field Requires")
	private String description;
	public long getId() {
		return Id;
	}
	public Course() {}

	@Override
	public String toString() {
		return "Course{" +
				"Id=" + Id +
				", courseID='" + courseID + '\'' +
				", description='" + description + '\'' +
				'}';
	}
		public Course(long id, String courseID, String description) {
			Id = id;
			this.courseID = courseID;
			this.description = description;
		}

		private void setId(long id) {
			Id = id;
		}
		public String getCourseID() {
			return courseID;
		}
		public void setCourseID(String courseID) {
			this.courseID = courseID;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		
}
