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

<div class="box-table">
    <table class="table-user">
        <caption class="capa">Мои подписки</caption>
        <thead>
        <tr>
            <td class="td_first">Тип</td>
            <td class="td_second">Название</td>
            <td class="td_third">Цена</td>
            <td class="td_fourth">Магазин</td>
            <td class="td_fifth"></td>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="p" items="${product}">
            <tr>
                <td class="td_first">${p.type}</td>
                <td class="td_second">${p.title}</td>
                <td class="td_third">${p.price}</td>
                <td class="td_fourth">${p.storeName}</td>
                <td class="td_fifth"><a href="<spring:url value="/stand/delete/?userId="/><sec:authentication property="principal.id"/>&productId=${p.id}" class="delete-sub"><p>Отписаться</p></a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>