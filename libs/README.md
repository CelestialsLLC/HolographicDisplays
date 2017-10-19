You must build several version of craftbukkit first to compile. These should be put into the `spigot` directory.

The file names should start with `spigot` and end with `.jar` for our build script to find it automatically! 

1) Grab buildtools

From here: https://www.spigotmc.org/wiki/buildtools/

2) Create requires jars 

You can see the versions required in the build.sh file.

3) Grab other jars not supported by buildtools

You also need to download:
* https://github.com/Sarabveer/CraftBukkit-Spigot-Binary/tree/master/spigot-1.7.5-R0.1
* https://github.com/Sarabveer/CraftBukkit-Spigot-Binary/tree/master/spigot-1.7.9-R0.3
* https://github.com/Sarabveer/CraftBukkit-Spigot-Binary/tree/master/spigot-1.7.2-R0.4
* https://github.com/Sarabveer/CraftBukkit-Spigot-Binary/tree/master/spigot-1.7.10-1.8-R0.1

4) Done!

As long as they are all in the `spigot` directory it will work fine.
