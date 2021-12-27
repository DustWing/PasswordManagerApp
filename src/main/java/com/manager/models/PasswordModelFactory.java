package com.manager.models;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.stream.Collectors;

public class PasswordModelFactory {

    public static PasswordModel createFromLine(String line) {

        return new PasswordModel("id", "username", "password", "domain", "description", "category");
    }

    public static PasswordModel createForTest(int no) {

        return new PasswordModel("id" + no, "username" + no, "password" + no, "domain" + no, "description" + no, "category" + no);
    }


    public static List<PasswordModel> createFromJson(String json) throws JsonProcessingException {

        final ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, new TypeReference<>() {
        });

    }

    public static PasswordModel createCopy(PasswordModel value) {
        return new PasswordModel(
                value.getId(),
                value.getUsername(),
                value.getPassword(),
                value.getDomain(),
                value.getDescription(),
                value.getCategory()
        );
    }

    public static List<PasswordModel> createCopyList(List<PasswordModel> list) {
        return list.stream().map(PasswordModelFactory::createCopy)
                .collect(Collectors.toList());
    }
}
