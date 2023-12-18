Для создания SCHEMA weather_api воспользуйтесь CREATED_DATA_BASE.sql.

rapidapi.service.interval.cron = 0 * * * * *  //расписание запросов в https://rapidapi.com/weatherapi/api/weatherapi-com

GET http://localhost:8080/api/weather/
Endpoint отдает наиболее актуальную информацию из БД сервиса.





POST http://localhost:8080/api/weather/average
body JSON
{
    "from":"17-12-2023",
    "to":"17-12-2023"
}
Endpoint отдает среднесуточные показатели из БД сервиса за указанный период,
 если from == to вернутся средние показатели за указанные сутки.
Валидация тела запроса:
 -обязательный интервал from to, два значения (отправит соответствующий HttpResponse);
 -каждое значение должно быть в правильном формате "dd-MM-yyyy" (отправит соответствующий HttpResponse);
 -первая дата должна быть меньше второй (отправит соответствующий HttpResponse);
 -если диапазон не входит в значения хранящиеся в BD
            (отправит соответствующий HttpResponse с максимально возможным диапазоном);