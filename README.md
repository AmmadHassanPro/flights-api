## The Flights API , Spring Boot microservice
 ### The Flights API is an enterprise grade Spring Boot microservice , that provides flight offers based on source and destination.

### Description
 The Flights API, can be used to search for flight offers between one city to another city in the whole world. It fetches offers from multiple airlines and from any airport to another. It is consumer of two APIs from Amadeus platform.
 
 Currently it has two endpoints:
 - ### /get-offers
   This HTTP/GET endpoint takes few query parameters and returns the flight offers recieved from 'The Flight Offers Search API'. Currently maximum of 10 flight offers can be returned to reduce the load on the downstream API, but this setting can be changed.  The returned response contains information about flights, prices, iteneries, number of bookable seats etc.
- ### /get-city-and-airport
  This HTTP/GET endpoint takes one path parameter about keyword and returns the list of matching cities aggregated with corresponding airports by consuming 'The Airport & City Search API' . It can be used to get IATA code of city or airport to form the request to get the offers. 
 
### Technologies
It uses following technologies:
- Spring Boot
- Java 8
- Spring Security
- Spring Cloud Config Server
- Oauth2 Client
- Spring Doc Open API
- WebClient
- Lombok
- WireMock
- MapStruct

  
![hd-diagram](https://github.com/AmmadHassanPro/flights-api/assets/20376377/6769fa85-2fb5-4b77-aa25-8dfd1d6a38fc)
