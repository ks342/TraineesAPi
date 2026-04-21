pipeline {
    agent any

    environment {
        IMAGE_NAME = "springboot-app"
        CONTAINER_NAME = "springboot-container"
        PORT = "8085"
    }

    stages {

        stage('Clone Code') {
            steps {
                git 'https://github.com/ks342/TraineesAPi.git'
            }
        }

        stage('Build JAR (Maven in Docker)') {
            steps {
                sh '''
                docker run --rm -v $PWD:/app -w /app maven:3.9.9-eclipse-temurin-17 mvn clean package
                '''
            }
        }

        stage('Build Docker Image') {
            steps {
                sh '''
                docker build -t $IMAGE_NAME .
                '''
            }
        }

        stage('Stop Old Container') {
            steps {
                sh '''
                docker rm -f $CONTAINER_NAME || true
                '''
            }
        }

        stage('Run New Container') {
            steps {
                sh '''
                docker run -d -p $PORT:8080 --name $CONTAINER_NAME $IMAGE_NAME
                '''
            }
        }
    }

    post {
        success {
            echo "App deployed successfully 🚀"
        }
        failure {
            echo "Build failed ❌ Check logs"
        }
    }
}
