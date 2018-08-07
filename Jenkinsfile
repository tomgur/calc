#!groovy
pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo 'Building..'
                sh "mvn --batch-mode -V -U -e clean compile -Dsurefire.useFile=false"
            }
        }
        stage('Test') {
            steps {
                sh "mvn --batch-mode -V -U -e clean test -Dsurefire.useFile=false"
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
    }
}