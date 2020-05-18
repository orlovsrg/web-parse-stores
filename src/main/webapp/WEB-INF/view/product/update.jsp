<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form action="<spring:url value="/product/update"/>" method="post">
    <table>
        <tr>
            <td>
                <label for="type">TYPE</label>
            </td>
            <td>
                <input id="type" name="type" type="text" value="phone">
            </td>
        </tr>
        <tr>
            <td>
                <label for="title">Title</label>
            </td>
            <td>
                <input id="title" name="title" type="text">
            </td>
        </tr>
        <tr>
            <td>
                <label for="price">Price</label>
            </td>
            <td>
                <input id="price" name="price" type="text">
            </td>
        </tr>
        <tr>
            <td>
                <label for="url">Url</label>
            </td>
            <td>
                <input id="url" name="url" type="url">
            </td>
        </tr>
        <tr>
            <td>
                <label for="imgUrl">Img url</label>
            </td>
            <td>
                <input id="imgUrl" name="imgUrl" type="imgUrl">
            </td>
        </tr>
        <tr>
            <td>
                <label for="storeId">Store ID</label>
            </td>
            <td>
                <input id="storeId" name="storeId" type="storeId">
            </td>
        </tr>
        <tr>
            <security:csrfInput/>
            <td rowspan="1">
                <input type="submit" value="Обновить">
            </td>

        </tr>

    </table>
</form>


</body>
</html>
