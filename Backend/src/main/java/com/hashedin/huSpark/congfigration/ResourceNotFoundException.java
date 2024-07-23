package com.hashedin.huSpark.congfigration;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String bookingNotFound) {
        super(bookingNotFound);
    }
}
