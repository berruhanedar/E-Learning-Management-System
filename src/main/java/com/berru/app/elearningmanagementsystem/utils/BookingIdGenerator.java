package com.berru.app.elearningmanagementsystem.utils;

import java.security.SecureRandom;

public class BookingIdGenerator {

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvxyz";
    private static final int ID_LENGTH = 14;
    private static final SecureRandom RANDOM = new SecureRandom();

    public static String generateTourBookingId() {
        return generateIdWithPrefix("T-");
    }

    public static String generateBookingPaymentId() {
        return generateIdWithPrefix("P-");
    }

    private static String generateIdWithPrefix(String prefix) {
        StringBuilder id = new StringBuilder(prefix);

        for (int i = 0; i < ID_LENGTH; i++) {
            int randomIndex = RANDOM.nextInt(CHARACTERS.length());
            id.append(CHARACTERS.charAt(randomIndex));
        }

        return id.toString().toUpperCase();
    }
}
