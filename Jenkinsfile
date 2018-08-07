#!groovy
pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo '------------ Building ------------'
                withEnv(["JAVA_HOME=${ tool 'JDK1.8' }", "PATH+MAVEN=${tool 'M2'}/bin:${env.JAVA_HOME}/bin"]) {
                    sh "mvn --batch-mode -V -U -e clean compile -Dsurefire.useFile=false"
                }
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
        stage('Build RPM') {
            steps {
                echo '------------ Building the RPM ------------'
                withEnv(["JAVA_HOME=${ tool 'JDK1.8' }", "PATH+MAVEN=${tool 'M2'}/bin:${env.JAVA_HOME}/bin"]) {
                    sh "mvn --batch-mode -V -U -e clean package -DskipTests -P rpm"
                }
                archiveArtifacts artifacts: 'target/rpm/calc/RPMS/noarch/*.rpm,target/*.jar', onlyIfSuccessful: true
            }

        }
    }
}