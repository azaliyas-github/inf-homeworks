<#ftl encoding="UTF-8"/>
<html lang="ru">
<#import "/General/header.ftl" as headernav>
<#import "/General/Nav_bar.ftl" as navbar>
<#import "/General/ads_block.ftl" as adsblock>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="../Main_Page/main_page_style.css">
    <link rel="stylesheet" href="../All_Categories/categories_style.css">
    <link rel="shortcut icon" href="../All_Categories/icon.png" type="image/x-icon" />
    <script src="https://kit.fontawesome.com/b99e675b6e.js"></script>
    <title>${title}-Restoke</title>
</head>
<body>

<@headernav.content/>

<div><div class="tape" style="letter-spacing: 5px; color: #2f3640; text-align: center;">${title}</div>

    <div class="filters">

        <form method="get">
            <div class="form_text">
                <div class="price_text">
                    <span>Set price range</span>
                </div>
                <div class="role_text">
                    <span>Set seller`s role</span>
                </div>
            </div>
            <div class="price_filter">
                <div class="min_price">
                    <input type="text" id="text" name="min_price" placeholder="Min price"/>
                </div>
                <div class="max_price">
                    <input type="text" id="text" name="max_price" placeholder="Max price"/>
                </div>
            </div>

            <div class="role_filter">
                <div class="form_radio_group">
                    <div class="form_radio_group-item">
                        <input id="radio-1" type="radio" name="radio" value="all" ${check_one}>
                        <label for="radio-1">All</label>
                    </div>
                    <div class="form_radio_group-item">
                        <input id="radio-2" type="radio" name="radio" value="0" ${check_two}>
                        <label for="radio-2">Individual</label>
                    </div>
                    <div class="form_radio_group-item">
                        <input id="radio-3" type="radio" name="radio" value="1" ${check_three}>
                        <label for="radio-3">Companies</label>
                    </div>
                </div>
            </div>

            <div class="filter_button">
                <input type="submit" name="filter_submit" value="Submit">
            </div>
        </form>

    </div>
</div>

<@navbar.content/>

<div class="main-content">
    <@adsblock.content/>
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
<script src="../All_Categories/categories_scripts.js"></script>
</body>
</html>