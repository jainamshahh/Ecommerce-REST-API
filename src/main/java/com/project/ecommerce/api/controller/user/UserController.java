package com.project.ecommerce.api.controller.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.ecommerce.model.Address;
import com.project.ecommerce.model.Users;
import com.project.ecommerce.repository.AddressRepository;

@RestController
@RequestMapping("/user")
public class UserController {

    AddressRepository addressRepository;

    public UserController(@Autowired AddressRepository addressRepository){
        this.addressRepository=addressRepository;
    }

    @GetMapping("/{userId}/address")
    public ResponseEntity<List<Address>> getAddress(
        @AuthenticationPrincipal Users user, @PathVariable Long userId) {
        if (!userHasPermission(user, userId)) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return ResponseEntity.ok(addressRepository.findByUser_Id(userId));
    }

    @PutMapping("/{userId}/address")
    public ResponseEntity<Address> putAddress(
        @AuthenticationPrincipal Users user, @PathVariable Long userId,
        @RequestBody Address address) {
        if (!userHasPermission(user, userId)) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        address.setId(null);
        Users refUser = new Users();
        refUser.setId(userId);
        address.setUser(refUser);
        return ResponseEntity.ok(addressRepository.save(address));
    }

    @PatchMapping("/{userId}/address/{addressId}")
    public ResponseEntity<Address> patchAddress(
        @AuthenticationPrincipal Users user, @PathVariable Long userId,
        @PathVariable Long addressId, @RequestBody Address address) {
        if (!userHasPermission(user, userId)) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        if (address.getId() == addressId) {
        Optional<Address> opOriginalAddress = addressRepository.findById(addressId);
        if (opOriginalAddress.isPresent()) {
            Users originalUser = opOriginalAddress.get().getUser();
            if (originalUser.getId() == userId) {
            address.setUser(originalUser);
            return ResponseEntity.ok(addressRepository.save(address));
            }
        }
        }
        return ResponseEntity.badRequest().build();
    }

    private boolean userHasPermission(Users user, Long id) {
        return user.getId() == id;
    }


}
