pipeline {
    agent any

    stages {

        stage('Build') {
            steps {
                echo "Building Java app"
                sh 'mvn clean package'
            }
        }

        stage('Docker Build') {
            steps {
                echo "Building Docker image"
                sh 'docker build -t java-app:latest .'
            }
        }

        stage('Deploy Blue-Green') {
            steps {
                echo "Running Ansible Playbook"
                sh 'ansible-playbook ansible/bluegreen.yml'
            }
        }
    }
}

