<#-- @ftlvariable name="user" type="com.example.food.db.entities.Account" -->
<#macro profile_view>
    <a href="/profile/edit" class="edit">&#9998;</a>

    <div class="user-profile">
        <img src="/static/res/${user.username}.jpg" onerror="this.src='/static/res/profile.jpg'"/>
        <div class="user-profile-text">
            <h2>${user.name}</h2>
            <p>${user.username}</p>
        </div>
    </div>
</#macro>