
Tools
=====

Oracle JDeveloper
-----------------

Version: 12.1.3.0.0

Purpose: IDE

Link: http://www.oracle.com/technetwork/developer-tools/jdev/overview/index.html


MagicDraw
---------

Version:

Purpose: UML Editor
Link: http://www.nomagic.com/products/magicdraw.html


Notepad++
---------

Version:

Purpose: Text Editor

Link: https://notepad-plus-plus.org/


SonarQube
---------

Version: 5.3

Purpose: Static Code Analysis

Link: http://www.sonarqube.org/


7-Zip
----

Version: 9.2

Purpose: Archive Tool

Link: http://www.7-zip.de/


Java
====

Java Development Kit
--------------------

Version: 1.7 & 1.8

Purpose: Programming Language

Link: http://www.oracle.com/technetwork/java/javase/downloads/index.html


Java Libraries
==============

A list of required Java libraries / 3rd party tools:

JUnit
-----

Version: 4.11

Purpose: Test framework

Link: http://junit.org/


Ant
---

Version: 1.9.2

Purpose: Build Tool

Link: http://ant.apache.org/


Cobertura
---------

Version: 2.1.1

Purpose: Code Coverage Measurement

Link: http://cobertura.github.io/cobertura/


Sonar Ant Task
--------------

Version: 2.4

Purpose: Analysing code with SonarQube

Link: http://docs.sonarqube.org/display/SCAN/Analyzing+with+SonarQube+Scanner+for+Ant


Note: How to embed a specific JDK depends on the actual IDE. It might be easier
to create a symbolic link (e.g. .\dependencies\jdk\) and to reference that
symbolic link within the IDE. This way the configuration for all subprojects
doesn't need to be changed on updating the JDK.

Note: In Windows environments simply create a symbolic link (i.e.
C:\Program Files\Java\jdk1.8.0_171) that references the actual JDK. This way
no further change to the project configuration is needed.

Note: Using a different IDE requires recreating the project and subprojects
within the other IDE. Keep a copy of your project configuration. Updating the
local project should be easy then.

Note: Before checking in changes to the repository clean up the project and
remove garbage created by the IDE.
