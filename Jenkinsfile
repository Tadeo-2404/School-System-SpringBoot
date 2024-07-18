pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                // Run Maven clean and install
                bat 'mvn clean install -DskipTests'
            }
        }
        stage('Test') {
            steps {
                // Run Maven clean and test
                bat 'mvn clean test'
            }
        }
        stage('Package') {
            steps {
                // Run Maven clean and package
                bat 'mvn clean package -DskipTests'
            }
        }
        stage('Run') {
            steps {
                // Run the Spring Boot application
                bat 'java -jar target/demo-0.0.1-SNAPSHOT.jar'
            }
        }
    }

    post {
        success {
            echo 'Pipeline completed successfully!'
        }
        failure {
            echo 'Pipeline failed.'
        }
    }
}
