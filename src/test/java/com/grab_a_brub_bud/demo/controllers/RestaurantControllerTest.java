package com.grab_a_brub_bud.demo.controllers;

import com.grab_a_brub_bud.demo.model.RestaurantModel;
import com.grab_a_brub_bud.demo.services.RestaurantService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RestaurantController.class)
class RestaurantControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RestaurantService restaurantService;

    private RestaurantModel restaurant;

    @BeforeEach
    void setUp() {
        restaurant = new RestaurantModel(1, "Good Food", 50, "African", "Lo-fi", "1234567");
    }

    @Test
    void getRestaurants() throws Exception {
        when(restaurantService.filterSearchResults(false)).thenReturn(Arrays.asList(restaurant));
        mockMvc.perform(get("/restaurants"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("restaurantsList"))
                .andExpect(view().name("restaurants"));
    }

    @Test
    void addRestaurantPage() throws Exception {
        mockMvc.perform(get("/restaurants/add"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("newRestaurant"))
                .andExpect(view().name("add_restaurant"));
    }

    @Test
    void addRestaurant() throws Exception {
        mockMvc.perform(post("/restaurants/add_new")
                        .flashAttr("newRestaurant", restaurant))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/restaurants"));

        verify(restaurantService, times(1)).save(any(RestaurantModel.class));
    }

    @Test
    void editRestaurantPage() throws Exception {
        when(restaurantService.findById(1)).thenReturn(restaurant);
        mockMvc.perform(get("/restaurants/edit/1"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("existingRestaurant"))
                .andExpect(view().name("edit_restaurant"));
    }

    @Test
    void editRestaurant() throws Exception {
        mockMvc.perform(post("/restaurants/edit")
                        .flashAttr("existingRestaurant", restaurant))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/restaurants"));

        verify(restaurantService, times(1)).edit(any(RestaurantModel.class));
    }

    @Test
    void deleteRestaurantPage() throws Exception {
        when(restaurantService.findById(1)).thenReturn(restaurant);
        mockMvc.perform(get("/restaurants/delete/1"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("existingRestaurant"))
                .andExpect(view().name("delete_restaurant"));
    }

    @Test
    void deleteRestaurant() throws Exception {
        mockMvc.perform(post("/restaurants/delete/confirmed")
                        .flashAttr("existingRestaurant", restaurant))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/restaurants"));

        verify(restaurantService, times(1)).delete(any(RestaurantModel.class));
    }
}
