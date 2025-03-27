
# TAM Challenge
## Description:
* The aim of the application is to expose 2 REST APIs. 
* The first API provides the ability to capture a number of images for different animals and store them in a database. The API is configured for capturing images for either a cat, dog or a bear from the follwoing URLs:  

`https://cataas.com/cat`  
`https://placebear.com/200/300`  
`https://place.dog/300/200`  

* 2 parameters are provided in the APIs to identify which animal and the number of images required.
* Another API is provided to retrieve the last image that was loaded into the database.



## Techincal implementation:
* Springboot is used for building the microservices with Java as the development language. 
* H2 database is used to capture the images.
        

* REST APIs:  
  
`http://{host_name}:{port}/fetchsave/{animal_type}/{number_of_images}`  

`http://{host_name}:{port}/fetchLastPhoto`

        For example:
            http://localhost:8080/fetchsave/dog/2
            http://localhost:8080/fetchLastPhoto

### Run the application using Maven

The application is configured with a mvnnw (wrapper) command to compile, build and install the application. Once imported to an IDE, the mvnw can be issued to compile and run Spring boot and validate the REST APIs  
The following are the instructions:  
`./mvnw clean`  
`./mvnw install`  

The REST APIs URLs are available on:  
`http://localhost:8080/fetchsave/{animal_type}/{number_of_images}`  
                animal_type are either cat, dog or bear  
                number_of_images is an integer with the number of images to retrieve  
`http://localhost:8080/fetchLastPhoto`  


### Build the application with Docker  
A docker file is available with the application to build and run the image.  
The following are the instructions:
* This requires the maven install phase, please follow the instructions for maven first  
* run the build command:  
`docker build -t tam-challenge .`
* validate that the image is created and available:  
`docker images`
* run the docker image  
`docker run -d -p 8000:8080 tam-challenge`  
* To check the rest APIs:  
in a browser open the following URLs:  
`http://localhost:8000/fetchsave/{animal_type}/{number_of_images}`  
animal_type are either cat, dog or bear  
number_of_images is an integer with the number of images to retrieve  
`http://localhost:8000/fetchLastPhoto`  


    
### Running the HTML file to validate the Microservices  
An HTML is included with the application to demonstrate a UI and to interact with the APIs. The HTML file is called TamChallenge.html and is located under /src/main/resources/static folder  
