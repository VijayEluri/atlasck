set "group=com.atlasck"
set "artifact=persistence-layer"
set "version=0.1.0.BUILD-SNAPSHOT"
set "finalName=%artifact%-%version%.jar"

mvn clean package -DskipTests=true
mvn install:install-file -Dfile=target/%finalName% -DgroupId=%group% \
	-DartifactId=%artifact% -Dversion=%version% -Dpackaging=jar
