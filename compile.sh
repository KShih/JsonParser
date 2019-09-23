#!/usr/bin/env bash

# TODO - ensure `target/classes` exists (creating it if it doesn't)
# use -p to create any parent dir if necessary
mkdir -p target/classes/
# TODO - compile all the Java files within the project and output them into `target/classes`
 javac -cp src/main/java/ -d target/classes/ src/main/java/edu/nyu/cs9053/homework2/*.java
