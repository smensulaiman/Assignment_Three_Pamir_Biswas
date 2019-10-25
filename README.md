   <h1>ASSIGNMENT QUESTION</h1><h3>Application In the last assignment, you started to create the framework for an application that analysis large volume of data. We are now going to add a new file to perform calculations on the information.</h3> 
 
Challenge:  Use a Lambda expression somewhere in this assignment.  This means you will have to use an interface…  
 
Create a file Calculations.java. This is the file that will perform calculations in analyzing the data in the previous assignment. Note: You will want to use a smaller version of your data file (20 rows) for testing.  Your Calculations.java class requires the following methods: 
 
Method: createDateArray 
 
   You will need to create one array to hold the dates (the first column of the data) which will be a one dimensional array ie: Date [ ] dateArray = new Date[r.findRowNumber()];   Use try and catch block 
 
Method: createDataArray 
 
   You will need to create a second array to hold the data from the remaining 6 columns ie: float[ ] [ ] dataArray = new float[r.findRowNumber( )] [7];    This is done, because we need to perform mathematical calculations on the values which cannot be done if the values are in a string format    This can be accomplished by using a switch case loop or not
 
Method: printArray 
 
   Print the data in the array (for the arrays just created) 
   

Create a method that performs some basic calculations to answer the following questions: 
 
   What date had the highest opening value? 
 
   What date had the highest closing value? 
 
   What date had the lowest opening value? 
 
   What date had the lowest closing value? 
 
   What was the top ten dates with the highest closing value (in order of high to lowest)? 
 
   What was the top ten dates with the lowest closing value (in order of lowest to highest)? 
 
   Add a column difference for all rows (determine by open amount - closing amount) and write to a new file (saved in your c:\temp\ directory).   
  
 
Make sure all lists/tables outputted is in good format and has column headers. 
 
Modify the file DataAnalyzer.java. This file will be used to call the methods in ReadFiles.java and Calculations.java. Be sure to demonstrate that all of your methods work through DataAnalyzer.java.  The results of the calculations should be outputted to the console or you can use JOption. 
 
When submitting your assignment, be sure to include your data files for testing and make sure your application is configure to open the data files from c:\temp. 
