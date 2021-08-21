<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="constants.AttributeConst" %>

<c:if test="${errors != null}">
    <div id="flush_error">
        入力内容にエラーがあります。<br />
        ・<c:out value="${errors}" /><br />
    </div>
</c:if>

<label for="${AttributeConst.COM_CONTENT.getValue()}">コメント</label><br />
<textarea name="${AttributeConst.COM_CONTENT.getValue()}" rows="10" cols="50">${comment.content}</textarea>
<br /><br />

<input type="hidden" name="${AttributeConst.TOKEN.getValue()}" value="${_token}" />
<button type="submit">投稿</button>