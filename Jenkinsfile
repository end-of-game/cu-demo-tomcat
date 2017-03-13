def host = "https://cu02.cloudunit.io"
def username = "johndoe"
def password = "abc2015"
def back
def front

stage "Init SCM"
node {
    checkout scm

    branch = env.BRANCH_NAME.replaceAll(/.*\//,"").toLowerCase().take(8)
    back = "back-${branch}"
    front = "front-${branch}"
}

stage "Build"
node {
    sh 'mvn clean package'
    sh "sed -i 's/CHEMIN_BACKEND/${back}-${username}.cu02.cloudunit.io/g' frontweb/js/app.js"
    sh "tar -C frontweb -zcf frontweb.tar.gz ."

    archiveArtifacts "backend/target/ROOT.war,frontweb.tar.gz"
    junit "backend/target/surefire-reports/*.xml"
}

stage "Create Front"
node {
    
    cloudunit(host, username, password, """
        rm-app --scriptUsage --name ${front}
        create-app --name ${front} --type apache-2-2
        
        open-port --nature web --port 80
        open-explorer --containerName ${front}-${username}
        change-directory /usr/local/apache2/htdocs
        upload-file --path frontweb.tar.gz
        unzip --file /usr/local/apache2/htdocs/frontweb.tar.gz
        list-files
        close-explorer
    """)
}

stage "Create Backend"
node {
    cloudunit(host, username, password, """
        rm-app --scriptUsage --name ${back}
        create-app --name ${back} --type tomcat-8
        add-module --name mysql-5-5
        add-jvm-option '-Dspring.profiles.active=embedded'

        deploy --path backend/target/ROOT.war --openBrowser false
    """)
}

