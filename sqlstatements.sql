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

CREATE TABLE planes(
    Id INTEGER NOT NULL,
    Capacity INTEGER NOT NULL,
    Count INTEGER NOT NULL,
    isFull ENUM("Yes", "No"),
    PRIMARY KEY(Id));

CREATE TABLE flightInfo(
    FlightNo INTEGER NOT NULL,
    Airline INTEGER NOT NULL,
    PlaneId INTEGER NOT NULL,
    Arrival DATETIME NOT NULL,
    Departure DATETIME NOT NULL,
    PRIMARY KEY(FlightNo, Airline, PlaneId),
    FOREIGN KEY (FlightNo) REFERENCES flights(FlightNo),
    FOREIGN KEY (Airline) REFERENCES airlines(Id),
    FOREIGN KEY (PlaneId) REFERENCES planes(Id));

CREATE TABLE seatings(
    PlaneId INTEGER NOT NULL,
    SeatNo INTEGER NOT NULL,
    Class ENUM("First", "Business", "Economy") NOT NULL,
    Customer INTEGER,
    SeatType ENUM("Window", "Aisle"),
    Price REAL NOT NULL,
    PRIMARY KEY(PlaneId, SeatNo),
    FOREIGN KEY (PlaneId) REFERENCES planes(Id));

CREATE TABLE creditCards(
    CardNo INTEGER NOT NULL,
    CustomerId INTEGER NOT NULL,
    FirstName VARCHAR(50) NOT NULL,
    LastName VARCHAR(50) NOT NULL,
    CCV INTEGER NOT NULL,
    EXP DATE NOT NULL,
    PRIMARY KEY(CardNo));

CREATE TABLE customers(
    Id INTEGER NOT NULL AUTO_INCREMENT,
    PassportNo VARCHAR(20) NOT NULL,
    Country VARCHAR(20) NOT NULL,
    FirstName VARCHAR(50) NOT NULL,
    LastName VARCHAR(50) NOT NULL,
    PRIMARY KEY(Id));

CREATE TABLE bookings(
    ReservationNo INTEGER NOT NULL AUTO_INCREMENT,
    Customer INTEGER NOT NULL,
    CardNo INTEGER NOT NULL,
    FlightNo INTEGER NOT NULL,
    SeatNo INTEGER NOT NULL,
    Cancelled ENUM("Yes", "No") NOT NULL,
    PRIMARY KEY(ReservationNo),
    FOREIGN KEY(Customer) REFERENCES customers(Id),
    FOREIGN KEY(CardNo) REFERENCES creditCards(cardNo),
    FOREIGN KEY(FlightNo) REFERENCES flights(FlightNo)
    );

CREATE TABLE transactions(
    Id INTEGER NOT NULL AUTO_INCREMENT,
    creditCard INTEGER NOT NULL,
    Amount REAL NOT NULL,
    Date DATE NOT NULL,
    Booking INTEGER NOT NULL,
    Refunded ENUM("Yes", "No") NOT NULL,
    PRIMARY KEY(Id),
    FOREIGN KEY(creditCard) REFERENCES creditCards(CardNo),
    FOREIGN KEY(Booking) REFERENCES bookings(ReservationNo));




