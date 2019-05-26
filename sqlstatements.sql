CREATE TABLE flights(
	Airline INTEGER NOT NULL,
	FlightNo INTEGER NOT NULL,
	SourceAirport VARCHAR(3) NOT NULL,
	DestAirport VARCHAR(3) NOT NULL,
	PRIMARY KEY (FlightNo, Airline),
    FOREIGN KEY (SourceAirport) REFERENCES airports(AirportCode),
    FOREIGN KEY (DestAirport) REFERENCES airports(AirportCode),
    FOREIGN KEY (Airline) REFERENCES airlines(Id));

CREATE TABLE airports(
	City VARCHAR(50) NOT NULL,
	AirportCode VARCHAR(3) NOT NULL,
	AirportName VARCHAR(50) NOT NULL,
	Country VARCHAR(20) NOT NULL,
	CountryAbbrev VARCHAR(10) NOT NULL,
	PRIMARY KEY (AirportCode));

CREATE TABLE airlines(
	Id INTEGER NOT NULL,
	Airline VARCHAR(50) NOT NULL,
	Abbreviation VARCHAR(20) NOT NULL,
	Country VARCHAR(10) NOT NULL,
	PRIMARY KEY (Id));