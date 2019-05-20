pipeline {
    agent any
    options {
        skipStagesAfterUnstable()
    }

    stages {
		stage('Maven clean') {
			steps {
				withMaven(maven : 'maven') {
					sh 'mvm clean compile'
				}
			}
		}
		
		stage('Maven test'){
			steps {
				withMaven(maven : 'maven') {
					sh 'mvn test'
				}
				withMaven(maven: 'maven') {
					sh 'mvn deploy'
				}
			}
		}
	
        stage('Docker Build') {
            steps {
                sh 'docker build ./Kwetteren/frontend/. -t kevinverkuijlenfontys/overheid-frontend:test'
                withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId:'dockerhub', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD']]) {
                  sh 'docker login -u $USERNAME -p $PASSWORD'
                }
                sh 'docker push kevinverkuijlenfontys/overheid-frontend:test'
            }
        }
    }
}
