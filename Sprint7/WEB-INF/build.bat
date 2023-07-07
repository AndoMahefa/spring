#compile
cd "D:/Logiciel/Tomcat/webapps/Sprint7_jar/src"
javac -d D:/Logiciel/Tomcat/webapps/Sprint7_jar/classes annotation/Anno_Url.java  
javac -d D:/Logiciel/Tomcat/webapps/Sprint7_jar/classes etu1896/framework/servlet/FrontServlet.java  
javac -d D:/Logiciel/Tomcat/webapps/Sprint7_jar/classes etu1896/framework/*.java  

# se mettre dans la repertoire contenant les packages et les .class
cd "D:/Logiciel/Tomcat/webapps/Sprint7_jar/classes"

# construire le fichier jar
jar -cvf ../framework_etu1896.jar .

# copie du jar dans le projet de test
cd ../
copy framework_etu1896.jar "D:/Logiciel/Tomcat/webapps/Sprint7/WEB-INF/lib/"


cd "D:/Logiciel/Tomcat/webapps/Sprint7"

jar -cvf test_frame.war .