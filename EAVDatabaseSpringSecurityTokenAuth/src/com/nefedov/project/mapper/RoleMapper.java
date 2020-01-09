package com.nefedov.project.mapper;

public class RoleMapper {
    public static String GET_ROLE = "select row_number() OVER() as id, value as role from roles";

}

