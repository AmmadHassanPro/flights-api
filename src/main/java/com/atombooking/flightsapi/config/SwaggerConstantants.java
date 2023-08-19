package com.atombooking.flightsapi.config;

public interface SwaggerConstantants {
	
	public static final String ERROR_UNEXPECTED_EX_RESPONSE_EXAMPLE = "{\r\n"
      		+ "  \"errors\": [\r\n"
      		+ "    {\r\n"
      		+ "      \"status\": 500,\r\n"
      		+ "      \"code\": 141,\r\n"
      		+ "      \"title\": \"SYSTEM ERROR HAS OCCURRED\"\r\n"
      		+ "    }\r\n"
      		+ "  ]\r\n"
      		+ "}" ;
	
	public static final String ERROR_400_GET_OFFERS_RESPONSE_EXAMPLE= "{\r\n"
      		+ "  \"errors\": [\r\n"
      		+ "    {\r\n"
      		+ "      \"status\": 400,\r\n"
      		+ "      \"code\": 477,\r\n"
      		+ "      \"title\": \"INVALID FORMAT\",\r\n"
      		+ "      \"detail\": \"invalid query parameter format\",\r\n"
      		+ "      \"source\": {\r\n"
      		+ "        \"parameter\": \"airport\",\r\n"
      		+ "        \"example\": \"CDG\"\r\n"
      		+ "      }\r\n"
      		+ "    }\r\n"
      		+ "  ]\r\n"
      		+ "}";
	
	public static final String CODES_TABLE_GET_OFFERS = "| Code    | Title |\r\n"
      		+ "| --------| ------- |\r\n"
      		+ "| 425     | INVALID DATE    |\r\n"
      		+ "| 477     | INVALID FORMAT    |\r\n"
      		+ "| 2668     | PARAMETER COMBINATION INVALID/RESTRICTED    |\r\n"
      		+ "| 4926     | INVALID DATA RECEIVED    |\r\n"
      		+ "| 10661     | MAXIMUM NUMBER OF OCCURRENCES EXCEEDED    |\r\n"
      		+ "| 32171     | MAXIMUM NUMBER OF OCCURRENCES EXCEEDED    |\r\n";
	
	public static final String CODES_TABLE_CITY_AND_AIRPORT = "| Code    | Title |\r\n"
      		+ "| --------| ------- |\r\n"
      		+ "| 572     | INVALID OPTION    |\r\n";
	
	public static final String ERROR_400_CITY_AND_AIRPORT_RESPONSE_EXAMPLE = "{\r\n"
			+ "  \"errors\": [\r\n"
			+ "    {\r\n"
			+ "      \"status\": 400,\r\n"
			+ "      \"code\": 477,\r\n"
			+ "      \"title\": \"INVALID FORMAT\",\r\n"
			+ "      \"detail\": \"invalid query parameter format\",\r\n"
			+ "      \"source\": {\r\n"
			+ "        \"parameter\": \"airport\",\r\n"
			+ "        \"example\": \"CDG\"\r\n"
			+ "      }\r\n"
			+ "    }\r\n"
			+ "  ]\r\n"
			+ "}";
	
	
	public static final String ERROR_404_CITY_AND_AIRPORT_RESPONSE_EXAMPLE = "{\r\n"
			+ "  \"errors\": [\r\n"
			+ "    {\r\n"
			+ "      \"status\": 404,\r\n"
			+ "      \"code\": 1797,\r\n"
			+ "      \"title\": \"NOT FOUND\",\r\n"
			+ "      \"detail\": \"no response found for this query parameter\",\r\n"
			+ "      \"source\": {\r\n"
			+ "        \"parameter\": \"airport\"\r\n"
			+ "      }\r\n"
			+ "    }\r\n"
			+ "  ]\r\n"
			+ "}";
	
	


}
