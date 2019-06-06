package com.transport.smart.controller;

import com.transport.smart.message.GenericMessage;
import com.transport.smart.message.SubscriptionRequest;
import com.transport.smart.model.Subscription;
import com.transport.smart.model.User;
import com.transport.smart.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.UUID;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/subscription/")
public class SubscriptionController {
    @Autowired
    UserRepository userRepository;

    @PostMapping("/add")
    public ResponseEntity<?> addSubscription(@RequestBody SubscriptionRequest subscriptionRequest){
        if(!userRepository.existsById(subscriptionRequest.getUserId())){
            return ResponseEntity.badRequest().body(new GenericMessage("user not found"));
        }
        User user = userRepository.findById(subscriptionRequest.getUserId()).get();
        if(user.getSubscription() != null){
            if(user.getSubscription().getExpiresOn().isBefore(LocalDateTime.now())) {
                Subscription sub = new Subscription();
                sub.setCreatedOn(LocalDateTime.now());
                sub.setExpiresOn(sub.getExpiresOn().plusDays(subscriptionRequest.getDays()));
                user.setSubscription(sub);
                userRepository.save(user);
                return ResponseEntity.ok().body(new GenericMessage("subscription updated"));
            }
            return ResponseEntity.ok().body(new GenericMessage("still valid"));
        }
        Subscription sub = new Subscription();
        sub.setCreatedOn(LocalDateTime.now());
        sub.setExpiresOn(LocalDateTime.now().plusDays(subscriptionRequest.getDays()));
        user.setSubscription(sub);
        userRepository.save(user);
        return ResponseEntity.ok().body(new GenericMessage("subscription created"));
    }
    @GetMapping("/user/{uid}")
    public ResponseEntity<?> validateSubById(@PathVariable("uid") UUID uid){
        if(!userRepository.existsById(uid)){
            return ResponseEntity.badRequest().body(new GenericMessage("user not found"));
        }
        User user = userRepository.findById(uid).get();
        if(user.getSubscription() == null)
            return ResponseEntity.badRequest().body(new GenericMessage("user has no active subscription"));
        if(user.getSubscription().getExpiresOn().isBefore(LocalDateTime.now())) {
            user.setSubscription(null);
            return ResponseEntity.badRequest().body(new GenericMessage("expired"));
        }
        return ResponseEntity.ok().body(new GenericMessage("valid"));
    }

    @GetMapping("/{uid}")
    public ResponseEntity<?> getSubscription(@PathVariable("uid") UUID uid){
        if(!userRepository.existsById(uid)){
            return ResponseEntity.badRequest().body(new GenericMessage("user not found"));
        }
        User user = userRepository.findById(uid).get();
        if(user.getSubscription() == null){
            return ResponseEntity.badRequest().body(new GenericMessage("subscription not found"));
        }
        return ResponseEntity.ok().body(user.getSubscription());
    }
}
