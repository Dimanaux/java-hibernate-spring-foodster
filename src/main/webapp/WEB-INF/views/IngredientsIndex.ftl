<#include "base.ftl">
<#include "sidemenu.ftl">

<#macro title>Ingredients</#macro>

<#macro imports>
    <link rel="stylesheet" href="/static/css/ingredients.css">
</#macro>

<#macro content>
    <div class="wrapper" style="background-image: url(./static/res/food.jpg);">
        <div class="logo"></div>
        <div class="container-wrapper">
            <@sidemenu></@sidemenu>
            <div class="container">
                <div class="label">Search by ingredients: </div>
                <div class="ingredients-form">
                    <form method="get" id="form">
                        <div class="ingredient">
                            <select id="ingredient-select 1" name="ingredient-select 1">
                                <option value="ingr1">ingr1</option>
                                <option value="ingr2">ingr2</option>
                                <option value="ingr3">ingr3</option>
                                <option value="ingr4">ingr4</option>
                            </select>
                        </div>
                        <p><button type="button" id="add__button">+</button></p>
                        <p><button type="submit" id="button-submit">Search</button></p>
                    </form>
                </div>
            </div>
        </div>
    </div>
</#macro>


<@page></@page>
