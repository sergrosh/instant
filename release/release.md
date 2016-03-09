## Release

#### Process
*   Verify updated / update release string in pom.xml
*   Build jar - mvn clean install
*   Duplicate jar file to /release directory
*   Add information to change log
*   Run new jar with snapshot - java -jar [SNAPSHOT_NAME].jar
*   Package without tests when getting errors mvn package -Dmaven.test.skip=true
