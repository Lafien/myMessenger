package com.nefedov.mapper;

public class UserMapper {
    public static String FIND_INFO_ABOUT_USER = "select username.username, firstname.firstname, secondname.secondname\n" +
            "from\n" +
            "(select value as username, id_object from\n" +
            "(select id_value, id_object, id_attribute, value from value\n" +
            "where id_object in (select id_object from value\n" +
            "where value = ?)\n" +  //username
            "and id_attribute in (1,2,3)) attrs\n" +
            "where attrs.id_attribute = 3) username,\n" +
            "(select value as firstname, id_object from\n" +
            "(select id_value, id_object, id_attribute, value from value\n" +
            "where id_object in (select id_object from value\n" +
            "where value = ?)\n" +   //username
            "and id_attribute in (1,2,3)) attrs\n" +
            "where attrs.id_attribute = 1) firstname,\n" +
            "(select value as secondname, id_object from\n" +
            "(select id_value, id_object, id_attribute, value from value\n" +
            "where id_object in (select id_object from value\n" +
            "where value = ?)\n" +   //username
            "and id_attribute in (1,2,3)) attrs\n" +
            "where attrs.id_attribute = 2) secondname\n" +
            "where username.id_object = firstname.id_object\n" +
            "and username.id_object = secondname.id_object";

    public static String USER_FOR_SPRING_SECURITY = "select login.login, passwords.password, roles.role from\n" +
            "(select test.value as login, id_object from\n" +
            "(select id_value, id_object, id_attribute, value from value\n" +
            "where id_object in (select id_object from value\n" +
            "where value = ?)\n" +    //username
            "and id_attribute in (3,4,10)) test\n" +
            "where test.id_attribute = 3) login,\n" +
            "(select test.value as password, id_object from\n" +
            "(select id_value, id_object, id_attribute, value from value\n" +
            "where id_object in (select id_object from value\n" +
            "where value = ?)\n" +  //username
            "and id_attribute in (3,4,10)) test\n" +
            "where test.id_attribute = 4) passwords,\n" +
            "(select test.value as role, id_object from\n" +
            "(select id_value, id_object, id_attribute, value from value\n" +
            "where id_object in (select id_object from value\n" +
            "where value = ?)\n" +    //username
            "and id_attribute in (3,4,10)) test\n" +
            "where test.id_attribute = 10) roles\n" +
            "where login.id_object = passwords.id_object\n" +
            "and login.id_object = roles.id_object";


    public static String CREATE_USER = "insert into object (id_object_type, name_object)\n" +
            "values (1,'user');" + "insert into value (id_object, id_attribute, value)\n" +
            "values ((select id_object from object\n" +
            "    ORDER BY id_object DESC \n" +
            "LIMIT 1),3, ?);" +"insert into value (id_object, id_attribute, value)\n" +
            "values ((select id_object from object\n" +
            "    ORDER BY id_object DESC \n" +
            "LIMIT 1),4, ?);" + "insert into value (id_object, id_attribute, value)\n" +
            "values ((select id_object from object\n" +
            "    ORDER BY id_object DESC \n" +
            "LIMIT 1),10, ?);";


}

