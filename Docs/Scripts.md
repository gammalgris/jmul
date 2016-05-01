
Scripts
=======

General
-------

The project comes with various build and deployment scripts, as well as
scripts which automate certain development tasks.

Relevant Scripts
----------------

location | target | description
---------|--------|------------
./Ant-Sonar/analysis-instrumented.xml | coverage | Compiles the sources and tests. Executes the tests and measures the test coverage. Initiates an analysis by SonarQube.
./Ant-Build/build-javadoc.xml | build | Builds the javadoc but requires that ./Ant-Build/build.xml is executed first.
./Ant-Deploy/deploy.xml | deploy | Compiles the sources, builds the javadoc and builds .jar files accordingly.
./Ant-Execute/run-instrumented-tests.xml | coverage | Compiles the sources and tests. Rebuilds the test data from scratch. Executes the tests and measures the test coverage.
./Ant-Misc/clean.xml | clean | Cleans all temporary folders.
./Dos-Misc/clean.bat |  | Cleans the project directory. It's recommended to use this script prior to checking in changes.
./Dos-Sonar/startSonar.bat |  | Starts a local instance of SonarQube.
