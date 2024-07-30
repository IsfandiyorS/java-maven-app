
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
    }
}
