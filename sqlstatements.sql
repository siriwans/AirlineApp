CREATE TABLE flights(
	Airline INTEGER NOT NULL,
	FlightNo INTEGER NOT NULL,
	SourceAirport VARCHAR(3) NOT NULL,
	DestAirport VARCHAR(3) NOT NULL,
	PRIMARY KEY (FlightNo, Airline),
    FOREIGN KEY (SourceAirport) REFERENCES airports(AirportCode),
    FOREIGN KEY (DestAirport) REFERENCES airports(AirportCode),
    FOREIGN KEY (Airline) REFERENCES airlines(Id));

CREATE TABLE flightinfo(
  airline INTEGER NOT NULL,
  arrival DATETIME NOT NULL,
  departure DATETIME NOT NULL,
  PRIMARY KEY(Airline));

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

CREATE TABLE planes(
  id INTEGER NOT NULL AUTO_INCREMENT,
  capacity INTEGER NOT NULL,
  first INTEGER NOT NULL,
  business INTEGER NOT NULL,
  economy INTEGER NOT NULL,
  full TINYINT DEFAULT 0,
  PRIMARY KEY(id));
