<!doctype html>
<html lang="ru">
<#macro title>Nav_bar</#macro>
<#macro content>
<nav class="navbar">

    <div class="btn">
        <span class="fas fa-bars"></span>
    </div>

    <div class="logo">
        <a href="/main"><h4>Restoke.</h4></a>
    </div>
    <ul class="nav-links">

        <li class="icon_list">
            <div class="search-box">
                <input type="text" class="search-txt" value="${searchQuery}" placeholder="Search">
                <a href="#" class="search-btn"><i class="fa fa-search" aria-hidden="true"></i></a>
            </div>
        </li>

        <li class="icon_list">
            <a class="icon" href="/my_account">
                <svg width="1.7em" height="1.8em" viewBox="0 0 16 16" class="bi bi-person" fill="white"
                     xmlns="http://www.w3.org/2000/svg">
                    <path fill-rule="evenodd"
                          d="M10 5a2 2 0 1 1-4 0 2 2 0 0 1 4 0zM8 8a3 3 0 1 0 0-6 3 3 0 0 0 0 6zm6 5c0 1-1 1-1 1H3s-1 0-1-1 1-4 6-4 6 3 6 4zm-1-.004c-.001-.246-.154-.986-.832-1.664C11.516 10.68 10.289 10 8 10c-2.29 0-3.516.68-4.168 1.332-.678.678-.83 1.418-.832 1.664h10z"/>
                </svg>
            </a>
        </li>

        <li class="icon_list">
            <a class="icon" href="/create_newAd">
                <svg width="1.7em" height="1.7em" viewBox="0 0 16 16" class="bi bi-bag-plus" fill="white"
                     xmlns="http://www.w3.org/2000/svg">
                    <path fill-rule="evenodd"
                          d="M8 1a2.5 2.5 0 0 0-2.5 2.5V4h5v-.5A2.5 2.5 0 0 0 8 1zm3.5 3v-.5a3.5 3.5 0 1 0-7 0V4H1v10a2 2 0 0 0 2 2h10a2 2 0 0 0 2-2V4h-3.5zM2 5v9a1 1 0 0 0 1 1h10a1 1 0 0 0 1-1V5H2z"/>
                    <path fill-rule="evenodd"
                          d="M8 7.5a.5.5 0 0 1 .5.5v1.5H10a.5.5 0 0 1 0 1H8.5V12a.5.5 0 0 1-1 0v-1.5H6a.5.5 0 0 1 0-1h1.5V8a.5.5 0 0 1 .5-.5z"/>
                </svg>
            </a>
        </li>
        <li class="icon_list">
            <a class="icon" href="/login">Log</a>
        </li>
    </ul>
</nav>
</#macro>
</html>