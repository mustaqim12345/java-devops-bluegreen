pipeline {
    agent any

    stages {

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

        stage('Deploy BLUE') {
            steps {
                sh '''
                docker stop blue || true
                docker rm blue || true

                docker run -d \
                  -p 8081:8080 \
                  --name blue \
                  -e APP_VERSION=BLUE \
                  java-app:latest
                '''
            }
        }

        stage('Deploy GREEN') {
            steps {
                sh '''
                docker stop green || true
                docker rm green || true

                docker run -d \
                  -p 8082:8080 \
                  --name green \
                  -e APP_VERSION=GREEN \
                  java-app:latest
                '''
            }
        }

        stage('Health Check GREEN') {
            steps {
                sh '''
                sleep 10
                curl -f http://localhost:8082
                '''
            }
        }

        stage('Switch Traffic (Stop BLUE)') {
            steps {
                sh 'docker stop blue'
            }
        }
    }
}

