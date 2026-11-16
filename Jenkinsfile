pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test'
                junit 'target/surefire-reports/**/*.xml'
            }
        }
        // Exercicio Aula 10: analise PMD
        stage('PMD') {
            steps {
                sh 'mvn pmd:check'
            }
        }
        stage('Deploy to Staging') {
            when {
                branch 'main'
            }
            steps {
                script {
                    // guarda a imagem anterior para o rollback
                    env.IMAGEM_ANTERIOR = sh(
                        script: "kubectl get deployment myapp -o jsonpath='{.spec.template.spec.containers[0].image}' || true",
                        returnStdout: true).trim()
                }
                sh 'docker build -t myapp:${GIT_COMMIT} .'
                sh 'kubectl apply -f k8s/deployment.yaml'
                // falha o stage se o rollout nao ficar saudavel
                sh 'kubectl rollout status deployment/myapp --timeout=120s'
            }
            post {
                // Exercicio Aula 10: rollback automatico se o deploy falhar
                failure {
                    sh 'kubectl rollout undo deployment/myapp'
                }
            }
        }
    }

    post {
        failure {
            slackSend channel: '#alerts', message: "Build Failed: ${env.JOB_NAME}"
        }
    }
}
