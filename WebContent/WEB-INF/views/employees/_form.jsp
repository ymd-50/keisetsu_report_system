<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="constants.AttributeConst" %>
<%@ page import="constants.ForwardConst" %>

<c:set var="action" value="${ForwardConst.ACT_EMP.getValue()}" />
<c:set var="commIdx" value="${ForwardConst.CMD_INDEX.getValue()}" />

<c:if test="${errors != null}">
    <div id="flush_error">
        入力内容にエラーがあります。<br />
        <c:forEach var="error" items="${errors}">
            ・<c:out value="${error}" /><br />
        </c:forEach>

    </div>
</c:if>

<label for="${AttributeConst.EMP_NAME.getValue()}">氏名</label><br />
<input type="text" name="${AttributeConst.EMP_NAME.getValue()}" value="${employee.name}" />
<br /><br />

<label for="${AttributeConst.EMP_SUBJECT.getValue()}">担当科目</label><br />
<select name="${AttributeConst.EMP_SUBJECT.getValue()}">
    <option value="${AttributeConst.SUB_HUM.getIntegerValue()}"<c:if test="${employee.subject == AttributeConst.SUB_HUM.getIntegerValue()}"> selected</c:if>>文系</option>
    <option value="${AttributeConst.SUB_SCI.getIntegerValue()}"<c:if test="${employee.subject == AttributeConst.SUB_SCI.getIntegerValue()}"> selected</c:if>>理系</option>
</select>
<br /><br />

<label for="${AttributeConst.EMP_WORK_STYLE.getValue()}">勤務形態</label><br />
<select name="${AttributeConst.EMP_WORK_STYLE.getValue()}">
    <option value="${AttributeConst.FULL_TIME.getIntegerValue()}"<c:if test="${employee.workStyle == AttributeConst.FULL_TIME.getIntegerValue()}"> selected</c:if>>常勤</option>
    <option value="${AttributeConst.PART_TIME.getIntegerValue()}"<c:if test="${employee.workStyle == AttributeConst.PART_TIME.getIntegerValue()}"> selected</c:if>>非常勤</option>
</select>
<br /><br />

<label for="${AttributeConst.EMP_MAIL.getValue()}">メールアドレス</label><br />
<input type="email" name="${AttributeConst.EMP_MAIL.getValue()}" value="${employee.mailAddress}" />
<br /><br />

<label for="${AttributeConst.EMP_PASS.getValue()}">パスワード</label><br />
<input type="password" name="${AttributeConst.EMP_PASS.getValue()}" />
<br /><br />

<input type="hidden" name="${AttributeConst.EMP_ID.getValue()}" value="${employee.id}" />
<input type="hidden" name="${AttributeConst.TOKEN.getValue()}" value="${_token}" />
<button type="submit">登録</button>
