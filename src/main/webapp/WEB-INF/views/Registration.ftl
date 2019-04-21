<#include "base.ftl">

<#macro title>Registration</#macro>
<#macro imports>
    <link href="/static/css/login.css" rel="stylesheet">
</#macro>

<#macro content>
    <div class="wrapper" style="background-image: url(res/food.jpg);">
        <div class="logo">
        </div>
        <div class="form">
            <div class="label">Sign Up</div>
            <form method="post">
                <#if RequestParameters.error??>
                    <p>${RequestParameters.error}</p>
                </#if>
                <p><input type="text" class="login-input" name="username" placeholder="Username"></p>
                <p><input type="password" class="login-input" name="password" placeholder="Password"></p>
                <p><input type="password" class="login-input" name="password2" placeholder="Confirm password"></p>
                <p><input type="text" class="login-input" name="name" placeholder="name"></p>

                <div class="form-bottom">
                    Already have an account? <a href="/auth">Log in!</a>
                    <button type="submit" class="button-submit" name="submit">Sign up</button>
                </div>
            </form>
        </div>
    </div>
</#macro>

<@page></@page>
