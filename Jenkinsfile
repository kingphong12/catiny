#!/usr/bin/env groovy

node {
  stage('checkout') {checkout scm}

  currentFolder = sh(script: 'pwd', , returnStdout: true).trim()
  timeSleepingBeforePrintLog = 120
  timeSleepingWaitMariadbReady = 120

  stage('check')
  {
    try {sh "java -version"}
    catch (e)
    {
      echo "not installed java"
    }
    try {sh "node -v"}
    catch (e)
    {
      echo "not installed node"
      sh "curl -fsSL https://deb.nodesource.com/setup_lts.x | bash -"
      sh "apt-get install -y nodejs"
      sh "node -v"
    }
    try {sh "docker-compose -v"}
    catch (e)
    {
      echo "not installed docker-compose"
      sh 'curl -L "https://github.com/docker/compose/releases/download/1.29.2/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose && '+
         'chmod +x /usr/local/bin/docker-compose && '+
         'ln -s /usr/local/bin/docker-compose /usr/bin/docker-compose'
    }
  }

  stage('clean and nohttp') {
    sh "chmod +x gradlew"
    sh "./gradlew clean --no-daemon"
    sh "./gradlew checkstyleNohttp --no-daemon"
  }

//   stage('npm install')
//   {
//     sh "./gradlew npm_install -PnodeInstall --no-daemon"
//   }
//
// Todo
//    stage('backend tests') {
//      try {
//        sh "./gradlew test integrationTest -PnodeInstall --no-daemon"
//      } catch (err) {
//        throw err
//      } finally {
//        junit '**/build/**/TEST-*.xml'
//      }
//    }
// Todo
//    stage('frontend tests') {
//      try {
//        sh "./gradlew npm_run_test-ci -PnodeInstall --no-daemon"
//      } catch (err) {
//        throw err
//      } finally {
//        junit '**/build/test-results/TESTS-*.xml'
//      }
//    }

  stage('publish docker')
  {
    // A pre-requisite to this step is to setup authentication to the docker registry
    // https://github.com/GoogleContainerTools/jib/tree/master/jib-gradle-plugin#authentication-methods
    // sh "./gradlew bootJar jib -Pprod -PnodeInstall --no-daemon" // build and publish
    //
    sh "./gradlew bootJar jibDockerBuild -Pprod -PnodeInstall --no-daemon" // only build
    docker.withRegistry('','docker-hub')
    {
      docker.image('yuvytung/catiny:latest').push()
    }
  }

  stage('up catiny-dev')
  {
		try
		{
			sh 'docker inspect docker_catiny-dev-mariadb_1'
// 			sh 'docker inspect docker_catiny-dev-ffmpeg_1'
			sh 'docker inspect docker_catiny-dev-redis_1'
			sh 'docker inspect docker_catiny-dev-elasticsearch_1'
// 			sh 'docker inspect docker_catiny-dev-kibana_1'
			sh 'docker inspect docker_catiny-dev-kafka_1'
			sh 'docker inspect docker_catiny-dev-zookeeper_1'
			sh 'docker inspect docker_catiny-dev-jhipster-registry_1'
		}
		catch (ignored)
		{
		  echo 'the necessary services (dev) are not running . try start it'
			sh "docker-compose -f ${currentFolder}/src/main/docker/app-dev.yml up -d"
			echo 'Sleep for ${timeSleepingWaitMariadbReady} seconds to wait for the mariadb to be ready'
			sleep(timeSleepingWaitMariadbReady)
		}

    sh "docker-compose -f ${currentFolder}/src/main/docker/app-dev.yml down catiny-dev-app"
    sh "docker-compose -f ${currentFolder}/src/main/docker/app-dev.yml up -d catiny-dev-app"
    sleep(timeSleepingBeforePrintLog)
    sh "docker logs docker_catiny-dev-app_1"
  }

  stage('up catiny-prod')
  {
  	try
  	{
  		sh 'docker inspect docker_catiny-mariadb_1'
//   		sh 'docker inspect docker_catiny-ffmpeg_1'
  		sh 'docker inspect docker_catiny-redis_1'
  		sh 'docker inspect docker_catiny-elasticsearch_1'
//   		sh 'docker inspect docker_catiny-kibana_1'
  		sh 'docker inspect docker_catiny-kafka_1'
  		sh 'docker inspect docker_catiny-zookeeper_1'
  		sh 'docker inspect docker_catiny-jhipster-registry_1'
  	}
		catch (ignored)
		{
			echo 'the necessary services (prod) are not running . try start it'
			sh "docker-compose -f ${currentFolder}/src/main/docker/app-prod.yml up -d"
			echo 'Sleep for ${timeSleepingWaitMariadbReady} seconds to wait for the mariadb to be ready'
			sleep(timeSleepingWaitMariadbReady)
		}
    sh "docker-compose -f ${currentFolder}/src/main/docker/app-prod.yml down catiny-app"
    sh "docker-compose -f ${currentFolder}/src/main/docker/app-prod.yml up -d catiny-app"
    sleep(timeSleepingBeforePrintLog)
    sh "docker logs docker_catiny-app_1"
  }
  stage('quality analysis')
  {
    withSonarQubeEnv('sonar')
    {
      sh "./gradlew sonarqube -Pprod -PnodeInstall --no-daemon"
    }
  }
/**
//   build inside docker
  docker.image('jhipster/jhipster:v7.1.0').inside('-u root -e GRADLE_USER_HOME=.gradle '+
  //   ' -v /data/volumes/jenkins_home:/var/jenkins_home '+
  ' -v /data/volumes/jhipster/temp/node-modules:/usr/local/lib/node-modules')
  {
    stage('quality analysis')
    {
      withSonarQubeEnv('sonar')
      {
        sh "./gradlew sonarqube --no-daemon"
      }
    }
  }
*/
}
