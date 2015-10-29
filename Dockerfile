# Ubuntu 14.04 LTS
# Oracle Java 1.8.0_11 64 bit
# Maven 3.3.3


# extend the most recent long term support Ubuntu version
FROM ubuntu:latest

MAINTAINER jack "askfish@gmail.com"

# this is a non-interactive automated build - avoid some warning messages
#ENV DEBIAN_FRONTEND noninteractive

# update dpkg repositories
RUN apt-get update 

# install wget
RUN apt-get install -y wget

# get maven 3.3.3
RUN wget --no-verbose -O /tmp/apache-maven-3.3.3.tar.gz http://archive.apache.org/dist/maven/maven-3/3.3.3/binaries/apache-maven-3.3.3-bin.tar.gz

# verify checksum
RUN echo "794b3b7961200c542a7292682d21ba36 /tmp/apache-maven-3.3.3.tar.gz" | md5sum -c

# install maven
RUN tar xzf /tmp/apache-maven-3.3.3.tar.gz -C /opt/
RUN ln -s /opt/apache-maven-3.3.3 /opt/maven
RUN ln -s /opt/maven/bin/mvn /usr/local/bin
RUN rm -f /tmp/apache-maven-3.3.3.tar.gz
ENV MAVEN_HOME /opt/maven


#install java
#RUN apt-get install -y default-jdk

# remove download archive files
RUN apt-get clean

# set shell variables for java installation
ENV java_version 1.8.0_65
ENV filename jdk-8u65-linux-x64.tar.gz
ENV downloadlink http://download.oracle.com/otn-pub/java/jdk/8u65-b17/$filename

# download java, accepting the license agreement
RUN wget --no-cookies --header "Cookie: oraclelicense=accept-securebackup-cookie" -O /tmp/$filename $downloadlink 

# unpack java
RUN mkdir /opt/java-oracle && tar -zxf /tmp/$filename -C /opt/java-oracle/
ENV JAVA_HOME /opt/java-oracle/jdk$java_version
ENV PATH $JAVA_HOME/bin:$PATH

# configure symbolic links for the java and javac executables
RUN update-alternatives --install /usr/bin/java java $JAVA_HOME/bin/java 20000 && update-alternatives --install /usr/bin/javac javac $JAVA_HOME/bin/javac 20000

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
ENV MAVEN_OPTS -Xms128m -Xmx512m -Xdebug -Xnoagent -Dsettings.localRepository=/mnt/diskA -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,address=8088,server=y,suspend=n


# Install OpenSSH
RUN apt-get update && apt-get install -y openssh-server
# Set password
ADD sshRootPd.txt .
RUN mkdir /var/run/sshd && \
  echo "root:`cat sshRootPd.txt`" | chpasswd && \
  # Allow root login with password
  sed -i 's/PermitRootLogin without-password/PermitRootLogin yes/' /etc/ssh/sshd_config && \
  # Prevent user being kicked off after login
  sed -i 's@session\s*required\s*pam_loginuid.so@session optional pam_loginuid.so@g' /etc/pam.d/sshd && \
  # Clean up
  rm sshRootPd.txt

# configure the container to run weixn
ENTRYPOINT  ["/usr/sbin/sshd", "-D"]

# Expose web port
EXPOSE 80
# Expose java debug port
EXPOSE 8088
# Expose SSH port
EXPOSE 22

# Run SSH server without detaching
CMD mvn -f /opt/jfinal-weixin/ jetty:run-war
