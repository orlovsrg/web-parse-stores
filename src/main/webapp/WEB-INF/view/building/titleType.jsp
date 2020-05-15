<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:forEach var="user" items="${user}">
    <div class="box">
        <div class="item">
            <div class="img">
                <a href="">
                    <img src="https://i1.foxtrot.com.ua/product/MediumImages/6471284_0.jpg" alt="" srcset="">
                </a>
            </div>
            <div class="info">
                <a href="#" class="small-item">text</a>
                <a href="#" class="small-item">text</a>
                <div class="info-inner">
                    <a href="#" class="small-item">text</a>
                    <a href="#" class="small-item">text</a>
                </div>
            </div>
        </div>
        <div class="item">
            <div class="img">
                <a href="">
                    <img src="https://i.allo.ua/media/catalog/product/cache/1/image/9df78eab33525d08d6e5fb8d27136e95/i/p/iphone_11_pro_sg_2_3.jpg"
                         alt="" srcset="">
                </a>
            </div>
            <div class="info">
                <a href="#" class="small-item">text</a>
                <a href="#" class="small-item">text</a>
                <div class="info-inner">
                    <a href="#" class="small-item">text</a>
                    <a href="#" class="small-item">text</a>
                </div>
            </div>
        </div>
    </div>
</c:forEach>
