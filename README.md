# Metadata Challenge

This project was built in Java 11. Below i'm going show up  how to run locally the application

## Starting application

### Step 1 - We start building necessary images
The first thing you must do , its access the project folder after git clone <br>

* ```sh
  cd "project_folder_path"
  ```
Inside the project folder you must execute the command to builder the image
* ```sh
    docker-compose build
  ```
### Step 2 - Good. Image was built :), Let's run the application
Execute the command:

* ```sh
   docker-compose up 
  ```
### Everthing All right, Its time to use the app
Access in your web browser, you'll see the endpoints and payload details
* ```sh
   http://localhost:9094/swagger-ui/index.html
  ```  
You will see some resources , lets take a look how they works

* You can use this resources to input a new student and new course 
```sh
   POST /student 
   {
      "firstName": "Pelé"
   }

   POST /course 
   {
      "name": "Rusty"
   }
   ```
* Another endpoint its to assoaciate a student to a course
```sh
   POST /course/registration
   {
      "studentId": 0,
      "courseId": 0
   }
```
* This obtain all students with a specific course
```sh
   GET /course/registration/{courseId}
```

* And the last obtain all courses for a specific student
```sh
   GET /student/registration/{studentId}
```