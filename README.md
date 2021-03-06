GitHub-Grader is targeted towards professors and TAs who use GitHub issues as 
the main channel for grading feedback. GitHub-Grader automates the GitHub 
website tasks.


#Grader Procedure
2. Rubric .ghg is distributed to graders.
3. Grader opens up GHGrader program and opens up the .ghg file.
3. Foreach student the grader has, the grader inputs student username and repository. (Alternative: if repo lives in a github organization, define it in rubric to replace username)
4. Foreach student
  + Grader grades student and then inputs each requirement grade - comment box for additional information
  + Grader saves record.
5. Grader presses Upload which automatically generates issues.
7. Grader can view average and other data in graphical form at any time after grading is complete.
6. Grader revises grade which automatically updates the grade and creates a new comment specifying what was changed and why.

#Rubric Procedure
- Rubric specified in [.ghg format][ghg] (by hand).
- Rubric .ghg is converted to .md or .html using [GHGrader][grader] to post up for students to view.


#.ghg Format
*subject to revision before 1.0 release*

Format for ghg is as followed

**Start of document has a YAML-styled variables list**

	---
	title: 
	allow-negative: 
	organization: 
	my_var:
	---


Mandatory variables are

+ title - name of the assignment

Non-mandatory settings variables are

+ allow-negative: if true, the calculated score can be negative (if user's score is less than deductions) else defaults to 0
+ organization: if this value is set, then the grader no longer needs to input a username and repo, instead it uses the organization name as the username.

Custom variables may be set and accessed anywhere in the requirements by $$(my_var)

Note that since variables are not case sensitive, having variables *myVar* and *MyVar* will cause one of them to become overwritten.

**The requirements and topics declaration is simply an indented list.**

The list starts with a topic followed by **tab indented** requirements and comments. 
Each requirement is started with an numeric (float or integer) value followed by a colon character. 
The number represents the point value of the requirement. 
Comments may be included after a requirement declaration.

For example

	Topic1
	  2: Requirement1
	  Comment1
	  Comment2
	  2: Requirement2
	  8: Requirement3
	Topic2
	  10: Requirement4
	  10: Requirement5

***Important***: **The indent is a tab (\t) character.**

Putting this all together, a sample ghg document would look like

	---
	title: Restaurant v2.1
	allow-negative: false
	organization: usc-csci201-fall2013
	required-for-each: 1: Clear documentation
	---
	Milestone v2.1A - Fulfill the Requirements of v2
	  2: One customer, one waiter
	  2: Multiple customers, one waiter
	  $$(required-for-each)
	Milestone v2.1B &ndash; Full Design of All 6 Agents (Cook, Waiter, Host, Customer, Cashier, Market)
	  1: You are to develop an interaction diagram for the normative scenario.
	  1: The interaction diagram should include message numbers, parameters, good message names.
	  2: Full Design document for all the agents. The document must include:
	  $$(required-for-each)
	Deductions
	  -10: Not using the agent methodology correctly:
	  Shouldn't access fields/values of another agent.
	  Shouldn't pass pointers in messages (other than agent pointers).
	  -5: Runtime errors other than concurrent modification errors, which we ignore for now.


[ghg]: #ghg-format