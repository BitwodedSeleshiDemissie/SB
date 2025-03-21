package edu.vandy.recommender.movies;

/**
 * This class centralizes all constants used by the movie recommender
 * project.
 */
public class Constants {
    public static final String MOVIES_BASE_URL = "http://localhost:9002";

    /**
     * All supported HTTP request endpoints.
     */
    public static class EndPoint {
        public static final String GET_ALL_MOVIES = "all-movies";
        public static final String GET_SEARCH = "search";
        public static final String GET_SEARCHES = "searches";
        public static final String SEARCH_QUERY = "/{query}";
    }

    /**
     * Supported HTTP request parameters identifiers.
     */
    public static class Params {
        public static final String QUERIES = "queries";
    }
}

