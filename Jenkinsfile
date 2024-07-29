pipeline {
    agent any
    tools {
        maven 'maven-3.9'
    }

    stages {
        stage('build jar') {
            steps {
                script {
                    echo 'build jar of application'
                    sh 'mvn package'
                }
            }
        }
        stage('build image') {
            steps {
                script {
                    echo 'build docker image of application'
                    withCredentials([
                        usernamePassword(credentialsId: 'docker-hub-repo', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD') 
                    ]){
                        sh 'docker build -t isfandiyors/maven-demo-app:jma-2.0 .'
                        sh "echo $PASSWORD | docker login -u $USERNAME --password-stdin" 
                        sh 'docker push isfandiyors/maven-demo-app:jma-2.0'
                    }
                }
            }
        }
        stage('deploy') {
            steps {
                script {
                    echo 'deploying application'
                }
            }
        }
    }
}
