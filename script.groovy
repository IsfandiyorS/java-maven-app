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

def changeCommit() {
withCredentials([
                            usernamePassword(credentialsId: 'JENKINS_PK', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')
                        ]) {
                            sh 'git config --global user.email "jenkins@example.com"'
                            sh 'git config --global user.name "jenkins"'

                            sh 'git status'
                            sh 'git branch'
                            sh 'git config --list'
                            
                            sh "git remote set-url origin https://${USERNAME}:${PASSWORD}@github.com/IsfandiyorS/java-maven-app.git"
                            sh 'git add .'
                            sh 'git commit -m "CI: version bump"'
                            sh 'git push origin HEAD:jenkins-job'
                        }
}

return this
