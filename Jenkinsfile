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
