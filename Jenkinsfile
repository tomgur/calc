#!groovy
pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo '------------ Building ------------'
                sh "mvn --batch-mode -V -U -e clean compile -Dsurefire.useFile=false"
            }
        }
        stage('Test') {
            steps {
                echo '------------ Testing ------------'
                withEnv(["JAVA_HOME=${ tool 'JDK1.8' }", "PATH+MAVEN=${tool 'M2'}/bin:${env.JAVA_HOME}/bin"]) {
                    sh "mvn --batch-mode -V -U -e clean test -Dsurefire.useFile=false"
                }
                }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
    }
}