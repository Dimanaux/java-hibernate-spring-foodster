<#include "base.ftl">
<#include "sidemenu.ftl">

<#macro title>Recipes</#macro>

<#macro imports>
    <link rel="stylesheet" href="/static/css/recipes.css">
    <script src="/static/js/search.js" defer></script>
    <link rel="stylesheet" href="/static/css/recipes_new.css">
</#macro>

<#macro content>
    <div class="wrapper" style="background-image: url(/static/res/food.jpg);">
        <div class="logo">
        </div>
        <div class="container-wrapper">
            <@sidemenu></@sidemenu>
            <div class="container">

                <div class="search">
                    <span class="search-border">
                        <input type="search" id="search-box" placeholder="Search">
                        <button type="submit" id="search-button" onclick="search();">&#128269;</button>
                    </span>
                </div>

                <div id="recipes-list">

                </div>

            </div>
        </div>
    </div>
</#macro>



<@page></@page>
