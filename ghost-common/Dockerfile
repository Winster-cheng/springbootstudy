FROM reg.haimaiche.net/library/mhc_jdk8_pp-agent:1.0.0

#FROM reg.haimaiche.net/library/mhc_jdk8:1.0.1
MAINTAINER yudan <yudan@maihaoche.com>
ENV REFRESHED_AT 2018-06-25
    
ARG JAR_FILE
ADD target/${JAR_FILE}  /home/admin/app/mhcapp.jar
RUN echo "java \$JAVA_OPTIONS -jar /home/admin/app/mhcapp.jar --spring.profiles.active=\$SPRING_PROFILES_ACTIVE --server.port=\$JETTY_PORT" >> /home/admin/app/hosts.sh &&\
    cat /home/admin/app/hosts.sh &&\
    chown -R admin:admin /home/admin &&\
    chmod u+x /home/admin/app/hosts.sh
     
USER admin
WORKDIR /home/admin

ENTRYPOINT /home/admin/app/hosts.sh
