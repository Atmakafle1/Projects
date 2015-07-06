package edu.gatech.seclass.gradescalc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Student {
	String studentName;
	String studentId;
	Course myCourse;
	private Students myStudents;
	private Grades myGrades;
	private String myName;
	private String myGtId;
	private String myAttendance;
	private String myTeam;
	private String myEmail;
	public Student(String name, String GtId, String team, String attendance, String email){
		myName = name;
		myGtId = GtId;
		myTeam =team;
		myAttendance = attendance;
		myEmail = email;
	}
	//new
	public Student(String string, String string2, Course course) {
		myName = string;
		myGtId = string2;
		myCourse = course;
		
	}

	
	public Student() {
		
	}
	public Student(String string, String string2) {
		myName = string;
		myGtId = string2;
		
	}
	public String getName() {
		return myName;
	}	
	public int getAttendance() {
		int Attendance = Integer.parseInt(myAttendance);
		return Attendance;
	}
	public String getTeam() {
		return myTeam;
	}
	public String getGtid() {
		return myGtId;
	}
	public Course getCourse(){
		return myCourse;
	}
	public String getEmail() {
		
		return myEmail;
	}
}
	

