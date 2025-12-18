set -e

echo "Compilando todos los módulos (skip tests)..."
mvn clean install -DskipTests

echo "Levantando servicios Spring Boot en AWS..."

nohup java -jar agregador/target/agregador.jar > agregador.log 2>&1 &
nohup java -jar fuente-dinamica/target/fuente-dinamica.jar > fuente-dinamica.log 2>&1 &
nohup java -jar fuente-proxy/target/fuente-proxy.jar > fuente-proxy.log 2>&1 &
nohup java -jar fuente-estatica/target/fuente-estatica.jar > fuente-estatica.log 2>&1 &
nohup java -jar utils/target/utils.jar > utils.log 2>&1 &

echo "Todos los servicios levantados en segundo plano"