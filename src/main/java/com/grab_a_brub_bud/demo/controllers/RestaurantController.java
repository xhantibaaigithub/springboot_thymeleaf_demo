package com.grab_a_brub_bud.demo.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.grab_a_brub_bud.demo.model.RestaurantModel;
import com.grab_a_brub_bud.demo.services.RestaurantService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
@RequestMapping("/restaurants")
public class RestaurantController {

    private static final String FILTER_SEARCH = "applyFilter";
    private final RestaurantService restaurantService;

    @GetMapping()
    public String getRestaurants(
            @RequestParam(required = false) Boolean applyfilter,
            final Model model,
            final HttpServletRequest httpServletRequestrequest) {

        // Obtain user session
        final HttpSession session = httpServletRequestrequest.getSession();

        // Store applyfilter session if applyfilter if present
        if (applyfilter == null) {
            applyfilter = false;
        }
        session.setAttribute(FILTER_SEARCH, applyfilter);

        // Retrieve applyfilter from active session
        final Boolean applyFilterSession = (Boolean) session.getAttribute(FILTER_SEARCH);

        // Store username obtained from session as property to be used in UI
        model.addAttribute(FILTER_SEARCH, applyFilterSession);

        List<RestaurantModel> restaurantsList = restaurantService.filterSearchResults(applyFilterSession);

        model.addAttribute("restaurantsList", restaurantsList);

        return "restaurants";
    }

    // Displays html template with empty Model object
    @GetMapping("/add")
    public String addRestaurantPage(Model model) {

        // Add empty model as paramter to be used in UI to store RestaurantModel field
        // inputs
        model.addAttribute("newRestaurant", new RestaurantModel());

        return "add_restaurant";
    }

    // Method to add new restaurant
    @PostMapping("/add_new")
    public String addRestaurant(@ModelAttribute RestaurantModel restaurant) {

        restaurantService.save(restaurant);

        return "redirect:/restaurants";
    }

    // Displays html template with an restaurant object
    @GetMapping("/edit/{id}")
    public String editRestaurantPage(Model model, @PathVariable Integer id) {

        RestaurantModel existingRestaurant = restaurantService.findById(id);

        // Assign selected restaurant object to UI
        model.addAttribute("existingRestaurant", existingRestaurant);

        return "edit_restaurant";
    }

    @PostMapping("/edit")
    public String editRestaurant(@ModelAttribute RestaurantModel restaurant) {
        restaurantService.edit(restaurant);

        return "redirect:/restaurants";
    }

    // Method to edit restaurant
    @GetMapping("/delete/{id}")
    public String deleteRestaurantPage(Model model, @PathVariable Integer id) {

        RestaurantModel existingRestaurant = restaurantService.findById(id);

        model.addAttribute("existingRestaurant", existingRestaurant);

        return "delete_restaurant";
    }

    @PostMapping("/delete/confirmed")
    public String deleteRestaurant(@ModelAttribute RestaurantModel restaurant) {

        restaurantService.delete(restaurant);

        return "redirect:/restaurants";
    }
}
