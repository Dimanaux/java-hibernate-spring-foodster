<#-- @ftlvariable name="post" type="com.example.food.db.entities.Post" -->
<#include "base.ftl">
<#include "sidemenu.ftl">

<#macro imports>
    <script src="/static/js/posts_id.js" defer></script>
    <link rel="stylesheet" href="/static/css/post.css">
</#macro>

<#macro title>Post #${post.id}</#macro>

<#macro content>
    <div class="wrapper" style="background-image: url(/static/res/food.jpg);">
        <div class="logo">
        </div>
        <div class="container-wrapper">
            <@sidemenu></@sidemenu>
            <div class="container">
                <div class="post">
                    <div class="post-data">
                        <h2>${post.title}</h2>
                        <p>${post.content}</p>
                        <small>${post.publishedAt}</small>
                    </div>
                    <hr>
                    <div class="create-comment">
                        <h3>Write a comment:</h3>
                        <label for="comment-text">Text:</label>
                        <textarea name="text" id="comment-text" cols="30" rows="10" required></textarea>
                        <button onclick="sendComment();">Comment!</button>
                    </div>
                    <hr>
                    <div class="comments" id="comments-list">
                        <h3>Comments:</h3>
                    </div>
                </div>
            </div>
        </div>
    </div>
</#macro>


<@page></@page>
