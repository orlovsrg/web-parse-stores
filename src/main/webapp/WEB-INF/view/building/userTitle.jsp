<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<sec:authorize access="hasRole('ROLE_ADMIN')" var="isAmin" />
<c:if test="${isAmin}" >
<div class="top-nav">

    <div class="dropdown">
        <div class="admin">
            <p class="admin-p">Админ панель</p>
        </div>
    </div>

    <div class="user">
        <a href="<spring:url value="/admin/parsing"/>" class="lk-style">
            Парсинг
        </a>

        <a href="<spring:url value="/product/update"/>" class="lk-style">
            Обновить позицию
        </a>
    </div>

</div>
</c:if>


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