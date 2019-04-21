<#-- @ftlvariable name="user" type="app.db.models.User" -->
<#macro header>
<header>
    <div class="brand">BR4ND</div>
    <form class="search" method="GET" action="/search">
        <span class="search-border">
            <input type="search" id="search-input" placeholder="list ingredients..." name="query">
            <input type="submit" id="search-button" value="&#128269;">
        </span>
    </form>
    <div class="bar__right">
        <#if user??>
            <a href="/profile" class="topnav-button">Profile</a>
            <a class="topnav-button" onclick="logout();">Log out</a>
        <#else>
            <a href="/auth" class="topnav-button">Log In</a>
            <a href="/registration" class="topnav-button">Sign Up</a>
        </#if>
    </div>
</header>
</#macro>
