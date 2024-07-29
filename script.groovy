def buildJar() {
    echo 'building jar file of application'
    sh 'mvn package'
}

def buildImage() {
    echo 'building docker image of application'
    withCredentials([
        usernamePassword(credentialsId: 'docker-hub-repo', usernameValue: 'USERNAME', passwordValue: 'PASSWORD')
    ]) {
        sh 'docker build -t isfandiyors/maven-demo-app:jma-2.1 .'
        sh 'echo $PASSWORD | docker login  -u $USERNAME --password-stdin'
        sh 'docker push isfandiyors/maven-demo-app:jma-2.1'
    }
}

def buildDeploy() {
    echo 'deploy application'
}

return this
