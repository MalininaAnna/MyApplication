package com.example.myapplication.database;

public class UserDbSchema {
    public static class UserTable{
        public static final String NAME = "users";
        public static final class Cols{
            public static final String UUID = "uuid";
            public static final String FIRSTNAME = "username";
            public static final String LASTNAME = "userlastname";
            public static final String PHONE = "userphone";
        }
    }
}
