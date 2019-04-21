<#include "base.ftl">
<#include "sidemenu.ftl">
<#include "recipes_view.ftl">
<#include "profile_view.ftl">

<#macro title>Profile</#macro>

<#macro imports>
    <link rel="stylesheet" href="/static/css/profile.css">
</#macro>

<#macro content>
    <div class="wrapper" style="background-image: url(/static/res/food.jpg);">
        <div class="logo">
        </div>
        <div class="container-wrapper">
            <@sidemenu></@sidemenu>
            <div class="container">

                <@profile_view></@profile_view>

                <@recipes_view></@recipes_view>

            </div>
        </div>
    </div>
</#macro>

<@page></@page>
