<#ftl encoding="UTF-8"/>
<html lang="ru">
<#import "/General/header.ftl" as headernav>
<#import "/General/Nav_bar.ftl" as navbar>
<head>
    <meta charset="UTF-8">
    <script src="https://kit.fontawesome.com/b99e675b6e.js"></script>
    <link rel="shortcut icon" href="Main_Page/icon.png" type="image/x-icon" />
    <title>Main page-Restoke</title>
    <!--    <script src="main_page_script.js"></script>-->

    <link rel="stylesheet" href="Main_Page/main_page_style.css">

    <!-- Scrollbar Custom CSS -->
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/malihu-custom-scrollbar-plugin/3.1.5/jquery.mCustomScrollbar.min.css">

</head>
<body>

<@headernav.content/>

<@navbar.content/>

<div class="tape" style="letter-spacing: 5px; color: #2f3640; text-align: center;">Welcome to Restoke!
    The best marketplace ever!
</div>

<div class="main-content">

    <div class="slideshow-container">

        <div class="mySlides fade">
            <div class="numbertext">1 / 3</div>
            <a href="All_Categories/Rent/cottages.html"><img src="Main_Page/cottages.jpg"
                                                                style="width:100%; height: 300px"></a>
            <!--            <div class="text">Caption Text</div>-->
        </div>

        <div class="mySlides fade">
            <div class="numbertext">2 / 3</div>
            <a href="../All_Categories/electronics/phones.html"><img src="Main_Page/phones.jpg" style="width:100%; height: 300px"></a>
            <!--            <div class="text">Caption Two</div>-->
        </div>

        <div class="mySlides fade">
            <div class="numbertext">3 / 3</div>
            <a href="../All_Categories/clothes/male/sneakers&shoes.html"><img src="Main_Page/shoes.jpg"
                                                                              style="width:100%; height: 300px"></a>
            <!--            <div class="text">Caption Three</div>-->
        </div>

        <a class="prev" onclick="plusSlides(-1)">&#10094;</a>
        <a class="next" onclick="plusSlides(1)">&#10095;</a>

    </div>
    <br>

    <div style="text-align:center">
        <span class="dot"></span>
        <span class="dot"></span>
        <span class="dot"></span>
    </div>

    <div class="">
        <img class="instruction_img" src="Main_Page/instruction.jpg" width="70%" height="70%">
    </div>

</div>

<!-- jQuery CDN - Slim version (=without AJAX) -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<!-- Popper.JS -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"
        integrity="sha384-cs/chFZiN24E4KMATLdqdvsezGxaGsi4hLGOzlXwp5UZB1LY//20VyM2taTB4QvJ"
        crossorigin="anonymous"></script>
<!-- Bootstrap JS -->
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"
        integrity="sha384-uefMccjFJAIv6A+rW+L4AHf99KvxDjWSu1z9VI8SKNVmz4sk7buKt/6v9KI65qnm"
        crossorigin="anonymous"></script>
<!-- jQuery Custom Scroller CDN -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/malihu-custom-scrollbar-plugin/3.1.5/jquery.mCustomScrollbar.concat.min.js"></script>
<script src=" https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="Main_Page/main_page_script.js"></script>
</body>
</html>