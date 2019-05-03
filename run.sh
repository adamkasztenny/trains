#!/bin/bash

GRAPH="$@"
echo "Running with graph $GRAPH"

sbt clean update compile "run $GRAPH"
