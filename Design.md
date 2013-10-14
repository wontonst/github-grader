#Functionality
+ Points broken down into requirement.
+ Requirement tagged to topic.
+ Deductions topic and specific deductions.

#Procedure for use
1. Rubric specified in .ghg format using RubricMaker 
2. Rubric .ghg is distributed to graders.
3. Foreach student the grader has, the grader inputs student username and repository. (Optional: define in rubric organization to replace username)
4. Foreach student
  + Grader grades student and then inputs each requirement grade - comment box for additional information
  + Grader saves record.
5. Grader presses Upload which automatically generates issues.
6. Grader revises grade which automatically updates the grade and creates a new comment specifying what was changed and why.
7. Grader can view average and other data in graphical form at any time after grading is complete.

#Details
##.ghg Format
*subject to revision before 1.0*
Format for ghg is as followed

Start of document has a YAML-styled variables list
```
---
title: 
allow-negative: 
my_var:
---
```

Mandatory variables are
+ title - name of the assignment

Non-mandatory settings variables are
+ allow-negative: if true, the calculated score can be negative (if user's score is less than deductions) else defaults to 0

