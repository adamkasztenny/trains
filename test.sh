#!/bin/bash

# run all tests, including the acceptance test
sbt clean test

# generate a coverage report that excludes the acceptance test
sbt clean coverage "testOnly *UnitTest *IntegrationTest" coverageReport

echo "See output above for coverage result (including an HTML report)"
