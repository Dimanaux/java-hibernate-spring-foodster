<#include "header.ftl">

<#macro title></#macro>
<#macro imports></#macro>

<#macro content></#macro>

<#macro page>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title><@title></@title></title>
    <script src="https://code.jquery.com/jquery-3.3.1.min.js" defer></script>

    <link href="https://fonts.googleapis.com/css?family=Open+Sans" rel="stylesheet">
    <link href="/static/css/about.css" rel="stylesheet">

    <script src="/static/js/goto.js" defer></script>
    <script src="/static/js/script.js" defer></script>

    <@imports></@imports>
</head>
<body>
    <@header></@header>
    <main>
        <@content></@content>
    </main>
</body>
</html>
</#macro>
