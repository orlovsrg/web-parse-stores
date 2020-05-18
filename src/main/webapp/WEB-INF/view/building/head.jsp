<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<div class="top-nav">

    <div class="dropdown">
        <button class="dropbtn">
            <i class="fa fa-folder" aria-hidden="true"></i>
            Каталог
        </button>
        <div class="dropdown-content">
            <a href="<spring:url value="/stand/by" />/phone" class="lk-style">Смартфоны</a>
            <a href="<spring:url value="/stand/by" />/laptop" class="lk-style">Ноутбуки</a>
            <a href="<spring:url value="/stand/by" />/tv_set" class="lk-style">Телевизоры</a>
        </div>
    </div>
    <sec:authorize access="isAuthenticated()" var="isUser"/>


    <div class="user">
        <i class="fa fa-chevron-circle-left"></i>
        <a href="<spring:url value="/"/>" class="lk-style">
            Домой
        </a>

        <c:choose>
            <c:when test="${isUser}">
                <i class="fa fa-address-card"></i>

                <a href="<spring:url value="/user"/>" class="lk-style">

                    Кабинет: <span id="user-name"><sec:authentication property="principal.name"/></span>
                </a>
                <i class="fa fa-user"></i>

                <a id="logout" class="lk-style">

                    Выход
                </a>

            </c:when>
            <c:otherwise>
                <i class="fa fa-user"></i>
                <a href="<spring:url value="/login"/>" class="lk-style">
                    Вход
                </a>
            </c:otherwise>
        </c:choose>
        <form:form id="logout-form" method="post" action="/logout">
            <sec:csrfInput/>
        </form:form>
    </div>

</div>
