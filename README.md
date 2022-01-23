# vodafone-technical-test
This is a private repository with all the content generated during my technical test for role of Lead Software Engineer MSH.

# How to run

1. Create directory for the test.
2. Clone the project using this command inside the created directory:
```
git clone https://github.com/jpargoteo/vodafone-technical-test.git .
```
2. Go to directory devices-svc-boot.
3. Run the following command:
```
mvn spring-boot:run
```
4. Access to the following url on a browser:
```
http://localhost:8080/h2/
```
5. Connect using the credential:
```
User Name: sa
Password:
```
6. Check if the data is present in the database using the client with the following queries:
```
SELECT * FROM DEVICES;
SELECT * FROM SIMS ;
```
7. If there are no data, run the script *data.sql* in the directory *devices-svc-boot/src/main/resources/data.sql*
8. Use the endpoints to check the API's
```
http://localhost:8080/devices/available
http://localhost:8080/devices/waiting-activation
http://localhost:8080/mng/devices/{id}/remove-configuration
http://localhost:8080/mng/devices/{id}/sim/activate
http://localhost:8080/mng/devices/{id}/sim/block
http://localhost:8080/mng/devices/{id}/sim/deactivate
http://localhost:8080/mng/devices/{id}/sim/waiting-activation
```
