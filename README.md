# Assignment3

# Code Smells
Lines 24-30: Variable names not descriptive
Lines 31, 39, 41, 43, 56-57, 117: not needed or never used variables and objects
Lines 1-66: not object oriented
Lines 71-116: Lengthy method

# Refactoring
Lines 1-66: Turned lengthy method and imports into MenuBar class
Line 24: changed the variable from mb to menuBar, because the original was not descriptive.
Line 25: changed the variable from fm to fileDropdown, because the original was not descriptive.
Line 26: changed the variable from em to editDropdown, because the original was not descriptive.
Line 27: changed the variable from d to textPane, because the original was not descriptive.
Line 28: changed the variable from nf to newFile, because the original was not descriptive.
Line 29: changed the variable from sf to saveFile, because the original was not descriptive.
Line 30: changed the variable from pf to printFile, because the original was not descriptive.
Line 31: variable removed because undo function is not required.
Line 32: changed the variable from c to copy, because the original was not descriptive.
Line 33: changed the variable from p to paste, because the original was not descriptive.
Line 39: removed because separator not required.
Line 41: removed because separator not required.
Line 43: removed because undo function is not required.
Lines 46-55: changed to lambda if-then statements in MenuBar class
Lines 56-57: removed because undo function is not required.
Lines 71-116: turned lengthy method into MenuAction class 
Line 117: removed because undo function is not required.
