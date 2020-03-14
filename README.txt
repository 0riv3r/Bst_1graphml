

*** check the project_structure.png file under the project's root folder

******************************************
***             setup env              ***
******************************************

yum -y update
reboot


java on CentOS7
===============

MUST RUN WITH JDK 1.8!!!

# list all the installed java versions
$ /usr/libexec/java_home -V

setup java:
-----------
$ yum search java | grep openjdk

$ yum install java-1.8.0-openjdk-headless.x86_64

$ yum install java-1.8.0-openjdk-devel.x86_64

# run and choose #1:
$ update-alternatives --config java #pick java 1.8

# run and choose #1:
$ update-alternatives --config javac #pick java 1.8

or switch to java 1.8 temporarily (no need if you did the above):
$ export JAVA_HOME=`/usr/libexec/java_home -v 1.8`


setup maven on CentOS7
======================

$ cd Downloads
$ wget https://www-us.apache.org/dist/maven/maven-3/3.6.3/binaries/apache-maven-3.6.3-bin.tar.gz
$ sudo tar xf apache-maven-3.6.3-bin.tar.gz -C /opt
$ sudo ln -s /opt/apache-maven-3.6.3 /opt/maven

$ sudo nano /etc/profile.d/maven.sh
    export M2_HOME=/opt/maven
    export MAVEN_HOME=/opt/maven
    export PATH=${M2_HOME}/bin:${PATH}

$ sudo chmod +x /etc/profile.d/maven.sh

$ source /etc/profile.d/maven.sh

$ mvn -version



******************************************
***            graphwalker             ***
******************************************

download into lib/
from: https://graphwalker.github.io/
graphwalker-cli-4.2.0.jar

$ cd /root/Bst_1graphml/lib
$ wget https://github.com/GraphWalker/graphwalker-project/releases/download/4.2.0/graphwalker-cli-4.2.0.jar

*** Maven ***
-------------

$ mvn -version
Apache Maven 3.6.3
...

clean all built/generated files
-------------------------------
$ bmvn clean

generate all the required files under target/
---------------------------------------------
$ bmvn graphwalker:generate-sources

run graphwalker online test via maven:
-------------------------------------- 
$ mvn graphwalker:test

run unit-tests  under test/
---------------------------
$ mvn test


run all together (after setting up the java version!):
------------------------------------------------------
$ mvn clean graphwalker:generate-sources graphwalker:test


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


**************************************************************
****     run GraphWalker online test as REST service      ****
**************************************************************

open firewall in port 8887
--------------------------
$ firewall-cmd --zone=public --add-port=8887/tcp --permanent
$ sudo firewall-cmd --reload
$ firewall-cmd --list-all


# Launch the GraphWalker REST service and load the model:
$ java -jar lib/graphwalker-cli-4.2.0.jar -d all online -s RESTFUL -m src/main/resources/com/cyberark/bst/BstModel.graphml "random(edge_coverage(100))"

REST APIs:
https://github.com/GraphWalker/graphwalker-project/wiki/Rest-API-overview

Postman
=======
The Postman data is at lib/GraphWalker.postman_collection.json
you can copy its content and paste in your Postman import.


CLI HTTP client
===============
https://httpie.org/

$ yum install httpie

$ http GET  localhost:8887/graphwalker/hasNext



# ignore these 2 lines:
# Run the graphwalker test file from the commandline with enabling assertion:
# PS C:\workspace\bst\bin\test> java -ea BstGwTest

