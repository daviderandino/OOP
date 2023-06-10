-MovieDB class manages movies, actors and directors

-Actors are added using addActor(String name, String nationality). Actors must be unique or an exception is thrown.  Method returns number of valid actors inserted so far

-Directors are added in the same way as Actors

-Movies are inserted using int addMovie(String title, String director, String…actors), if actors and director already have been inserted, otherwise an exception is thrown. Number of movies added so far is returned

-Ratings for movies are added using double addRating(String movie, Double…ratings). Movies should be valid, ratings should be included between 0 and 10. Average rating for the movie is returned

-Method List<String> bestActors() returns actors appearing only in movies with an average score higher than 8.

-Method List<String> worstDirectors() returns directors having directed at least 1 movie with average rating lower than 6.

-Method Map<String, List<String> groupActorsByDirector() returns a map with director and a list of actors he or she has directed in all his/her movies.
