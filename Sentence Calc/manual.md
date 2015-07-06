#User Manuals#

##Description##
This is a program to calculate average sentence length in a .txt file

##Instructions##

###Windows User###


1. Extract the package in your local disk and find its directory, for example "C:\Users\All users\Desktop\Program". Here we refer {dir} as `C:\Users\All users\Desktop\Program`

2. - **Win7 user**: open the Command Prompt window by clicking the Start button, clicking All Programs, clicking Accessories, and then clicking Command Prompt

   - **Win8 user**: swipe up to show the Appsscreen or clicking on the down arrow icon at the bottom of the screen. Swipe or scroll to the right and locate the Windows System section heading.Under Windows System, press or click on Command Prompt
3. At the prompt, enter `cd {dir}`
4. At the next prompt, enter  `java main path_to_essay  or java main path_to_essay -l minlength -d delimiter`
   
###Mac User###
1. Extract the package in your local disk and find its directory, for example "/Users/jim/Program". Here we refer {dir} as `/Users/jim/Program`
2. Open the Command Prompt window by clicking Finder, clicking Applications, clicking Utilities, clicking Terminal
3. At the prompt, enter `cd {dir}`
4. At the next prompt, enter  `java main pathtoessay` or `java main path_to_essay -l minlength -d delimiter`

**path_to_essay** is the file path of your .txt file that need to be evaluated.  For example in windows `C:\Users\All User\Desktop\Test.txt` or in mac `/Users/jim/Documents/Test.txt`

**minlength** is optional if you want to specfy the minimum word length, use integer such as 3 or 5

**delimiter** is optional if you want to specify the delimiter for the sentence, for example, use "." or "?"


####Examples####
**Windows user**

       java main C:\Users\All User\Desktop\Test.txt -l 3 -d .
       java main D:\Thesis.txt -l 5
       java main C:\Users\All Users\Documents\Test.txt -d ?
       java main C:\Users\All User\Desktop\Test.txt

**Mac user**

       java main /Users/All User/Desktop/Test.txt -l 3 -d .
       java main /Thesis.txt -l 5
       java main /Users/All Users/Documents/Test.txt -d ?
       java main /Users/All User/Desktop/Test.txt


