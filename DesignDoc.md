# Design Document

## _ReelDeal_ Design

## 1. Problem Statement

the reel deal is a letterboxd clone that lets you rate and review the movies you have recently seen to share your thoughts to your fellow friends and family. you can also preview the films your friends have left reviews on and add them to a watch list to later review. The reel deal will also feature popular movies if you're unsure what to watch next.

## 2. Top Questions to Resolve in Review


1. Working with multiple gsi.
2. Figuring how to make a search function. (looking up certain movies by title, director or actor)
3. Creating endpoint for movie API.
4. Should I incorporate a follow feature for other movie buffs
5. Find a way to link socials or sign in with socials 


## 3. Use Cases


1. As a user, I want to be able to create an account on Reel Deal so that I can save my favorite movies and review them.

2. As a user, I want to be able to search for movies by title, director, or actor so that I can easily find the films I'm looking for.

3. As a user, I want to be able to rate and review movies on Reel Deal so that I can share my thoughts with others.

4. As a user, I want to be able to follow other users on Reel Deal so that I can see their reviews and ratings, and discover new movies.

5. As a user, I want to be able to save movies to my "watchlist" on Reel Deal so that I can keep track of the films I want to watch later.

6. As a user, I want to be able to create custom lists on Reel Deal so that I can organize my favorite films by genre, director, or any other criteria.

7. As a user , I want to be able to view statistics and data on movies on Reel Deal so that I can learn more about the films I watch.

8. As a user, I want to be able to view trailers and posters for movies on Reel Deal so that I can get a better idea of what a film is about before watching it.

9. As a user, I want to be able to connect my ReelDeal account to my social media accounts so that I can share my reviews and ratings with my friends.

### 4.1. In Scope

* looking at reviews from others people
* search movies by title director and actor
* show popular movies to leave reviews on
* Adding, updating, deleting, and viewing reviews


### 4.2. Out of Scope

* following other users 
* login with socials

# 5. Proposed Architecture Overview


We will use API Gateway and Lambda to create endpoints that will handle the creation, updating, and retrieval of movies and reviews. 

We will store movie review information in a table in DynamoDB.

ReelDeal will also provide a web interface for users. A main page providing a variety of options to add, update and reminisce of all the movies you watched and maybe some you warn others not to watch.


# 6. API

## 6.1. Public Models

```
// Movie 
    private int id;
    private String title;
    private String description;
    private String releaseDate;
    private String posterUrl;
    private List<String> genres;
    private List<String> cast;
    private String director;


// Review 
    private int id;
    private int movieId;
    private String username;
    private String text;
    private int rating;
    private Date date;


// User 
    private int id;
    private String username;
    private String password;
    private String email;
    private List<Review> reviews;


```
Get All Movies Endpoint
GET /movies - Retrieve a list of all movies
if there is no data, will throw NoDataFoundException

Get Movie Endpoint
GET /movies/{id} - Retrieve a specific movie by its ID
if movie id is not found throw 'MovieNotFoundException'

Create Movie Endpoint
POST /movies - Accepts post to create a new movie includes title and short description for non null fields 
if movie contains invalid characters will throw 'InvalidAttributeValueException'
```{ "title": "The Godfather", "description": "no gabbagool?","releaseDate": "1972-03-24","posterUrl": "the-godfather.jpg","genres": ["Drama", "Crime"],"cast": ["Al Pacino"],"director": "Francis Ford Coppola"}```

Update Movie Endpoint
PUT /movies/{id} - Update an existing movie
Accepts data to Movie 
if movie is not found throw 'MovieNotFoundException'
```{ "description": "organized crime in New York City.","genres": ["Crime", "Drama"] }```

Delete Movie Endpoint
DELETE /movies/{id} - Delete a movie
accepts data to delete movie by using movie ID

GetMovieReview Endpoint
GET /movies/{id}/reviews - Retrieve all reviews for a specific movie
returns movie review based on ID
if movie review is not found throw 'MovieNotFoundException'

GetReview Endpoint
GET /reviews/{id} - Retrieve a specific review by its ID
returns review for specific ID
if review is not found 'ReviewNotFoundException'

AddMovieReview Endpoint
POST /reviews - Create a new review
accepts post request to review 
contains movieId , username, review, ratings, and date
```{ "id": 1, "movieId": 1, "username": "user1", "text": "Great movie, highly recommend it!", "rating": 5, "date : "2023-01-24" }```

UpdateMovieReview Endpoint
PUT /reviews/{id} - Update an existing review
accepts data to update review by ID
if review is not found throw 'ReviewNotFoundException'
```{ "text": "Amazing movie, definitely a must-watch!", "rating": 5 }```

DeleteReview Endpoint
DELETE /reviews/{id} - Delete a review
accepts data to delete review by ID

GetAllUsers Endpoint
GET /users - Retrieve a list of all users
Returns all users if there is no data, will throw 'NoDataFoundException'

GetUserById Endpoint
GET /users/{id} - Retrieve a specific user by its ID
Returns user by id if ID does not exist 'UserNotFoundException'

GetUserByUsername Endpoint
GET /users/{username} - Retrieve a specific user by its username
Returns user by username if ID does not exist 'UserNotFoundException'

AddUser Endpoint
POST /users - Create a new user
creates new user with username, password, and email
```{ "username": "user1", "email": "user1@example.com", "password": "password1" }```

UpdateUser Endpoint
PUT /users/{id} - Update an existing user
updates users information if user is not found, throw 'UserNotFoundException'
```{ "email": "user1new@example.com", "password": "password2" }```

DeleteUser Endpoint
DELETE /users/{id} - Delete a user
accepts data to delete user by ID

# 7. Tables

Movies:
- Table partition key: id (String)
- Title (String)
- Description (String)
- ReleaseDate (String)
- PosterUrl (String)
- Genres (List<String>)
- Cast (List<String>)
- Director (String)
- GSI: Title-index
- GSI key: Title (String)

Reviews:
- Table partition key: id (String)
- MovieId (String)
- Username (String)
- Text (String)
- Rating (Number)
- moviedate (String)
- GSI: MovieId-index
- GSI key: MovieId (String)
- GSI: Username-index
- GSI key: Username (String)

Users:
- partition key : Username (String)
- Email (String)
- GSI: Username-index
- GSI key: Username (String)


Pages

