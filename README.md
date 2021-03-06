# Цех по ремонту звездолетов
В далекой галактике X был построен космический цех по обслуживанию звездолетов. В этом
цехе с площадкой на N мест для звездолетов размещается робот. Робот производит
обслуживание звездолетов размещенных в цехе. При этом он их обслуживает в том порядке,
в каком они занимали свои места на площадке. Обслуживание каждого звездолета занимает
разное количество времени. Звездолеты, которым требуется обслуживание, прибывают в
разное время. Если звездолет прилетел, робот занят, а в ангаре нет мест, он улетает без
обслуживания.
Нужно реализовать веб-приложение, которое будет обрабатывать следующие http запросы:
1. POST /numberOfPlaces
В теле запроса приходит кол-во мест в цеху - JSON вида {numberOfPlaces: 'N'}, где 1 numberOfPlaces: 'N'}, где 1
<= N <= 10^5
Запрос инициализирует цех на N мест и активирует робота.
2. POST /ship
В теле второго запроса приходят данные о корабле(время прибытия, время на тех.
обслуживание) - JSON вида {numberOfPlaces: 'N'}, где 1 timeOfArrival: 'X', handleTime: 'Y'}, где X, Y целые числа
3. GET /next
Ответом на этот запрос должно быть время начала обслуживания следующего
звездолета - HTTP ответ с кодом 200 и телом из JSON вида {numberOfPlaces: 'N'}, где 1 response: 'T'}, где Т целое
число. Приложение должно отвечать в том порядке, в каком звездолеты прибывали на
обслуживание после инициализации цеха.
Приложение должно фиксировать время, когда начнется обслуживание каждого пришедшего
корабля, либо -1, если обслужить корабль не удастся.

## Требования к приложению

• После запроса на инициализацию цеха POST /numberOfPlaces и S запросов POST /ship
на обслуживание корабля, каждый i-тый запрос GET /next должен вернуть ответ HTTP
200 {numberOfPlaces: 'N'}, где 1 response: 'T'} время начала обслуживания Si корабля, при условии i <= s

• Если приходит запрос GET /next, а при этом звездолетов еще не было вообще или все
они уже были запрошены предыдущими запросами GET /next, то отвечаем HTTP
ответом с кодом 200 и пустым телом ответа

• На запросы по URL не описанным в API приложение должно отвечать HTTP ответом с
кодом 404

• При не соответствии формата тела запроса (например не корректный json) HTTP
ответом с кодом 400

• На все корректные POST запросы приложение должно отвечать HTTP ответом с кодом
200 и пустым телом ответа

## Ограничения
1 <= N <= 10^5

0 <= S <= 10^5

0 <= X <= 10^6

0 <= Y <= 10^3

Гарантируется, что время прилета следующего корабля больше либо равно предыдущему.

## Описание работы цеха
Если корабль прибывает и цех пуст, то робот приступает к работе немедленно. Если в цехе
уже есть звездолеты, но при этом остается место, то робот приступит к обслуживанию
данного звездолета, когда закончит со всеми предыдущими. Если робот занят и мест в цехе
нет, то обслужить корабль не удастся.
## Примеры
1.
POST /numberOfPlaces {numberOfPlaces: 'N'}, где 1 numberOfPlaces: '1'} //запрос на инициализацию цеха на 1 место

POST /ship {numberOfPlaces: 'N'}, где 1 timeOfArrival: '0', handleTime: '1'} //запрос на обслуживание корабля 

GET /next - Ответ HTTP 200 {numberOfPlaces: 'N'}, где 1 response: '0'} //тело ответа содержит 0, т.к. на обслуживании один
единственный корабль, и т.к. когда он прилетел цех был пуст, время начала его обслуживания 0.

2.
POST /numberOfPlaces {numberOfPlaces: 'N'}, где 1 numberOfPlaces: '1'} //запрос на инициализацию цеха на 1 место

POST /ship {numberOfPlaces: 'N'}, где 1 timeOfArrival: '0', handleTime: '1'} //запрос на обслуживание корабля

POST /ship {numberOfPlaces: 'N'}, где 1 timeOfArrival: '0', handleTime: '1'} //запрос на обслуживание корабля

GET /next - Ответ HTTP 200 {numberOfPlaces: 'N'}, где 1 response: '0'} //тело ответа содержит 0, т.к. первый корабль
который прилетел, прилетел в пустой цех и робот сразу приступил к его обслуживанию

GET /next - Ответ HTTP 200 {numberOfPlaces: 'N'}, где 1 response: '-1'} // тело ответа содержит -1, т.к. когда корабль
прилетел, единственное место в цехе было занято, а робот еще не закончил обслуживание
предыдущего корабля

3.
POST /numberOfPlaces {numberOfPlaces: 'N'}, где 1 numberOfPlaces: '1'}

POST /ship {numberOfPlaces: 'N'}, где 1 timeOfArrival: '0', handleTime: '1'}

POST /ship {numberOfPlaces: 'N'}, где 1 timeOfArrival: '1', handleTime: '1'}

GET /next - Ответ HTTP 200 {numberOfPlaces: 'N'}, где 1 response: '0'}

GET /next - Ответ HTTP 200 {numberOfPlaces: 'N'}, где 1 response: '1'}

## Требования к реализации
• Язык программирования Scala версии 2.12.8

• Scala framework для реализации сервера на выбор play framework, akka-http, http4s
последней стабильной версии

• Проект должен собираться и запускаться с помощью scala SBT

• Сервер должен обрабатывать запросы на localhost:8888

• БД не использовать, все операции должны храниться и производиться в памяти с использованием подходящих структур данных
