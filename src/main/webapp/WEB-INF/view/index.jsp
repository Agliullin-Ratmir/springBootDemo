<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html lang="en"
      xmlns="http://www.w3.org/1999/xhtml"
      xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3"
      xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout">
<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <title>index</title>
</head>
<body>
<div>
    <div>
        <h1>Spring Boot JSP Example</h1>
        <h2>Choose your option: </h2>

        <strong><a href="/addNewUser">Add new user</a></strong><br>
        <strong><a href="/login">Login</a></strong><br>
        <strong><a href="/getUsers">Get users</a></strong><br>
    </div>
</div>
</body>
</html>