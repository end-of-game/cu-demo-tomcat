connect --login johndoe --password abc2015 --host http://cuplatform_tomcat_1.tomcat.cloud.unit:8080

create-app --name backend-#APPNAME --type tomcat-8
use backend#APPNAME
add-jvm-option "-Dspring.profiles.active=embedded"
add-module --name mysql-5-5

disconnect

