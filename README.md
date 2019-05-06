# BTC MicroService with Spring Boot, AWS Elastic Beanstalk and Docker

This is BTC MicroService built using Spring Boot and is deployed on AWS Elastic Beanstalk using Docker. Similar to the steps described for LTC service, even this service can be modified and deployed to AWS Lambda to make it serverless. The API reads the backend application data directly from resources/json/btc.json file, however the architecture is easily modifiable to plug with backend MongoDB. For testing purposes, steps are also given to deploy locally or as a container on docker.

# Deploy to AWS Elastic Beanstalk

Install and get the Elastic Beanstalk Command Line Interface (EB CLI) running by referring this link: https://docs.aws.amazon.com/elasticbeanstalk/latest/dg/eb-cli3-install.html
 
### Refer document BTC_API_Deployed_To_AWS_EBS_Screenshots.docx for steps with screenshots.

Follow these steps for deploying to AWS Elastic Beanstalk:

1. Clean and rebuild the code.
	
	gradlew clean bootJar
	
2. Go inside cloned project directory and execute below cmd. Keep default values or enter values as per your wish.
	
	eb init

3. Create an environment by executing below cmd. 
	
	eb create btc-service
	
4. In case any changes are made, please commit the changes and then deploy / update the environment by executing below cmd. 
	
	eb deploy
 
5. Access the API using link like below.
	
	http://btc-service.w2dnkmgtrc.us-west-2.elasticbeanstalk.com/btc?inputDate=05/08/2019
	
6. EBS provisions all the required resources for running a node application in AWS. Go to AWS Console under Elastic Beanstalk service section and view the above created application.

# Run locally

This project is setup to build with Gradle. To build and run from a packaged jar locally:

    gradlew bootRun

or 

    gradlew clean bootJar
    java -jar build/libs/BTCService-0.0.1-SNAPSHOT.jar

# Deploy to Docker locally

Please install and run docker locally if not already done. To build the image, first build the application, then build the docker image

    gradlew clean bootJar
    docker build -t btc-service .
    
## Run on docker

    docker run --name btc-service -p 8081:8081 -d btc-service
    
# Test

    curl http://localhost:8081/btc?inputDate=05/08/2019
