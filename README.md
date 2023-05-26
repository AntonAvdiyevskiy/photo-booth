
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
            ![image](https://github.com/AntonAvdiyevskiy/photo-booth/assets/23200921/937b7a40-d392-409f-aee8-651ba07b8332)  
            Response:
            ![image](https://github.com/AntonAvdiyevskiy/photo-booth/assets/23200921/eae84875-1d2f-4a5c-8c47-ef58a160e5e6)


2) There were also implemented endpoint for creation of customer who will be able to create the order  
       POST http://localhost:8080/photo-booth/customer  
       ![image](https://github.com/AntonAvdiyevskiy/photo-booth/assets/23200921/41ba4378-2cf0-43a3-81c1-d7e0450f8834)  
        We get response:  
        ![image](https://github.com/AntonAvdiyevskiy/photo-booth/assets/23200921/028f53c6-4fcc-4062-974d-fddd5505b9b5)

     
   This new created customer was saved to H2 database(inmemory database). This db is used just  
   in demonstration goal for saving data we work with. For commercial development of course some   
   real DB should be used.  
   
   3) This is Endpoint for creation of new order  
      POST http://localhost:8080/photo-booth/customer/1/order  
      where 1 - the id of existing customer  
      ![image](https://github.com/AntonAvdiyevskiy/photo-booth/assets/23200921/5ebe0ec6-55e2-4773-b7f7-592a694000c7)  
                        
      So, here customer with id 1 creates new order for three types of photo packages:    
      prints (4x6 photo) for $5, panoramas (6x12 prints) for $7, and strips (two 2x6 photo strips) for $5.    
      And we get the following response:    
      ![image](https://github.com/AntonAvdiyevskiy/photo-booth/assets/23200921/38e70459-0425-46ad-a8c2-51424c547197)  

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

