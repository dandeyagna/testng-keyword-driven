# testng-keyword-driven
Keyword Driven Framework which uses TestNG internally

## Reqirements
To compile and execute this you need to have Gradle binary

## Directory Structure
* Put Test Cases under src/main/resources/tests

*cat src/main/resources/tests/loginTestSuite/testLoginWithCorrectPassword.csv*


|login|correctUserName|correctPassword|
| ------------- |:-------------:| -----:| ---: |
|**doSomeThing**| | |


## Execute
*gradle run* 

## Development
*gradle cleanEclipse eclipse*
