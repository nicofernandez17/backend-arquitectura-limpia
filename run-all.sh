set -e

echo "Compilando todos los módulos (skip tests + cobertura)..."

mvn clean install \
  -DskipTests=true \
  -Dmaven.test.skip=true \
  -Djacoco.skip=true \
  -Dcheckstyle.skip=true \
  -Dspotbugs.skip=true \
  -Dpmd.skip=true

echo "Levantando TODOS los servicios Spring Boot (sin logs)..."

nohup java -jar components/agregador/target/agregador/agregador-1.0-SNAPSHOT.jar > /dev/null 2>&1 &
nohup java -jar components/fuente-dinamica/target/agregador/fuente-dinamica-1.0-SNAPSHOT.jar > /dev/null 2>&1 &
nohup java -jar components/fuente-proxy/target/fuente-proxy-1.0-SNAPSHOT.jar > /dev/null 2>&1 &
nohup java -jar components/fuente-estatica/target/fuente-estatica-1.0-SNAPSHOT.jar > /dev/null 2>&1 &
nohup java -jar components/utils/target/utils-1.0-SNAPSHOT.jar > /dev/null 2>&1 &

echo "Todos los servicios levantados en segundo plano (sin logs)"