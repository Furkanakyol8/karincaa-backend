package com.furkan.karincaa.security.helper;

import com.furkan.karincaa.exception.CustomAuthenticationException;
import com.furkan.karincaa.model.entity.Customer;
import com.furkan.karincaa.model.entity.User;
import lombok.experimental.UtilityClass;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@UtilityClass
public class UserHelper {
    public User getLoggedUser(){
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof UsernamePasswordAuthenticationToken) {
            return (User) authentication.getDetails();
        } else {
            return null;
        }
    }

    public Customer getLoggedCustomer(){
        User loggedUser = getLoggedUser();
        if (loggedUser == null) {
            return null;
        }

        if(Customer.class.isAssignableFrom(loggedUser.getClass())){
            return (Customer) loggedUser;
        }

        throw new CustomAuthenticationException("User not customer!");
    }
}
