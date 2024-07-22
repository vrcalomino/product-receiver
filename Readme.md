# Product Requests Receiver

This is one of the services of this [project](https://github.com/vrcalomino/product-queue).

This one consumes the queue and sends an email with the requested information.

## Run the project!

I'd recommend running the whole project following the instructions of the main Readme. 
However, you can run this service alone.

First you need to create an .env file with these credentials:

```.dotenv
RABBITMQ_HOST=localhost
RABBITMQ_PORT=5672
RABBITMQ_USERNAME=guest
RABBITMQ_PASSWORD=guest
EMAIL_SENDER=email@email.com
EMAIL_SENDER_PASSWORD=app password
```

Note: The email provided will be the one sending the email, and the password needed to run this app is an
application password which you can get [here](https://support.google.com/mail/answer/185833?hl=es-419).

### Local:

I'll leave you steps to run the project manually:

- Run `mvn clean package`
- Then run `java -jar target/product-0.0.1-SNAPSHOT.jar`

### With docker:

- Run `docker build -t <image_name> .`
- Then run `docker run <image_name>`

### Important: this project will run but do nothing without a rabbitmq valid connection.

#### Technologies:

- Maven
- Java 17