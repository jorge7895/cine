FROM maven:3.8.3-openjdk-17
RUN mkdir /home/jenkins
RUN groupadd -g 992 jenkins
RUN useradd -r -u 1002 -g jenkins -d /home/jenkins jenkins
RUN chown jenkins:jenkins /home/jenkins
USER jenkins
WORKDIR /home/jenkins