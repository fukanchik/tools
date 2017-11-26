export CLASSPATH=target/wikitools-0.0.1.jar
export JOPT="-Xms256m -Xmx512m -server -XX:+UseParallelGC"
export JOPT="$JOPT -Djdk.xml.totalEntitySizeLimit=2147480000"

echo "java $JOPT ru.fuxx.Repack $1 $2 $3"
java $JOPT ru.fuxx.Repack "$@"
