package com.example.application.views.form.service;

import com.example.application.views.form.model.UserDetails;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

@Service
public class UserDetailsService implements Serializable {

    private String previousHandle;

    public void store(UserDetails userDetails) throws ServiceException {
        if (previousHandle == null || !previousHandle.equals(userDetails.getHandle())) {
            previousHandle = userDetails.getHandle();
            throw new ServiceException("This exception simulates an error in the backend, and is intentional. Please try to submit the form again.");
        }
    }

    public String validateHandle(String handle) {
        if (handle == null) {
            return "Handle can't be empty";
        }
        if (handle.length() < 4) {
            return "Handle can't be shorter than 4 characters";
        }
        List<String> reservedHandles = Arrays.asList("admin", "test", "null", "void");
        if (reservedHandles.contains(handle)) {
            return String.format("'%s' is not available as a handle", handle);
        }
        return null;
    }

    public static class ServiceException extends Exception {
        public ServiceException(String msg) {
            super(msg);
        }
    }
}
