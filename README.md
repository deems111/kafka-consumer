# Kafka Consumer Test Application 
Author: deemsy - https://github.com/deems111

### Description
Java 17 / Spring Boot 3 Application: 
receive messages from the queue in Kafka and save to a table in the database - PostgreSQL.

### Tech stack
      * Java 17
      * Spring Boot 3
      * Hibernate 6
      * Kafka 3
      * PostgreSQL 15
Other  
   * Liquibase / Maven / Docker (for Kafka)
### Before Using
1) Update Kafka Properties in application.properties*:
   * spring.kafka.bootstrap-servers
   * kafka.topic.name
   * kafka.consumer.groupId
2) Update DataSource Properties in application.properties (use only PostgreSQL, tested on PostgreSQL 15, not Postgres 11 and earlier - not supported):
   * spring.datasource.url
   * spring.datasource.username
   * spring.datasource.password
3) Check Application Port in application.properties (use only PostgreSQL, tested on PostgreSQL 15, not Postgres 11 and earlier - not supported):
   * spring.datasource.url
   * spring.datasource.username
   * spring.datasource.password

*- Create topic:


<code>
kafka-topics.sh --bootstrap-server SERVER_ADDRESS --topic TOPIC_NAME --create --partitions 1 --replication-factor 1
</code>


Create Group


<code>
kafka-console-consumer.sh --bootstrap-server SERVER_ADDRESS --topic TOPIC_NAME --group GROUP_NAME
</code>


Add Consumer to Group.
