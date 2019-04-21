<#include "base.ftl">
<#include "posts_view.ftl">

<#include "sidemenu.ftl">

<#macro title>Posts</#macro>

<#macro content>
    <div class="wrapper" style="background-image: url(/static/res/food.jpg);">
        <div class="logo">
        </div>
        <div class="container-wrapper">
            <@sidemenu></@sidemenu>
            <div class="container">
                <@posts_view></@posts_view>
            </div>
        </div>
    </div>
</#macro>


<@page></@page>
