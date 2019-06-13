--adding a booking
--1.remove price of seats from creditcard, create transaction
--2.fill up the seat, add to plane
DELIMITER $
CREATE TRIGGER `check_seat_not_booked`
BEFORE INSERT ON `bookings`
FOR EACH ROW
BEGIN
  IF (SELECT customer FROM seatings WHERE NEW.seatno = seatno AND planeid = (
      select planeid from flightInfo where NEW.airline = airline AND NEW.flightNo = flightNo)
      ) IS NOT NULL THEN
    SIGNAL SQLSTATE '12345'
      SET MESSAGE_TEXT = 'Seat already taken on this plane and flight';

  END IF ;
END$
DELIMITER;

DELIMITER $
CREATE TRIGGER `update_booked_seats`
AFTER INSERT ON `bookings`
FOR EACH ROW
BEGIN
  UPDATE seatings SET customer = NEW.customer
  WHERE seatno = NEW.seatno AND planeid = (
    SELECT f.planeid FROM flightInfo f
    WHERE f.flightno = NEW.flightno AND f.airline = NEW.airline
    );
END $
DELIMITER;

DELIMITER $
CREATE TRIGGER `check_flights_not_overbooked`
BEFORE INSERT ON `bookings`
FOR EACH ROW
BEGIN
  IF (( SELECT p.count FROM planes p WHERE p.id = (
          SELECT f.planeid
          FROM flightInfo f
          WHERE f.airline = NEW.airline
          AND f.flightno = NEW.flightno
      )) + 1)
      >=
      ( SELECT p.capacity FROM planes p WHERE p.id = (
          SELECT f.planeid
          FROM flightInfo f
          WHERE f.airline = NEW.airline
          AND f.flightno = NEW.flightno
      ) ) THEN
        SIGNAL SQLSTATE '12345'
          SET MESSAGE_TEXT = 'This flight on this plane is overbooked';
  END IF;
END $
DELIMITER;

DELIMITER $
CREATE TRIGGER `update_booked_flights`
AFTER INSERT ON `bookings`
FOR EACH ROW
BEGIN
  UPDATE planes p SET p.count = (p.count + 1)
  WHERE p.id = (
    SELECT f.planeid FROM flightInfo f
    WHERE f.flightno = NEW.flightno AND f.airline = NEW.airline
    );
END $
DELIMITER;

--cancelling a booking

DELIMITER $
CREATE TRIGGER `update_cancelled_flights`
AFTER UPDATE ON `bookings`
FOR EACH ROW
BEGIN
  UPDATE transactions SET refunded = 'Yes'
  WHERE NEW.cancelled = 'Yes'
  AND booking = NEW.reservationNo;
END $
DELIMITER;

DELIMITER $
CREATE TRIGGER `remove_cancelled_flight`
AFTER UPDATE ON `bookings`
FOR EACH ROW
BEGIN
  UPDATE planes p SET p.count = (p.count - 1)
  WHERE p.id = (
    SELECT planeid FROM flightinfo
    WHERE airline = NEW.airline
    AND flightno = NEW.flightno
  );
END $
DELIMITER;

DELIMITER $
CREATE TRIGGER `remove_cancelled_seat`
AFTER UPDATE ON `bookings`
FOR EACH ROW
BEGIN
  UPDATE seatings SET custumer = NULL
  WHERE planeid = (
      SELECT planeid FROM flightinfo
      WHERE airline = NEW.airline
      AND flightno = NEW.flightno )
  AND seatno = NEW.seatno;
END $
DELIMITER;


