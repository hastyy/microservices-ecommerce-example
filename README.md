# E-Commerce Microservices Example

This is a just-for-fun project to explore some technologies around Spring and the microservices architecture.

## How to run

To start the system you just have to run the `start.sh` script on the root directory:

```
./start.sh
```

And to stop the system you can run the `stop.sh` script:

```
./stop.sh
```

## Testing the system

1. Create a user:

```
curl --location --request POST 'http://localhost:8080/users/users' \
--header 'Content-Type: application/json' \
--data-raw '{
	"email": "user@example.com",
	"password": "mypassword"
}'
```

2. Authenticate within the system:

```
curl --location --request POST 'http://localhost:8080/users/authentication/login' \
--header 'Content-Type: application/json' \
--data-raw '{
	"email": "user@example.com",
	"password": "mypassword"
}'
```

3. Copy the JWT you get as a response to the previous request

4. Start creating orders:

```
curl --location --request POST 'http://localhost:8080/orders//orders' \
--header 'Authorization: Bearer <your-token-here>' \
--header 'Content-Type: application/json' \
--data-raw '{
	"products": [
		{
			"productId": 1,
			"quantity": 10
		},
		{
			"productId": 2,
			"quantity": 20
		},
		{
			"productId": 3,
			"quantity": 30
		}
	]
}'
```

_NOTE:_ Order creation might return 504 status code some times due to a timeout on the api gateway because the threshold is pretty low
