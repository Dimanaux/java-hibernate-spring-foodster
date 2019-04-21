<#include "base.ftl">
<#include "sidemenu.ftl">

<#macro title>New post</#macro>

<#macro imports>
    <script type="text/javascript" src="/static/js/posts_new.js"></script>
    <script type="text/javascript" src="/static/js/goto.js"></script>
	<link href="/static/css/create_recipe.css" rel="stylesheet">
</#macro>

<#macro content>
    <div class="wrapper" style="background-image: url(/static/res/food.jpg);">
        <div class="logo"></div>
        <div class="container-wrapper">
            <@sidemenu></@sidemenu>
            <div class="container">
                <div class="label">Create a post:</div>
                <div class="recipe">
                    <form action="/posts" class="form" method="post">
                        <div>
                            <input type="text" name="title" id="name" placeholder="Post title">
                        </div>

                        <div>
                            <textarea name="content" id="description" placeholder="Text"></textarea>
                        </div>

                        <input type="submit" id="button-submit" value="Create post">
                    </form>
                </div>
            </div>
        </div>
    </div>
</#macro>

<@page></@page>

