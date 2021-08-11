# volumes for dev
mkdir -p \
 /data/volumes/jhipster/catiny-dev/app/ \
 /data/volumes/jhipster/catiny-dev/mysql/ \
 /data/volumes/jhipster/catiny-dev/elasticsearch/
chmod 777 /data/volumes/jhipster/catiny-dev/elasticsearch/
# volumes for prod
mkdir -p \
 /data/volumes/jhipster/catiny-prod/app/ \
 /data/volumes/jhipster/catiny-prod/mysql/ \
 /data/volumes/jhipster/catiny-prod/elasticsearch/
chmod 777 /data/volumes/jhipster/catiny-prod/elasticsearch/
#jenkins
mkdir -p /data/volumes/jenkins_home
ln -s /data/volumes/jenkins_home /var/jenkins_home
#sonarqube
mkdir -p /data/volumes/sonarqube/data \
 /data/volumes/sonarqube/logs \
 /data/volumes/sonarqube/extensions
