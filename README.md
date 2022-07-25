# Rest API

## Conf & Instalation

* macOs Catalina(10.15.7)
* IntelliJ (version:2019.3.1)
* Languange: java (version:17 JDK)
* Maven project
* NodeJS (14)


## Before the start

* Import changes for Maven project
* Go to File - Project structure - SDKs and choose Java 17
* Open IntelliJ and clone this project
* Open the terminal and run the script:

```

mvn clean install

```


## Start

* Right-click on the "RestApi" file(src/test/java/apiTest) then choose run the "Feature:RestApi" option


## Reasons behind the chosen framework and pattern(s)

* Maven helps to download dependencies in java projects, you do not have to download any jar file.
Just add  the versions of the jar files in the pom.xml to execute your tests.



## Next possible steps for improvements
* Cucumber can be used for requests and responses
* Mvn commands can be added to execute test steps separately/easily.
