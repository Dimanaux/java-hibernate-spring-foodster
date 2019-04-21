<#-- @ftlvariable name="recipes" type="java.util.List<app.db.models.Recipe>" -->
<#macro recipes_view>
    <div class="recipes-bar__top">
        <div class="label">Recipes:</div>
        <a href="/recipes/new" class="add-recipe"><span class="plus">+</span> Add recipe</a>
    </div>
    <div class="list">
        <#list recipes as recipe>
            <div>
                <a href="/recipes/${recipe.id}">
                    <span class="item">
                        <img src="/static/res/item1.jpg" alt="item1">
                        <span class="item-text">
                            <h3 class="heading">${recipe.title}</h3>
                            <#if recipe.ingredients?? && recipe.ingredients?has_content>
                                <p>Ingredients:</p>
                                <ul class="ingredients">
                                    <#list recipe.ingredients as ingredient>
                                        <li>${ingredient.name}</li>
                                    </#list>
                                </ul>
                            </#if>
                        </span>
                        <small>${recipe.date}</small>
                    </span>
                </a>
            </div>
        </#list>
    </div>
</#macro>