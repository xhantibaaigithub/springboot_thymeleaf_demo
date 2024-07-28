package com.grab_a_brub_bud.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantModel {

    private Integer id;
    private String title;
    private Integer capacity;
    private String cuisine;
    private String music;
    private String contacts;

}
