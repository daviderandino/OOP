package movies;

import java.util.LinkedList;
import java.util.List;

public class Actor {
	
	private String name;
	private String nationality;
	
	List<Movie> movies;
	
	public Actor(String name, String nationality) {
		this.name = name;
		this.nationality = nationality;
		movies = new LinkedList<>();
	}
	
	public void addMovie(Movie m) {
		movies.add(m);
	}
	
	public List<Movie> movieList() {
		return movies;
	}

	public String getName() {
		return name;
	}

	public String getNationality() {
		return nationality;
	}

	public List<Movie> getMovies() {
		return movies;
	}
	
	
	
}
