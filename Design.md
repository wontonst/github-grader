<!--
**or just use this real-world algorithm**
```
function useGhg(User you, Grader[] graders, Student[] students){
   GithubGrader application = you.installGHG()
   GHGRubric rubric = you.useTextEditor()
   HTMLRubric html_rubric = application.convertToHTML(rubric)
   foreach(Student s in students)
      s.giveRubric(html_rubric)
   foreach(Grader g in graders)
      GithubGrader graderapp = g.installGHG()
      foreach(Student s in g.getListOfStudentsToGrade)
         graderapp.inputStudent(s.getGithubUsername(),s.getGithubRepositoryName())
      foreach(Student s in g.getListOfStudentsToGrade)
         
}
```
oh wait, people speak english, not pseudocode. derp.-->

#GitHub Interactions
+ Will use eclipse [eGit-Github][https://github.com/eclipse/egit-github/tree/master/org.eclipse.egit.github.core] GitHub API library.
+ Must be able to create new issues.
+ Must be able to modify existing issues.
	+ There must be a way of identifying issues that were created.
	+ Alternatively, a list of issues created can be stored locally in the save file.
+ Issues contain grade information.
+ Revising grade information on an issue must create a comment stating the change effected.

#GUI
+ Must have menu bar
	+ New option to open a .ghg file.
		+ Upon opening a valid ghg file, the program will prompt for inputting student repository information.
	+ Open option to open existing .ghd file.
	+ Save option to save .ghd file.
		+ Becomes save as if file hasn't been saved before.
	+ Save as option to save as a new .ghd file.
	+ Close option to exit program.

#GHD Save File

#GHG Format
+ Has YAML-styled header block for macros.
	+ Header block must contain nonempty *title* field.
	+ Nonmandatory fields:
		+ allow-negative - allows user final score to be less than zero
		+ organization - instead of using a username/repository pair, it will default the username to the organization.
+ Has list of Topics
	+ Topic has a point value calculated from its requirements
	+ Topic has list of requirements.
	+ Topic cannot exist without at least one requirement.
+ There must be at least one topic for a valid GHG file.
+ Requirement has a point value.
	+ Requirement point value can be negative, meaning a deduction.
	+ Requirement has a list of comments.
	+ Requirement does not need at least one comment.
+ Comments are simple descriptions of requirements.

#GH-Grader
+ Can convert a .ghg into .md or .html.
	+ Can optionally add variables in yaml header block for Jekyll.
+ Parses .ghg and displays GUI for graders.
+ Stores grades into .ghgdata file for opening at a later date.
+ Calculates scores and average across students.
+ Interfaces with Github.


[ghg]: #ghg-format
[grader]: #gh-grader
