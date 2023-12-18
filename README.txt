spring.jpa.hibernate.ddl-auto=update
spring.datasource.url=jdbc:mysql://${MYSQL_HOST:localhost}:3306/weather_api?useSSL=false
spring.datasource.username=bestuser
spring.datasource.password=bestuser

rapidapi.service.interval.cron = 0 * * * * *  //расписание запросов в https://rapidapi.com/weatherapi/api/weatherapi-com

GET http://localhost:8080/api/weather/
Endpoint отдает наиболее актуальную информацию из БД сервиса.




POST http://localhost:8080/api/weather/average
body JSON
{
    "from":"17-12-2023",
    "to":"17-12-2023"
}
Endpoint отдает среднесуточные показатели из БД сервиса за указанный период.