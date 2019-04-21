<#-- @ftlvariable name="ingredients" type="java.util.List<app.db.models.Ingredient>" -->
<#-- @ftlvariable name="recipe" type="app.db.models.Recipe" -->
<#include "base.ftl">
<#include "sidemenu.ftl">

<#macro imports>
    <script src="/static/js/posts_id.js" defer></script>
    <link rel="stylesheet" href="/static/css/recipe_new.css">
</#macro>

<#macro title>Recipe of #${recipe.dish.name}</#macro>

<#macro content>
    <div class="wrapper" style="background-image: url(/static/res/food.jpg);">
        <div class="logo">
        </div>
        <div class="container-wrapper">
            <@sidemenu></@sidemenu>
            <div class="container">
                <div class="recipe">
                    <div class="recipe-data">
                        <h2>${recipe.title}</h2>
                        <ul class="ingredients">
                            <#list ingredients as ingredient>
                                <li>${ingredient.name}</li>
                            </#list>
                        </ul>
                        <p>${recipe.text}</p>
                        <small>${recipe.date}</small>
                    </div>
                    <div class="create-comment">
                        <h3>Write a comment:</h3>
                        <label for="comment-text">Text:</label>
                        <textarea name="text" id="comment-text" cols="30" rows="10" required></textarea>
                        <button onclick="sendComment();">Comment!</button>
                    </div>
                    <div class="comments" id="comments-list">
                        <h3>Comments:</h3>
                    </div>
                </div>
            </div>
        </div>
    </div>
</#macro>


<@page></@page>
