package com.nilsw13.springboot.replicate.responsetype.collection;


import java.util.List;

/**
 * Represents a paginated list of collections returned by the Replicate API.
 *
 * This class wraps the collection results with pagination metadata, allowing
 * for efficient navigation through large sets of collections. When the API
 * returns more collections than can be efficiently processed in a single
 * request, it divides the results into pages and provides URLs to navigate
 * to the next or previous page.
 *
 * The pagination links (next and previous) are fully-qualified URLs that can
 * be directly used to retrieve the corresponding page of results. When there
 * are no more pages in a particular direction, the corresponding link will be null.
 *
 * @author Nilsw13
 * @since 1.0.0
 */
public class CollectionModelList {

    private String next;
    private String previous;
    List<CollectionModel> results;

    public CollectionModelList() {

        /**
         * Default constructor for CollectionModelList class.
         *
         * This empty constructor exists for the following reasons:
         * 1. Required by JSON/Jackson deserialization process when mapping API responses
         * 2. Enables library users to instantiate response objects when needed
         * 3. Supports serialization/deserialization in various client implementations
         *
         * Although empty, this constructor is essential for the proper functioning
         * of the API client library and should not be removed.
         */
    }

    public String getNext() {
        return next;
    }

    public String getPrevious() {
        return previous;
    }

    public List<CollectionModel> getResults() {
        return results;
    }

    public void setResults(List<CollectionModel> results) {
        this.results = results;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public void setNext(String next) {
        this.next = next;
    }
}
