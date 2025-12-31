pipeline {
    agent any

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build Jar') {
            steps {
                sh 'mvn clean package'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh 'docker build -t java-app:latest .'
            }
        }

        stage('Blue-Green Deploy') {
            steps {
                sh 'ansible-playbook ansible/bluegreen-deploy.yml'
            }
        }

        stage('Test App') {
            steps {
                sh '''
                echo "Testing Blue"
                curl -s http://localhost:8081 || true
                echo ""
                echo "Testing Green"
                curl -s http://localhost:8082 || true
                '''
            }
        }
    }
}
