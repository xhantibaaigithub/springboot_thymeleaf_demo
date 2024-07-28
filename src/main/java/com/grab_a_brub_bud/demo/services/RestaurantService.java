package com.grab_a_brub_bud.demo.services;

import com.grab_a_brub_bud.demo.model.RestaurantModel;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RestaurantService {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(RestaurantService.class);

    private static List<RestaurantModel> restaurants;

    static {
        restaurants = new ArrayList<>();
        restaurants.add(new RestaurantModel(1, "Good Food", 50, "African", "Lo-fi", "1234567"));
        restaurants.add(new RestaurantModel(2, "Nice Grub", 25, "French", "RnB", "456789"));
        restaurants.add(new RestaurantModel(3, "Fast and hot", 79, "Fries", "Mid-tempo", "987684"));
        restaurants.add(new RestaurantModel(4, "Mogodu Monday", 105, "Diverse", "House", "9879871"));
    }

    public List<RestaurantModel> filterSearchResults(Boolean applyFilter) {
        if (applyFilter != null && applyFilter) {
            return restaurants.stream().filter(restaurant -> restaurant.getCapacity() > 50).toList();
        } else {
            return restaurants;
        }
    }


    public void save(RestaurantModel restaurant) {
        restaurants.add(restaurant);
    }

    public RestaurantModel findById(Integer id) {
        return restaurants.stream().filter(
                        restaurant -> restaurant
                                .getId()
                                .equals(id))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public void edit(RestaurantModel restaurant) {

        for (int i = 0; i < restaurants.size(); i++) {
            if (restaurants.get(i).getId().equals(restaurant.getId())) {
                restaurants.set(i, restaurant);
                return;
            }
        }
        throw new IllegalArgumentException("Restaurant with id " + restaurant.getId() + " not found");
    }

    public void delete(RestaurantModel restaurantToBeDeleted) {

        RestaurantModel restaurant = restaurants.stream()
                .filter(existingRestaurant -> existingRestaurant.getId().equals(restaurantToBeDeleted.getId()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "Restaurant with ID " + restaurantToBeDeleted.getId() + " not found"));
        restaurants.remove(restaurant);
    }

}
