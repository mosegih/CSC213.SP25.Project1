#!/bin/bash

# Variables
SRC_DIR="src/main/java"
TEST_DIR="src/test/java"
BIN_DIR="bin"
JAR_FILE="uniquehands.jar"

# Create bin directory if it doesn't exist
mkdir -p "$BIN_DIR"

# Compile the main source code
echo "Compiling main classes..."
javac -d "$BIN_DIR" "$SRC_DIR"/edu/canisius/csc213/project1/*.java

# Compile the test classes
echo "Compiling test classes..."
javac -cp ".:lib/junit-5.8.2.jar" -d "$BIN_DIR" "$TEST_DIR"/edu/canisius/csc213/project1/*.java

# Create JAR file
echo "Creating JAR file..."
jar cvf "$JAR_FILE" -C "$BIN_DIR" .

# Run the program
echo "Running UniqueHands simulation..."
java -cp "$BIN_DIR" edu.canisius.csc213.project1.UniqueHands