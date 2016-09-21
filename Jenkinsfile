def cloudunit

stage "Init SCM"
node {
    checkout scm
    cloudunit = load 'cloudunit.groovy'
    cloudunit.install_cli()
}

stage "Build"
node {
    env.PATH = "${tool 'NODE630'}/bin:${env.PATH}"
    cloudunit.mvn 'clean package -DskipTests'
}

stage "Create FrontWeb"
node {
    cloudunit.setValue('frontweb/js/app.js', 'CHEMIN_BACKEND', 'backend${BRANCH_NAME}${BUILD_ID}-johndoe-admin.g2c.cloudunit.dev')
    cloudunit.compress('frontweb', 'frontweb')
    cloudunit.callAction 'cloudunit/actions/frontweb.cmd'
}

stage "Create Backend"
node {
    cloudunit.callAction 'cloudunit/actions/backend.cmd'
}

stage "Deploy applications"
node {
    cloudunit.callAction 'cloudunit/actions/deploy-applications.cmd'
}

