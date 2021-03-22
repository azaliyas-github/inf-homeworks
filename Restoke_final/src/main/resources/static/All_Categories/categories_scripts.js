$('.btn').click(function () {
    $(this).toggleClass("click");
    $('.sidebar').toggleClass("show")
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

$('.ads-btn').click(function () {
    $('.menu .menu_ul .ads-show').toggleClass("show12");
    $('.menu .menu_ul .thirteenth').toggleClass("rotate");
});

$('.business-btn').click(function () {
    $('nav ul .business-show').toggleClass("show13");
    $('nav ul .lol').toggleClass("rotate");
});

$('nav ul li').click(function () {
    $(this).addClass("active").siblings().removeClass("active");
});