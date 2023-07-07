@REM #compile
cd "D:/Logiciel/Tomcat/webapps/Source/src"
javac -d D:/Logiciel/Tomcat/webapps/Source/classes *.java    

@REM # se mettre dans la repertoire contenant les packages et les .class
cd "D:/Logiciel/Tomcat/webapps/Source/classes"

@REM # construire le fichier jar
jar -cvf ../framework_etu1896.jar .

@REM # copie du jar dans le projet de test
cd ../
move /Y framework_etu1896.jar "D:/Logiciel/Tomcat/webapps/Test/WEB-INF/lib/"

cd "D:/Logiciel/Tomcat/webapps/Test"
jar -cvf test_frame.war .
move /Y test_frame.war "D:/Logiciel/Tomcat/webapps"

cd "D:/Logiciel/Tomcat/webapps/Test/WEB-INF/src"
javac -d D:/Logiciel/Tomcat/webapps/Test/classes *.java    

cd "D:/Logiciel/Tomcat/webapps/Source"
copy build.bat "D:/Logiciel/Tomcat/webapps/Test/WEB-INF"