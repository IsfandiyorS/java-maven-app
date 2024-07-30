def buildJar() {
    echo 'building jar file of application'
    sh 'mvn clean package'
}

def buildImage(String IMAGE_VERSION) {
    echo 'building docker image of application'
    withCredentials([
        usernamePassword(credentialsId: 'docker-hub-repo', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')
    ]) {
        sh "docker build -t isfandiyors/maven-demo-app:$IMAGE_VERSION ."
        sh 'echo $PASSWORD | docker login  -u $USERNAME --password-stdin'
        sh "docker push isfandiyors/maven-demo-app:$IMAGE_VERSION"
    }
}

def buildDeploy() {
    echo 'deploy application'
}

return this
