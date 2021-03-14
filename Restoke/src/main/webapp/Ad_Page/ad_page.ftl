<#ftl encoding="UTF-8"/>
<html lang="ru">
<#import "/General/header.ftl" as headernav>
<#import "/General/Nav_bar.ftl" as navbar>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="../Main_Page/main_page_style.css">
    <link rel="stylesheet" href="../Ad_Page/ad_page.css">
    <link rel="shortcut icon" href="../All_Categories/icon.png" type="image/x-icon" />
    <script src="https://kit.fontawesome.com/b99e675b6e.js"></script>
    <title></title>
</head>
<body>

<@headernav.content/>

<div><div class="tape" style="letter-spacing: 5px; color: #2f3640; text-align: center;">Объявление</div>
</div>

<@navbar.content/>

<div class="main-content">
    <div class="ad_part1">
        <div class="ad_header"><h1>${header}</h1></div>
        <div class="ad_photo"><img src="${photoref}"></div>
        <div class="ad_date_of_publishing"><p>${date}</p></div>
        <div class="ad_city"><p>${city}</p></div>
        <div class="ad_description"><p>${description}</p></div>
    </div>
    <div class="ad_part2">
        <div class="price"><h1>${price}</h1></div>
        <div class="seller_inf">
            <div class="seller_email"><p>${email}</p></div>
            <div class="seller_phone_number"><p>${phone}</p></div>
            <div class="seller_name"><p>${name}&nbsp${surname}</p></div>
            <div class="seller_rating"><p>${rating}</p></div>
            <div class="number_of_feedback"><p>${feedbacknum}</p></div>
        </div>
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
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="../Ad_Page/ad_page_scripts.js"></script>
</body>
</html>