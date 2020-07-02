# jeonserver

### Build

Requirements:
- openjdk 11 or higher
- maven  3.6.3 or higher

#### Regular jar
```shell script
mvn package
```

#### Uber jar (All-in-one jar)
```shell script
mvn package -Dquarkus.package.uber-jar=true
```

#### Docker
Build
```shell script
mvn package -Dquarkus.container-image.build=true
```

### Run
#### Regular jar
```shell script
java -jar target/jeonserver-X.X.X-runner.jar
```

#### Uber-jar (All-in-one jar)
```shell script
java -jar target/jeonserver-X.X.X-runner.jar
```

#### Docker
```shell script
docker run -v /path/to/config:/config -p 8090:8080 user/jeonserver:X.X.X
```

Regular jar contains a runner and a lib folder with all dependencies while uber-jar include the runner
and all dependencies in the one jar file.

Configuration file should be placed at `$PWD/config/application.yml` so the working dir looks as:
#### Regular jar
```
<app>:
 |- config
 |     \- application.yml
 |- jeonserver-X.X.X-runner.jar
 \- lib
     |- libA.jar
     |- libB.jar
     |- ...
     \- libZ.jar
```

#### Uber-jar
```
<app>:
 |- config
 |     \- application.yml
 \- jeonserver-X.X.X-runner.jar
```

#### Docker
For docker image application.yml should be mapped to the /config folder in the image.

