connect --login johndoe --password abc2015 --host http://192.168.50.1:8080

create-app --name backend-#APPNAME --type tomcat-8
use backend#APPNAME
add-jvm-option "-Dspring.profiles.active=embedded"
add-module --name mysql-5-5

disconnect

