package com.manager.cache;


import com.manager.models.PasswordModel;
import com.manager.models.PasswordModelFactory;

import java.util.ArrayList;
import java.util.List;

import static com.manager.constants.Themes.DARK_THEME;

public class SessionBean {

    private SessionBean() {
    }


    private static class Cache {
        private static final SessionBean INSTANCE = new SessionBean();
    }

    public static SessionBean getInstance() {
        return Cache.INSTANCE;
    }


    /**
     * user name for login
     */
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * master password for decryption
     */
    private char[] password;

    public char[] getPassword() {
        //TODO remove salt to hide real value from memory
        return password;
    }

    public void setPassword(char[] password) {
        //TODO add salt to hide real value from memory
        this.password = password;
    }


    /**
     * loaded passwords
     */
    private List<PasswordModel> passwordModelList = new ArrayList<>();

    public List<PasswordModel> getPasswordModelList() {
        return PasswordModelFactory.createCopyList(passwordModelList);
    }

    public void setPasswordModelList(List<PasswordModel> passwordModelList) {
        this.passwordModelList = passwordModelList;
    }

    public void addPasswordModel(PasswordModel value) {
        //TODO need to encrypt username and password
        passwordModelList.add(value);
    }

    public void updatePasswordModel(PasswordModel value) {
        //TODO need to encrypt username and password
//        passwordModelList.stream().
        passwordModelList.add(value);
    }


    /**
     * Selected theme for the app - default DARK_THEME
     */
    private String selectedTheme = DARK_THEME;

    public String getSelectedTheme() {
        return selectedTheme;
    }

    public void setSelectedTheme(String selectedTheme) {
        this.selectedTheme = selectedTheme;
    }


    public enum Environment {
        LOCAL,
        GRPC
    }

    /**
     * The environment to decide if the app will fetch/save on local or on server
     */
    private Environment environment = Environment.LOCAL;

    public Environment getEnvironment() {
        return environment;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
