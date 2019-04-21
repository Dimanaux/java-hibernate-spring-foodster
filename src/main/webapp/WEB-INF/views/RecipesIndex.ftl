<#include "base.ftl">
<#include "recipes_view.ftl">
<#include "sidemenu.ftl">

<#macro title>Recipes</#macro>

<#macro imports>
    <link rel="stylesheet" href="/static/css/recipes.css">
</#macro>

<#macro content>
    <div class="wrapper" style="background-image: url(/static/res/food.jpg);">
        <div class="logo">
        </div>
        <div class="container-wrapper">
            <@sidemenu></@sidemenu>
            <div class="container">

                <@recipes_view></@recipes_view>

            </div>
        </div>
    </div>
</#macro>


<@page></@page>
