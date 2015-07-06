package edu.gatech.seclass.gradescalc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.JOptionPane;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.xmlbeans.impl.values.XmlValueDisconnectedException;

public class Grades extends Exception {
	FileInputStream file;
	FileInputStream file2;
	String fileName;
	int numberOfAssignments;
	int numberOfProjects;
	XSSFWorkbook gradesWorkbook;
	
	public Grades(String gradesDb){
		try{
			file = new FileInputStream(new File(gradesDb));
			System.out.println(gradesDb);
			fileName = gradesDb;
			gradesWorkbook = new XSSFWorkbook(file);			
		}
		catch(FileNotFoundException e){
			System.out.println("Grade(string) constructor error");
		} 
		catch (IOException e) {
			
			System.out.println("Grade(string) constructor error");
		}
		finally{
			
			try {
				if(file !=null){
					System.out.println("file was closed");
					file.close();
				}
				else{
					System.out.println("file was not closed");
				}
			} catch (IOException e) {
				
				System.out.println("File close error");
			}
		}
	}
	public Grades() throws FileNotFoundException{
		fileName = "DB/GradesDatabase6300-grades.xlsx";
	}
	
	public int findNumAssignments() throws IOException {
		//XSSFWorkbook workbook = new XSSFWorkbook(file);
        //Get second (Individual Grades) sheet from the Grades workbook
        XSSFSheet sheet = gradesWorkbook.getSheetAt(1);
        //Set the rowIterator
        Iterator<Row> rowIterator = sheet.iterator();
        Row row = rowIterator.next();
        //For each row, iterate through each columns
        Iterator<Cell> cellIterator = row.cellIterator();
        while(cellIterator.hasNext()) {  
        	Cell cell = cellIterator.next();
            numberOfAssignments = cell.getColumnIndex(); //set the column index to variable
            }
		return numberOfAssignments; //returns the variable
    }

	public int findNumProjects() throws IOException {
		//Get the workbook instance for XLS file 
        //XSSFWorkbook workbook = new XSSFWorkbook(file);
        //Get first sheet from the workbook
        XSSFSheet sheet = gradesWorkbook.getSheetAt(2);
        //Set the rowIterator
        Iterator<Row> rowIterator = sheet.iterator();
        Row row = rowIterator.next();
        //For each row, iterate through each columns
        Iterator<Cell> cellIterator = row.cellIterator();
        while(cellIterator.hasNext()) {
    	   Cell cell = cellIterator.next();
           numberOfProjects = cell.getColumnIndex();
        }
       return numberOfProjects;
	}
	public void addMyAssignments(String string) {
		int cellIndex = 0;
		
		//XSSFWorkbook workbook = new XSSFWorkbook(file);
		
		XSSFSheet sheet = gradesWorkbook.getSheetAt(1);
		
		Iterator<Row> rowIterator = sheet.iterator();
		
		while(rowIterator.hasNext()){
			Row row = rowIterator.next();
			Iterator<Cell> cellIterator = row.cellIterator(); 
			
			if(row.getRowNum() == 0){
				
				while(cellIterator.hasNext()){
					Cell cell = cellIterator.next();
					
					cellIndex++;
				}
			}
		 }
		Cell cell2 = sheet.getRow(0).createCell(cellIndex);
		cell2.setCellValue(string);
			try {
				file.close();
		
			FileOutputStream outFile = new FileOutputStream(new File(fileName));
			
			gradesWorkbook.write(outFile);
			
			outFile.close();
			
			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	public void updateMyGrades(Grades grades) {
		try{
			file = new FileInputStream(new File(grades.fileName));
			System.out.println(fileName);
			gradesWorkbook = new XSSFWorkbook(file);			
		}
		catch(FileNotFoundException e){
			System.out.println("Grade(string) constructor error");
		} 
		catch (IOException e) {
			
			System.out.println("Grade(string) constructor error");
		}
		finally{
			
			try {
				if(file !=null){
					System.out.println("file was closed");
					file.close();
				}
				else{
					System.out.println("file was not closed");
				}
			} catch (IOException e) {
				
				System.out.println("File close error");
			}
		}	
		
	}
	public void addGradesforAssign(String assignmentName,
			HashMap<Student, Integer> grades) {
		Student stude = new Student();
		String stu;
		Student stud;
		int cellIndex = 0;
		
		//XSSFWorkbook workbook = new XSSFWorkbook(file);
	
		XSSFSheet sheet = gradesWorkbook.getSheetAt(1);
		
		
		Set <Student> keys= grades.keySet();
		
		Iterator<Row> rowIterator1 = sheet.iterator();
        Row row1 = rowIterator1.next();
			Iterator<Cell> cellIterator2 = row1.cellIterator();
			while(cellIterator2.hasNext()){
				
				
				Cell cell3 = cellIterator2.next();
				
				cellIndex = cell3.getColumnIndex();
				
			}
		
		
		Iterator<Student> keyStudIterator = keys.iterator();
		while(keyStudIterator.hasNext()){
			Iterator<Row> rowIterator=sheet.iterator();
			Student stud2= keyStudIterator.next();
			
			while(rowIterator.hasNext()){
				Row row = rowIterator.next();
				DataFormatter dataf = new DataFormatter();
				stu = dataf.formatCellValue(row.getCell(0));
				if(stu.equals(stud2.getGtid())){
					
					Iterator<Cell> cellIterator = row.iterator();
					while(cellIterator.hasNext()){
						
						Cell cell = cellIterator.next();
						
						if(cell.getColumnIndex() + 1 == cellIndex){
							
							int row2 = row.getRowNum();
							Cell cell2 = sheet.getRow(row2).createCell(cellIndex);
							cell2.setCellValue(grades.get(stud2));
						}
					}
				}
			}
		}
		try {
			file.close();
	
		FileOutputStream outFile = new FileOutputStream(new File(fileName));
		
		gradesWorkbook.write(outFile);
		
		outFile.close();
		
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public int getAverage(Student student1) {
		float averageGrade1 = 0;
		int averageGrade = 0;
		int cellIndex = 0;
		int columnIndex = 0;
		int totalGrade = 0;
		
	//	System.out.println("check 1");
		XSSFSheet sheet = gradesWorkbook.getSheetAt(1);
		
		
		Iterator<Row> rowIterator1 = sheet.iterator();
        Row row1 = rowIterator1.next();
		Iterator<Cell> cellIterator2 = row1.cellIterator();
		while(cellIterator2.hasNext()){
			Cell cell3 = cellIterator2.next();
		//	System.out.println("check 2");
			cellIndex = cell3.getColumnIndex();
			
		}
	//	System.out.println("The cellIndex is " + cellIndex);
	//	System.out.println("check 3");	
		Iterator<Row> rowIterator = sheet.iterator();
		while(rowIterator.hasNext()){
			//System.out.println("check 4");
			Row row = rowIterator.next();
			DataFormatter dataf4 = new DataFormatter();
			String iD = dataf4.formatCellValue(row.getCell(0));
			
			if(iD.equals(student1.getGtid())){
				System.out.println("check 5");
				while(cellIndex > columnIndex){
					
					columnIndex++;
					
					int theCell = (int) row.getCell(columnIndex).getNumericCellValue();
					
					totalGrade = theCell + totalGrade;
					
					averageGrade1 = (float)totalGrade/columnIndex;
					
					averageGrade = Math.round(averageGrade1);
					
				}
			}
		}
		System.out.println("check 7");
		return averageGrade;
		
		
	}
	public int getProjectAverage(Student student, String myTeam) {
		float averageProjectGrade1 = 0;
		int averageProjectGrade = 0;
		int cellIndex = 0;
		int columnIndex = 0;
		float totalGrade = 0;
		float tGrade = 0;
		int teamIndex = 0;
		String studentTeam;
		//System.out.println("check 1");
		XSSFSheet sheet = gradesWorkbook.getSheetAt(2);
		
		Iterator<Row> rowIterator1 = sheet.iterator();  //sets the rowIterator
        Row row1 = rowIterator1.next();					//set row1 Row variable  
		Iterator<Cell> cellIterator2 = row1.cellIterator(); //cell iterator
		while(cellIterator2.hasNext()){					//this loops to find the column index 
			Cell cell3 = cellIterator2.next();
			//System.out.println("check 2");
			cellIndex = cell3.getColumnIndex();			//find number of projects
			
		}
		float[] indGrades = new float[cellIndex];		//declare and init int array
		int[] proGrades = new int[cellIndex];
		//all good
		//System.out.println("The cellIndex is " + cellIndex);
		//System.out.println("check 3");	
		Iterator<Row> rowIterator = sheet.iterator();
		while(rowIterator.hasNext()){
			//System.out.println("check 4");
			Row row = rowIterator.next();
			DataFormatter dataf4 = new DataFormatter();
			String iD = dataf4.formatCellValue(row.getCell(0));
			//System.out.println("the iD is " + iD);
			if(iD.equals(student.getGtid())){
				//System.out.println("check 5");
				while(cellIndex > columnIndex){
					//System.out.println("check 6");
					columnIndex++;
					//System.out.println("the columnIndex is " + columnIndex);
					int theCell = (int) row.getCell(columnIndex).getNumericCellValue();
					//System.out.println("theCell is " + theCell);
					indGrades[columnIndex - 1] = theCell;
				}
			}
		}
		//all up to here
		
		//System.out.println("check 7");
		//get project grades
		sheet = gradesWorkbook.getSheetAt(3); //sets the Team Grades sheet
		//System.out.println("check 8");
				Iterator<Row> rowIterator5 = sheet.iterator();
				while(rowIterator5.hasNext()){
				
					Row row5 = rowIterator5.next();
					Iterator<Cell> cellIterator5=row5.iterator();
					while(cellIterator5.hasNext()){
						
						Cell cell5 = cellIterator5.next();
						DataFormatter dataf5 = new DataFormatter();
						String myId = dataf5.formatCellValue(cell5);
						if(myId.equals(myTeam)){
							//System.out.println(columnIndex);
							//System.out.println(teamIndex);
							while(columnIndex > teamIndex){
								teamIndex++;
								int theCell2 = (int) row5.getCell(teamIndex).getNumericCellValue();
								//System.out.println(theCell2);
								//System.out.println(teamIndex);
								proGrades[teamIndex - 1] =theCell2;
							}
						}
						
								
					}
				}
						
				//System.out.println("check 13");
				for(int x = 0; x < columnIndex; ++x){
					
					tGrade = (float)proGrades[x] * (indGrades[x]/100);
				
					totalGrade = tGrade + totalGrade;
					
					averageProjectGrade1 = totalGrade/columnIndex;
				
					averageProjectGrade = Math.round(averageProjectGrade1);
				}
				
		//System.out.println("check 15");
		return averageProjectGrade;
		
		
	}
	public void addIndContributions(String projectName1,
			HashMap<Student, Integer> contributions1) {
		Student stude = new Student();
		String stu;
		Student stud;
		int cellIndex = 0;
		boolean projectName = false;
		XSSFSheet sheet = gradesWorkbook.getSheetAt(2);
		
		//System.out.println("check 1");
		Set <Student> keys= contributions1.keySet();
		//get column index
		Iterator<Row> rowIterator1 = sheet.iterator();
		//System.out.println("check 2");
        Row row1 = rowIterator1.next();
        //System.out.println("check 3");
			Iterator<Cell> cellIterator2 = row1.cellIterator();
			while(cellIterator2.hasNext() && !projectName){
				//System.out.println("check 4");
				Cell cell3 = cellIterator2.next();
				if(cell3.getStringCellValue().equals(projectName1)){
				cellIndex = cell3.getColumnIndex();
				projectName = true;
				}
			}
			
			
					
		//iterate through hash keys
		Iterator<Student> keyStudIterator = keys.iterator();
		while(keyStudIterator.hasNext()){
			//System.out.println("check 6");
			Iterator<Row> rowIterator=sheet.iterator();
			Student stud2= keyStudIterator.next();
			
			while(rowIterator.hasNext()){
				//System.out.println("check 7");
				Row row = rowIterator.next();
				DataFormatter dataf = new DataFormatter();
				stu = dataf.formatCellValue(row.getCell(0));
				//System.out.println("the cell value is " + stu);
				//System.out.println("the stud2 value is " + stud2.getGtid());
				if(stu.equals(stud2.getGtid())){
					//System.out.println("check 8");
					Iterator<Cell> cellIterator = row.iterator();
					while(cellIterator.hasNext()){
						//System.out.println("check 9");
						Cell cell = cellIterator.next();
						//System.out.println(cell.getColumnIndex());
						if(cell.getColumnIndex()== cellIndex){
							//System.out.println("check 10");
							
							int row2 = row.getRowNum();
							Cell cell2 = sheet.getRow(row2).createCell(cellIndex);
							cell2.setCellValue(contributions1.get(stud2));
							//System.out.println(contributions1.get(stud2));
						}
					}
				}
			}
		}
		//System.out.println("check 11");
		try {
			file.close();
	
		FileOutputStream outFile = new FileOutputStream(new File(fileName));
		
		gradesWorkbook.write(outFile);
		
		outFile.close();
		
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println("check 12");
	}
	public int findOverallGrade(String myFormula, Student student, double proAve, double stuAve) {
		int i = 0;
		Double value1 = 0.0;
		Double value2 = 0.0;
		Double value3 = 0.0;
		int formLength = 0;
		double tO= 0;
		Boolean ATT = false;
		Boolean APG = false;
		Boolean AAG = false;
		Boolean nonLegit = false;
		Boolean b1 = false;
		String[] form;
		form = myFormula.split(" ");
		formLength = form.length;
		
		if(formLength == 11) {
			while(formLength > i){
				System.out.println(form[i]);
				switch(form[i]){
				case "ATT": 
					ATT = true;
					break;
				case "APG":
					APG = true;
					break;
				case "AAG":
					AAG = true;
					break;
				default:
					nonLegit = true;
					break;
			
				}
				i++;
			}
			if(ATT && APG && AAG){
			
			tO = (Double.parseDouble(form[2]) * student.getAttendance()) +(Double.parseDouble(form[6]) * stuAve) +(Double.parseDouble(form[10]) * proAve);
			System.out.println(tO);
			}
			else{
				throw new GradeFormulaException();
			}
		}
		else if(formLength == 7) {
			while(formLength > i){
				System.out.println(form[i]);
				switch(form[i]){
				case "ATT": 
					ATT = true;
					break;
				case "APG":
					APG = true;
					break;
				case "AAG":
					AAG = true;
					break;
				default:
					nonLegit = true;
					break;
			
				}
				i++;
			}
			if(ATT && AAG){
				tO = (Double.parseDouble(form[2]) * student.getAttendance()) +(Double.parseDouble(form[6]) * stuAve);
			}
			else if(ATT && APG){
				tO = (Double.parseDouble(form[2]) * student.getAttendance()) +(Double.parseDouble(form[6]) * proAve);
			}
			else if(AAG && APG){
				tO = (Double.parseDouble(form[6]) * stuAve) +(Double.parseDouble(form[6]) * proAve);
			}
			else{
				throw new GradeFormulaException();
			}
		}
		else if(formLength == 3) {
			while(formLength > i){
				System.out.println(form[i]);
				switch(form[i]){
				case "ATT": 
					ATT = true;
					break;
				case "APG":
					APG = true;
					break;
				case "AAG":
					AAG = true;
					break;
				default:
					nonLegit = true;
					break;
			
				}
				i++;
			}
			if(ATT){
				tO = (Double.parseDouble(form[2]) * student.getAttendance());
			}
			else if(AAG){
				tO = (Double.parseDouble(form[2]) * proAve);
			}
			else if(APG){
				tO = (Double.parseDouble(form[2]) * proAve);
			}
			else{
				throw new GradeFormulaException();
			}
		}
		else {
			throw new GradeFormulaException();
		}
		
		
	
		
		return (int) Math.round(tO);
	}

}


