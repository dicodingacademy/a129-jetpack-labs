package com.dicoding.academies.utils;

import androidx.test.espresso.IdlingResource;
import androidx.test.espresso.idling.CountingIdlingResource;

public class EspressoIdlingResource {
    private static final String RESOURCE = "GLOBAL";
    private static CountingIdlingResource espressoTestIdlingResource = new CountingIdlingResource(RESOURCE);

    public static void increment() {
        espressoTestIdlingResource.increment();
    }

    public static void decrement() {
        espressoTestIdlingResource.decrement();
    }

    public static IdlingResource getEspressoIdlingResource() {
        return espressoTestIdlingResource;
    }
}
