<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<div class="box">
    <table>
        <caption>Мои подписки</caption>
        <tbody>
        <c:forEach var="p" items="${product}">
            <tr>
                <td>id: ${p.id}</td>
                <td>title: ${p.title}</td>
                <td>price: ${p.price}</td>
                <td>store id: ${p.storeId}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>