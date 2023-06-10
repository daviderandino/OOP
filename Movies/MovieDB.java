package movies;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Formatter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;


public class MovieDB {
	
	TreeMap<String, Actor> actors = new TreeMap<>();
	TreeMap<String, Director> directors = new TreeMap<>();
	TreeMap<String, Movie> movies = new TreeMap<>();

    public int addActor(String name, String nationality) throws Exception {
    	if (actors.containsKey(name)) throw new Exception("Already inserted");
    	actors.put(name, new Actor(name, nationality));
    	return actors.size();

    }
    
    public int addDirector(String name, String nationality) throws Exception {
    	if (directors.containsKey(name)) throw new Exception("Already inserted");
    	directors.put(name, new Director(name, nationality));
    	return directors.size();

    }
    
    public int addMovie(String title, String director, String...actorNames) throws Exception{
    	if(movies.containsKey(title)) throw new Exception("Already inserted");
    	if(!directors.containsKey(director)) throw new Exception("Non valid director");
    	if(!actors.keySet().containsAll(Arrays.asList(actorNames))) throw new Exception("Missing actor");
    	
    	Map<String, Actor> actorsMap = new TreeMap<>();
    	
    	for(String a:actorNames) {
    		Actor actor = actors.get(a);
    		actorsMap.put(a,actor);
    	}
    	
    	Director d = directors.get(director);
    	
    	Movie movie = new Movie(title,d,actorsMap);
    	
    	movies.put(title, movie);
    	
    	d.addMovie(movie);
    	
    	for(String a:actorNames) {
    		Actor actor = actors.get(a);
    		actor.addMovie(movie);
    	}
    
    	return movies.size();

    }
    
    
    public double addRating(String movie, Double...ratings) throws Exception {
    	
    	if(!movies.containsKey(movie)) throw new Exception("Movie does not exist");
    	
    	for(Double r:ratings) {
    		if(r<0 || r>10) throw new Exception("Invalid rating");
    	}
    	
    	Movie m = movies.get(movie);
    	
    	for(Double r:ratings) {
    		m.addRatings(r);
    	}
    	
    	Double avg = 0.0;
    	int k = 0;
    	
    	for(Double r:ratings) {
    		k++;
    		avg += r;
    	}
    	
    	Double d = avg/k;
    	 
    	d = round(d,2);
    	m.setAvgRating(d);
    	return d;
    	
    }
    
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
    
    public List<String> bestActors(){
    	
     /*
     List<String> act = new LinkedList<>();
     for(Actor actor:actors.values()) {
    	 for(Movie movie:actor.movieList()) {
    		 if(movie.getAvgRating()>8 && !act.contains(actor.getName()))
    			 act.add(actor.getName());
    	 }
     }
     
     return act ; 
     */
     
     return actors.values().stream()
             .filter(actor -> actor.getMovies().stream()
                     .allMatch(movie -> movie.getAvgRating() > 8))
             .map(Actor::getName)
             .collect(Collectors.toList());
    
    }
    
    public List<String> worstDirectors(){
    	/*
    	List<String> dir = new LinkedList<>();
    	for(Director director:directors.values()) {
       	 for(Movie movie:director.movieList()) {
       		 if(movie.getAvgRating()<6 && !dir.contains(director.getName()))
       			 dir.add(director.getName());
       	 }
       }
    	return dir;
    	*/
    	
    	return directors.values().stream()
                .filter(director -> director.getMovies().stream()
                        .anyMatch(movie -> movie.getAvgRating() < 6))
                .map(Director::getName)
                .collect(Collectors.toList());
    }
    
    public TreeMap<String, List<String>> groupActorsByDirector(){
    	TreeMap<String,List<String>> map = new TreeMap<>();
    	
    	for(Director director:directors.values()) {
    		for(Movie movie:director.movieList()) {
    			for(Actor actor:movie.actorsList()) {
    				
    				if(map.containsKey(director.getName())) {
    					List<String> l = map.get(director.getName());
    					if(!l.contains(actor.getName())) {
    						l.add(actor.getName());
    						map.put(director.getName(), l);
    					}
    				}
    				else {
    					map.put(director.getName(), new LinkedList<>());
    					List<String> l = map.get(director.getName());
    					l.add(actor.getName());
						map.put(director.getName(), l);
    				}
    				
    			}
    		}
    	}
    	
    	return map;
    	
    }
               
}
