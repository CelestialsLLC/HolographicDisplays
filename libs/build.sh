mkdir spigot
cd spigot

curl -o BuildTools.jar https://hub.spigotmc.org/jenkins/job/BuildTools/lastSuccessfulBuild/artifact/target/BuildTools.jar

java -jar BuildTools.jar --rev 1.8.8