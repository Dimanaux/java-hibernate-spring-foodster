<#-- @ftlvariable name="ingredients" type="java.util.List<app.db.models.Ingredient>" -->
<#include "base.ftl">
<#include "sidemenu.ftl">

<#macro title>New recipe</#macro>

<#macro imports>
    <script type="text/javascript" src="/static/js/file_input.js" defer></script>
    <script type="text/javascript" src="/static/js/ingredients_create_old.js" defer></script>
	<link href="/static/css/create_recipe.css" rel="stylesheet">
</#macro>

<#macro content>
    <div class="wrapper" style="background-image: url(/static/res/food.jpg);">
        <div class="logo"></div>
        <div class="container-wrapper">
            <@sidemenu></@sidemenu>
            <div class="container">
                <div class="label">Create recipe:</div>
                <div class="recipe">
                    <div class="form">
                        <input type="text" name="name" id="name" placeholder="Recipe name">

                        <div style="margin: 1rem;">
                            <label for="dish">Select dish:</label>
                            <select name="dish" id="dish"></select>
                        </div>

                        <textarea name="description" id="description" placeholder="Description"></textarea>


                        <h3 class="avatar-label">Ingredients:</h3>
                        <div id="form">
                            <div class="ingredients-list"></div>
                            <button type="button" id="add__button">+</button>
                        </div>
                        <button type="submit" id="button-submit" onclick="sendRecipe();">Create</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</#macro>

<@page></@page>

