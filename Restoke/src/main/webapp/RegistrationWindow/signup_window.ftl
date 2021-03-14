<#ftl encoding="UTF-8"/>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Регистрация-Restoke</title>
    <link rel="stylesheet" type="text/css" href="RegistrationWindow/style_for_signup_window.css">
    <link rel="shortcut icon" href="RegistrationWindow/icon.png" type="image/x-icon" />
</head>
<body>

<div class="wrapper">
    <form class="registrationBox" name="myForm" action="/restoke_war/sign-up" method="post">
        <svg width="5em" height="5em" viewBox="0 0 16 16" class="bi bi-basket" fill="currentColor" xmlns="http://www.w3.org/2000/svg">-->
            <path fill-rule="evenodd" d="M5.757 1.071a.5.5 0 0 1 .172.686L3.383 6h9.234L10.07 1.757a.5.5 0 1 1 .858-.514L13.783 6H15a1 1 0 0 1 1 1v1a1 1 0 0 1-1 1v4.5a2.5 2.5 0 0 1-2.5 2.5h-9A2.5 2.5 0 0 1 1 13.5V9a1 1 0 0 1-1-1V7a1 1 0 0 1 1-1h1.217L5.07 1.243a.5.5 0 0 1 .686-.172zM2 9v4.5A1.5 1.5 0 0 0 3.5 15h9a1.5 1.5 0 0 0 1.5-1.5V9H2zM1 7v1h14V7H1zm3 3a.5.5 0 0 1 .5.5v3a.5.5 0 0 1-1 0v-3A.5.5 0 0 1 4 10zm2 0a.5.5 0 0 1 .5.5v3a.5.5 0 0 1-1 0v-3A.5.5 0 0 1 6 10zm2 0a.5.5 0 0 1 .5.5v3a.5.5 0 0 1-1 0v-3A.5.5 0 0 1 8 10zm2 0a.5.5 0 0 1 .5.5v3a.5.5 0 0 1-1 0v-3a.5.5 0 0 1 .5-.5zm2 0a.5.5 0 0 1 .5.5v3a.5.5 0 0 1-1 0v-3a.5.5 0 0 1 .5-.5z"/>
        </svg>
        <h1>Create Account</h1>
        <p>${varning}</p>
        <div class="form-row">
            <div class="form-group col-md-6">
                <label for="inputName4">Name</label>
                <input type="text" class="form-control" id="inputName4" placeholder="Your Name" name="name">
            </div>
            <div class="form-group col-md-6">
                <label for="inputSurname4">Surname</label>
                <input type="text" class="form-control" id="inputSurname4" placeholder="Your surname" name="surname">
            </div>
<!--            <div class="form-group col-md-6">-->
<!--                <label for="inputBirth4">Birth</label>-->
<!--                <input type="date" class="form-control" id="inputBirth4" placeholder="Your Birth" name="birth">-->
<!--            </div>-->
        </div>

        <div class="form-row">
            <div class="form-group col-md-6">
                <label for="inputEmail4">Email</label>
                <input type="email" class="form-control" id="inputEmail4" placeholder="Email" name="email">
            </div>
            <div class="form-group col-md-6">
                <label for="inputCity">City</label>
                <input type="text" class="form-control" id="inputCity" name="city">
            </div>
            <div class="form-group col-md-6">
                <label for="inputPassword4">Password</label>
                <input type="password" class="form-control" id="inputPassword4" placeholder="Password" name="password">
            </div>
        </div>

        <div class="form-group">
            <div class="form-check">
                <label class="form-check-label">
                    <input class="form-check-input" type="checkbox"> Remember me
                </label>
            </div>
        </div>
        <a href="../Main_Page/main_page.html"><button type="submit" class="btn btn-primary">Sign up</button></a>
    </form>
</div>


<script src="script_for_signup_window.js"></script>
</body>
</html>