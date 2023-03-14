package com.keccoredump.demo.dto;

public class AwsSecretsDto {

    private String username;

    private String password;

    private String host;

    private String engine;

    private String post;

    private String dbInstanceIdentifier;

    public AwsSecretsDto(){

    }

    public AwsSecretsDto(String username, String password, String host, String engine, String post, String dbInstanceIdentifier) {
        this.username = username;
        this.password = password;
        this.host = host;
        this.engine = engine;
        this.post = post;
        this.dbInstanceIdentifier = dbInstanceIdentifier;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getDbInstanceIdentifier() {
        return dbInstanceIdentifier;
    }

    public void setDbInstanceIdentifier(String dbInstanceIdentifier) {
        this.dbInstanceIdentifier = dbInstanceIdentifier;
    }
}
