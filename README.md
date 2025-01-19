``docker run -d -p 3306:3306 -e MYSQL_DATABASE=task-management -e MYSQL_ALLOW_EMPTY_PASSWORD=true mysql:8.3.0``

``docker network create task-management-network ``

``docker network connect task-management-network <mysql container name>``

``docker build -t task-management-api . ``

``docker run -p <outside port>:8080 -e MY_SQL_HOST=<mysql container name> --net task-management-network task-management-api``