Read Me
=======

The script .\Batch\Dos-Sonar\startSonar.bat can be invoked to start a local instance of SonarQube.
A prerequisite for starting SonarQube is that the script .\Batch\Dos-Sonar\setenvJ7.bat runs without
error. Check both scripts for correct paths (i.e. paths to JDK, Ant and SonarQube).

Development is done via JDeveloper (see project files *.jws and *.jpr) but any other IDE is suitable
as well. The development project is split into several distinct sub projects (see folder .\Utilities)
which are also identified by individual package structures). Check the UML model for dependencies
between the sub projects. Test code is also stored separately.

The script .\Batch\Ant-Sonar\analysis-instrumented.xml can be invoked to compile all sources including
tests. Tests are then executed. Test reports, coverage reports, source code and the binaries are then
provided to perform an analysis via SonarQube.

The script .\Batch\Ant-Deploy\deploy.xml is used to build jars according to the actual sources.

When executing scripts change to the directory where the script is located. The scripts work with
relative paths and expect a certain directory structure. Changes to the project's directory
structure requires changing the scripts.

The file .\Links\Links.html contains various useful links (e.g. local SonarQube instance, reports,
etc.).
