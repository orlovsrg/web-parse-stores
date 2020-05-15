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
            <a href="<spring:url value="/stand" />/phone" class="lk-style">Смартфоны</a>
            <a href="<spring:url value="/stand" />/laptop" class="lk-style">Ноутбуки</a>
            <a href="<spring:url value="/stand" />/tv_set" class="lk-style">Телевизоры</a>
        </div>
    </div>

    <div class="user">
        <i class="fa fa-chevron-circle-left"></i>
        <a href="<spring:url value="/"/>" class="lk-style">
            Домой
        </a>
        <sec:authorize access="isAuthenticated()" var="isUser"/>
        <c:choose>
            <c:when test="${isUser}">
                <span><sec:authentication property="name"/></span>
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
