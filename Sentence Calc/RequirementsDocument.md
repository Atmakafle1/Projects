# **Requirements Document -- Team 07**

##1 User Requirements##

###1.1 Software Interfaces###

The program is to be developed using the Java JDK (Java Development Kit) tools.

###1.2 User Interfaces###

 - User could specify the path of text file via a command prompt. 
 - User could specify which delimiters count as sentence separators, using the flag -d
 - User could specify a lower limit for word length, using the flag -l
 - The user will be shown the number of words in the essay, the one entered into the command prompt, at the completion of the    program. 
 - If an error is encountered a user friendly error message will be displayed to the user.

###1.3 User Characteristics###

<table>
<tr><td>User</td> <td>Education</td>  <td>Experience</td> <td>Technical Expertise</td>   </tr>
<tr><td>Teacher</td> <td>Teaching degree</td> <td>The user has used other word processing software, but it is unkown if they have used the command prompt</td> <td>Teacher has a general understanding of how to use a computer.</td> </tr>
<tr><td>Students</td> <td>High school graduate to Doctorate degree</td>   <td>The user has used other word processing software, but it is unkown if they have used the command prompt</td>   <td>no technical experience to very proficient</td> </tr>
</table>

##2 System Requirements##
 
###2.1 Functional Requirements
   - The program should be able to count the total words of the file except those from sentences below the limit
   - The program should be able to count the number of qualified sentences based on user's specification
   - The output should be the average sentence length that is rounded to the nearest integer
   
###2.2 Non-Functional Requirements
   - The programs comes with an additional documentation about how to use this tool.
