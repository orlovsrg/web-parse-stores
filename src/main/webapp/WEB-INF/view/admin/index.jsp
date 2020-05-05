<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        a {
            display: block;
            align-items: center;
            text-decoration: none;
            width: 60px;
            height: 25px;
            border-radius: 5px;
            background: #292929;
            color: silver;
            /*text-align: center;*/
        }
    </style>
</head>
<body>
<a href="<spring:url value="/admin/parsing" />">Parsing</a>
</body>
</html>
