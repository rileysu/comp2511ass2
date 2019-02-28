#!/bin/sh

if [ "$#" -eq 1 ] && [ -f "$1" ]; then
	java -cp bin ShipmentPlanner $1
else
	echo "Incorrect Arguments!"
fi
