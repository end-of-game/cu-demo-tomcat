def callAction(args) {
    print 'sed "s/#APPNAME/${BRANCH_NAME}${BUILD_ID}/g" ' + "${args} > ${args}.1"
    sh 'sed "s/#APPNAME/${BRANCH_NAME}${BUILD_ID}/g" ' + "${args} > ${args}.1"

    sh "cat ${args}.1"
    java "-jar /tmp/cloudunit-cli.jar --cmdfile ${args}.1"
    sh "rm -f ${args}.1"
}

def setValue(file, regexp, value) {
    print 'sed -i "s/' + "${regexp}/${value}/g" + '" ' + "${file}"
    sh 'sed -i "s/' + "${regexp}/${value}/g" + '" ' + "${file}"
    sh "cat ${file}"
}

def install_cli() {
    sh "wget https://github.com/Treeptik/cloudunit/releases/download/1.0/CloudUnitCLI.jar -O /tmp/cloudunit-cli.jar"
}

def mvn(args) {
    sh "${tool 'M3'}/bin/mvn ${args}"
}

def java(args) {
    sh "${tool 'JAVA8'}/bin/java ${args}"
}

def npm(args) {
    sh "${tool 'NODE630'}/bin/npm ${args}"
}

def bower(args) {
    sh "${tool 'NODE630'}/bin/bower ${args}"
}

def gulp(args) {
    sh "${tool 'NODE630'}/bin/gulp ${args}"
}

def compress(target, sources) {
    sh "ls ${sources}"
    sh "tar cvf ${target}.tar -C ${sources} . "
    sh "gzip -f ${target}.tar"
}

this