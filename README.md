# Swagger generated server

Spring Boot Server 

## Overview  
Please verify that maven is installed and path is already set.
Run below commands
mkdir -p $HOME/clearScore
unzip the code into this folder

#To Start the application

please run start.sh file, this file is in resources folder.
sh $HOME/clearScore/spring-server/src/main/resources/start.sh

If you are not able to run application using start.sh
Please import application into Eclipse or STS as a maven project.
then run as maven install and run the spring boot application.

#Endpoints
CreditCard enpoint 
http://localhost:8080/creditcards  

#Design
As there are two extenal API endpoint, hence created two different Callable task..
as in future it will be easier to handle them property if complexities increases.

Credit card is parent class whereas CsCards and ScoredCards extends CreditCard.
Keeping their response in two different class will be beneficial in future. Common variable are kept in CreditCard.

ExecutorService is used to delegate the task of creating and handling thread and focus on business logic.
As there are only two API calls,hence used fixedThreadPool of size two.

Task of fetching cards implements Callable interface as we want some data to be returned.
Two threads might run parallely, reducing the time.

After both threads returns, executor service is shutdown to free the resources.


