
def gv

pipeline {
    agent any
    tools {
        maven 'maven-3.9'
    }

    stages {

        stage('increment version') {
            steps {
                script {
                    echo 'incrementing version ...'
                    sh 'mvn build-helper:parse-version versions:set \
                        -DnewVersion=\\\${parsedVersion.majorVersion}.\\\${parsedVersion.minorVersion}.\\\${parsedVersion.nextIncrementalVersion} \
                        versions:commit'
                    def matcher = readFile('pom.xml') =~ '<version>(.+)</version>'
                    def version = matcher[0][1]
                    env.IMAGE_NAME = "$version-$BUILD_NUMBER"
                }
            }
        }

        stage('init') {
            steps {
                script {
                    gv = load 'script.groovy'
                }
            }
        }

        stage('build jar') {
            steps {
                script {
                    gv.buildJar()
                }
            }
        }
        stage('build image') {
            steps {
                script {
                    gv.buildImage(env.IMAGE_NAME)
                }
            }
        }
        stage('deploy') {
            steps {
                script {
                    gv.buildDeploy()
                }
            }
        }
        stage('commit version update'){
            steps{
                script {
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
            }
        }
    }
}
