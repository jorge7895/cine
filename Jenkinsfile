pipeline {
	agent any
    agent {
		dockerfile {
			filename 'Dockerfile'
			args '-v /datos/.m2/repository:/home/jenkins/.m2/repository'
		}
    }
    stages {
    	stage('Compilacion y ejecucion de test') {
    		steps {
				sh 'mvn -Djacoco.formats=xml clean org.jacoco:jacoco-maven-plugin:0.8.8:prepare-agent test org.jacoco:jacoco-maven-plugin:0.8.8:report'

				jacoco( 
 			     execPattern: 'target/*.exec',
      			 classPattern: 'target/classes',
      			 sourcePattern: 'src/main/java',
      			 exclusionPattern: 'src/test*'
				)
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
