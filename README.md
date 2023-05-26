
**Running app with java command**

We can run application on our PC if we have Java17 installed just using commands:
1) mvn clean package
2) java -jar target/photo-booth-0.0.1-SNAPSHOT.jar

For this business tasks REST API were implemented, also there is swagger documentation 
we can go to swagger page -> http://localhost:8080/swagger-ui/index.html#/
or just use implemented endpoints through the postman or other client soft.
**Endpoints functional description**
1) Endpoint for reversing the order of the words
            PUT http://localhost:8080/photo-booth/customer/sentence/reverse
            Body(Example): {
                    "sentence": "This is a good day"
                  }
            We get response:
            {
             "sentence": "day good a is This"
            }
2) There were also implemented endpoint for creation of customer who will be able to create the order
       POST http://localhost:8080/photo-booth/customer
       Body(Example): {
                       "name" : "Anton Avdiyevskiy",
                       "email" : "anto@gmail.com"
                       }
        We get response:
                       {
                       "name" : "Anton Avdiyevskiy",
                       "email" : "anto@gmail.com",
                       "id":1
                        }
     
   This new created customer was saved to H2 database(inmemory database). This db is used just
   in demonstration goal for saving data we work with. For commercial development of course some 
   real DB should be used.
   3) This is Endpoint for creation of new order
      POST http://localhost:8080/photo-booth/customer/1/order
      where 1 - the id of existing customer
      Body(example): {  "orders":[{
                        "orderType":"STRIPS"
                         },
                         {
                        "orderType":"PRINTS"
                         },
                         {
                        "orderType":"PANORAMAS"
                         }]
                     }                
      So, here customer with id 1 creates new order for three types of photo packages:
      prints (4x6 photo) for $5, panoramas (6x12 prints) for $7, and strips (two 2x6 photo strips) for $5.
      And we get the following response:
                  [
                     {
                     "orderType": "STRIPS",
                     "id": "1",
                     "price": 5.0,
                     "customerId": 1,
                     "date": "2023-05-26T01:20:20.189+00:00"
                     },
                     {
                     "orderType": "PRINTS",
                     "id": "2",
                     "price": 5.0,
                     "customerId": 1,
                     "date": "2023-05-26T01:20:20.189+00:00"
                     },
                     {
                     "orderType": "PANORAMAS",
                     "id": "3",
                     "price": 7.0,
                     "customerId": 1,
                     "date": "2023-05-26T01:20:20.189+00:00"
                     }
                  ]
     according to the terms of the lottery, the customer can win a prize if he made an order with all
     three types of prints in a happy minute. Now every 13th minute of an hour is considered lucky, and in
     case this customer make the same request but in 13th minute of an hour he get`s the next response,
     where  he receives 2 cheaper types of prints as a gift, we can see the price 0, the prices for these 
     prints will not be taken into account in the order.
                   [
                     {
                     "orderType": "STRIPS",
                     "id": "1",
                     "price": 0,
                     "customerId": 1,
                     "date": "2023-05-26T01:20:20.189+00:00"
                     },
                     {
                     "orderType": "PRINTS",
                     "id": "2",
                     "price": 0,
                     "customerId": 1,
                     "date": "2023-05-26T01:20:20.189+00:00"
                     },
                     {
                     "orderType": "PANORAMAS",
                     "id": "3",
                     "price": 7.0,
                     "customerId": 1,
                     "date": "2023-05-26T01:20:20.189+00:00"
                     }
                    ]  
   4) Endpoint for getting tax per month
     GET http://localhost:8080/photo-booth/tax/year/2023/month/5
     Total revenue for May is $17 and in this case we get the following response
                   {
                    "tax": 1.46625
                   }

**Notes**
Every time we run the app we should again fulfill our db with data because of using H2 db for demonstration.
All services were covered by unit tests.


**Running through Docker**

Also we can run our app in Docker container. We need to execute the following steps:
        
1) mvn clean package
2) docker build --tag=photo-booth:latest .
3) docker run -p8887:8888 photo-booth:latest

 In this case we can go to swagger page -> http://localhost:8887/swagger-ui/index.html#/
    or just use implemented endpoints on 8887 port.

