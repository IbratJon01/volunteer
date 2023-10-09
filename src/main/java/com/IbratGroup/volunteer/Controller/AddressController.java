package com.IbratGroup.volunteer.Controller;

import com.IbratGroup.volunteer.Entity.Address;
import com.IbratGroup.volunteer.Service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping
    public Address addAddress(@RequestBody Address address) {
        return addressService.saveAddress(address);
    }

    @GetMapping("/{id}")
    public Address getAddressById(@PathVariable Long id) {
        return addressService.getAddressById(id);
    }
}