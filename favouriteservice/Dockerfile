FROM java:8-jre
WORKDIR usr/src
ENV MYSQL_DATABASE=pubg_tour
ENV MYSQL_USER=
ENV MYSQL_PASSWORD=
ENV MYSQL_CI_URL=jdbc:mysql://localhost:3306/pubg_tour
ADD ./target/favouriteservice-0.0.1-SNAPSHOT.jar /usr/src/favouriteservice-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","favouriteservice-0.0.1-SNAPSHOT.jar"]