<%-- 
    Document   : slideshow
    Created on : 03-Dec-2016, 23:09:59
    Author     : Conno
--%>
<div class="slideshow-container">
    <div class="mySlides fade">
      <div class="numbertext">1 / 3</div>
      <img class="displayImage" src="image/product_image/image1.jpg" style="width:100%">
      <div class="text">Fallout New Vegas: Like the previous game but with blackjack</div>
    </div>

    <div class="mySlides fade">
      <div class="numbertext">2 / 3</div>
      <img class="displayImage" src="image/product_image/image2.jpg" style="width:100%">
      <div class="text">Counter Strike: We hope you speak Russian</div>
    </div>

    <div class="mySlides fade">
      <div class="numbertext">3 / 3</div>
      <img class="displayImage" src="image/product_image/image3.jpg" style="width:100%">
      <div class="text">Overwatch: Counter strike but in English this time</div>
    </div>

    <a class="prev" onclick="plusSlides(-1)">&#10094;</a>
    <a class="next" onclick="plusSlides(1)">&#10095;</a>
</div>
<br>

<div style="text-align:center">
    <span class="dot" onclick="currentSlide(1)"></span> 
    <span class="dot" onclick="currentSlide(2)"></span> 
    <span class="dot" onclick="currentSlide(3)"></span> 
</div>
<script type="text/javascript" src="js/slideshow.js"></script>
