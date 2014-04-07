DBpedia pipeline example
========================

A simple example pipeline using dbpedia-spotlight.

The example is configured to use the public spotlight demo, but it is of course preferable to use a local instance of the service.

The dependency of the spotlight annotator can currently be satisfied by cloning https://github.com/jgrivolla/dbpedia-spotlight and running `git checkout fix-uima-annotator`, and then `mvn clean install` from within the `uima/` directory.
