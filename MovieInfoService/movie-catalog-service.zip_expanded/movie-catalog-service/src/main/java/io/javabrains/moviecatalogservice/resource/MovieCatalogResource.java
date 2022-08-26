package io.javabrains.moviecatalogservice.resource;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.reactive.function.client.WebClientAutoConfiguration;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import io.javabrains.moviecatalogservice.models.CatalogItem;
import io.javabrains.moviecatalogservice.models.Movie;
import io.javabrains.moviecatalogservice.models.Rating;
import io.javabrains.moviecatalogservice.models.UserRating;
import io.javabrains.moviecatalogservice.services.MovieInfo;
import io.javabrains.moviecatalogservice.services.UserRatingInfo;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private DiscoveryClient discoveryClient;
	
	/*@Autowired
	private WebClient.Builder webClientBuilder;*/
	
	@Autowired
	MovieInfo movieInfo;
	
	@Autowired
	UserRatingInfo userRatingInfo;
	
	
	
	
	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId){
		
		// get all rated movie IDs 
		UserRating userRating = userRatingInfo.getUserRating(userId);
	    
		// For each movie ID call movie info service and get details
		return userRating.getUserRating().stream()
				.map(rating->movieInfo.getCatalogItem(rating))
		.collect(Collectors.toList());
		
		// Put them all together 
		
		/* Done in 1st step 
		 return Collections.singletonList(
				new CatalogItem("Transformers","Test",4)
				);*/	
	}

	
	
}


/*
 * UserRating ratings = restTemplate
 *                      .getForObject("http://localhost:8083/ratingsdata/users/" + userId, UserRating.class);
 * replace localhost:8083 with service name because eureka server only knows the name of the services
 */


/*	Movie movie = webClientBuilder.build()
.get()
.uri("http://localhost:8082/movies/" + rating.getMovieId())
.retrieve()
.bodyToMono(Movie.class) //you will get something return but not now ..it may be after sometime
.block(); */