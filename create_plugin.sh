#!/bin/bash

echo -n "Enter the plugin name: "
read plugin_name

if [ -z "$plugin_name" ]; then
    echo "Plugin name cannot be empty."
    exit 1
fi

artifactId="$plugin_name"
baseGroupId="com.prayujt.minecraft"
groupId="${baseGroupId}.${plugin_name%Plugin}"

mvn archetype:generate \
    -DgroupId="$groupId" \
    -DartifactId="$artifactId" \
    -DarchetypeArtifactId=maven-archetype-quickstart \
    -DinteractiveMode=false

if [ -d "$artifactId" ]; then
    cd "$artifactId"

    cat > pom.xml <<EOL
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd">
  <modelVersion>4.0.0</modelVersion>
  <groupId>$groupId</groupId>
  <artifactId>$artifactId</artifactId>
  <packaging>jar</packaging>
  <version>1.0</version>
  <name>$artifactId</name>
  <url>http://maven.apache.org</url>

  <repositories>
    <repository>
      <id>papermc-repo</id>
        <url>https://repo.papermc.io/repository/maven-public/</url>
    </repository>
  </repositories>

  <dependencies>
    <dependency>
        <groupId>io.papermc.paper</groupId>
        <artifactId>paper-api</artifactId>
        <version>1.20.1-R0.1-SNAPSHOT</version>
      <scope>provided</scope>
    </dependency>
  </dependencies>
</project>
EOL

    echo "pom.xml has been updated with the new content."

    rm -rf src/test

    mkdir -p src/main/resources
    cat > src/main/resources/plugin.yml <<EOL
name: $plugin_name
version: 1.0
api-version: 1.20

main: $groupId.PluginCore
EOL

    echo "plugin.yml has been created with the new content."

    pluginCorePath="src/main/java/${groupId//.//}/PluginCore.java"
    mkdir -p "$(dirname "$pluginCorePath")"
    cat > "$pluginCorePath" <<EOL
package ${groupId};

import org.bukkit.plugin.java.JavaPlugin;

public class PluginCore extends JavaPlugin {

    @Override
    public void onEnable(){
        // Fired when server plugin is first enabled
        getLogger().info("$plugin_name has been enabled.");
    }

    @Override
    public void onDisable(){
        // Fired when the server stops and disables all plugins
    }
}
EOL

    echo "PluginCore.java has been created with the new content."

    rm -f "src/main/java/${groupId//.//}/App.java"
    touch .projectile
else
    echo "Error: Directory $artifactId does not exist."
    exit 1
fi

