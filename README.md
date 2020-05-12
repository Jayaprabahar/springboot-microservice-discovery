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
| ------------- | ------------- | ---------------------- |
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
4.	Spring Page is returned on all list results
5.	Alpine image is used which is based on Alpine Linux project, which leads to much slimmer images in general.
6.	Reactive implementation - Only for rest API client implementation, I used this. Reactive programming is also easier, instead of rendering as a List or Page, I have to go for Flux
7.	Timedelay is added for WebClient to consume both album services - local & discog
8.	AlbumServiceQueryBuilder - Added for creating query param for out going calls based on incoming queryparams
9.	CustomPageImpl is implemented as PageImpl dont have default constructor to render as ParameterizedTypeReference
10.	Discog properties are retained as a java class; DiscogApiProperties instead of property reading
11.	getAlbumResourceFromDiscogs method- Eventhough it is private method which is called internally, I kept at controller side as because it is again making service or api call to different service.
12.	I relayed on ResponseStatus exceptions. I dont see a need for controller advice class
13.	JsonUtils holds methods for parsing JSON across the application
14.	AlbumKey holds composite key of id values of Album & Artist

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
