# Given a Dictionary D where key is in format YYYY-MM-DD and the corresponding value in Int. returns a Dictionary With Key type String is Day corresponding value is the sum of values of that day 


## Introduction
The Solution of the above problem statement is written in java 

## Table of contents
* [Test/com/company](#Unit test)
* [src/com/company](#Solution.java file)
* [ReadMe](#readme)

## Technologies
Project is created with:
* Java
* JUnit5

## General info
This project is simple program to convert the dates to days as Key and fill the sum of corrosponding dates as value in a Dictionary (Map in java)

## Algorithm
Date in the dictionary is obtained and converted to date object and then
converted to Calender to obtain the day numberthen using Switch statement
And the corrosponding Valuse Obtained from input is Updated in Output 
If there is any days whose values remain unfilled ,Arithmetic Progression 
is used to fill the missing values
##### Arithmetic progression
InitialValue +(N-1)*commonDifference

N is the index: N(1)=4, N(2)=6 N(N)=8 ....

Common Difference is obtained by  Substracting PreviousDay value from NextDay .

## Features
The Solution can handle invalid dates by 
returning null and printing "invalid date"

## Limitations
The sum of values of any specific day should 
not exceed Max Integer Value


	



		

### I’m constantly working to improve myself, and I’d like to hear your thoughts about what are the areas I could improve upon. even if the application is not accepted.
### abhis.9427@gmail.com
  
		
