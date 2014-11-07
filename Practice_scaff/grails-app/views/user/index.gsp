<html>
	<head>
		<meta name="layout" content="main"/>
</head>
<body>
<g:render template="/user/loginBox"/>
<script>
$(function() {
$('#loginForm').ajaxForm(function(result) {
$('#loginBox').html(result);
});
});
</script>


<g:if test="${album?.year < 1980 && album?.genre == 'Rock'}">
Classic rock
</g:if>
<g:elseif test="${album?.year >= 1980 && album?.genre == 'Rock'}">
Modern Rock
</g:elseif>
<g:else>
Other
</g:else>
<g:each in="${album?.songs?}">
<span class="tag">${it.title}</span>
</g:each>
it is ${albums}
<ol>
<g:collect in="${albums}" expr="${it}">
<li>${it.name}</li>
</g:collect>
</ol>

<ol>
<g:each in="${albums.name}" >
<li>${it}</li>
</g:each>
</ol>


<g:findAll in="${albums}" expr="${it.name?.contains('Raman')}">
<li>${it.name}</li>
</g:findAll>

<g:link controller="album" action="list">list Albums</g:link>
<g:link action="register" >Show album with id </g:link>


<link rel="stylesheet"
href="${resource(dir:'css',file:'main.css')}"></link>

<g:form action="register" name="registerForm">
...
<g:textField name="login" value="${user?.login}"></g:textField>
...
</g:form>

<p>
<g:radio name="myGroup" value="1" checked="${someValue == 1}" /> Radio 1 </p>
<p>
<g:radio name="myGroup" value="2" checked="${someValue == 2}" /> Radio 2 </p>


<g:select name="genre"
from="${['Rock', 'Blues', 'Jazz']}"
value="${albums}" />
The following is the resulting HTML select, given an album with a genre of Rock:
<select name="genre">
<option value="Rock" selected="selected">Rock</option>
<option value="Blues">Blues</option>
<option value="Jazz">Jazz</option>
</select>


<g:datePicker name="myDate" value="${new Date()}" />




<g:hasErrors bean="${album}">
<ul class="errors">
<g:eachError bean="${album}">
<li>${it.defaultMessage}</li>
</g:eachError>
</ul>
</g:hasErrors>


<div id="artists">
<g:render template="/album/form"/>
</div>

<div id="navButtons">
<ul>
<li><a href="#"><g:message code="gtunes.my.music"/></a></li>
<li><g:link controller="store" action="shop">
<g:message code="gtunes.the.store"/>
</g:link>
</li>
</ul>
</div>


<g:repeat times="3">
Hello number ${it}
</g:repeat>




</body>
</html>