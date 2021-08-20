<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="constants.AttributeConst" %>

<c:if test="${errors != null}">
    <div id="flush_error">
        入力内容にエラーがあります。<br />
        <c:forEach var="error" items="${errors}">
            ・<c:out value="${error}" /><br />
        </c:forEach>

    </div>
</c:if>

<fmt:parseDate value="${report.reportDate}" pattern="yyyy-MM-dd" var="reportDay" type="date" />
<label for="${AttributeConst.REP_DATE.getValue()}">日付</label><br />
<input type="date" name="${AttributeConst.REP_DATE.getValue()}" value="<fmt:formatDate value='${reportDay}' pattern='yyyy-MM-dd' />" />
<br /><br />

<p>学年<br />
<select name="${AttributeConst.REP_GRADE.getValue()}" size="1">
    <option value="${AttributeConst.GRADE1.getIntegerValue()}">高校1年</option>
    <option value="${AttributeConst.GRADE2.getIntegerValue()}">高校2年</option>
    <option value="${AttributeConst.GRADE3.getIntegerValue()}">高校3年</option>
</select></p>
<br /><br />

<c:choose>
    <c:when test="${report.lessonStyle == AttributeConst.GROUP.getIntegerValue()}">
        <label for="${AttributeConst.REP_GRADE.getValue()}">クラス名</label><br />
        <input type="text" name="${AttributeConst.REP_CLASS_NAME.getValue()}" value="${report.grade}" />
        <br /><br />
    </c:when>
    <c:otherwise>
        <label for="${AttributeConst.REP_GRADE.getValue()}">学校名 / 生徒名</label><br />
        <input type="text" name="school" value="" />
        &nbsp;/&nbsp;
        <input type="text" name="student" value="" />
        <input type="hidden" name="${AttributeConst.REP_CLASS_NAME.getValue()}" value="${school} / ${student}" />
        <br /><br />
    </c:otherwise>
</c:choose>

<label for="${AttributeConst.REP_GRADE.getValue()}">教科</label><br />
<input type="text" name="${AttributeConst.REP_SUBJECT.getValue()}" value="${report.grade}" />
<br /><br />


<label for="${AttributeConst.REP_TITLE.getValue()}">単元・問題番号</label><br />
<input type="text" name="${AttributeConst.REP_TITLE.getValue()}" value="${report.title}" />
<br /><br />

<label for="${AttributeConst.REP_CONTENT.getValue()}">内容</label><br />
<textarea name="${AttributeConst.REP_CONTENT.getValue()}" rows="10" cols="50">${report.content}</textarea>
<br /><br />

<label for="${AttributeConst.REP_CONTENT.getValue()}">欠席者</label><br />
<textarea name="${AttributeConst.REP_ABSENTEE.getValue()}" rows="10" cols="50"><c:out value="${report.content}"/></textarea>
<br /><br />

<input type="hidden" name="${AttributeConst.REP_ID.getValue()}" value="${report.id}" />
<input type="hidden" name="${AttributeConst.TOKEN.getValue()}" value="${_token}" />
<button type="submit">投稿</button>
