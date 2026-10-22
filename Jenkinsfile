pipeline {
    agent any

    options {
        timestamps()
        disableConcurrentBuilds()
        timeout(time: 30, unit: 'MINUTES')
    }

    parameters {
        booleanParam(name: 'BUILD_IMAGE', defaultValue: false, description: 'Build the Docker image after tests pass')
        booleanParam(name: 'PUSH_IMAGE', defaultValue: false, description: 'Push the versioned image and latest to Docker Hub')
        booleanParam(name: 'DEPLOY_LOCAL', defaultValue: false, description: 'Replace the local portal container on the Jenkins agent')
        string(name: 'DOCKERHUB_REPO', defaultValue: 'YOUR-USERNAME/customer-usage-alert-portal', description: 'Docker Hub namespace and repository')
        string(name: 'ALERT_THRESHOLD_PERCENT', defaultValue: '80', description: 'Usage percentage that raises a warning (0-100)')
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build and Test') {
            steps {
                sh 'mvn -B clean verify'
            }
            post {
                always {
                    junit testResults: 'target/surefire-reports/*.xml', allowEmptyResults: true
                }
                success {
                    archiveArtifacts artifacts: 'target/customer-usage-alert-portal.jar', fingerprint: true
                }
            }
        }

        stage('Docker Build') {
            when {
                expression { params.BUILD_IMAGE || params.PUSH_IMAGE || params.DEPLOY_LOCAL }
            }
            steps {
                sh 'docker build --pull -t usage-alert:${BUILD_NUMBER} .'
            }
        }

        stage('Docker Push') {
            when {
                expression { params.PUSH_IMAGE }
            }
            steps {
                withCredentials([usernamePassword(credentialsId: 'dockerhub-creds',
                    usernameVariable: 'DOCKERHUB_USERNAME', passwordVariable: 'DOCKERHUB_TOKEN')]) {
                    sh '''
                        set -eu
                        echo "$DOCKERHUB_TOKEN" | docker login --username "$DOCKERHUB_USERNAME" --password-stdin
                        docker tag "usage-alert:$BUILD_NUMBER" "$DOCKERHUB_REPO:$BUILD_NUMBER"
                        docker tag "usage-alert:$BUILD_NUMBER" "$DOCKERHUB_REPO:latest"
                        docker push "$DOCKERHUB_REPO:$BUILD_NUMBER"
                        docker push "$DOCKERHUB_REPO:latest"
                        docker logout
                    '''
                }
            }
        }

        stage('Deploy Local Container') {
            when {
                expression { params.DEPLOY_LOCAL }
            }
            steps {
                sh '''
                    set -eu
                    docker rm -f customer-data-usage-alert-portal || true
                    docker run -d --name customer-data-usage-alert-portal --restart unless-stopped -p 8085:8085 -e SERVER_PORT=8085 -e DB_URL="jdbc:h2:file:/data/usage-alert;AUTO_SERVER=TRUE" -e ALERT_THRESHOLD_PERCENT="$ALERT_THRESHOLD_PERCENT" -v usage-alert-data:/data "usage-alert:$BUILD_NUMBER"
                    for attempt in $(seq 1 20); do
                      if curl --fail --silent http://localhost:8085/actuator/health >/dev/null; then
                        exit 0
                      fi
                      sleep 3
                    done
                    docker logs customer-data-usage-alert-portal
                    exit 1
                '''
            }
        }
    }

    post {
        failure {
            echo 'Quality gate failed. Docker publish/deploy stages were not reached.'
        }
    }
}
