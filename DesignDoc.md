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

_This is where we work backwards from the customer and define what our customers would like to do (and why). You may also include use cases for yourselves (as developers), or for the organization providing the product to customers._

1. As a user, I want to be able to create an account on Reel Deal so that I can save my favorite movies and review them.

2. As a user, I want to be able to search for movies by title, director, or actor so that I can easily find the films I'm looking for.

3. As a user, I want to be able to rate and review movies on Reel Deal so that I can share my thoughts with others.

4. As a user, I want to be able to follow other users on Reel Deal so that I can see their reviews and ratings, and discover new movies.

5. As a user, I want to be able to save movies to my "watchlist" on Reel Deal so that I can keep track of the films I want to watch later.

6. As a user, I want to be able to create custom lists on Reel Deal so that I can organize my favorite films by genre, director, or any other criteria.

7. As a user , I want to be able to view statistics and data on movies on Reel Deal so that I can learn more about the films I watch.

8. As a user, I want to be able to view trailers and posters for movies on Reel Deal so that I can get a better idea of what a film is about before watching it.


### 4.1. In Scope

* looking at reviews from others people
* search movies by title director and actor
* show popular movies to leave reviews on.
* Adding, updating, deleting, and viewing reviews.


### 4.2. Out of Scope

* following other users .


# 5. Proposed Architecture Overview

_Describe broadly how you are proposing to solve for the requirements you described in Section 2. This may include class diagram(s) showing what components you are planning to build. You should argue why this architecture (organization of components) is reasonable. That is, why it represents a good data flow and a good separation of concerns. Where applicable, argue why this architecture satisfies the stated requirements._

We will use API Gateway and Lambda to create endpoints that will handle the creation, updating, and retrieval of movies and reviews. 

We will store movie review information in a table in DynamoDB.

ReelDeal will also provide a web interface for users. A main page providing a variety of options to add, update and reminisce of all the movies you watched and maybe some you warn others not to watch.


# 6. API

## 6.1. Public Models

_Define the data models your service will expose in its responses via your *`-Model`* package._

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

GET /movies - Retrieve a list of all movies
GET /movies/{id} - Retrieve a specific movie by its ID
POST /movies - Create a new movie
PUT /movies/{id} - Update an existing movie
DELETE /movies/{id} - Delete a movie

GET /movies/{id}/reviews - Retrieve all reviews for a specific movie
GET /reviews/{id} - Retrieve a specific review by its ID
POST /reviews - Create a new review
PUT /reviews/{id} - Update an existing review
DELETE /reviews/{id} - Delete a review

GET /users - Retrieve a list of all users
GET /users/{id} - Retrieve a specific user by its ID
GET /users/{username} - Retrieve a specific user by its username
POST /users - Create a new user
PUT /users/{id} - Update an existing user
DELETE /users/{id} - Delete a user


# 7. Tables

_Define the DynamoDB tables you will need for the data your service will use. It may be helpful to first think of what objects your service will need, then translate that to a table structure, like with the *`Playlist` POJO* versus the `playlists` table in the Unit 3 project._

Movies:
- Table primary key: id (String)
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
- Table primary key: id (String)
- MovieId (String)
- Username (String)
- Text (String)
- Rating (Number)
- Date (String)
- GSI: MovieId-index
- GSI key: MovieId (String)
- GSI: Username-index
- GSI key: Username (String)

Users:
- Table primary key: id (String)
- Username (String)
- Email (String)
- GSI: Username-index
- GSI key: Username (String)


