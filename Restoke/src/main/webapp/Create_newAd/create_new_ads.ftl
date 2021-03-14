<#ftl encoding="UTF-8"/>
<html lang="ru">
<#import "/General/header.ftl" as headernav>
<#import "/General/Nav_bar.ftl" as navbar>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="Create_newAd/create_new_ads_style.css">
    <script src="https://kit.fontawesome.com/b99e675b6e.js"></script>
    <link rel="shortcut icon" href="Create_newAd/icon.png" type="image/x-icon" />
    <title>Create new Ad-Restoke</title>
</head>
<body>

<@headernav.content/>

<div class="menu">
    <ul class="menu_ul">
        <li><a href="/restoke_war/my_account">My Account</a></li>
        <li><a href="/restoke_war/favorites">Favorites</a></li>
        <li><a href="/restoke_war/my_ads">My Ads</a></li>
        <li><a href="/restoke_war/create_newAd" style="background: #2f3640">Create new Ad</a></li>
        <li><a href="/restoke_war/my_messages">My Messages</a></li>
    </ul>
</div>

<@navbar.content/>

<div class="main-content">
    <div class="create_ad_logo">
        <p>Creating Ad</p>
    </div>
    <form class="create_ad_form" name="myAd" action="/restoke_war/create_newAd" method="post" enctype="multipart/form-data" >
        <div class="user_img">
            <img src="Create_newAd/user_icon.png" height="50%" width="50%">
        </div>
        <div class="custom-file">
            <input name="photo" type="file" class="custom-file-input" id="customFile" accept="image" required="required" title="Необходимо выбрать минимум один файл" >
            <label class="custom-file-label" for="customFile"></label>
        </div>

        <div class="item_info">

            <label for="product_name">Ad Name</label><br>
            <input type="text" name="products_name" id="product_name" placeholder="Ad name"><br>

            <label for="product_price">Price</label><br>
            <input type="number" name="products_price" id="product_price" placeholder="Price"><br>

            <label for="product_phone">Seller's phone</label><br>
            <input type="number" name="products_phone" id="product_phone" placeholder="Seller's Phone"><br>

            <label for="product_email">Seller's Email</label><br>
            <input type="email" name="products_email" id="product_email" placeholder="Seller's Email"><br>

            <label for="product_city">Seller's city</label><br>
            <input type="text" name="products_city" id="product_city" placeholder="Seller's city"><br>

            <div>
                <label for="product_category">Category</label><br>
                <select name="subcategory" id="product_category" class="categories">
                    <#if categories??>
                        <#list categories as category>
                            <#if category.subcategoriesNames??>
                                <#list category.subcategoriesNames as subcategory>
                                    <option >${subcategory}</option>-->
                                </#list>
                            </#if>
                        </#list>
                    </#if>
                </select><br>

            </div>

            <div class="characteristic_ad">
                <label for="characteristic">Description</label><br>
                <textarea name="description" class="characteristic" id="characteristic" rows="3" cols="30"
                          placeholder="Write your product characteristic"></textarea><br>
            </div>

            <a href="#" style="text-decoration: none"><input type="submit" name="" value="Create"></a>

        </div>

    </form>
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
<script src="Create_newAd/create_new_ads_scripts.js"></script>
</body>
</html>