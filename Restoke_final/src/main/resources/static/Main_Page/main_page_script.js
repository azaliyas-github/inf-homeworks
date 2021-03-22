$('.btn').click(function () {
    $(this).toggleClass("click");
    $('.sidebar').toggleClass("show")
});

$('.btn').click(function () {
    $('.main-content').toggleClass("black")
});

$('.feat-btn').click(function () {
    $('nav ul .feat-show').toggleClass("show");
    $('nav ul .first').toggleClass("rotate");
});

$('.serv-btn').click(function () {
    $('nav ul .serv-show').toggleClass("show1");
    $('nav ul .second').toggleClass("rotate");
});

$('.trans-btn').click(function () {
    $('nav ul .trans-show').toggleClass("show2");
    $('nav ul .third').toggleClass("rotate");
});

$('.clothes-btn').click(function () {
    $('nav ul .clothes-show').toggleClass("show3");
    $('nav ul .fourth').toggleClass("rotate");
});

$('.accessries-btn').click(function () {
    $('nav ul .accessories-show').toggleClass("show4");
    $('nav ul .fifth').toggleClass("rotate");
});

$('.electr-btn').click(function () {
    $('nav ul .electr-show').toggleClass("show5");
    $('nav ul .sixth').toggleClass("rotate");
});

$('.toys-btn').click(function () {
    $('nav ul .toys-show').toggleClass("show6");
    $('nav ul .seventh').toggleClass("rotate");
});

$('.home_dacha-btn').click(function () {
    $('nav ul .home_dacha-show').toggleClass("show7");
    $('nav ul .eighth').toggleClass("rotate");
});

$('.hobby_relax-btn').click(function () {
    $('nav ul .hobby_relax-show').toggleClass("show8");
    $('nav ul .ninth').toggleClass("rotate");
});

$('.animals-btn').click(function () {
    $('nav ul .animals-show').toggleClass("show9");
    $('nav ul .tenth').toggleClass("rotate");
});

$('.male-btn').click(function () {
    $('nav ul .male-show').toggleClass("show10");
    $('nav ul .eleventh').toggleClass("rotate");
});

$('.female-btn').click(function () {
    $('nav ul .female-show').toggleClass("show11");
    $('nav ul .twelth').toggleClass("rotate");
});

$('.business-btn').click(function () {
    $('nav ul .business-show').toggleClass("show12");
    $('nav ul .lol').toggleClass("rotate");
});

$('nav ul li').click(function () {
    $(this).addClass("active").siblings().removeClass("active");
});

var slideIndex = 0;
showSlides();

function showSlides() {
    var i;
    var slides = document.getElementsByClassName("mySlides");
    var dots = document.getElementsByClassName("dot");
    for (i = 0; i < slides.length; i++) {
        slides[i].style.display = "none";
    }
    slideIndex++;
    if (slideIndex > slides.length) {
        slideIndex = 1
    }
    for (i = 0; i < dots.length; i++) {
        dots[i].className = dots[i].className.replace(" active", "");
    }
    slides[slideIndex - 1].style.display = "block";
    dots[slideIndex - 1].className += " active";
    setTimeout(showSlides, 7000); // Change image every 2 seconds
}


var slideIndex1 = 1;
showSlides1(slideIndex1);

function plusSlides(n) {
    showSlides1(slideIndex1 += n);
}

function currentSlide(n) {
    showSlides1(slideIndex1 = n);
}

function showSlides1(n) {
    var i;
    var slides = document.getElementsByClassName("mySlides");
    var dots = document.getElementsByClassName("dot");
    if (n > slides.length) {
        slideIndex1 = 1
    }
    if (n < 1) {
        slideIndex1 = slides.length
    }
    for (i = 0; i < slides.length; i++) {
        slides[i].style.display = "none";
    }
    for (i = 0; i < dots.length; i++) {
        dots[i].className = dots[i].className.replace(" active", "");
    }
    slides[slideIndex1 - 1].style.display = "block";
    dots[slideIndex1 - 1].className += " active";
}

