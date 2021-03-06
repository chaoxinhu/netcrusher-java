#!/bin/sh

if [ $# -ne 2 ]
then
    echo "run-tcp-crusher <bind-address:port> <connect-address:port>"
    exit 1
fi

CRUSHER_BIN="$(dirname -- $(readlink -f -- $0))"

export MAVEN_OPTS="-XX:+UseParNewGC -XX:+UseConcMarkSweepGC $MAVEN_OPTS"

mvn \
    exec:java \
    -f "$CRUSHER_BIN/dependencies.xml" \
    -Dlogback.configurationFile="$CRUSHER_BIN/logback.xml" \
    -Dexec.mainClass=org.netcrusher.tcp.main.TcpCrusherMain \
    -Dexec.args="$1 $2"