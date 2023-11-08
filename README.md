# Books-Sample-Project
This is a sample project I made in my free time to have it in times I want to learn about new libraries/technologies that appear so I can implement them in this simple project.

The application is a very simple app with little design but with a clean code architecture each implemented in their own module with a buildSrc approach to manage dependencies and all the request are emulated like they were API request with delays and in some case exceptions throws

The application has a simple login with two states in which it can have the user saved from preferences or the use can come fresh. 

After we login we have a home screen in which we have three tabs Favoures, Books and User:
In the favourites books we emulate that we do a request to an API to get the favourite books
In the books we emulate that we do a request to an API to get all the books and we save them in a Room database so next time we call the all books request we get it from the database
In the user tab we have info from the user logged in coming from an emulated API request and from preferences as well. With a logout button we can use to go to the login again clearing all the data such as preferences and database

We can click in any book to go to a book detail screen in which we are shown the book detail info

Libraries used in the applicattion:
Coroutines with flows for all layers of the app
Compose for all the views
Compose destinations for easier compose navigation
Koin for dependency injection
Room for database
DataStore for prferences
Truth for more readable test cases

