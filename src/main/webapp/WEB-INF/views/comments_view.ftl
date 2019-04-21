<#-- @ftlvariable name="comments" type="java.util.List" -->
<#macro comments_view>
    <#list comments as comment>
        <h3>
            <#if comment.author.name?? >
                ${comment.author.name}
            <#else>
                ${comment.author.username}
            </#if>
        </h3>
        <p>
            ${comment.text}
        </p>
        <small>
            ${comment.date}
        </small>
    </#list>
</#macro>