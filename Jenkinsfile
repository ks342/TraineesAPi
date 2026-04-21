pipeline {
    agent any

    tools {
        maven 'Maven'
        jdk 'JDK17'
    }

    environment {
        IMAGE_NAME = "traineeapi"
        DOCKERHUB_USER = "ks9897"
        CONTAINER_NAME = "traineeapi-container"
    }

    stages {

        stage('Build JAR') {
            steps {
                dir('Assignment 10 Trainee Testing') {
                    sh 'mvn clean package'
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                dir('Assignment 10 Trainee Testing') {
                    sh 'docker build -t traineeapi .'
                }
            }
        }

        stage('Login to DockerHub') {
            steps {
                withCredentials([usernamePassword(
                    credentialsId: 'dockerhub-creds',
                    usernameVariable: 'USER',
                    passwordVariable: 'PASS'
                )]) {
                    sh '''
            docker logout
            docker login -u %USER% -p %PASS%
            '''
                }
            }
        }

        stage('Tag Image') {
            steps {
                sh 'docker tag traineeapi %DOCKERHUB_USER%/traineeapi:latest'
            }
        }

        stage('Push to DockerHub') {
            steps {
                sh 'docker push %DOCKERHUB_USER%/traineeapi:latest'
            }
        }

        stage('Run Container') {
            steps {
                sh '''
                docker rm -f traineeapi-container || exit 0
                docker run -d -p 8211:8080 --name traineeapi-container traineeapi
                '''
            }
        }
    }
}
