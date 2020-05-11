<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<div class="form-wrap">
    <div class="tabs">
        <h3 class="signup-tab"><a class="active" href="#signup-tab-content">Регистрация</a></h3>
        <h3 class="login-tab"><a href="#login-tab-content">Войти</a></h3>
    </div>

    <div class="tabs-content">
        <div id="signup-tab-content" class="active">
            <form:form   cssClass="signup-form" method="post" action="/user/registration" modelAttribute="userDto">
                <form:errors path="name" cssClass="message" />
                <form:input path="name" type="text" cssClass="input" id="user_name" autocomplete="off"
                            placeholder="Имя"/>
                <form:errors path="login" cssClass="message"  />
                <form:input path="login" type="text" cssClass="input" id="user_name" autocomplete="off"
                            placeholder="Логин"/>
                <form:errors path="birthDay" cssClass="message"  />
                <form:input path="birthDay" type="date" cssClass="input" id="user_name" autocomplete="off"
                            placeholder="День рождения"/>
                <form:errors path="phoneNumber" cssClass="message"  />
                <form:input path="phoneNumber" type="tel" cssClass="input" id="user_name" autocomplete="off"
                            placeholder="Номер телефона"/>
                <form:errors path="password" cssClass="message"  />
                <form:input path="password" type="password" cssClass="input" id="user_pass" autocomplete="off"
                            placeholder="Пароль"/>
                <form:errors path="secondPassword" cssClass="message"  />
                <form:input path="secondPassword" type="password" cssClass="input" id="user_pass" autocomplete="off"
                            placeholder="Подтвердите ароль"/>
                <form:errors path="email" cssClass="message"  />
                <form:input path="email" type="email" cssClass="input" id="user_email" autocomplete="off"
                            placeholder="Почта"/>

                <input type="submit" class="button" value="Регистрация">
            </form:form>
            <div class="help-text">

            </div>
        </div>

        <div id="login-tab-content">
            <form:form class="login-form" action="/user/login" method="post" modelAttribute="userDto">
                <form:errors path="checkLogin" cssClass="message"  />
                <form:input path="checkLogin" type="text" cssClass="input" id="user_name" autocomplete="off"
                            placeholder="Логин"/>
                <form:errors path="checkPassword" cssClass="message"  />
                <form:input path="checkPassword" type="password" cssClass="input" id="user_pass" autocomplete="off"
                            placeholder="Пароль"/>
<%--                <input type="checkbox" class="checkbox" id="remember_me">--%>
<%--                <label for="remember_me">Запомнить</label>--%>
                <input type="submit" class="button" value="Войти">
            </form:form>
            <div class="help-text">
                <p><a href="#">Восстановить данные?</a></p>
            </div>
        </div>
    </div>
</div>
