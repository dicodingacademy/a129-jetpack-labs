package com.dicoding.academies.utils;

import androidx.annotation.NonNull;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

class DiskIOThreadExecutor implements Executor {

    private final Executor mDiskIO;

    DiskIOThreadExecutor() {
        mDiskIO = Executors.newSingleThreadExecutor();
    }

    @Override
    public void execute(@NonNull Runnable command) {
        mDiskIO.execute(command);
    }
}