package com.grab_a_brub_bud.demo.services;

import com.grab_a_brub_bud.demo.model.RestaurantModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantServiceTest {

    private RestaurantService restaurantService;

    @BeforeEach
    void setUp() {
        restaurantService = new RestaurantService();
    }

    @Test
    void filterSearchResults() {
        // Test without filter
        List<RestaurantModel> allRestaurants = restaurantService.filterSearchResults(false);
        assertEquals(4, allRestaurants.size());

        // Test with filter
        List<RestaurantModel> filteredRestaurants = restaurantService.filterSearchResults(true);
        assertEquals(2, filteredRestaurants.size());
    }

    @Test
    void save() {
        RestaurantModel newRestaurant = new RestaurantModel(5, "Test Restaurant", 100, "Test Cuisine", "Test Music", "12345");
        restaurantService.save(newRestaurant);

        RestaurantModel savedRestaurant = restaurantService.findById(5);
        assertNotNull(savedRestaurant);
        assertEquals("Test Restaurant", savedRestaurant.getTitle());
    }

    @Test
    void findById() {
        RestaurantModel restaurant = restaurantService.findById(1);
        assertNotNull(restaurant);
        assertEquals("Good Food", restaurant.getTitle());

        assertThrows(IllegalArgumentException.class, () -> restaurantService.findById(999));
    }

    @Test
    void edit() {
        RestaurantModel restaurantToEdit = new RestaurantModel(1, "Edited Food", 60, "Edited Cuisine", "Edited Music", "7654321");
        restaurantService.edit(restaurantToEdit);

        RestaurantModel editedRestaurant = restaurantService.findById(1);
        assertNotNull(editedRestaurant);
        assertEquals("Edited Food", editedRestaurant.getTitle());

        assertThrows(IllegalArgumentException.class, () -> {
            RestaurantModel nonExistentRestaurant = new RestaurantModel(999, "Non-existent", 0, "None", "None", "0000000");
            restaurantService.edit(nonExistentRestaurant);
        });
    }

    @Test
    void delete() {
        RestaurantModel restaurantToDelete = new RestaurantModel(2, "Nice Grub", 25, "French", "RnB", "456789");
        restaurantService.delete(restaurantToDelete);

        assertThrows(IllegalArgumentException.class, () -> restaurantService.findById(2));

        assertThrows(IllegalArgumentException.class, () -> {
            RestaurantModel nonExistentRestaurant = new RestaurantModel(999, "Non-existent", 0, "None", "None", "0000000");
            restaurantService.delete(nonExistentRestaurant);
        });
    }
}
