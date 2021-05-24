# Run Jacoco
mvn jacoco:prepare-agent install jacoco:report

# Data in
Bank/target/site/jacoco/index.html

# Docker Sonarqube local
docker run -d --name sonarqube -e SONAR_ES_BOOTSTRAP_CHECKS_DISABLE=true -p 9000:9000 sonarqube:latest

http://localhost:9000/
User: admin
Password: admin

New Password: sonar

mvn sonar:sonar -Dsonar.projectKey=aulateste -Dsonar.host.url=http://localhost:9000 -Dsonar.login=c2fb543a07bc5b66e6de03e89d0c2c835983f60c
