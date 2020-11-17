FROM java:8
LABEL maintainer = "Iga WIlk"
COPY Laboratorium4.java .
RUN curl -L -o /mysql-connector-java-8.0.13.jar https://repo1.maven.org/maven2/mysql/mysql-connectior-java/8.0.13/mysql-connector-java-8.0.13.jar
RUN javac Laboratorium4.java
CMD["java","-classpath","mysql-connector-java-8.0.13.jar:.","Laboratorium4"]
