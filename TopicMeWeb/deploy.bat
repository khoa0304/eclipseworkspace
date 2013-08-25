call mvn tomcat:undeploy
call echo "Finish undeploying"
call mvn package tomcat:deploy