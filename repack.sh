export CLASSPATH=target/wikitools-0.0.1.jar
export JOPT="-Xms256m -Xmx512m -server -XX:+UseParallelGC"

java $JOPT ru.fuxx.Repack $*
