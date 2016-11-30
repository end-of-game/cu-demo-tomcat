def host = "http://192.168.50.1:8080/"
def username = "johndoe"
def password = "abc2015"

stage "Init SCM"
node {
    checkout scm
}

stage "Build"
node {
    sh 'mvn clean package -DskipTests'
}

stage "Create Front"
node {
    sh "sed -i 's/CHEMIN_BACKEND/backend${BRANCH_NAME}-johndoe-admin.cloudunit.dev/g' frontweb/js/app.js"
    sh "tar -C frontweb -zcf frontweb.tar.gz"
    
    cloudunit(host, username, password, """
        rm-app --scriptUsage --name front${BRANCH_NAME}
        create-app --name front${BRANCH_NAME} --type apache-2-2
        
        open-port --nature web --port 80
        open-explorer --containerName dev-johndoe-front${BRANCH_NAME}-apache-2-2
        change-directory /usr/local/apache2/htdocs
        upload-file --path frontweb.tar.gz
        unzip --file /usr/local/apache2/htdocs/front.tar.gz
        list-files
        close-explorer
    """)
}

stage "Create Backend"
node {
    cloudunit(host, username, password, """
        rm-app --scriptUsage --name back${BRANCH_NAME}
        create-app --name back${BRANCH_NAME} --type tomcat-8
        add-module --name mysql-5-5
        add-jvm-option '-Dspring.profiles.active=embedded'

        deploy --path backend/target/ROOT.war --openBrowser false
    """)
}

