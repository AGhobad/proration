How to use:


To compile:
mvn clean compile
or manually compile by javac


javadoc:
 mvn javadoc:javadoc
(see target\site\apidocs)
or
javadoc -d target\classes\javadoc  src\main\java\com\vonage\prorate\engine\*.java


to run:
runit.bat year month day dayOfCycle monthlyFee
e.g.: runit.bat 1999 2 4 21 120 
or
mvn exec:java   -Dexec.args="$*"  -Dexec.mainClass=com.vonage.prorate.main.ProrationMain -Dexec.args=" 1999 2 4 21 120 "


to test:
mvn test









mvn compile

e.g.
runit.bat 1999 1 14 21  100
