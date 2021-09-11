package com.day9exercise.demo.FlightsAvailable;

import com.day9exercise.demo.Country.Country;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;



@RestController
@RequestMapping(path="flightsavailable")
public class FlightsAvailableController {

    @GetMapping
    public List<FlightsAvailable> getFlightsAvailable() {
        return 
    }

//    @PostMapping
//
//
//    @PutMapping
//
//
//    @DeleteMapping



}
