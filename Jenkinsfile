pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                // Checkout code from version control
                checkout scm
            }
        }
        stage('Build') {
            steps {
                // Run Maven clean and install
                sh 'mvn clean install'
            }
        }
        stage('Test') {
            steps {
                // Run Maven clean and test
                sh 'mvn clean test'
            }
        }
        stage('Package') {
            steps {
                // Run Maven clean and package
                sh 'mvn clean package'
            }
        }
        stage('Run') {
            steps {
                // Run the Spring Boot application
                sh 'java -jar target/your-spring-boot-app.jar'
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
