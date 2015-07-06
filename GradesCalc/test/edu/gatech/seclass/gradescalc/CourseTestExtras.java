package edu.gatech.seclass.gradescalc;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class CourseTestExtras {

	 @Test
	    public void testAddTeamProjects() {
	        course.addTeamProject("Project 4");
	        course.updateGrades(new Grades(GRADES_DB));
	        assertEquals(4, course.getNumProjects());
	        course.addTeamProject("Project 5");
	        course.updateGrades(new Grades(GRADES_DB));
	        assertEquals(5, course.getNumProjects());
	    }
	    @Test
	    public void testAddStudent() {
	        course.addStudent("Jackie Flan", "882882882", "JF@gatech.com", "3", "2", "1", "N");
	        course.updateStudents(new Students(STUDENTS_DB));
	        assertEquals(17, course.getNumStudents());
	        course.addStudent("Benedict Sourpatch", "228228228", "BS@gatech.com", "2", "1", "1", "Y");
	        course.updateStudents(new Students(STUDENTS_DB));
	        assertEquals(18, course.getNumStudents());
	    }
	    
	    @Test
	    public void testGetTeamProjectGrade() {
	    	String projectName1 = "Project 2";
	        String projectTeam = "Team 2";
	        assertEquals(90, course.getTeamProjectGrade(projectName1, projectTeam));
	    }
	    @Test
	    public void testAddTeamProjectGrade() {
	        String projectName1 = "Project 2";
	        String projectTeam = "Team 2";
	        course.addTeamProjectGrade(projectName1, projectTeam, 50);
	        course.updateGrades(new Grades(GRADES_DB));
	        String projectName2 = "Project 3";
	        String projectTeam = "Team 1";
	        course.addTeamProjectGrade(projectName2,projectTeam, 20);
	        course.updateGrades(new Grades(GRADES_DB));
	        assertEquals(50, course.getTeamProjectGrade(projectName1));
	        assertEquals(20, course.getTeamProjectGrade(projectName2));
	    }

}
