package com.dicoding.picodiploma.myidleresource;

import androidx.test.espresso.IdlingResource;
import androidx.test.espresso.idling.CountingIdlingResource;

public class EspressoIdlingResource {
    private static final String RESOURCE = "GLOBAL";
    private static CountingIdlingResource espressoTestIdlingResource = new CountingIdlingResource(RESOURCE);

    static void increment() {
        espressoTestIdlingResource.increment();
    }

    static void decrement() {
        espressoTestIdlingResource.decrement();
    }

    static IdlingResource getEspressoIdlingResource() {
        return espressoTestIdlingResource;
    }
}
