# ✈️ Flight Booking API - Project Test

A Spring Boot REST API that powers flight booking chatbots with real flight data integration, and handles booking services for customers. This API provides endpoints for flight search and AI-powered conversation handling.

**Note: This is a backend API only - no frontend interface included. See https://github.com/danielwennstrom/FlightBooking_frontend**

## Key Features
- **Flight Search**: Integration with Google Flights API for real-time flight data
- **Airport Database**: Comprehensive airport selection from CSV data
- **AI-Powered Booking**: OpenAI integration with specialized flight booking prompts
- **Booking Management**: Create, view, and cancel flight reservations
- **Conversation Processing**: Extract flight details from natural language conversations

## Known Limitations
- While the AI itself only needs the IATA code to structure the data to call the Google Flights API, the airport data presented to the user on the frontend side is limited to open-sourced information in the provided CSV dataset.
- Airport data doesn't guarantee flight availability for locations beyond major hubs
- Booking simulation only - no actual flight purchases

## Built With
- Spring Boot
- OpenAI API
- Google Flights API (via RapidAPI)
- OpenCSV to parse CSV files
- Dotenv for environment variables
- MapStruct
- H2 and MySQL/MariaDB

## Prerequisites
- Java 17+
- Maven 3.6+
- OpenAI API account
- RapidAPI account (for Google Flights access)

## Quick Start
1. Clone the repository
2. Set up environment variables (see Installation section)
3. Run `./mvnw spring-boot:run` (or your preferred method)
4. API will be available at `http://localhost:8080`
5. View API documentation at `http://localhost:8080/swagger-ui.html`
6. Test endpoints with Postman, curl, or your preferred API client

## Installation
Before running the application, some environment variables need to be set. This project uses Dotenv to handle these variables. A '.env' file should be created in ```src/main/java/se/lexicon/flightbooking_api/config```.  
These variables include:
```yaml  
DB_USER=YOUR_USERNAME  
DB_PASSWORD=YOUR_PASSWORD  
OPENAI_API_KEY=YOUR_OPENAI_API_KEY  
RAPIDAPI_API_KEY=YOUR_RAPIDAPI_API_KEY  
```  

## TODO
- Refactor the flights searching into their own tools? Currently, the API call is done from the frontend side using structured data returned from the AI once all information is collected.
- Filter airports by type before anything else, ```medium_airport``` and ```large_airport```, which would likely be more viable for available flights.
- Redesign the entity structures, maybe collect more information from the conversation such as preferred currency and passenger information (number of adults, children, etc.) to be used for the flights search.
- Implement configuration options to enable/disable certain features.
- Rip out the whole booking system into a separate project, and turn it into a proper API.

## API documentation
API documentation is available through Swagger UI at http://localhost:8080/swagger-ui.html.   
If you'd prefer to work with the mock flight data in ```src/main/resources/data``` for development, see ```FlightSearchController.java``` until configuration options are implemented.

## Credits
- **DataCrawler** at RapidAPI for their Google Flights API (https://rapidapi.com/DataCrawler/api/google-flights2)
- **OurAirports** for their open airports data (https://ourairports.com/data/)
- **OpenAI** for providing the AI conversation capabilities
- **Lexicon** - This project was created as part of their Java course