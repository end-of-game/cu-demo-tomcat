connect --login johndoe --password abc2015 --host http://192.168.50.4:8080

use backend#APPNAME
deploy --path backend/target/backend-0.1.war --openBrowser false

use front#APPNAME
open-explorer --containerName g2c-johndoe-front#APPNAME-apache-2-2
enter-directory /usr/local/apache2/htdocs
upload-file --path frontweb.tar.gz
unzip --file frontweb.tar.gz
list-files
close-explorer

disconnect
