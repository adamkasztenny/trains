# Trains
Java 8 and [sbt](https://www.scala-sbt.org/download.html) are needed to run this project.

To run it:
```
./run.sh <graph arguments>
```

e.g.

```
./run.sh AB5 BC4 CD8 DC8 DE6 AD5 CE2 EB3 AE7
```

and to run tests:
```
./test.sh
```
which generates a coverage report with [Scoverage](https://github.com/scoverage/sbt-scoverage).
