<%@ page import="com.test.Album" %>



<div class="fieldcontain ${hasErrors(bean: albumInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="album.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${albumInstance?.name}"/>

</div>

