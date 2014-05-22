Maven 
~/Documents/Tools/apache-maven-3.2.1/bin/mvn archetype:generate -DarchetypeArtifactId=jersey-quickstart-grizzly2 \
-DarchetypeGroupId=org.glassfish.jersey.archetypes -DinteractiveMode=false \
-DgroupId=com.leeloo -DartifactId=virtual-is-viral -Dpackage=com.leeloo \
-DarchetypeVersion=2.8

Clean:
~/Documents/Tools/apache-maven-3.2.1/bin/mvn clean

Run:
~/Documents/Tools/apache-maven-3.2.1/bin/mvn jetty:run

~/Documents/Tools/apache-maven-3.2.1/bin/mvn jetty:run-war

proxy
git config --global http.proxy http://s7218:hamlet123456@iffipx.ifint.biz:8080

git config --global https.proxy http://s7218:hamlet123456@iffipx.ifint.biz:8080