# Project 5: Snake

* Author: Michael Wendell
* Class: CS121 Section 1
* Semester: Fall 2021

## Overview

This Java application will create and run a game of Snake that will allow a user to control a snake on a desired grid size.
This application will also show the points and level of the game. 

## Reflection

When I first started this project I had a hard time trying to visualize the game and understand how each class would work 
and their purpose in the game. At first, I was able to create a basic board layout similar to the LitBrite, but got stuck 
trying to stick to the UML example. After some time and help, I was able to comprehend in my own way how the snake object 
worked and how it changed the Tiles type to show it. I was pretty excited once I was able to see a small green tile going 
around the board and from that point most things started to click. The only other issues I encountered at the very end were
trying to get input from the user and updating the JTextLabels on the board. After a few emails and some thinking, I was
able to move some code to the different method locations and surprisingly got everything work correctly. 

I found Project 5 was the most difficult to complete out of all the projects. I think this mostly was due to the large amount
of content that needed to be understood for this game while also being something that was created mostly by ourselves since 
alot of the classes had to be written on our own. While this project was challenging I found it very satisfying to have a 
working game and be able to interact with it and play it in the end. Looking back I should have started working on this project
earlier, so I could have more time to enjoy interacting with it and refining the different fun options that I could add.
Altogether, this project gave me a greater understanding and appreciation for the applications I use and  the effort that goes 
into their client side GUIs.

## Compiling and Using

To compile, execute the following commands in the main project directory:
```
$ javac SnakeGUI.java
$ javac Snake.java
$ javac SnakeSettings.java
$ javac SnakeSettingsPanel.java
$ javac SnakeTile.java
```

Run the compiled class with the command:
```
$ java SnakeGUI
```

The user will then be prompted with a width, height, and speed for the game. To move the Snake around the user will use
the arrow keys.

## Sources used

- All the code I wrote were references from class notes, lectures, and activities.