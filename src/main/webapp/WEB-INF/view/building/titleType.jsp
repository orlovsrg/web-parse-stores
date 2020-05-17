<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:forEach var="entry" items="${map}">
    <div class="box">

        <c:forEach items="${entry.value}" var="p">
            <div class="item">
                <div class="img">
                    <a href="">
                        <img src="${p.imgUrl}" alt="" srcset="">
                    </a>
                </div>
                <div class="info">
                    <div class="info-inner">
                        <a href="#" class="small-item">${p.title}</a>
                            <c:if test="${isUser}" >
                            <sec:authentication var="userId" property="principal.id" />
                            <a href="<spring:url value="/stand/subscription?userId=${userId}&productId=${p.id}&productType=${type}"/>"
                               class="small-item">Подписаться</a>
                            </c:if>

                    </div>
                    <a href="#" class="small-item">${p.storeId}</a>
                    <div class="info-inner">
                        <a href="#" class="small-item">Старая цена</a>
                        <a href="#" class="small-item">${p.price}</a>
                    </div>
                </div>
            </div>
        </c:forEach>

            <%--        <div class="item">--%>
            <%--            <div class="img">--%>
            <%--                <a href="">--%>
            <%--                    <img src="https://i.allo.ua/media/catalog/product/cache/1/image/9df78eab33525d08d6e5fb8d27136e95/i/p/iphone_11_pro_sg_2_3.jpg"--%>
            <%--                         alt="" srcset="">--%>
            <%--                </a>--%>
            <%--            </div>--%>
            <%--            <div class="info">--%>
            <%--                <a href="#" class="small-item">text</a>--%>
            <%--                <a href="#" class="small-item">text</a>--%>
            <%--                <div class="info-inner">--%>
            <%--                    <a href="#" class="small-item">text</a>--%>
            <%--                    <a href="#" class="small-item">text</a>--%>
            <%--                </div>--%>
            <%--            </div>--%>
            <%--        </div>--%>

    </div>
</c:forEach>
