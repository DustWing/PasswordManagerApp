package com.manager.services;

import com.manager.models.PasswordModel;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

class PasswordServiceLocalTest {

    private static final Properties properties = new Properties();
    private static final String TEST_KEY = "password";
    private static final String TEST_SALT = "salt";


    @BeforeAll
    static void init() {
        try (InputStream is = PasswordServiceLocalTest.class.getResourceAsStream("/application.properties")) {
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    void write() {

        List<PasswordModel> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(createModelTest(i));

        }

        try {
            System.out.println("before write request");

            new PasswordServiceLocal(TEST_KEY.toCharArray(), TEST_SALT.getBytes(StandardCharsets.UTF_8)).write(
                    list,
                    properties.getProperty("default.local.file") + "/test"
            ).whenComplete((unused, throwable) -> {
                System.out.println("Completed");

            });

            System.out.println("after write request");


            //wait for future to end
            Thread.sleep(3000);
            assert true;
        } catch (Exception e) {
            e.printStackTrace();
            assert false;
        }

    }

    @Test
    void read() {
        PasswordService<List<PasswordModel>> passwordService = new PasswordServiceLocal(TEST_KEY.toCharArray(), TEST_SALT.getBytes(StandardCharsets.UTF_8));

        try {
            List<PasswordModel> list = passwordService.read(properties.getProperty("default.local.file") + "/test").get();
            System.out.println(list);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

    }


    private PasswordModel createModelTest(int value) {

        return new PasswordModel(
                "id" + value,
                "username" + value,
                "password" + value,
                "domain" + value,
                "description" + value,
                "category" + value);
    }
}