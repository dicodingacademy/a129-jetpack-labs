package com.dicoding.picodiploma.myidleresource;

import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.idling.CountingIdlingResource;

public class EspressoIdlingResource {
    private static final String RESOURCE = "GLOBAL";
    private static CountingIdlingResource espressoTestIdlingResource = new CountingIdlingResource(RESOURCE);

    static void increment() {
        espressoTestIdlingResource.increment();
    }

    static void decrement() {
        espressoTestIdlingResource.decrement();
    }

    static IdlingResource getEspressoIdlingResourcey() {
        return espressoTestIdlingResource;
    }
}
