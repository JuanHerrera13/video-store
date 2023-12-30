# Video Store 📼

That's my Video Store project!

A project developed with the purpose to study and improve my knowledge around Java, Spring, MongoDB and good practices.

The video-store project is basically what the name already says: a video-store 😛 

With this application it's possible to
add a bunch of movies and assign them to customers. The business rules will be explained later.

# What's necessary to run this project? 📚

- Have Java installed in your machine. If you don't have Java already, it can be installed by accessing the link
https://www.oracle.com/java/technologies/downloads/, or through the IDE which will automatically ask if you want to 
download an X version.
- I'm using the IntelliJ IDE to run the project. But you can choose any of your preference (as long as it's possible to 
run Java applications).
- Have MongoDB installed. There are a lot of reasons that I could put to justify my choice, but I'll just mention the 
database's performance. If you aren't familiar with MongoDB, you can take a look at its official documentation pages by 
the links: https://www.mongodb.com/docs/manual/installation/, https://www.mongodb.com/docs/manual/tutorial/getting-started/.
To manage, search and some more reasons, I'm using the MongoDB Compass, the official MongoDB GUI (graphical user interface)
that can be installed by the link: https://www.mongodb.com/docs/compass/current/install/.
- Have Postman installed. Postman is a platform for API development. By using it, it's possible to create HTTP requests 
by hitting the application's endpoints. The Postman app can be installed by the link: https://www.postman.com/downloads/

# How to run? 🏃🏻‍

- Reload all the Gradle projects.
- Build the application. 
- Start a MongoDB server. 
- Run the VideoStoreApplication. 
- Call the Video Store APIs and have a little bit of fun 🫡

# Business Rules 🤓

- A customer has a rental limit of five films. Once he/she reaches his/hers limit, he/she will not be able to rent 
until he/she returns a film.
- A customer can not rent a movie that was already rented by him/her.
- A customer cannot return a film that has not been rented by him/her.
- It is not possible to add a Customer that already exists in db (it's verified by the ssn).
- It is not possible to add a Movie that already exists in db (it's verified by the title). 
- It is not possible to rent a film if there are no more copies available.