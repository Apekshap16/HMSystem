package io.javabrains.moviecatalogservice.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import io.javabrains.moviecatalogservice.models.Rating;
import io.javabrains.moviecatalogservice.models.UserRating;

@Service
public class UserRatingInfo {

	@Autowired
	private RestTemplate restTemplate;
	

	@HystrixCommand(fallbackMethod = "getFallbackUserRating"
			/*, commandProperties = {
					   @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="2000"),
					   @HystrixProperty(name="circuitBreaker.requestVolumeThreshold",value="5"),
					   @HystrixProperty(name="circuitBreaker.errorThreshouldPercentage",value="50"),
					   @HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds",value="5000")
					   
			}*/
			)
	public UserRating getUserRating(String userId) {
		return restTemplate.getForObject("http://ratings-data-service/ratingsdata/users/" + userId, UserRating.class);
	}
	
	public UserRating getFallbackUserRating(String userId) {
		  UserRating userRating= new UserRating();
		  userRating.setUserRating(Arrays.asList(
				     new Rating("0",0)
				  ));
		  return userRating;
		}
	
	
}
