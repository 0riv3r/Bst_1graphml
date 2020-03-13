ssh -i ~/.ssh/mac_aws.pem ubuntu@ec2-3-11-13-13.eu-west-2.compute.amazonaws.com

# list all the installed java versions
$ /usr/libexec/java_home -V

# switch to java 1.8 temporarily
$ export JAVA_HOME=`/usr/libexec/java_home -v 1.8`

mvn clean
mvn graphwalker:generate-sources
mvn graphwalker:test
mvn test

mvn clean graphwalker:generate-sources graphwalker:test