FROM reg.maihaoche.com/library/mhc_jdk8_pp-agent:1.0.0
USER root
ADD . /root/build
RUN cd /root/build/ && mvn -q -X -DskipTests=true compile && mvn package -DskipTests=true \
        && mv ghost/target/*.jar /app.jar \
        && cd / && rm -rf /root/build/

EXPOSE 8088
ENTRYPOINT exec java $JAVA_OPTS -jar /app.jar