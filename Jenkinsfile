pipeline {
    agent {
        principal {
            image 'maven:3.8.6-eclipse-temurin-17-alpine' 
            args '-v /root/.m2:/root/.m2' 
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
