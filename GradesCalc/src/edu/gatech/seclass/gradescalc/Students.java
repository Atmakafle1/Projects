package edu.gatech.seclass.gradescalc;

import static org.hamcrest.CoreMatchers.instanceOf;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class Students {
	FileInputStream file;
	FileInputStream file2;
	int numberOfStudents;
	String gradesDb;
	Student addition;
	HashSet<Student> sizeOfRoster = new HashSet<Student>();
	XSSFWorkbook studentsWorkbook;
	public Students(String studentsDb){
		try {
			file = new FileInputStream(new File(studentsDb));
			studentsWorkbook = new XSSFWorkbook(file);
		} catch (FileNotFoundException e) {
			System.out.println("Error in Students constructer");
		} catch (IOException e) {
			System.out.println("Error in Students constructer");
		}
		finally{
			try {
				if(file != null){
					file.close();
					System.out.println("file was closed");
				} 
				else{
					System.out.println("file was not closed");
				}
			}
			catch (IOException e) {
					System.out.println("Error in finally");
			}
		}
	}
	
	
	public int findNumberOfStudents(){
		//Get the workbook instance for XLS file 
        //XSSFWorkbook workbook = new XSSFWorkbook(file);       
        XSSFSheet sheet = studentsWorkbook.getSheetAt(0);
        //Iterate through each rows from first sheet
        numberOfStudents = sheet.getLastRowNum();
        return numberOfStudents;
	}


	public HashSet<Student> studentRoster()  {
		String studentName = null;
		String studentId = null;
		String studentTeam = null;
		String studentAttendance = null;
		String studentEmail = null;
		try{
			Grades grades = new Grades();

		try{
		
		file2 = new FileInputStream(new File(grades.fileName)); 
		
		XSSFWorkbook workbook2 = new XSSFWorkbook(file2);
		//XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet3 = workbook2.getSheetAt(0);
		XSSFSheet sheet = studentsWorkbook.getSheetAt(0);
		XSSFSheet sheet2 = studentsWorkbook.getSheetAt(1);
	    //Iterate through each rows from first sheet
        Iterator<Row> rowIterator = sheet.iterator();    //first sheet iterator
        while(rowIterator.hasNext()) {					//starts iteratring
            Row row = rowIterator.next();					
            if(row.getRowNum()!=0){						//checks to see row 0 is not selected
            	DataFormatter dataf = new DataFormatter();	
            	studentId = dataf.formatCellValue(row.getCell(1));  //sets the cell value of 1(names) to studentId
            	studentName = row.getCell(0).getStringCellValue();
            	studentEmail = row.getCell(2).getStringCellValue();	//added this here <------------------
             	sheet2 = studentsWorkbook.getSheetAt(1);
             	Iterator<Row> rowIterator2 = sheet2.iterator();
             	while(rowIterator2.hasNext()) {
             		Row row2 = rowIterator2.next();
             		Iterator<Cell> cellIterator = row2.cellIterator();
             		if(row2.getRowNum() != 0){
             			while(cellIterator.hasNext()){
             				Cell cell = cellIterator.next();
             				if(cell.getStringCellValue().equals(studentName)){
             					studentTeam = row2.getCell(0).getStringCellValue();
             					Iterator<Row> rowIterator3=sheet3.iterator();
             					while(rowIterator3.hasNext()){
             						Row row3 = rowIterator3.next();
             						if(row3.getRowNum() !=0){
             							DataFormatter dataf4 = new DataFormatter();
             							String iD = dataf4.formatCellValue(row3.getCell(0));
             							if(iD.equals(studentId)){
             								DataFormatter dataf3 = new DataFormatter();
             								studentAttendance = dataf3.formatCellValue(row3.getCell(1));
             								sizeOfRoster.add(new Student(studentName, studentId,studentTeam, studentAttendance, studentEmail));
             							}
             						}
             					}
             				}
             			}
             		}
             	}
            }
        }
	}
		catch(IOException e){
			System.out.println("Error");
		}
	}
		catch(IOException e){
			System.out.println("error");
		}
		return sizeOfRoster;
	}


	public Student findStudentByName(String string) {
		Student stud = new Student("Fred","2", "3", "3", "2");
		//Init sizeOfRoster hashset
		studentRoster();
		//Creates an iterator for sizeOfRoster hashset
		Iterator<Student> studentIterator =sizeOfRoster.iterator();
		
		//While loop to iterate through the hashset
		while(studentIterator.hasNext()){
			Student student = studentIterator.next();
			if(string.equals(student.getName())){
				stud = student;
			}		
		}
		return stud;
	}

	public Student findStudentId(String string) {
		
		Student stud = new Student("Fred","2", "3", "3", "2");
		//Init sizeOfRoster hashset
		studentRoster();
		//Creates an iterator for sizeOfRoster hashset
		Iterator<Student> studentIterator =sizeOfRoster.iterator();
		
		//While loop to iterate through the hashset
		while(studentIterator.hasNext()){
			Student student = studentIterator.next();
			if(string.equals(student.getGtid())){
				stud = student;
			}			
		}

		return stud;
	}	
	
}
