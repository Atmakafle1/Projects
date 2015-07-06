package edu.gatech.seclass.gradescalc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Course {
	private int numberOfStudents;
	private int numberOfAssignments;
	private int numberOfProjects;
	private Student nameOfStudent;
	private HashSet<Student> sizeOfRoster;
	private Students myStudents;
	private Grades myGrades;
	private Student myStudent;
	private Student idOfStudent;
	private int averageAssignmentGrade;
	private int averageProjectGrade;
	private int myAttendance;
	private String myFormula = "ATT * 0.2 + AAG * 0.3 + APG * 0.5";;
	private int overallGrade;
	private String myTeam;

	
	public Course(Students students, Grades grades) {
		myStudents = students;
		myGrades = grades;
	}
	public Student getStudentByID(String string) {
		idOfStudent = myStudents.findStudentId(string);
		return idOfStudent;
	}

	public Student getStudentByName(String string) {
		nameOfStudent = myStudents.findStudentByName(string);
		return nameOfStudent;
	}

	public int getNumProjects(){
		
		
			try {
				numberOfProjects = myGrades.findNumProjects();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
		return numberOfProjects;
	}

	public int getNumAssignments() {
		try {
			numberOfAssignments = myGrades.findNumAssignments();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return numberOfAssignments;
	}

	public int getNumStudents() {
		numberOfStudents = myStudents.findNumberOfStudents();
		return numberOfStudents;
	}
	public HashSet<Student> getStudents() {
		Student student;
		sizeOfRoster = myStudents.studentRoster();
		return sizeOfRoster;
	}
	//up and operational
	public void addAssignment(String string){
		myGrades.addMyAssignments(string);
		try {
			System.out.println(myGrades.findNumAssignments());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//new
	public void updateGrades(Grades grades) {
		 myGrades.updateMyGrades(grades);
	}
	//new
	public void addGradesForAssignment(String assignmentName,
			HashMap<Student, Integer> grades) {
		
		myGrades.addGradesforAssign(assignmentName, grades);
	}
	//this one
	public int getAverageAssignmentsGrade(Student student1) {
		Student studen = myStudents.findStudentByName(student1.getName());
		averageAssignmentGrade = myGrades.getAverage(studen);
		return averageAssignmentGrade;
	}
	
	//new
	public int getAverageProjectsGrade(Student student) {
		Student studen = myStudents.findStudentId(student.getGtid());
		String mySudent = studen.getTeam();
		averageProjectGrade = myGrades.getProjectAverage(student, mySudent);
		return averageProjectGrade;
	}
	
	//new
	public void addIndividualContributions(String projectName1,
			HashMap<Student, Integer> contributions1) {
		myGrades.addIndContributions(projectName1, contributions1);
		
	}

	//Complete
	public int getAttendance(Student student) {
		Student studen = myStudents.findStudentByName(student.getName());
		int mySudent = studen.getAttendance();
		
		return mySudent;
	}

	//Complete
	public String getTeam(Student student) {
		Student studen = myStudents.findStudentByName(student.getName());
		String mySudent = studen.getTeam();
		return mySudent;
	}
	
	
	public String getFormula() {		
		
		return myFormula;
	}
	public String getEmail(Student student) {
		Student studen = myStudents.findStudentByName(student.getName());
		
		return studen.getEmail();
	}
	public void setFormula(String text) {
		
		myFormula = text;
		
	}
	public int getOverallGrade(Student student) {
		Student studen = myStudents.findStudentByName(student.getName());
		myTeam = studen.getTeam();
		myFormula = getFormula();
		System.out.println(myFormula);
		double proAve = myGrades.getProjectAverage(studen, myTeam);
		
		double stuAve =myGrades.getAverage(studen);
		overallGrade = myGrades.findOverallGrade(myFormula, studen, proAve, stuAve);
		return overallGrade;
	}

}
