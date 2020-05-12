# Best Music DB Microservices Application

Welcome to Best Music DB Microservices Application API !!


## Brief
1.	It is a microservices application
2.	It is designed as maven multi module project. bestmusicdb-micro-service is the holiding module. It holds separate microservice implementations
	a. bestmusicdb-album-service (API for album related calls. Also Eureka client)
	b. bestmusicdb-artist-service (API for artist related calls. Also Eureka client)
	c. bestmusicdb-discovery-service (Eureka Server)


## Tech Stack

| Technology    | Version       | Purpose                |
| ------------- | :-----------: | ---------------------- |
| Java 		    | OpenJDK 11    |                        |
| Spring Boot   | 2.3.0.RC1     | Easy to use            |
| Spring Cloud  | Hoxton.SR4    | Microservice discovery |
| Maven         | 3.6.3         |                        |
| Reactor-spring| 1.0.1.RELEASE |                        |
| Test Option	| MockMvc		|                        |
| Junit			| 5				|                        |


## Discovery configurations

| Application Name 				| eureka service| Port |
| ----------------------------- | ------------- | ---- |
| bestmusicdb-discovery-service |       -       | 8761 |
| bestmusicdb-artist-service    |artist-service | 8081 |
| bestmusicdb-artist-service    |artist-service | 8082 |


## Build & Execute Steps
1.	You can build independently each application (or)
2.  You can build from the parent module


## Features/Choices
1.	Docker configuration is added for all implementations.
2.	lombok is used across the application for less coding and better readability
3.	lombok's Builder pattern used for better object creation
4.	H2 inmemeory DB is used for storing the entity informations. The url and the credentials are mentioned in the application properties.
5.	Spring Page is returned on all list results
6.	Alpine image is used which is based on Alpine Linux project, which leads to much slimmer images in general.
7.	Reactive implementation - Only for rest API client implementation, I used this. Reactive programming is also easier, instead of rendering as a List or Page, I have to go for Flux
8.	Timedelay is added for WebClient to consume both album services - local & discog
9.	AlbumServiceQueryBuilder - Added for creating query param for out going calls based on incoming queryparams
10.	CustomPageImpl is implemented as PageImpl dont have default constructor to render as ParameterizedTypeReference
11.	Discog properties are retained as a java class; DiscogApiProperties instead of property reading
12.	getAlbumResourceFromDiscogs method- Eventhough it is private method which is called internally, I kept at controller side as because it is again making service or api call to different service.
13.	I relayed on ResponseStatus exceptions. I dont see a need for controller advice class
14.	JsonUtils holds methods for parsing JSON across the application
15.	AlbumKey holds composite key of id values of Album & Artist


## Sample inputs
{
    "artistName": "fIRST name"
}

{
    "genre": "comedy",
    "title": "First Movie",
    "year": 2050
}


## Curl command execution

```
$ curl -i -H "Content-Type: application/json" http://localhost:8081/artists/
  % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                 Dload  Upload   Total   Spent    Left  Speed
100   355    0   355    0     0  29583      0 --:--:-- --:--:-- --:--:-- 32272HTTP/1.1 200
Content-Type: application/json
Transfer-Encoding: chunked
Date: Tue, 12 May 2020 22:23:34 GMT

{"content":[{"artistId":1,"artistName":"Last name"}],"pageable":{"sort":{"unsorted":true,"sorted":false,"empty":true},"offset":0,"pageSize":20,"pageNumber":0,"unpaged":false,"paged":true},"last":true,"totalPages":1,"totalElements":1,"first":true,"size":20,"number":0,"sort":{"unsorted":true,"sorted":false,"empty":true},"numberOfElements":1,"empty":false}
```
```
$ curl -i -H "Content-Type: application/json" http://localhost:8081/artists/1/albums
  % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                 Dload  Upload   Total   Spent    Left  Speed
100   437    0   437    0     0  16807      0 --:--:-- --:--:-- --:--:-- 16807HTTP/1.1 200
Content-Type: application/json
Transfer-Encoding: chunked
Date: Tue, 12 May 2020 22:26:55 GMT

{"content":[{"artistId":1,"albumId":1,"artistName":"Last name","title":"First Movie","year":2050,"genre":"Family","resourceUrl":null}],"number":0,"size":20,"totalElements":1,"pageable":{"sort":{"unsorted":true,"sorted":false,"empty":true},"offset":0,"pageSize":20,"pageNumber":0,"unpaged":false,"paged":true},"last":true,"totalPages":1,"sort":{"unsorted":true,"sorted":false,"empty":true},"first":true,"numberOfElements":1,"empty":false}
```
```
$ curl --header "Content-Type: application/json" --request POST --data '{ "artistName": "Last name" }' http://localhost:8081/artists/
  % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                 Dload  Upload   Total   Spent    Left  Speed
100   169    0   140  100    29   5833   1208 --:--:-- --:--:-- --:--:--  7041{"timestamp":"2020-05-12T22:28:37.567+00:00","status":409,"error":"Conflict","message":"Artist Last name already exist.","path":"/artists/"}
```


## Parts you found easy
Developing rest api application & microservice using SpringBoot is a downhill task for an experienced Java developer


## Parts I found difficult
This is my first micro service project and I rarely developed rest APIs, as i haven't worked on those projects at my current role. Based on my conceptual knowledge and experience with technologies, I implemented this solution. I hope I made a decent application.


## Parts I skipped
Parts I skipped, because of time constraint.
1.	I didn't right test case for album service, because it is almost same as artist service. Also from artist service there are calls to album service, which can indirectly test the same
2.	Expose metrics. However I enabled Actuators. With prometheus, it is easy to implement. https://docs.spring.io/spring-metrics/docs/current/public/prometheus. Also graylog can be used for functional logging
3.	Didn;t write reactive programming, but it is not tough Job


If you face any problem, please contact jpofficial@gmail.com!!
*Hurrah!*
