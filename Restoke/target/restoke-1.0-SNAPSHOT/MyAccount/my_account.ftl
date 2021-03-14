<#ftl encoding="UTF-8"/>
<html lang="ru">
<#import "/General/header.ftl" as headernav>
<#import "/General/Nav_bar.ftl" as navbar>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="MyAccount/my_account_style.css">
    <script src="https://kit.fontawesome.com/b99e675b6e.js"></script>
    <link rel="shortcut icon" href="MyAccount/icon.png" type="image/x-icon" />
    <title>My Account-Restoke</title>
</head>
<body>

<@headernav.content/>

<div class="menu">
    <ul class="menu_ul">
        <li><a href="/restoke_war/my_account" style="background: #2f3640">My Account</a></li>
        <li><a href="/restoke_war/favorites">Favorites</a></li>
        <li><a href="/restoke_war/my_ads" class="ads-btn">My Ads</a></li>
        <li><a href="/restoke_war/create_newAd">Create new Ad</a></li>
        <li><a href="/restoke_war/my_messages">My Messages</a></li>
    </ul>
</div>

<@navbar.content/>

<div class="main-content">
    <h1>Account</h1>
    <div class="user_img">
        <img src="user_icon.png" height="50%" width="50%">
    </div>
    <div class="custom-file">
        <input type="file" class="custom-file-input" id="customFile">
        <label class="custom-file-label" for="customFile"></label>
    </div>
    <h2>Profile</h2>
    <table>
        <tr>
            <td>User`s Name</td>
            <td>${name}</td>
        </tr>

        <tr>
            <td>User`s Lastname</td>
            <td>${lastname}</td>
        </tr>

        <tr>
            <td>Date of registration</td>
            <td>${date}</td>
        </tr>

        <tr>
            <td>User`s EMail</td>
            <td>${email}</td>
        </tr>

    </table>
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
<script src="MyAccount/my_account_script.js"></script>
</body>
</html>