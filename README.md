## The Flights API
The Flights API is an enterprise grade Spring Boot microservice , that provides flight offers based on source and destination.

### Description
 The Flights API, can be used to search for flight offers between one city to another city in the whole world. It fetches offers from multiple airlines and from any airport to another. It is consumer of two APIs from Amadeus platform.
 
 Currently it has two endpoints:
 - #### /get-offers
   This HTTP/GET endpoint takes few query parameters and returns the flight offers recieved from 'The Flight Offers Search API'. Currently maximum of 10 flight offers can be returned to reduce the load on the downstream API, but this setting can be changed.  The returned response contains information about flights, prices, iteneries, number of bookable seats etc.
- #### /get-city-and-airport
  This HTTP/GET endpoint takes one path parameter about keyword and returns the list of matching cities aggregated with corresponding airports by consuming 'The Airport & City Search API' . It can be used to get IATA code of city or airport to form the request to get the offers. 
 
### Technologies
It uses following technologies:
- Java 8
- Spring Boot
- Spring Security
- Spring Cloud Config Server
- Oauth2 Client
- Spring Doc Open API
- WebClient
- Lombok
- WireMock
- MapStruct

  
![hd-diagram](https://github.com/AmmadHassanPro/flights-api/assets/20376377/6769fa85-2fb5-4b77-aa25-8dfd1d6a38fc)


### Dependencies
- Register to get the api keys on the [Amadeus Platform](https://developers.amadeus.com/)
- Since it uses config server to fetch the configuration , it is important that the config server is running, the code is currently hosted at: [Github Link](https://github.com/AmmadHassanPro/flights-api-config-server)
- Must have Java 8 or higher

### Running Instructions
- Upon resgistering to Amadeus platform, you will get Client Id and Client Secret, Please add an environment variable in your machine named 'CLIENT_ID' and 'CLIENT_SECRET' with their corresponding values obtained from the platform.
- Run the config server by downloading the code (mentioned above).
- Run the 'flights-api'  by downloading the code and on the root directory of code, type './gradlew bootRun' on terminal.
- Inspect the swagger api at url : http://localhost:8080/swagger-ui/index.html
  
### Flow of Request for Getting Offers

![FlowDiagram](https://github.com/AmmadHassanPro/flights-api/assets/20376377/ab6d870c-dda2-4537-b067-8ddd333b942c)



Suppose that you would like to get best flight offers for a round trip from Chicago to Seattle. First you will call the endpoint "/get-city-and-airport/chicago" and perform a get request via postman but headers 'consumer-name' and 'request-uuid' are mandatory with whatever value you like. It should return a response something like this

~~~
{
    "data": [
        {
            "type": "location",
            "subType": "CITY",
            "name": "CHICAGO",
            "detailedName": "CHICAGO/IL/US",
            "id": "CCHI",
            "iataCode": "CHI",
            "address": {
                "cityName": "CHICAGO",
                "cityCode": "CHI",
                "countryName": "UNITED STATES OF AMERICA",
                "countryCode": "US",
                "stateCode": "IL",
                "regionCode": "NAMER"
            },
            "airports": [
                {
                    "type": "location",
                    "subType": "AIRPORT",
                    "name": "O HARE INTERNATIONAL",
                    "detailedName": "CHICAGO/IL/US:O HARE INTERNATI",
                    "id": "AORD",
                    "iataCode": "ORD",
                    "address": {
                        "cityName": "CHICAGO",
                        "cityCode": "CHI",
                        "countryName": "UNITED STATES OF AMERICA",
                        "countryCode": "US",
                        "stateCode": "IL",
                        "regionCode": "NAMER"
                    }
                },
                {
                    "type": "location",
                    "subType": "AIRPORT",
                    "name": "MIDWAY INTERNATIONAL",
                    "detailedName": "CHICAGO/IL/US:MIDWAY INTERNATI",
                    "id": "AMDW",
                    "iataCode": "MDW",
                    "address": {
                        "cityName": "CHICAGO",
                        "cityCode": "CHI",
                        "countryName": "UNITED STATES OF AMERICA",
                        "countryCode": "US",
                        "stateCode": "IL",
                        "regionCode": "NAMER"
                    }
                },
                {
                    "type": "location",
                    "subType": "AIRPORT",
                    "name": "CHICAGO ROCKFORD INTL",
                    "detailedName": "CHICAGO/IL/US:CHICAGO ROCKFORD",
                    "id": "ARFD",
                    "iataCode": "RFD",
                    "address": {
                        "cityName": "CHICAGO",
                        "cityCode": "CHI",
                        "countryName": "UNITED STATES OF AMERICA",
                        "countryCode": "US",
                        "stateCode": "IL",
                        "regionCode": "NAMER"
                    }
                },
                {
                    "type": "location",
                    "subType": "AIRPORT",
                    "name": "DUPAGE",
                    "detailedName": "CHICAGO/IL/US:DUPAGE",
                    "id": "ADPA",
                    "iataCode": "DPA",
                    "address": {
                        "cityName": "CHICAGO",
                        "cityCode": "CHI",
                        "countryName": "UNITED STATES OF AMERICA",
                        "countryCode": "US",
                        "stateCode": "IL",
                        "regionCode": "NAMER"
                    }
                },
                {
                    "type": "location",
                    "subType": "AIRPORT",
                    "name": "EXECUTIVE",
                    "detailedName": "CHICAGO/IL/US:EXECUTIVE",
                    "id": "APWK",
                    "iataCode": "PWK",
                    "address": {
                        "cityName": "CHICAGO",
                        "cityCode": "CHI",
                        "countryName": "UNITED STATES OF AMERICA",
                        "countryCode": "US",
                        "stateCode": "IL",
                        "regionCode": "NAMER"
                    }
                }
            ]
        }
    ]
}
~~~

Notice how it grouped the city and its corresponding airports togeather. Next, you can find the IATA code of th city , in this case it would be 'CHI' and in case of Seattle it would be 'SEA'. Next, we would consume the GET endpoint 'get-offers', by passing in the cities code and other values as params (can be found in the Swagger doc) to get the flight offers. A example would be calling the GET on '/get-offers?adults=1&max=1&nonStop=false&originLocationCode=CHI&destinationLocationCode=SEA&departureDate=2023-11-15&returnDate=2023-11-20' with the required headers would return the followin response

~~~
{
    "data": [
        {
            "type": "flight-offer",
            "id": "1",
            "source": "GDS",
            "instantTicketingRequired": false,
            "nonHomogeneous": false,
            "oneWay": false,
            "lastTicketingDate": "2023-09-14",
            "numberOfBookableSeats": 3,
            "itineraries": [
                {
                    "duration": "PT4H30M",
                    "segments": [
                        {
                            "departure": {
                                "iataCode": "ORD",
                                "at": "2023-11-15T07:30:00",
                                "terminal": "5"
                            },
                            "arrival": {
                                "iataCode": "SEA",
                                "at": "2023-11-15T10:00:00"
                            },
                            "carrierCode": "DL",
                            "number": "1578",
                            "aircraft": {
                                "code": "320"
                            },
                            "operating": {
                                "carrierCode": "DL"
                            },
                            "duration": "PT4H30M",
                            "id": "1",
                            "numberOfStops": 0,
                            "blacklistedInEU": false
                        }
                    ]
                },
                {
                    "duration": "PT3H57M",
                    "segments": [
                        {
                            "departure": {
                                "iataCode": "SEA",
                                "at": "2023-11-20T18:00:00"
                            },
                            "arrival": {
                                "iataCode": "ORD",
                                "at": "2023-11-20T23:57:00",
                                "terminal": "5"
                            },
                            "carrierCode": "DL",
                            "number": "1074",
                            "aircraft": {
                                "code": "320"
                            },
                            "operating": {
                                "carrierCode": "DL"
                            },
                            "duration": "PT3H57M",
                            "id": "8",
                            "numberOfStops": 0,
                            "blacklistedInEU": false
                        }
                    ]
                }
            ],
            "price": {
                "currency": "USD",
                "total": "167.77",
                "base": "128.37",
                "fees": [
                    {
                        "amount": "0.00",
                        "type": "SUPPLIER"
                    },
                    {
                        "amount": "0.00",
                        "type": "TICKETING"
                    }
                ],
                "grandTotal": "167.77"
            },
            "pricingOptions": {
                "fareType": [
                    "PUBLISHED"
                ],
                "includedCheckedBagsOnly": false
            },
            "validatingAirlineCodes": [
                "DL"
            ],
            "travelerPricings": [
                {
                    "travelerId": "1",
                    "fareOption": "STANDARD",
                    "travelerType": "ADULT",
                    "price": {
                        "currency": "USD",
                        "total": "167.77",
                        "base": "128.37"
                    },
                    "fareDetailsBySegment": [
                        {
                            "segmentId": "1",
                            "cabin": "ECONOMY",
                            "fareBasis": "VAVSH3BQ",
                            "class": "E",
                            "includedCheckedBags": {
                                "quantity": 0
                            },
                            "brandedFare": "BASICECON"
                        },
                        {
                            "segmentId": "8",
                            "cabin": "ECONOMY",
                            "fareBasis": "TAUNA0BQ",
                            "class": "E",
                            "includedCheckedBags": {
                                "quantity": 0
                            },
                            "brandedFare": "BASICECON"
                        }
                    ]
                }
            ],
            "lastTicketingDateTime": "2023-09-14"
        },
        {
            "type": "flight-offer",
            "id": "2",
            "source": "GDS",
            "instantTicketingRequired": false,
            "nonHomogeneous": false,
            "oneWay": false,
            "lastTicketingDate": "2023-09-14",
            "numberOfBookableSeats": 3,
            "itineraries": [
                {
                    "duration": "PT4H30M",
                    "segments": [
                        {
                            "departure": {
                                "iataCode": "ORD",
                                "at": "2023-11-15T07:30:00",
                                "terminal": "5"
                            },
                            "arrival": {
                                "iataCode": "SEA",
                                "at": "2023-11-15T10:00:00"
                            },
                            "carrierCode": "DL",
                            "number": "1578",
                            "aircraft": {
                                "code": "320"
                            },
                            "operating": {
                                "carrierCode": "DL"
                            },
                            "duration": "PT4H30M",
                            "id": "1",
                            "numberOfStops": 0,
                            "blacklistedInEU": false
                        }
                    ]
                },
                {
                    "duration": "PT4H2M",
                    "segments": [
                        {
                            "departure": {
                                "iataCode": "SEA",
                                "at": "2023-11-20T07:20:00"
                            },
                            "arrival": {
                                "iataCode": "ORD",
                                "at": "2023-11-20T13:22:00",
                                "terminal": "5"
                            },
                            "carrierCode": "DL",
                            "number": "1189",
                            "aircraft": {
                                "code": "320"
                            },
                            "operating": {
                                "carrierCode": "DL"
                            },
                            "duration": "PT4H2M",
                            "id": "5",
                            "numberOfStops": 0,
                            "blacklistedInEU": false
                        }
                    ]
                }
            ],
            "price": {
                "currency": "USD",
                "total": "167.77",
                "base": "128.37",
                "fees": [
                    {
                        "amount": "0.00",
                        "type": "SUPPLIER"
                    },
                    {
                        "amount": "0.00",
                        "type": "TICKETING"
                    }
                ],
                "grandTotal": "167.77"
            },
            "pricingOptions": {
                "fareType": [
                    "PUBLISHED"
                ],
                "includedCheckedBagsOnly": false
            },
            "validatingAirlineCodes": [
                "DL"
            ],
            "travelerPricings": [
                {
                    "travelerId": "1",
                    "fareOption": "STANDARD",
                    "travelerType": "ADULT",
                    "price": {
                        "currency": "USD",
                        "total": "167.77",
                        "base": "128.37"
                    },
                    "fareDetailsBySegment": [
                        {
                            "segmentId": "1",
                            "cabin": "ECONOMY",
                            "fareBasis": "VAVSH3BQ",
                            "class": "E",
                            "includedCheckedBags": {
                                "quantity": 0
                            },
                            "brandedFare": "BASICECON"
                        },
                        {
                            "segmentId": "5",
                            "cabin": "ECONOMY",
                            "fareBasis": "TAUNA0BQ",
                            "class": "E",
                            "includedCheckedBags": {
                                "quantity": 0
                            },
                            "brandedFare": "BASICECON"
                        }
                    ]
                }
            ],
            "lastTicketingDateTime": "2023-09-14"
        },
        {
            "type": "flight-offer",
            "id": "3",
            "source": "GDS",
            "instantTicketingRequired": false,
            "nonHomogeneous": false,
            "oneWay": false,
            "lastTicketingDate": "2023-09-14",
            "numberOfBookableSeats": 3,
            "itineraries": [
                {
                    "duration": "PT4H35M",
                    "segments": [
                        {
                            "departure": {
                                "iataCode": "ORD",
                                "at": "2023-11-15T14:15:00",
                                "terminal": "5"
                            },
                            "arrival": {
                                "iataCode": "SEA",
                                "at": "2023-11-15T16:50:00"
                            },
                            "carrierCode": "DL",
                            "number": "1189",
                            "aircraft": {
                                "code": "320"
                            },
                            "operating": {
                                "carrierCode": "DL"
                            },
                            "duration": "PT4H35M",
                            "id": "2",
                            "numberOfStops": 0,
                            "blacklistedInEU": false
                        }
                    ]
                },
                {
                    "duration": "PT3H57M",
                    "segments": [
                        {
                            "departure": {
                                "iataCode": "SEA",
                                "at": "2023-11-20T18:00:00"
                            },
                            "arrival": {
                                "iataCode": "ORD",
                                "at": "2023-11-20T23:57:00",
                                "terminal": "5"
                            },
                            "carrierCode": "DL",
                            "number": "1074",
                            "aircraft": {
                                "code": "320"
                            },
                            "operating": {
                                "carrierCode": "DL"
                            },
                            "duration": "PT3H57M",
                            "id": "8",
                            "numberOfStops": 0,
                            "blacklistedInEU": false
                        }
                    ]
                }
            ],
            "price": {
                "currency": "USD",
                "total": "167.77",
                "base": "128.37",
                "fees": [
                    {
                        "amount": "0.00",
                        "type": "SUPPLIER"
                    },
                    {
                        "amount": "0.00",
                        "type": "TICKETING"
                    }
                ],
                "grandTotal": "167.77"
            },
            "pricingOptions": {
                "fareType": [
                    "PUBLISHED"
                ],
                "includedCheckedBagsOnly": false
            },
            "validatingAirlineCodes": [
                "DL"
            ],
            "travelerPricings": [
                {
                    "travelerId": "1",
                    "fareOption": "STANDARD",
                    "travelerType": "ADULT",
                    "price": {
                        "currency": "USD",
                        "total": "167.77",
                        "base": "128.37"
                    },
                    "fareDetailsBySegment": [
                        {
                            "segmentId": "2",
                            "cabin": "ECONOMY",
                            "fareBasis": "VAVSH3BQ",
                            "class": "E",
                            "includedCheckedBags": {
                                "quantity": 0
                            },
                            "brandedFare": "BASICECON"
                        },
                        {
                            "segmentId": "8",
                            "cabin": "ECONOMY",
                            "fareBasis": "TAUNA0BQ",
                            "class": "E",
                            "includedCheckedBags": {
                                "quantity": 0
                            },
                            "brandedFare": "BASICECON"
                        }
                    ]
                }
            ],
            "lastTicketingDateTime": "2023-09-14"
        },
        {
            "type": "flight-offer",
            "id": "4",
            "source": "GDS",
            "instantTicketingRequired": false,
            "nonHomogeneous": false,
            "oneWay": false,
            "lastTicketingDate": "2023-09-14",
            "numberOfBookableSeats": 3,
            "itineraries": [
                {
                    "duration": "PT4H30M",
                    "segments": [
                        {
                            "departure": {
                                "iataCode": "ORD",
                                "at": "2023-11-15T07:30:00",
                                "terminal": "5"
                            },
                            "arrival": {
                                "iataCode": "SEA",
                                "at": "2023-11-15T10:00:00"
                            },
                            "carrierCode": "DL",
                            "number": "1578",
                            "aircraft": {
                                "code": "320"
                            },
                            "operating": {
                                "carrierCode": "DL"
                            },
                            "duration": "PT4H30M",
                            "id": "1",
                            "numberOfStops": 0,
                            "blacklistedInEU": false
                        }
                    ]
                },
                {
                    "duration": "PT4H5M",
                    "segments": [
                        {
                            "departure": {
                                "iataCode": "SEA",
                                "at": "2023-11-20T11:20:00"
                            },
                            "arrival": {
                                "iataCode": "ORD",
                                "at": "2023-11-20T17:25:00",
                                "terminal": "5"
                            },
                            "carrierCode": "DL",
                            "number": "1376",
                            "aircraft": {
                                "code": "320"
                            },
                            "operating": {
                                "carrierCode": "DL"
                            },
                            "duration": "PT4H5M",
                            "id": "7",
                            "numberOfStops": 0,
                            "blacklistedInEU": false
                        }
                    ]
                }
            ],
            "price": {
                "currency": "USD",
                "total": "167.77",
                "base": "128.37",
                "fees": [
                    {
                        "amount": "0.00",
                        "type": "SUPPLIER"
                    },
                    {
                        "amount": "0.00",
                        "type": "TICKETING"
                    }
                ],
                "grandTotal": "167.77"
            },
            "pricingOptions": {
                "fareType": [
                    "PUBLISHED"
                ],
                "includedCheckedBagsOnly": false
            },
            "validatingAirlineCodes": [
                "DL"
            ],
            "travelerPricings": [
                {
                    "travelerId": "1",
                    "fareOption": "STANDARD",
                    "travelerType": "ADULT",
                    "price": {
                        "currency": "USD",
                        "total": "167.77",
                        "base": "128.37"
                    },
                    "fareDetailsBySegment": [
                        {
                            "segmentId": "1",
                            "cabin": "ECONOMY",
                            "fareBasis": "VAVSH3BQ",
                            "class": "E",
                            "includedCheckedBags": {
                                "quantity": 0
                            },
                            "brandedFare": "BASICECON"
                        },
                        {
                            "segmentId": "7",
                            "cabin": "ECONOMY",
                            "fareBasis": "TAUNA0BQ",
                            "class": "E",
                            "includedCheckedBags": {
                                "quantity": 0
                            },
                            "brandedFare": "BASICECON"
                        }
                    ]
                }
            ],
            "lastTicketingDateTime": "2023-09-14"
        },
        {
            "type": "flight-offer",
            "id": "5",
            "source": "GDS",
            "instantTicketingRequired": false,
            "nonHomogeneous": false,
            "oneWay": false,
            "lastTicketingDate": "2023-09-14",
            "numberOfBookableSeats": 3,
            "itineraries": [
                {
                    "duration": "PT4H35M",
                    "segments": [
                        {
                            "departure": {
                                "iataCode": "ORD",
                                "at": "2023-11-15T14:15:00",
                                "terminal": "5"
                            },
                            "arrival": {
                                "iataCode": "SEA",
                                "at": "2023-11-15T16:50:00"
                            },
                            "carrierCode": "DL",
                            "number": "1189",
                            "aircraft": {
                                "code": "320"
                            },
                            "operating": {
                                "carrierCode": "DL"
                            },
                            "duration": "PT4H35M",
                            "id": "2",
                            "numberOfStops": 0,
                            "blacklistedInEU": false
                        }
                    ]
                },
                {
                    "duration": "PT4H2M",
                    "segments": [
                        {
                            "departure": {
                                "iataCode": "SEA",
                                "at": "2023-11-20T07:20:00"
                            },
                            "arrival": {
                                "iataCode": "ORD",
                                "at": "2023-11-20T13:22:00",
                                "terminal": "5"
                            },
                            "carrierCode": "DL",
                            "number": "1189",
                            "aircraft": {
                                "code": "320"
                            },
                            "operating": {
                                "carrierCode": "DL"
                            },
                            "duration": "PT4H2M",
                            "id": "5",
                            "numberOfStops": 0,
                            "blacklistedInEU": false
                        }
                    ]
                }
            ],
            "price": {
                "currency": "USD",
                "total": "167.77",
                "base": "128.37",
                "fees": [
                    {
                        "amount": "0.00",
                        "type": "SUPPLIER"
                    },
                    {
                        "amount": "0.00",
                        "type": "TICKETING"
                    }
                ],
                "grandTotal": "167.77"
            },
            "pricingOptions": {
                "fareType": [
                    "PUBLISHED"
                ],
                "includedCheckedBagsOnly": false
            },
            "validatingAirlineCodes": [
                "DL"
            ],
            "travelerPricings": [
                {
                    "travelerId": "1",
                    "fareOption": "STANDARD",
                    "travelerType": "ADULT",
                    "price": {
                        "currency": "USD",
                        "total": "167.77",
                        "base": "128.37"
                    },
                    "fareDetailsBySegment": [
                        {
                            "segmentId": "2",
                            "cabin": "ECONOMY",
                            "fareBasis": "VAVSH3BQ",
                            "class": "E",
                            "includedCheckedBags": {
                                "quantity": 0
                            },
                            "brandedFare": "BASICECON"
                        },
                        {
                            "segmentId": "5",
                            "cabin": "ECONOMY",
                            "fareBasis": "TAUNA0BQ",
                            "class": "E",
                            "includedCheckedBags": {
                                "quantity": 0
                            },
                            "brandedFare": "BASICECON"
                        }
                    ]
                }
            ],
            "lastTicketingDateTime": "2023-09-14"
        },
        {
            "type": "flight-offer",
            "id": "6",
            "source": "GDS",
            "instantTicketingRequired": false,
            "nonHomogeneous": false,
            "oneWay": false,
            "lastTicketingDate": "2023-09-14",
            "numberOfBookableSeats": 3,
            "itineraries": [
                {
                    "duration": "PT4H42M",
                    "segments": [
                        {
                            "departure": {
                                "iataCode": "ORD",
                                "at": "2023-11-15T18:00:00",
                                "terminal": "5"
                            },
                            "arrival": {
                                "iataCode": "SEA",
                                "at": "2023-11-15T20:42:00"
                            },
                            "carrierCode": "DL",
                            "number": "1376",
                            "aircraft": {
                                "code": "320"
                            },
                            "operating": {
                                "carrierCode": "DL"
                            },
                            "duration": "PT4H42M",
                            "id": "4",
                            "numberOfStops": 0,
                            "blacklistedInEU": false
                        }
                    ]
                },
                {
                    "duration": "PT3H57M",
                    "segments": [
                        {
                            "departure": {
                                "iataCode": "SEA",
                                "at": "2023-11-20T18:00:00"
                            },
                            "arrival": {
                                "iataCode": "ORD",
                                "at": "2023-11-20T23:57:00",
                                "terminal": "5"
                            },
                            "carrierCode": "DL",
                            "number": "1074",
                            "aircraft": {
                                "code": "320"
                            },
                            "operating": {
                                "carrierCode": "DL"
                            },
                            "duration": "PT3H57M",
                            "id": "8",
                            "numberOfStops": 0,
                            "blacklistedInEU": false
                        }
                    ]
                }
            ],
            "price": {
                "currency": "USD",
                "total": "167.77",
                "base": "128.37",
                "fees": [
                    {
                        "amount": "0.00",
                        "type": "SUPPLIER"
                    },
                    {
                        "amount": "0.00",
                        "type": "TICKETING"
                    }
                ],
                "grandTotal": "167.77"
            },
            "pricingOptions": {
                "fareType": [
                    "PUBLISHED"
                ],
                "includedCheckedBagsOnly": false
            },
            "validatingAirlineCodes": [
                "DL"
            ],
            "travelerPricings": [
                {
                    "travelerId": "1",
                    "fareOption": "STANDARD",
                    "travelerType": "ADULT",
                    "price": {
                        "currency": "USD",
                        "total": "167.77",
                        "base": "128.37"
                    },
                    "fareDetailsBySegment": [
                        {
                            "segmentId": "4",
                            "cabin": "ECONOMY",
                            "fareBasis": "VAVSH3BQ",
                            "class": "E",
                            "includedCheckedBags": {
                                "quantity": 0
                            },
                            "brandedFare": "BASICECON"
                        },
                        {
                            "segmentId": "8",
                            "cabin": "ECONOMY",
                            "fareBasis": "TAUNA0BQ",
                            "class": "E",
                            "includedCheckedBags": {
                                "quantity": 0
                            },
                            "brandedFare": "BASICECON"
                        }
                    ]
                }
            ],
            "lastTicketingDateTime": "2023-09-14"
        },
        {
            "type": "flight-offer",
            "id": "7",
            "source": "GDS",
            "instantTicketingRequired": false,
            "nonHomogeneous": false,
            "oneWay": false,
            "lastTicketingDate": "2023-09-14",
            "numberOfBookableSeats": 3,
            "itineraries": [
                {
                    "duration": "PT4H35M",
                    "segments": [
                        {
                            "departure": {
                                "iataCode": "ORD",
                                "at": "2023-11-15T14:15:00",
                                "terminal": "5"
                            },
                            "arrival": {
                                "iataCode": "SEA",
                                "at": "2023-11-15T16:50:00"
                            },
                            "carrierCode": "DL",
                            "number": "1189",
                            "aircraft": {
                                "code": "320"
                            },
                            "operating": {
                                "carrierCode": "DL"
                            },
                            "duration": "PT4H35M",
                            "id": "2",
                            "numberOfStops": 0,
                            "blacklistedInEU": false
                        }
                    ]
                },
                {
                    "duration": "PT4H5M",
                    "segments": [
                        {
                            "departure": {
                                "iataCode": "SEA",
                                "at": "2023-11-20T11:20:00"
                            },
                            "arrival": {
                                "iataCode": "ORD",
                                "at": "2023-11-20T17:25:00",
                                "terminal": "5"
                            },
                            "carrierCode": "DL",
                            "number": "1376",
                            "aircraft": {
                                "code": "320"
                            },
                            "operating": {
                                "carrierCode": "DL"
                            },
                            "duration": "PT4H5M",
                            "id": "7",
                            "numberOfStops": 0,
                            "blacklistedInEU": false
                        }
                    ]
                }
            ],
            "price": {
                "currency": "USD",
                "total": "167.77",
                "base": "128.37",
                "fees": [
                    {
                        "amount": "0.00",
                        "type": "SUPPLIER"
                    },
                    {
                        "amount": "0.00",
                        "type": "TICKETING"
                    }
                ],
                "grandTotal": "167.77"
            },
            "pricingOptions": {
                "fareType": [
                    "PUBLISHED"
                ],
                "includedCheckedBagsOnly": false
            },
            "validatingAirlineCodes": [
                "DL"
            ],
            "travelerPricings": [
                {
                    "travelerId": "1",
                    "fareOption": "STANDARD",
                    "travelerType": "ADULT",
                    "price": {
                        "currency": "USD",
                        "total": "167.77",
                        "base": "128.37"
                    },
                    "fareDetailsBySegment": [
                        {
                            "segmentId": "2",
                            "cabin": "ECONOMY",
                            "fareBasis": "VAVSH3BQ",
                            "class": "E",
                            "includedCheckedBags": {
                                "quantity": 0
                            },
                            "brandedFare": "BASICECON"
                        },
                        {
                            "segmentId": "7",
                            "cabin": "ECONOMY",
                            "fareBasis": "TAUNA0BQ",
                            "class": "E",
                            "includedCheckedBags": {
                                "quantity": 0
                            },
                            "brandedFare": "BASICECON"
                        }
                    ]
                }
            ],
            "lastTicketingDateTime": "2023-09-14"
        },
        {
            "type": "flight-offer",
            "id": "8",
            "source": "GDS",
            "instantTicketingRequired": false,
            "nonHomogeneous": false,
            "oneWay": false,
            "lastTicketingDate": "2023-09-14",
            "numberOfBookableSeats": 3,
            "itineraries": [
                {
                    "duration": "PT4H42M",
                    "segments": [
                        {
                            "departure": {
                                "iataCode": "ORD",
                                "at": "2023-11-15T18:00:00",
                                "terminal": "5"
                            },
                            "arrival": {
                                "iataCode": "SEA",
                                "at": "2023-11-15T20:42:00"
                            },
                            "carrierCode": "DL",
                            "number": "1376",
                            "aircraft": {
                                "code": "320"
                            },
                            "operating": {
                                "carrierCode": "DL"
                            },
                            "duration": "PT4H42M",
                            "id": "4",
                            "numberOfStops": 0,
                            "blacklistedInEU": false
                        }
                    ]
                },
                {
                    "duration": "PT4H2M",
                    "segments": [
                        {
                            "departure": {
                                "iataCode": "SEA",
                                "at": "2023-11-20T07:20:00"
                            },
                            "arrival": {
                                "iataCode": "ORD",
                                "at": "2023-11-20T13:22:00",
                                "terminal": "5"
                            },
                            "carrierCode": "DL",
                            "number": "1189",
                            "aircraft": {
                                "code": "320"
                            },
                            "operating": {
                                "carrierCode": "DL"
                            },
                            "duration": "PT4H2M",
                            "id": "5",
                            "numberOfStops": 0,
                            "blacklistedInEU": false
                        }
                    ]
                }
            ],
            "price": {
                "currency": "USD",
                "total": "167.77",
                "base": "128.37",
                "fees": [
                    {
                        "amount": "0.00",
                        "type": "SUPPLIER"
                    },
                    {
                        "amount": "0.00",
                        "type": "TICKETING"
                    }
                ],
                "grandTotal": "167.77"
            },
            "pricingOptions": {
                "fareType": [
                    "PUBLISHED"
                ],
                "includedCheckedBagsOnly": false
            },
            "validatingAirlineCodes": [
                "DL"
            ],
            "travelerPricings": [
                {
                    "travelerId": "1",
                    "fareOption": "STANDARD",
                    "travelerType": "ADULT",
                    "price": {
                        "currency": "USD",
                        "total": "167.77",
                        "base": "128.37"
                    },
                    "fareDetailsBySegment": [
                        {
                            "segmentId": "4",
                            "cabin": "ECONOMY",
                            "fareBasis": "VAVSH3BQ",
                            "class": "E",
                            "includedCheckedBags": {
                                "quantity": 0
                            },
                            "brandedFare": "BASICECON"
                        },
                        {
                            "segmentId": "5",
                            "cabin": "ECONOMY",
                            "fareBasis": "TAUNA0BQ",
                            "class": "E",
                            "includedCheckedBags": {
                                "quantity": 0
                            },
                            "brandedFare": "BASICECON"
                        }
                    ]
                }
            ],
            "lastTicketingDateTime": "2023-09-14"
        },
        {
            "type": "flight-offer",
            "id": "9",
            "source": "GDS",
            "instantTicketingRequired": false,
            "nonHomogeneous": false,
            "oneWay": false,
            "lastTicketingDate": "2023-09-14",
            "numberOfBookableSeats": 3,
            "itineraries": [
                {
                    "duration": "PT4H42M",
                    "segments": [
                        {
                            "departure": {
                                "iataCode": "ORD",
                                "at": "2023-11-15T18:00:00",
                                "terminal": "5"
                            },
                            "arrival": {
                                "iataCode": "SEA",
                                "at": "2023-11-15T20:42:00"
                            },
                            "carrierCode": "DL",
                            "number": "1376",
                            "aircraft": {
                                "code": "320"
                            },
                            "operating": {
                                "carrierCode": "DL"
                            },
                            "duration": "PT4H42M",
                            "id": "4",
                            "numberOfStops": 0,
                            "blacklistedInEU": false
                        }
                    ]
                },
                {
                    "duration": "PT4H5M",
                    "segments": [
                        {
                            "departure": {
                                "iataCode": "SEA",
                                "at": "2023-11-20T11:20:00"
                            },
                            "arrival": {
                                "iataCode": "ORD",
                                "at": "2023-11-20T17:25:00",
                                "terminal": "5"
                            },
                            "carrierCode": "DL",
                            "number": "1376",
                            "aircraft": {
                                "code": "320"
                            },
                            "operating": {
                                "carrierCode": "DL"
                            },
                            "duration": "PT4H5M",
                            "id": "7",
                            "numberOfStops": 0,
                            "blacklistedInEU": false
                        }
                    ]
                }
            ],
            "price": {
                "currency": "USD",
                "total": "167.77",
                "base": "128.37",
                "fees": [
                    {
                        "amount": "0.00",
                        "type": "SUPPLIER"
                    },
                    {
                        "amount": "0.00",
                        "type": "TICKETING"
                    }
                ],
                "grandTotal": "167.77"
            },
            "pricingOptions": {
                "fareType": [
                    "PUBLISHED"
                ],
                "includedCheckedBagsOnly": false
            },
            "validatingAirlineCodes": [
                "DL"
            ],
            "travelerPricings": [
                {
                    "travelerId": "1",
                    "fareOption": "STANDARD",
                    "travelerType": "ADULT",
                    "price": {
                        "currency": "USD",
                        "total": "167.77",
                        "base": "128.37"
                    },
                    "fareDetailsBySegment": [
                        {
                            "segmentId": "4",
                            "cabin": "ECONOMY",
                            "fareBasis": "VAVSH3BQ",
                            "class": "E",
                            "includedCheckedBags": {
                                "quantity": 0
                            },
                            "brandedFare": "BASICECON"
                        },
                        {
                            "segmentId": "7",
                            "cabin": "ECONOMY",
                            "fareBasis": "TAUNA0BQ",
                            "class": "E",
                            "includedCheckedBags": {
                                "quantity": 0
                            },
                            "brandedFare": "BASICECON"
                        }
                    ]
                }
            ],
            "lastTicketingDateTime": "2023-09-14"
        },
        {
            "type": "flight-offer",
            "id": "10",
            "source": "GDS",
            "instantTicketingRequired": false,
            "nonHomogeneous": false,
            "oneWay": false,
            "lastTicketingDate": "2023-09-14",
            "numberOfBookableSeats": 3,
            "itineraries": [
                {
                    "duration": "PT4H38M",
                    "segments": [
                        {
                            "departure": {
                                "iataCode": "ORD",
                                "at": "2023-11-15T06:15:00",
                                "terminal": "3"
                            },
                            "arrival": {
                                "iataCode": "SEA",
                                "at": "2023-11-15T08:53:00"
                            },
                            "carrierCode": "AS",
                            "number": "333",
                            "aircraft": {
                                "code": "73J"
                            },
                            "operating": {
                                "carrierCode": "AS"
                            },
                            "duration": "PT4H38M",
                            "id": "3",
                            "numberOfStops": 0,
                            "blacklistedInEU": false
                        }
                    ]
                },
                {
                    "duration": "PT4H4M",
                    "segments": [
                        {
                            "departure": {
                                "iataCode": "SEA",
                                "at": "2023-11-20T06:00:00"
                            },
                            "arrival": {
                                "iataCode": "ORD",
                                "at": "2023-11-20T12:04:00",
                                "terminal": "3"
                            },
                            "carrierCode": "AS",
                            "number": "408",
                            "aircraft": {
                                "code": "73J"
                            },
                            "operating": {
                                "carrierCode": "AS"
                            },
                            "duration": "PT4H4M",
                            "id": "6",
                            "numberOfStops": 0,
                            "blacklistedInEU": false
                        }
                    ]
                }
            ],
            "price": {
                "currency": "USD",
                "total": "197.78",
                "base": "156.28",
                "fees": [
                    {
                        "amount": "0.00",
                        "type": "SUPPLIER"
                    },
                    {
                        "amount": "0.00",
                        "type": "TICKETING"
                    }
                ],
                "grandTotal": "197.78"
            },
            "pricingOptions": {
                "fareType": [
                    "PUBLISHED"
                ],
                "includedCheckedBagsOnly": false
            },
            "validatingAirlineCodes": [
                "AS"
            ],
            "travelerPricings": [
                {
                    "travelerId": "1",
                    "fareOption": "STANDARD",
                    "travelerType": "ADULT",
                    "price": {
                        "currency": "USD",
                        "total": "197.78",
                        "base": "156.28"
                    },
                    "fareDetailsBySegment": [
                        {
                            "segmentId": "3",
                            "cabin": "ECONOMY",
                            "fareBasis": "GH2ODVBN",
                            "class": "X",
                            "includedCheckedBags": {
                                "quantity": 0
                            },
                            "brandedFare": "SV"
                        },
                        {
                            "segmentId": "6",
                            "cabin": "ECONOMY",
                            "fareBasis": "GH4OAVBN",
                            "class": "X",
                            "includedCheckedBags": {
                                "quantity": 0
                            },
                            "brandedFare": "SV"
                        }
                    ]
                }
            ],
            "lastTicketingDateTime": "2023-09-14"
        }
    ],
    "dictionaries": {
        "locations": {
            "ORD": {
                "cityCode": "CHI",
                "countryCode": "US"
            },
            "SEA": {
                "cityCode": "SEA",
                "countryCode": "US"
            }
        },
        "aircraft": {
            "320": "AIRBUS A320",
            "73J": "BOEING 737-900"
        },
        "currencies": {
            "USD": "US DOLLAR"
        },
        "carriers": {
            "AS": "ALASKA AIRLINES",
            "DL": "DELTA AIR LINES"
        }
    }
}
~~~

### References
- Amadeus Airport and City Search API [Documenataion](https://developers.amadeus.com/self-service/category/flights/api-doc/airport-and-city-search/api-reference)
- Amadeus Flight Offers Search [Documenataion](https://developers.amadeus.com/self-service/category/flights/api-doc/flight-offers-search/api-reference)
- Amadeus Apis [Postman Collection](https://www.postman.com/amadeus4dev/workspace/amadeus-for-developers-s-public-workspace/documentation/2672636-27471449-d2ca-a8c4-1399-6b0cfbddd079)