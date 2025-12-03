pipeline {
    agent any

    
    environment {
        // Must match the SonarQube server name configured in Jenkins
        SONARQUBE_SERVER = 'Sonar'

        // Replace with your preferred SonarQube project key
        SONAR_PROJECT_KEY = 'spring-sample-app'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Prepare Maven Wrapper') {
            steps {
                // Ensure mvnw is executable on Linux agents
                sh 'chmod +x mvnw'
            }
        }

        stage('Build & Test') {
            steps {
                sh './mvnw clean verify -B'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv("${SONARQUBE_SERVER}") {
                    sh """
                        ./mvnw sonar:sonar \
                          -Dsonar.projectKey=${SONAR_PROJECT_KEY} \
                          -Dsonar.projectName="Spring Sample App" \
                          -Dsonar.sources=src \
                          -Dsonar.java.binaries=target/classes
                    """
                }
            }
        }

        stage('Quality Gate') {
            steps {
                // Wait up to 5 minutes for SonarQube to compute the Quality Gate
                timeout(time: 5, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: true
                }
            }
        }
    }
}
