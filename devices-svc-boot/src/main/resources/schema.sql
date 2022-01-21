DROP TABLE IF EXISTS DEVICES;
DROP TABLE IF EXISTS SIMS;

CREATE TABLE SIMS
(

    id           INT AUTO_INCREMENT PRIMARY KEY,
    operatorCode VARCHAR(250),
    countryName  VARCHAR(250),
    status       VARCHAR(250)
);


CREATE TABLE DEVICES
(

    id          INT AUTO_INCREMENT PRIMARY KEY,
    status      VARCHAR(250),
    temperature DOUBLE,
    sim         INT
);

ALTER TABLE DEVICES
    ADD FOREIGN KEY (sim) REFERENCES SIMS (id);