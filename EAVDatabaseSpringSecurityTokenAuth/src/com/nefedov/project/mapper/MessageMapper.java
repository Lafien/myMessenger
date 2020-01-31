package com.nefedov.project.mapper;

public class MessageMapper {

    public static String CREATE_MESSAGE = "insert into object (id_object_type, name_object) values (2,'message'); \n" +
            "insert into value (id_object, id_attribute, value) values\n" +
            "((select id_object from object ORDER BY id_object DESC LIMIT 1),5, localtimestamp);\n" +
            "insert into value (id_object, id_attribute, value) values\n" +
            "((select id_object from object ORDER BY id_object DESC LIMIT 1),6, ?);\n" +
            "insert into value (id_object, id_attribute, value) values ((select id_object from object ORDER BY id_object DESC \n" +
            "LIMIT 1),7, (select cast(id_object as text) \n" +
            "from value where value = ?));\n" +
            "insert into value (id_object, id_attribute, value)\n" +
            "values ((select id_object from object ORDER BY id_object DESC \n" +
            "LIMIT 1),8, (select cast(id_object as text)\n" +
            "from value where value = ?));";


public static String GET_MESSAGE_IN_CHAT = "select date_trunc('second', " +
        "cast(datecreate.datecreate as timestamp)) as datecreate," +
        " textmessage.textmessage, msgownerusername.msgowner, msgtousername.msgto\n" +
        "from\n" +
        "(select id_object, value as datecreate \n" +
        "from\n" +
        "(select  id_object, id_attribute, value\n" +
        "from value\n" +
        "where id_object in (\n" +
        "select id_object from value where id_object in\n" +
        "(select id_object from mesOwner\n" +
        "where value in (select cast(id_object as text) from value\n" +
        "where value = ?))      --from \n" +
        "and id_attribute = 8 \n" +
        "and value in (select cast(id_object as text) from value\n" +
        "where value = ?)  -- to\n" +
        "union\n" +
        "select id_object from value where id_object in\n" +
        "(select id_object from mesOwner\n" +
        "where value in (select cast(id_object as text) from value\n" +
        "where value = ?))   --from\n" +
        "and id_attribute = 8 \n" +
        "and value in (select cast(id_object as text) from value\n" +
        "where value = ?)) --to \n" +
        "and (id_attribute = 6 or id_attribute = 7 or id_attribute = 5)) main\n" +
        "where id_attribute = 5 ) datecreate,\n" +
        "--\n" +
        "(select id_object, value as textmessage \n" +
        "from\n" +
        "(select  id_object, id_attribute, value\n" +
        "from value\n" +
        "where id_object in (\n" +
        "select id_object from value where id_object in\n" +
        "(select id_object from mesOwner\n" +
        "where value in (select cast(id_object as text) from value\n" +
        "where value = ?))      --from \n" +
        "and id_attribute = 8 \n" +
        "and value in (select cast(id_object as text) from value\n" +
        "where value = ?)  -- to\n" +
        "union\n" +
        "select id_object from value where id_object in\n" +
        "(select id_object from mesOwner\n" +
        "where value in (select cast(id_object as text) from value\n" +
        "where value = ?))   --from\n" +
        "and id_attribute = 8 \n" +
        "and value in (select cast(id_object as text) from value\n" +
        "where value = ?)) --to \n" +
        "and (id_attribute = 6 or id_attribute = 7 or id_attribute = 5)) main\n" +
        "where id_attribute = 6 ) textmessage,\n" +
        "--\n" +
        "(select msgowner.id_object, value.value as msgowner from value,\n" +
        "(select id_object, value as msgowner \n" +
        "from\n" +
        "(select  id_object, id_attribute, value\n" +
        "from value\n" +
        "where id_object in (\n" +
        "select id_object from value where id_object in\n" +
        "(select id_object from mesOwner\n" +
        "where value in (select cast(id_object as text) from value\n" +
        "where value = ?))      --from \n" +
        "and id_attribute = 8 \n" +
        "and value in (select cast(id_object as text) from value\n" +
        "where value = ?)  -- to\n" +
        "union\n" +
        "select id_object from value where id_object in\n" +
        "(select id_object from mesOwner\n" +
        "where value in (select cast(id_object as text) from value\n" +
        "where value = ?))   --from\n" +
        "and id_attribute = 8 \n" +
        "and value in (select cast(id_object as text) from value\n" +
        "where value = ?)) --to \n" +
        "and (id_attribute = 6 or id_attribute = 7 or id_attribute = 5)) main\n" +
        "where id_attribute = 7 ) msgowner\n" +
        "where value.id_object = cast(msgowner.msgowner as integer)\n" +
        "and value.id_attribute =3) msgownerusername,\n" +
        "--\n" +
        "(select msgowner.id_object, value.value as msgto from value,\n" +
        "(select id_object, value as msgowner \n" +
        "from\n" +
        "(select  id_object, id_attribute, value\n" +
        "from value\n" +
        "where id_object in (\n" +
        "select id_object from value where id_object in\n" +
        "(select id_object from mesOwner\n" +
        "where value in (select cast(id_object as text) from value\n" +
        "where value = ?))      --from\n" +
        "and id_attribute = 8 \n" +
        "and value in (select cast(id_object as text) from value\n" +
        "where value = ?)  -- to\n" +
        "union\n" +
        "select id_object from value where id_object in\n" +
        "(select id_object from mesOwner\n" +
        "where value in (select cast(id_object as text) from value\n" +
        "where value = ?))   --from\n" +
        "and id_attribute = 8 \n" +
        "and value in (select cast(id_object as text) from value\n" +
        "where value = ?)) --to \n" +
        "and (id_attribute = 6 or id_attribute = 7 or id_attribute = 5 or id_attribute = 8)) main\n" +
        "where id_attribute = 8 ) msgowner\n" +
        "where value.id_object = cast(msgowner.msgowner as integer)\n" +
        "and value.id_attribute =3) msgtousername\n" +
        "--\n" +
        "where datecreate.id_object = textmessage.id_object\n" +
        "and datecreate.id_object = msgownerusername.id_object\n" +
        "and datecreate.id_object = msgtousername.id_object\n" +
        "order by datecreate.datecreate";

}
