package com.nefedov.project.mapper;

public class UserMapper {
    public static String FIND_INFO_ABOUT_USER = "select username.username, surname.surname, firstname.firstname\n" +
            "from\n" +
            "(select value as username, id_object from\n" +
            "(select id_value, id_object, id_attribute, value from value\n" +
            "where id_object in (select id_object from value\n" +
            "where value = ?)\n" +
            "and id_attribute in (1,2,3)) attrs\n" +
            "where attrs.id_attribute = 3) username,\n" +
            "(select value as surname, id_object from\n" +
            "(select id_value, id_object, id_attribute, value from value\n" +
            "where id_object in (select id_object from value\n" +
            "where value = ?)\n" +
            "and id_attribute in (1,2,3)) attrs\n" +
            "where attrs.id_attribute = 1) surname,\n" +
            "(select value as firstname, id_object from\n" +
            "(select id_value, id_object, id_attribute, value from value\n" +
            "where id_object in (select id_object from value\n" +
            "where value = ?)\n" +
            "and id_attribute in (1,2,3)) attrs\n" +
            "where attrs.id_attribute = 2) firstname\n" +
            "where surname.id_object = firstname.id_object\n" +
            "and username.id_object = surname.id_object";

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

    public static String FIND_ALL_USER = "select login.login, passwords.password, roles.role from\n" +
            "(select test.value as login, id_object from\n" +
            "(select id_value, id_object, id_attribute, value from value\n" +
            "where id_object in (select id_object from value\n" +
            ")\n" +
            "and id_attribute in (3,4,10)) test\n" +
            "where test.id_attribute = 3) login,\n" +
            "(select test.value as password, id_object from\n" +
            "(select id_value, id_object, id_attribute, value from value\n" +
            "where id_object in (select id_object from value\n" +
            ")\n" +
            "and id_attribute in (3,4,10)) test\n" +
            "where test.id_attribute = 4) passwords,\n" +
            "(select test.value as role, id_object from\n" +
            "(select id_value, id_object, id_attribute, value from value\n" +
            "where id_object in (select id_object from value\n" +
            ")\n" +
            "and id_attribute in (3,4,10)) test\n" +
            "where test.id_attribute = 10) roles\n" +
            "where login.id_object = passwords.id_object\n" +
            "and login.id_object = roles.id_object";


    public static String GET_FRIENDS_CONTACT = "select username.username, surname.surname, firstname.firstname\n" +
            "from\n" +
            "(select value as surname, id_object from\n" +
            "(select  id_object, id_attribute, value from value where id_object IN \n" +
            "(select CAST(value AS integer) from value\n" +
            "where id_attribute = 9    \n" +
            "and id_object in (select id_object from value\n" +
            "where value = ?))      --друзья 6-го пользователя\n" +
            "and (id_attribute = 1 or id_attribute = 2 or id_attribute = 3)) main\n" +
            "where id_attribute = 1 ) surname,\n" +
            "(select value as firstname, id_object from\n" +
            "(select  id_object, id_attribute, value from value where id_object IN \n" +
            "(select CAST(value AS integer) from value\n" +
            "where id_attribute = 9    \n" +
            "and id_object in (select id_object from value\n" +
            "where value = ?))      --друзья 6-го пользователя\n" +
            "and (id_attribute = 1 or id_attribute = 2 or id_attribute = 3)) main\n" +
            "where id_attribute = 2 ) firstname,\n" +
            "(select value as username, id_object from\n" +
            "(select  id_object, id_attribute, value from value where id_object IN \n" +
            "(select CAST(value AS integer) from value\n" +
            "where id_attribute = 9    \n" +
            "and id_object in (select id_object from value\n" +
            "where value = ?))      --друзья 6-го пользователя\n" +
            "and (id_attribute = 1 or id_attribute = 2 or id_attribute = 3)) main\n" +
            "where id_attribute = 3 ) username\n" +
            "where surname.id_object = firstname.id_object\n" +
            "and surname.id_object = username.id_object";

    public static String ADD_CONTACT = "insert into value (id_object, id_attribute, value)\n" +
            "values ((select id_object from value\n" +
            "where value = ?),9, (select cast(id_object as text) -- username пользователя, которому добавляется контакт\n" +
            "from value\n" +
            "where value = ?)) -- логин добавляемого пользователя";



    public static String GET_CHATS = "select username.username, surname.surname, firstname.firstname\n" +
            "from\n" +
            "(select value as surname, id_object from\n" +
            "(select id_object, id_attribute, value from value where id_object in (\n" +
            "select cast(value as integer) from value where id_object in (\n" +
            "select id_object from mesowner\n" +
            "where cast(value as integer) in (select id_object from value\n" +
            "where value = ?))  -- чаты 6-го пользователя\n" +
            "and id_attribute = 8 )\n" +
            "and (id_attribute = 1 or id_attribute = 2 or id_attribute = 3)) main\n" +
            "where id_attribute = 1) surname,\n" +
            "(select value as firstname, id_object from\n" +
            "(select id_object, id_attribute, value from value where id_object in (\n" +
            "select cast(value as integer) from value where id_object in (\n" +
            "select id_object from mesowner\n" +
            "where cast(value as integer) in (select id_object from value\n" +
            "where value = ?))  -- чаты 6-го пользователя\n" +
            "and id_attribute = 8 )\n" +
            "and (id_attribute = 1 or id_attribute = 2 or id_attribute = 3)) main\n" +
            "where id_attribute = 2) firstname,\n" +
            "(select value as username, id_object from\n" +
            "(select id_object, id_attribute, value from value where id_object in (\n" +
            "select cast(value as integer) from value where id_object in (\n" +
            "select id_object from mesowner\n" +
            "where cast(value as integer) in (select id_object from value\n" +
            "where value = ?))  -- чаты 6-го пользователя\n" +
            "and id_attribute = 8 )\n" +
            "and (id_attribute = 1 or id_attribute = 2 or id_attribute = 3)) main\n" +
            "where id_attribute = 3) username\n" +
            "where surname.id_object = firstname.id_object\n" +
            "and firstname.id_object = username.id_object and username.username != ?";

    public static String CHANGE_SURNAME = "UPDATE value SET value = ? WHERE id_object in (select id_object from value\n" +
            "where value = ?) and id_attribute = 1;";

    public static String CHANGE_FIRSTNAME = "UPDATE value SET value = ? WHERE id_object in (select id_object from value\n" +
            "where value = ?) and id_attribute = 2;";

    public static String ADD_SURNAME = "insert into value (id_object, id_attribute, value)\n" +
            "values ((select id_object from value\n" +
            "where value = ?),1, ?)";

    public static String ADD_FIRSTNAME = "insert into value (id_object, id_attribute, value)\n" +
            "values ((select id_object from value\n" +
            "where value = ?),2, ?)";
}

