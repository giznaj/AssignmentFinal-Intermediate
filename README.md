AssignmentFinal-Intermediate
=

Centennial - Assignment Final - (Alien Hunt 1.0)
=

# About
This is an applet/game written in Java.  This demonstrates probability in a simple game.  The game player tries to uncover 6 GREEN Aliens before 2 RED aliens.


## How to play
* Use the applet menu to start/stop the game
* Using the menu select your desired games options
* Start the game and start to click/select 1 of the 8 boxes
* You will keep selecting boxes until you either get 6 GREEN or 2 RED
* Check the console.output for real-time game updates


### How to run
* Clone the repo from Github via your preferred choice (SSH or HTTPS)
* Open the solution/src in your IDE of choice (Eclipse was use for this prohject)
* Run the solution within the IDE
* The applet is the interface to the game (menus and buttons)


#### Open Issues
* after a game is running, the user can toggle the sound off, then back on.  This means they can play music while there is 
no active game running.  This is intended.  If I set a boolean for gameInProgress to false, then the last paint method never gets 
displayed so you don't see the last alien uncovered.  fixed in next version.

* if a user plays more than 1 game in a minutes time, the time stamp could be the exact same (only displays to seconds).  This will 
be fixed in the next version.