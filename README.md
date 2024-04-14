# maven-archetype-hexagonal

Ejecutar esto para instalar el artefacto:

1.- Hacer la instalación:

> mvn clean install

2- Luego hacer la publicación al m2 local:

```agsl
mvn install:install-file \
"-Dfile=target/maven-archetype-hexagonal-1.0-SNAPSHOT.jar" \
"-DgroupId=com.kathesama" \
"-DartifactId=maven-archetype-hexagonal" \
"-Dversion=1.0-SNAPSHOT" \
"-Dpackaging=jar" \
"-DgeneratePom=true" \
"-DcreateChecksum=true"
```

Al momento de usar el archetype tomar en cuenta esto:

```agsl
mvn archetype:generate \
-DgroupId=com.mygroup \
-DartifactId=my-artifactId \
-DarchetypeGroupId=com.kathesama \
-DarchetypeArtifactId=maven-archetype-hexagonal \
-DarchetypeVersion=1.0-SNAPSHOT
```
