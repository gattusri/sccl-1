FROM tomcat:8
LABEL app=my-app
COPY target/*.war /usr/local/tomcat/webapps/sample-azure-docke.war
# dummy commit

