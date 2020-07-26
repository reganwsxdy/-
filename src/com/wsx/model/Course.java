package com.wsx.model;
/**
 * 
 * @author 63227
 * ¿Î³ÌÐÅÏ¢
 * 
 */

public class Course {
	private int id;
	private String name;
	private int teacher_Id;
	private int max_student_num;
	private String info;
	private int selected_num = 0;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getTeacher_Id() {
		return teacher_Id;
	}
	public void setTeacher_Id(int teacher_Id) {
		this.teacher_Id = teacher_Id;
	}
	public int getMax_student_num() {
		return max_student_num;
	}
	public void setMax_student_num(int max_student_num) {
		this.max_student_num = max_student_num;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public int getSelected_num() {
		return selected_num;
	}
	public void setSelected_num(int selected_num) {
		this.selected_num = selected_num;
	}
	public String toString(){
		return this.name;
	}
	
	
}
