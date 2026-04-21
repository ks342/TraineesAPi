// // pipeline {
//     agent any

//     tools {
//         maven 'Maven'
//         jdk 'JDK17'
//     }

//     environment {
//         IMAGE_NAME = "traineeapi"
//         DOCKERHUB_USER = "ks9897"
//         CONTAINER_NAME = "traineeapi-container"
//     }

//     stages {

//         stage('Build JAR') {
//             steps {
//                 dir('Assignment 10 Trainee Testing') {
//                     bat 'mvn clean package'
//                 }
//             }
//         }

//         stage('Build Docker Image') {
//             steps {
//                 dir('Assignment 10 Trainee Testing') {
//                     bat 'docker build -t traineeapi .'
//                 }
//             }
//         }

//         stage('Login to DockerHub') {
//             steps {
//                 withCredentials([usernamePassword(
//                     credentialsId: 'dockerhub-creds',
//                     usernameVariable: 'USER',
//                     passwordVariable: 'PASS'
//                 )]) {
//                     bat '''
//             docker logout
//             docker login -u %USER% -p %PASS%
//             '''
//                 }
//             }
//         }

//         stage('Tag Image') {
//             steps {
//                 bat 'docker tag traineeapi %DOCKERHUB_USER%/traineeapi:latest'
//             }
//         }

//         stage('Push to DockerHub') {
//             steps {
//                 bat 'docker push %DOCKERHUB_USER%/traineeapi:latest'
//             }
//         }

//         stage('Run Container') {
//             steps {
//                 bat '''
//                 docker rm -f traineeapi-container || exit 0
//                 docker run -d -p 8211:8080 --name traineeapi-container traineeapi
//                 '''
//             }
//         }
//     }
// }




pipeline {
    agent any

    environment {
        IMAGE_NAME = "your-dockerhub-username/springboot-app"
        CONTAINER_NAME = "springboot-container"
        PORT = "8085"
    }

    stages {

        stage('Clone Code') {
            steps {
                git branch:'main',
                git 'https://github.com/ks342/TraineesAPi.git'
            }
        }

        stage('Build JAR') {
            steps {
                dir('Assignment 10 Trainee Testing') {
                    sh '''
                    docker run --rm -v $PWD:/app -w /app maven:3.9.9-eclipse-temurin-17 mvn clean package
                    '''
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                sh 'docker build -t $IMAGE_NAME .'
            }
        }

        stage('Login to DockerHub') {
            steps {
                withCredentials([usernamePassword(
                    credentialsId: 'dockerhub-creds',
                    usernameVariable: 'DOCKER_USER',
                    passwordVariable: 'DOCKER_PASS'
                )]) {
                    sh 'echo $DOCKER_PASS | docker login -u $DOCKER_USER --password-stdin'
                }
            }
        }

        stage('Push to DockerHub') {
            steps {
                sh 'docker push $IMAGE_NAME'
            }
        }

        stage('Run Container') {
            steps {
                sh '''
                docker rm -f $CONTAINER_NAME || true
                docker run -d -p $PORT:8080 --name $CONTAINER_NAME $IMAGE_NAME
                '''
            }
        }
    }
}
