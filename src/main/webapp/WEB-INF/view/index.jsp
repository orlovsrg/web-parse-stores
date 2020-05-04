<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="<spring:url value="/static/css/start_page.css"/> " rel="stylesheet" type="text/css">
</head>
<body>
<header>
    <section id="logo">
        <h1>Салэ ?<h2>салэ салэ ... !</h2></h1>
    </section>
    <nav id="main_nav">
        <a href="<spring:url value="/parse" />">Parsing</a>
        <a href="#">What king this сайт</a>
        <a href="#">Войти</a>
        <a href="#">Выйти</a>
    </nav>
</header>
<main id="main">

    <nav id="bar_menu">
        <ul id="store_button">
            <li><a href="#">Магаз</a></li>
            <ul id="outer_show_menu">
                <li><a href="#">Телефоны</a></li>
                <li><a href="#">Ноутбуки</a></li>
                <li><a href="#">Планшеты</a></li>
            </ul>
        </ul>
    </nav>

    <section id="main_section">
        <img src="<spring:url value="/static/img/iPhone.jpg" /> ">
        <div id="thing_info">
            <span>Название</span>
            <span>Магазин</span>
            <span>Цена</span>
        </div>
    </section>
</main>
</body>
</html>
