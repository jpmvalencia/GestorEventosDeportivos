package com.sportsevent.sportseventmanager.common.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sportsevent.sportseventmanager.common.adapters.InstantAdapter;

import java.time.Instant;

public class GsonProvider {
    public static Gson createGson() {
        return new GsonBuilder().registerTypeAdapter(Instant.class, new InstantAdapter()).create();
    }
}
