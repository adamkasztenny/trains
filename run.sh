#!/bin/bash

sbt clean update compile "run $@"
