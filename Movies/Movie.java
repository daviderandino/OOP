package movies;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Movie {
	
	private String title;
	private Director director;
	private Map<String, Actor> actors;
	private List<Double> ratings;
	private Double avgRating;
	
	public Movie(String title, Director director, Map<String, Actor> actors) {
		this.title = title;
		this.director = director;
		this.actors = actors;
		actors = new TreeMap<>();
		ratings = new LinkedList<>();
		avgRating = 0.0;
	}
	
	public void addRatings(Double rat) {
		ratings.add(rat);
	}
	
	public void setAvgRating(Double avg) {
		this.avgRating = avg;
	}
	
	public Double getAvgRating() {
		return this.avgRating;
	}
	
	public Collection<Actor> actorsList(){
		return actors.values();
	}
	
	

}
