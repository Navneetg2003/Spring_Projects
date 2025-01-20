package com.myapp.p_23_maplocator.Controller;

import com.myapp.p_23_maplocator.Service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Contrroller {
    @Autowired
    private CountryService countryService;

    @GetMapping("/map")
    public String map() {
        return "Map";
    }
}
