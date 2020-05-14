#!/bin/bash

if [ $# -ne 1 ]
then
	echo "Give only one argument"
	exit
else
	cd $1
	javac Main.java Elements.java Functions.java Summary.java
	cd ..
	echo "Give the absolute path to the file: soc-Epinions1.txt"
	read filepath
	echo
	java -cp . Classes.Main $filepath
fi
