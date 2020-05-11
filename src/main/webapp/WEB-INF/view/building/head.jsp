<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="top-nav">

    <div class="dropdown">
        <button class="dropbtn">
            <i class="fa fa-folder" aria-hidden="true"></i>
            Каталог
        </button>
        <div class="dropdown-content">
            <a href="<spring:url value="/stand" />/phone" class="lk-style">Смартфоны</a>
            <a href="<spring:url value="/stand" />/laptop" class="lk-style">Ноутбуки</a>
            <a href="<spring:url value="/stand" />/tv" class="lk-style">Телевизоры</a>
        </div>
    </div>

    <div class="user">
        <i class="fa fa-chevron-circle-left"></i>
        <a href="<spring:url value="/"/>" class="lk-style">

            Домой
        </a>
        <i class="fa fa-user"></i>
        <a href="<spring:url value="/user"/>" class="lk-style">
            Вход
        </a>
    </div>

</div>
