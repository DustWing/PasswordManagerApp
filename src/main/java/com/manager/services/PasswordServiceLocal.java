package com.manager.services;

import com.manager.exceptions.PasswordServiceException;
import com.manager.models.PasswordModel;
import com.manager.models.PasswordModelFactory;
import com.manager.utils.AesGcmEncryption;
import com.manager.utils.FileUtils;
import com.manager.utils.JsonUtils;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class PasswordServiceLocal implements PasswordService<List<PasswordModel>> {

    final char[] key;
    final byte[] salt;

    public PasswordServiceLocal(char[] key, byte[] salt) {
        this.key = key;
        this.salt = salt;
    }


    @Override
    public CompletableFuture<Void> write(final List<PasswordModel> list, final String to) {

        return CompletableFuture.supplyAsync(() -> {
            try {

                //generate random IV
                final byte[] IV = AesGcmEncryption.generateIv();

                // Add IV and encrypt json value
                final String encrypted = Base64.getEncoder().encodeToString(IV) +
                        AesGcmEncryption.encryptToBase64(
                                JsonUtils.toJson(list).getBytes(StandardCharsets.UTF_8),
                                AesGcmEncryption.generateKeyFromPassword(key, salt),
                                IV
                        );


                //write to file
                FileUtils.write(to, encrypted);
                return null;
            } catch (Exception e) {
                throw new PasswordServiceException("Could not write to file", e);
            }
        });
    }

    @Override
    public CompletableFuture<List<PasswordModel>> read(final String from) {

        return CompletableFuture.supplyAsync(() -> {
            try {

                //fetch from file
                final String content = FileUtils.read(from);

                //find IV
                final byte[] IV = Base64.getDecoder().decode(content.substring(0, 16).getBytes(StandardCharsets.UTF_8));

                //decrypt in json format
                final String json = AesGcmEncryption.decryptFromBase64(content.substring(16),
                        AesGcmEncryption.generateKeyFromPassword(key, salt),
                        IV);

                //create models
                return PasswordModelFactory.createFromJson(FileUtils.read(json));
            } catch (Exception e) {
                throw new PasswordServiceException("Could not read from file", e);
            }
        });
    }


}
