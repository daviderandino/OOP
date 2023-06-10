package example;

import java.util.List;
import java.util.TreeMap;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import movies.MovieDB;

public class TestApp {
    private MovieDB movieDB;

    @Before
    public void setUp() throws Exception {
        movieDB = new MovieDB();
    }

    @Test
    public void testAddActor() throws Exception {
        int actorsCount = movieDB.addActor("Tom Hanks", "American");
        Assert.assertEquals(1, actorsCount);

        // Adding the same actor again should throw an exception
        try {
            movieDB.addActor("Tom Hanks", "American");
            Assert.fail("Expected an Exception to be thrown");
        } catch (Exception e) {
            Assert.assertEquals("Already inserted", e.getMessage());
        }
    }

    @Test
    public void testAddDirector() throws Exception {
        int directorsCount = movieDB.addDirector("Christopher Nolan", "British");
        Assert.assertEquals(1, directorsCount);

        // Adding the same director again should throw an exception
        try {
            movieDB.addDirector("Christopher Nolan", "British");
            Assert.fail("Expected an Exception to be thrown");
        } catch (Exception e) {
            Assert.assertEquals("Already inserted", e.getMessage());
        }
    }

    @Test
    public void testAddMovie() throws Exception {
        movieDB.addDirector("Christopher Nolan", "British");
        movieDB.addActor("Tom Hanks", "American");

        int moviesCount = movieDB.addMovie("Inception", "Christopher Nolan", "Tom Hanks");
        Assert.assertEquals(1, moviesCount);

        // Adding the same movie again should throw an exception
        try {
            movieDB.addMovie("Inception", "Christopher Nolan", "Tom Hanks");
            Assert.fail("Expected an Exception to be thrown");
        } catch (Exception e) {
            Assert.assertEquals("Already inserted", e.getMessage());
        }

        // Adding a movie with a non-existing director should throw an exception
        try {
            movieDB.addMovie("Interstellar", "Steven Spielberg", "Tom Hanks");
            Assert.fail("Expected an Exception to be thrown");
        } catch (Exception e) {
            Assert.assertEquals("Non valid director", e.getMessage());
        }

        // Adding a movie with missing actors should throw an exception
        try {
            movieDB.addMovie("Interstellar", "Christopher Nolan", "Tom Hanks", "Leonardo DiCaprio");
            Assert.fail("Expected an Exception to be thrown");
        } catch (Exception e) {
            Assert.assertEquals("Missing actor", e.getMessage());
        }
    }

    @Test
    public void testAddRating() throws Exception {
        movieDB.addDirector("Christopher Nolan", "British");
        movieDB.addActor("Tom Hanks", "American");

        movieDB.addMovie("Inception", "Christopher Nolan", "Tom Hanks");

        double averageRating = movieDB.addRating("Inception", 9.5, 8.7, 9.0);
        Assert.assertEquals(9.07, averageRating, 0.01);

        // Add ratings exceeding the valid range, should throw an exception
        try {
            movieDB.addRating("Inception", 9.5, 8.7, 11.0);
            Assert.fail("Expected an Exception to be thrown");
        } catch (Exception e) {
            Assert.assertEquals("Invalid rating", e.getMessage());
        }

        // Add ratings for a non-existing movie, should throw an exception
        try {
            movieDB.addRating("Interstellar", 8.5, 9.2);
            Assert.fail("Expected an Exception to be thrown");
        } catch (Exception e) {
            Assert.assertEquals("Movie does not exist", e.getMessage());
        }
    }

    @Test
    public void testBestActors() throws Exception {
        movieDB.addDirector("Christopher Nolan", "British");
        movieDB.addDirector("Steven Spielberg", "American");
        movieDB.addActor("Tom Hanks", "American");
        movieDB.addActor("Leonardo DiCaprio", "American");
        movieDB.addActor("Matthew McConaughey", "American");

        movieDB.addMovie("Inception", "Christopher Nolan", "Leonardo DiCaprio");
        movieDB.addMovie("Interstellar", "Christopher Nolan", "Matthew McConaughey");
        movieDB.addMovie("Catch Me If You Can", "Steven Spielberg", "Tom Hanks", "Leonardo DiCaprio");
        
        movieDB.addRating("Inception", 9.5, 8.7, 10.0);
        movieDB.addRating("Interstellar", 5.0, 8.0, 7.0);
        movieDB.addRating("Catch Me If You Can", 8.5, 9.0);
        
        List<String> bestActors = movieDB.bestActors();

        Assert.assertEquals(2, bestActors.size());
        Assert.assertTrue(bestActors.contains("Leonardo DiCaprio"));
        Assert.assertTrue(bestActors.contains("Tom Hanks"));
    }

    @Test
    public void testWorstDirectors() throws Exception {
        movieDB.addDirector("Christopher Nolan", "British");
        movieDB.addDirector("Steven Spielberg", "American");
        movieDB.addActor("Tom Hanks", "American");
        movieDB.addActor("Matthew McConaughey", "American");
        
        movieDB.addMovie("Inception", "Christopher Nolan", "Tom Hanks");
        movieDB.addMovie("Interstellar", "Christopher Nolan", "Matthew McConaughey");
        movieDB.addMovie("War of the Worlds", "Steven Spielberg", "Tom Hanks");
        
        movieDB.addRating("Inception", 9.5, 8.7, 10.0);
        movieDB.addRating("Interstellar", 5.0, 3.0, 7.0);
        movieDB.addRating("War of the Worlds", 8.5, 9.0);
        
        List<String> worstDirectors = movieDB.worstDirectors();
   
        Assert.assertEquals(1, worstDirectors.size());
        Assert.assertTrue(worstDirectors.contains("Christopher Nolan"));
    }

    @Test
    public void testGroupActorsByDirector() throws Exception {
        movieDB.addDirector("Christopher Nolan", "British");
        movieDB.addDirector("Steven Spielberg", "American");
        movieDB.addActor("Tom Hanks", "American");
        movieDB.addActor("Leonardo DiCaprio", "American");
        movieDB.addActor("Matthew McConaughey", "American");

        movieDB.addMovie("Inception", "Christopher Nolan", "Leonardo DiCaprio");
        movieDB.addMovie("Interstellar", "Christopher Nolan", "Matthew McConaughey");
        movieDB.addMovie("Catch Me If You Can", "Steven Spielberg", "Tom Hanks", "Leonardo DiCaprio");

        TreeMap<String, List<String>> actorsByDirector = movieDB.groupActorsByDirector();

        Assert.assertEquals(2, actorsByDirector.size());

        List<String> nolanActors = actorsByDirector.get("Christopher Nolan");
        Assert.assertEquals(2, nolanActors.size());
        Assert.assertTrue(nolanActors.contains("Leonardo DiCaprio"));
        Assert.assertTrue(nolanActors.contains("Matthew McConaughey"));

        List<String> spielbergActors = actorsByDirector.get("Steven Spielberg");
        Assert.assertEquals(2, spielbergActors.size());
        Assert.assertTrue(spielbergActors.contains("Tom Hanks"));
        Assert.assertTrue(spielbergActors.contains("Leonardo DiCaprio"));
    }
}
