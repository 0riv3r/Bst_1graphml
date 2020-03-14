
MUST RUN WITH JDK 1.8!!!
------------------------

# list all the installed java versions
$ /usr/libexec/java_home -V

# switch to java 1.8 temporarily
$ export JAVA_HOME=`/usr/libexec/java_home -v 1.8`


Maven
=====
mvn -version
Apache Maven 3.6.3
...

mvn clean
mvn graphwalker:generate-sources
mvn graphwalker:test
mvn test

mvn clean graphwalker:generate-sources graphwalker:test

==================================================================================


https://learntocodetogether.com/binary-search-tree-java/
https://github.com/namvdo/data-structures-and-algorithms-in-java/blob/master/binary-search-tree/Bst.java

https://github.com/microsoft/vscode-java-test/issues/851


Launch the model editor
======================
java -jar C:\workspace\bst\lib\graphwalker-studio-4.2.0.jar
open http://localhost:9090/studio.html in a web browser.


generate offline testcase
=========================

java -jar lib/graphwalker-cli-4.2.0.jar offline  -m src/main/resources/com/cyberark/bst/BstModel.graphml "random(edge_coverage(100))"

java -jar lib/graphwalker-cli-4.2.0.jar offline  -m src/main/resources/com/cyberark/bst/BstModel.graphml "random(edge_coverage(100))"  | jq '.currentElementName'

java -jar lib/graphwalker-cli-4.2.0.jar offline -u -o  -d 147945811993279  -m src/main/resources/com/cyberark/bst/BstModel.graphml "random(edge_coverage(100))"  | jq '.currentElementName'


# Generate test code for the model:
java -jar lib/graphwalker-cli-4.2.0.jar source -i src/main/resources/com/cyberark/bst/BstModel.graphml src/main/templates/java.template > reports/generated/BstGwTest.java


online
======

java -jar lib/graphwalker-cli-4.2.0.jar -d all online -s RESTFUL -m src/main/resources/com/cyberark/bst/BstModel.graphml "random(edge_coverage(100))"


Run the graphwalker test file from the commandline with enabling assertion:
PS C:\workspace\bst\bin\test> java -ea BstGwTest

