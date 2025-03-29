﻿# COMP_4200_Project
Work Out Android App from Group 5 

# 5 fragments screen:
## Home Page Fragment (Not fully developed)
Display the summary of the user workout <br>

## Exercise List Fragment: 
User can see a list of Exercise Available: <br>
  _ Click on View Button on the right of the exercise card to see its full detail <br>
  _ Click on the plus icon on top to Add your own exercise.<br>
  
### Exercise Database Activity:
Add an exercise based on 4 information: Muscle Group, Name, Descriptiom and Image (Not developed for image yet) <br>
4 Functionality with the database: <br>
    + <b>Create</b> an exercise and added to the database<br>
    + <b>Read</b> all the exercise database to check
    + <b>Update</b> the exercise description based on exercise's name<br>
    + <b>Delete </b> the exercise based on name <br>
    + You can even delete the whole database if the delete button is hold long (3 default exercise will always be there)

## Start Page Fragment
Select the Exercises List from Exercise List database to start your work out <br>
### Start exercise Activity (Not fully developed yet)
Click on the Start button to start the selected exercises <br>
A selected exercise will have Sets, Reps and Weights for user to do <br>

### Choose exercise to do (Not fully developed yet)
User can choose the exercise imported from the Exercise Lise <br>
Once chosen, the current plan will display all the exercise chosen <br>

## Statistic Page Fragment
In Exercise List Fragment (2nd fragment), click on the exercise card (not View button area) to add an exercise for the statistic fragment <br>
This selected exercise will have its own Table in the database called SelectedExercise in DBHelper.java <br>
### Workout recommendation and Bar Chart
#### Calender 
Calender View display exercise done that day (Not developed yet) <br>
#### Workout Recommendation view
The exercises added will be reflected in the Workout Recommendation, which will recommend the least work out muscle group <br>
#### Bar chart and Reset button
A bar chart displaying all exercise chosen for clear visualization <br>
Click on the reset button to delete the Selected Exercise and refresh the fragment <br>
Hold the button to play the minigame! <br>

## User Page Fragment (Not fully developed)
Information about the user and notification page
