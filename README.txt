You must have Maven installed in order to proceed. And to connect to MySQL database go to src/main/java/app/DBUtilities.java and in String url change existing user and password to yours.

To run the command-line application:
1. download and unzip
2. in command prompt navigate to where the app was downloaded and then to the folder which contains pom.xml file, e.g. cd downloads\ConsoleApp-master
3. type mvn compile to compile the project
4. type mvn exec:java -Dexec.mainClass=app.Main to execute the project
