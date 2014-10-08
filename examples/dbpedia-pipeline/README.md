DBpedia pipeline example
========================

A simple example pipeline using dbpedia-spotlight.

The example is configured to use the public spotlight demo, but it is of course preferable to use a local instance of the service.

The dependency of the spotlight annotator can currently be satisfied by cloning https://github.com/jgrivolla/dbpedia-spotlight and running `git checkout fix-uima-annotator`, and then `mvn clean install` from within the `uima/` directory.

Instructions
------------

### Install Spotlight Annotator
```bash
git clone https://github.com/dbpedia-spotlight/dbpedia-spotlight.git
cd dbpedia-spotlight
cd uima
mvn clean install
```

### Run Pipeline

1. `git clone https://github.com/EUMSSI/EUMSSI-tools.git`
2. In eclipse, create new Java project from location `EUMSSI-tools/examples/dbpedia-pipeline`.
3. Right click -> Configure... -> Convert to maven project
3. Run `DbPediaPipeline` from Eclipse
 1. possibly remove dependency on ConceptMapper (needed for BasicPipeline, but not for DbPediaPipeline)
