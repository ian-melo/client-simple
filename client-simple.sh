#!/bin/bash
SOURCE="${BASH_SOURCE[0]}"
while [ -h "$SOURCE" ]; do # resolve $SOURCE until the file is no longer a symlink
  DIR="$( cd -P "$( dirname "$SOURCE" )" >/dev/null 2>&1 && pwd )"
  SOURCE="$(readlink "$SOURCE")"
  [[ $SOURCE != /* ]] && SOURCE="$DIR/$SOURCE" # if $SOURCE was a relative symlink, we need to resolve it relative to the path where the symlink file was located
done
DIR="$( cd -P "$( dirname "$SOURCE" )" >/dev/null 2>&1 && pwd )"

POM_PATH="$DIR"
JAR="$(mvn -q -f $POM_PATH -Dexec.executable=echo -Dexec.args='${project.artifactId}-${project.version}.jar' --non-recursive exec:exec 2>&1)"
JAR_PATH="$DIR/target/$JAR"

mvn clean install -f $POM_PATH -DskipTests=true 2>&1
java -jar $JAR_PATH
