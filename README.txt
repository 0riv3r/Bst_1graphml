
*** java ***
------------

MUST RUN WITH JDK 1.8!!!

# list all the installed java versions
$ /usr/libexec/java_home -V

# switch to java 1.8 temporarily
$ export JAVA_HOME=`/usr/libexec/java_home -v 1.8`


*** graphwalker ***
-------------------

download into lib/
from: https://graphwalker.github.io/
graphwalker-cli-4.2.0.jar
graphwalker-studio-4.2.0.jar


*** Maven ***
-------------

mvn -version
Apache Maven 3.6.3
...

clean all built/generated files
-------------------------------
mvn clean

generate all the required files under target/
---------------------------------------------
mvn graphwalker:generate-sources

run graphwalker online test via maven:
-------------------------------------- 
mvn graphwalker:test

run unit-tests  under test/
---------------------------
mvn test


run all together (after setting up the java version!):
------------------------------------------------------
mvn clean graphwalker:generate-sources graphwalker:test


==================================================================================


Launch the model editor
======================
java -jar C:\workspace\bst\lib\graphwalker-studio-4.2.0.jar
open http://localhost:9090/studio.html in a web browser.


generate offline testcase
=========================

java -jar lib/graphwalker-cli-4.2.0.jar offline  -m src/main/resources/com/cyberark/bst/BstModel.graphml "random(edge_coverage(100))"

java -jar lib/graphwalker-cli-4.2.0.jar offline  -m src/main/resources/com/cyberark/bst/BstModel.graphml "random(edge_coverage(100))"  | jq '.currentElementName'

java -jar lib/graphwalker-cli-4.2.0.jar offline -u -o  -d 147945811993279  -m src/main/resources/com/cyberark/bst/BstModel.graphml "random(edge_coverage(100))"  | jq '.currentElementName'


Generate test code for the model
================================
java -jar lib/graphwalker-cli-4.2.0.jar source -i src/main/resources/com/cyberark/bst/BstModel.graphml src/main/templates/java.template > reports/generated/BstGwTest.java


run online test as REST service
===============================

java -jar lib/graphwalker-cli-4.2.0.jar -d all online -s RESTFUL -m src/main/resources/com/cyberark/bst/BstModel.graphml "random(edge_coverage(100))"


Run the graphwalker test file from the commandline with enabling assertion:
PS C:\workspace\bst\bin\test> java -ea BstGwTest

