<#-- @ftlvariable name="_csrf" type="org.springframework.security.web.csrf.CsrfToken"-->
<#-- @ftlvariable name="error" type="java.util.Optional<String>" -->

<#include "base.ftl">

<#macro title>Auth</#macro>
<#macro imports>
    <link href="/static/css/login.css" rel="stylesheet">
</#macro>

<#macro content>
    <div class="wrapper" style="background-image: url(/static/res/food.jpg);">
        <div class="logo"></div>

        <div class="form">
<#--            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>-->

            <div class="label">Log In</div>

            <form method="post">
                <#if RequestParameters.error??>
                    <p>${RequestParameters.error}</p>
                </#if>

                <p><input type="text" class="login-input" name="username" placeholder="Username"></p>
                <p><input type="password" class="login-input" name="password" placeholder="Password"></p>

                <div class="form-bottom">
                    Don't have an account? <a href="/registration">Sign up!</a>
                    <button type="submit" class="button-submit" name="submit">Log in</button>
                </div>
                <div>
                    <label for="remember_me">Remember me</label>
                    <input type="checkbox" id="remember_me" name="remember_me">
                </div>
            </form>

<#--            <#if error.isPresent()>-->
<#--                <p>The email or password you have entered is invalid, try again.</p>-->
<#--            </#if>-->
        </div>
    </div>
</#macro>

<@page></@page>
