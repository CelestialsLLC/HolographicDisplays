mkdir spigot
cd spigot

curl -o BuildTools.jar https://hub.spigotmc.org/jenkins/job/BuildTools/lastSuccessfulBuild/artifact/target/BuildTools.jar

java -jar BuildTools.jar --rev 1.12
java -jar BuildTools.jar --rev 1.11
java -jar BuildTools.jar --rev 1.10
java -jar BuildTools.jar --rev 1.9.4
java -jar BuildTools.jar --rev 1.9
java -jar BuildTools.jar --rev 1.8.8
java -jar BuildTools.jar --rev 1.8.3
java -jar BuildTools.jar --rev 1.8
