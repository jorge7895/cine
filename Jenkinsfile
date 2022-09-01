pipeline {
    agent any
    tools {
    	maven "Maven inicial"
    	jdk 'OpenJDK 17 inicial'
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
					-Dsonar.java.coveragePlugin=jacoco \
					-Dsonar.tests=src/test/java \
					-Dsonar.coverage.jacoco.xmlReportPaths=target/site/jacoco/jacoco.xml \
    			    -Dsonar.junit.reportsPaths=target/surefire-reports \
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
