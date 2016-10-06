# Status

## Cobertura

Last Check: 2016.10.06

Used Version: 2.1.1

Latest Version: 2.1.1

Link: http://cobertura.github.io/cobertura/

Purpose: Code coverage with SonarQube.

## Commons Lang

Last Check: 2016.10.06

Used Version: 3.2.1

Latest Version: 3.4

Link: https://commons.apache.org/proper/commons-lang/

Purpose: Some classes use the HashCodeBuilder and EqualsBuilder.

## DB4O

Latest Check: 2016.10.06

Used Version: 8.0

Latest Version: 8.0 (discontinued since 2014)

Link: https://en.wikipedia.org/wiki/Db4o

Purpose: Some legacy tests compared the performance of the persistence framework with the performance of DB4O.

## JUnit

Latest Check: 2016.10.06

Used Version: 4.11

Latest Version: 4.12

Link: http://junit.org/junit4/

Purpose: Tests (not only unit tests) are implemented with this framework.

Note: Changes regarding test runners (see https://github.com/junit-team/junit4/pull/763 ) should be evaluated. A migration as soon as possible is pending.

## MarkdownJ

Latest Check: 2016.10.06

Used Version: ?

Latest Version: ? (no development activities since 2014)

Link: https://github.com/myabc/markdownj

Purpose: Evaluation of the capabilities of this library. There is a need to generate structured and formatted html documentation from markdown files in the build and deployment process.

## Sonar Ant Task

Latest Check: 2016.10.06

Used Version: 2.4

Latest Version: 2.4.1

Link: http://docs.sonarqube.org/display/SCAN/Analyzing+with+SonarQube+Scanner+for+Ant

Purpose: Integration of static code analysis into build and deployment processes.

Note: There have been updates to SonarQube and the sonar ant task. A migration to the lastest version is pending.