#!/bin/bash

# This script automates the build, run, and cleanup process for the Java application.
# It is designed to be executed from within the 'praktische-umsetzung' directory.

# Exit the script immediately if any command fails
set -e

# --- 1. Compile and Package ---
# The `mvn clean package` command first cleans the project's target directory,
# then compiles the source code, and finally packages it into a JAR file.
echo ">>> STEP 1: Compiling and packaging the application..."
mvn clean package

# --- 2. Run the Application ---
# We execute the main class from the newly created JAR file.
# The classpath (-cp) is set to the JAR in the 'target' directory.
echo ""
echo ">>> STEP 2: Running the application..."
java -cp target/funktionales-sortieren-1.0-SNAPSHOT.jar ch.bbw.m323.Main

# --- 3. Clean Up ---
# The `mvn clean` command removes the 'target' directory, deleting all
# compiled classes and the JAR file to keep the project directory clean.
echo ""
echo ">>> STEP 3: Cleaning up build artifacts..."
mvn clean

echo ""
echo ">>> All steps completed successfully."
