<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:forEach var="entry" items="${map}">
    <div class="box">

        <c:forEach items="${entry.value}" var="p">
            <div class="item">
                <div class="img">
                    <a href="${p.url}">
                        <img src="${p.imgUrl}" alt="" srcset="">
                    </a>
                </div>

                <div class="info">
                    <div class="info-inner">
                        <a href="${p.url}" class="small-item">${p.title}</a>
                            <c:if test="${isUser}" >
                            <sec:authentication var="userId" property="principal.id" />
                            <a href="<spring:url value="/stand/subscription?userId=${userId}&productId=${p.id}&productType=${type}"/>"
                               class="small-item">Подписаться</a>
                            </c:if>

                    </div>
                    <a href="${p.url}" class="small-item">${p.storeName}</a>
                    <div class="info-inner">
<%--                        <a href="#" class="small-item">Старая цена</a>--%>
                        <a href="${p.url}" class="small-item">${p.price}</a>
                    </div>
                </div>
            </div>
        </c:forEach>

    </div>
</c:forEach>
