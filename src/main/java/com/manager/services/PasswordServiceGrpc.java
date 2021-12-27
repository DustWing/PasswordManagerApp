package com.manager.services;

import com.manager.models.PasswordModel;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class PasswordServiceGrpc implements PasswordService<List<PasswordModel>>{
    @Override
    public CompletableFuture<Void> write(final List<PasswordModel> value, final String to) {
        return null;
    }

    @Override
    public CompletableFuture<List<PasswordModel>> read(final String from) {
        return null;
    }
}
