set -e

echo "Compilando todos los módulos (skip tests + cobertura)..."

mvn clean install \
  -DskipTests=true \
  -Dmaven.test.skip=true \
  -Djacoco.skip=true \
  -Dcheckstyle.skip=true \
  -Dspotbugs.skip=true \
  -Dpmd.skip=true

mkdir -p logs

echo "Levantando TODOS los servicios Spring Boot..."

nohup java -jar agregador/target/agregador/agregador-1.0-SNAPSHOT.jar > logs/agregador.log 2>&1 &
nohup java -jar fuente-dinamica/target/agregador/fuente-dinamica-1.0-SNAPSHOT.jar > logs/fuente-dinamica.log 2>&1 &
nohup java -jar fuente-proxy/target/fuente-proxy-1.0-SNAPSHOT.jar > logs/fuente-proxy.log 2>&1 &
nohup java -jar fuente-estatica/target/fuente-estatica-1.0-SNAPSHOT.jar > logs/fuente-estatica.log 2>&1 &
nohup java -jar utils/target/utils-1.0-SNAPSHOT.jar > logs/utils.log 2>&1 &

echo "Todos los servicios levantados en segundo plano"