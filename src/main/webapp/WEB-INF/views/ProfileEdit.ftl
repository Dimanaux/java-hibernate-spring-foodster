<#include "base.ftl">

<#include "sidemenu.ftl">

<#macro title>Edit profile</#macro>

<#macro imports>
    <link href="/static/css/edit_profile.css" rel="stylesheet">
    <script type="text/javascript" src="/static/js/file_input.js" defer></script>
</#macro>

<#macro content>
<div class="wrapper" style="background-image: url(/static/res/food.jpg);">
    <div class="logo">
    </div>
    <div class="container-wrapper">
        <@sidemenu></@sidemenu>
        <div class="container">
            <div class="label">Edit profile: </div>
            <div class="recipe">
                <form action="/profile" class="recipe-form">
                    <input class="input" type="text" name="name" id="name" placeholder="Name">
                    <input type="submit" value="change">
                </form>

                <form action="/profile/picture" class="recipe-form" method="post" enctype="multipart/form-data">
                    <label class="avatar-label">Avatar:</label>
                    <input type="file" name="photo" id="photo" accept=".jpg, .jpeg, .png">
                    <label id="photo-label" for="photo"><span class="upload-icon">&#10531;</span> Choose an image</label>
                    <input type="submit" id="button-submit" value="Save pic">
                </form>
            </div>
        </div>
    </div>
</div>
</#macro>

<@page></@page>