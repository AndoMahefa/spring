# se mettre dans la repertoire des classes
cd "D:/Logiciel/Tomcat/webapps/Sprint4 jar/WEB-INF/classes"
# construire le fichier jar
jar cvf framework_etu1896.jar -C classes/ .
# copie du jar dans le projet de test
cd ../
copy etu1896.jar "D:/Logiciel/Tomcat/webapps/Sprint4/WEB-INF/lib/"