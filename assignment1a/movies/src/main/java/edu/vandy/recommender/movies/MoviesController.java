package edu.vandy.recommender.movies;

import edu.vandy.recommender.movies.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static edu.vandy.recommender.movies.Constants.EndPoint.GET_ALL_MOVIES;
import static edu.vandy.recommender.movies.Constants.EndPoint.GET_SEARCH;
import static edu.vandy.recommender.movies.Constants.EndPoint.GET_SEARCHES;

/**
 * The Spring controller for the {@link MoviesService}, whose endpoint
 * handler methods return a {@link List} of objects containing
 * information about movies.
 *
 * {@code @RestController} is a convenience annotation for creating
 * Restful controllers. It is a specialization of {@code @Component}
 * and is automatically detected through classpath scanning. It adds
 * the {@code @Controller} and {@code @ResponseBody} annotations. It
 * also converts responses to JSON or XML.
 */
@RestController
public class MoviesController {
    /**
     * A central interface that provides configuration for this
     * microservice and is read-only while the application is running,
     */
    ApplicationContext applicationContext;

    /**
     * Spring-injected {@link MoviesService}.
     */
    // TODO -- ensure that 'service' is autowired with the appropriate
    // @Bean factory method.
    @Autowired
    private MoviesService service;

    /**
     * A request for testing Eureka connection.
     *
     * @return The application name.
     */
    @GetMapping({"/", "/actuator/info"})
    ResponseEntity<String> info() {
        // Indicate the request succeeded.  and return the application
        // name.
        return ResponseEntity
            .ok(applicationContext.getId() 
                + " is alive\n");
    }

    /**
     * @return A {@link List} of all movies
     */
    @GetMapping("/"+GET_ALL_MOVIES)
    public List<Movie> getAllMovies() {
        return service.getMovies();
    }
    // TODO -- Create an endpoint with an annotation that maps HTTP
    // GET requests onto a handler method for "all-movies"
    // (GET_ALL_MOVIES) that takes no parameters and forwards to the
    // MoviesService.getMovies() method.

    /**
     * Search for movie titles in the database containing the given
     * query {@link String}.
     *
     * @param query The search query
     * @return A {@link List} of movie titles containing the query
     *         represented as {@link Movie} objects
     */
    @GetMapping("/{query}")
    public List<Movie> getMovies(@PathVariable String query) {
        return service.search(query);
    }
    // TODO -- Create an endpoint with an annotation that maps HTTP
    // GET requests onto a handler method for "search" (GET_SEARCH)
    // that uses a @PathVariable parameter and forwards to the
    // MoviesService.search(String query) method.

    /**
     * Search for movie titles in the database containing the given
     * {@link String} queries
     *
     *
     * @return A {@link List} of movie titles containing the queries
     *         represented as {@link Movie} objects
     */
    @GetMapping("/"+GET_SEARCHES)
    public List<Movie> getSearches(@RequestParam List<String> queries) {
        return service.search(queries);

    }

    // TODO -- Create an endpoint with an annotation that maps HTTP
    // GET requests onto a handler method for "searches"
    // (GET_SEARCHES) that uses a @RequestParam parameter and forwards
    // to the MoviesServices.search(List<String> queries) method.
}
