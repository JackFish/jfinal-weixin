# based on https://github.com/JackFish/java-mvn-ssh
FROM index.alauda.cn/jackzhou/java-mvn-ssh

MAINTAINER jack "askfish@gmail.com"

# Make ssh dir
RUN mkdir /opt/jfinal-weixin/

# Copy source to docker vm
ADD res/ /opt/jfinal-weixin/res/
ADD src/ /opt/jfinal-weixin/src/
ADD webapp/ /opt/jfinal-weixin/webapp/
ADD pom.xml /opt/jfinal-weixin/pom.xml

#build war
#RUN mvn -f /opt/jfinal-weixin/ install

#set debug mode
ENV MAVEN_OPTS "-Xms128m -Xmx512m -Xdebug -Xnoagent -Djava.compiler=NONE -Dfile.encoding=UTF-8 -Xrunjdwp:transport=dt_socket,address=8088,server=y,suspend=n"

# configure the container to run weixn
ENTRYPOINT mvn -f /opt/jfinal-weixin/ jetty:run-war

# Expose web port
EXPOSE 80
# Expose java debug port
EXPOSE 8088
# Expose SSH port
# EXPOSE 22

# Run SSH server without detaching
# CMD
