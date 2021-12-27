package com.manager.services;

import java.util.concurrent.CompletableFuture;

public interface PasswordService<T> {

    CompletableFuture<Void> write(final T value, final String to);

    CompletableFuture<T> read(final String from);

}
