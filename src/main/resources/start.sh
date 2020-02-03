echo "Setting path variables"
export HTTP_PORT="8080"
export CSCARDS_ENDPOINT="https://app.clearscore.com/api/global/backend-tech-test/v1/cards"
export SCOREDCARDS_ENDPOINT="https://app.clearscore.com/api/global/backend-tech-test/v2/creditcards"

cd $HOME/clearScore/spring-server
echo "Building project"
mvn package

echo "Starting creditCards spring boot applicatioin"
java -Dserver.port=$HTTP_PORT -jar target/swagger-spring-1.0.0.jar 





