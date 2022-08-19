pipeline {
    agent any
    tools {
    	maven "Maven inicial"
    	jdk 'OpenJDK 17 inicial'
    }
    stages {
    	stage('Compilacion y ejecucion de test') {
    		steps {
				sh 'mvn clean test'
    		}
    	}
	stage('Publicar en sonar') {
		steps {
	            script{

			def scannerHome = tool 'sonarqube';
			def jobNameFiltered = "${env.JOB_NAME}".split('/').join('-')

			withSonarQubeEnv('sonarqube') {
				sh " ${tool("sonarqube")}/bin/sonar-scanner \
		                    -Dsonar.projectKey=${jobNameFiltered} \
                		    -Dsonar.projectName=${jobNameFiltered} \
				    -Dsonar.sources=src/main/java/ \
				    -Dsonar.language=java \
				    -Dsonar.java.binaries=target/classes,target/test-classes"
			}
		    }
		}
	}
    }
    post {
       always {
          junit(
        	allowEmptyResults: true,
        	testResults: 'target/surefire-reports/*.xml',
        	skipPublishingChecks: true
      	)
      }
   }     
}
