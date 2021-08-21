<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="constants.ForwardConst" %>
<%@ page import="constants.AttributeConst" %>

<c:set var="actRep" value="${ForwardConst.ACT_REP.getValue()}" />
<c:set var="commUpd" value="${ForwardConst.CMD_UPDATE.getValue()}" />
<c:set var="commIdx" value="${ForwardConst.CMD_INDEX.getValue()}" />
<c:set var="commDel" value="${ForwardConst.CMD_DESTROY.getValue()}" />

<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">

        <c:choose>
            <c:when test="${report.lessonStyle == AttributeConst.GROUP.getIntegerValue()}">
                <h2>授業報告 編集</h2>
            </c:when>
            <c:otherwise>
                <h2>個別報告 編集</h2>
            </c:otherwise>
        </c:choose>
        <form method="POST" action="<c:url value='?action=${actRep}&command=${commUpd}' />">
            <c:import url="_form.jsp" />
        </form>

            <p>
                <a href="#" onclick="confirmDestroy();">この日報を削除する</a>
            </p>
            <form method="POST" action="<c:url value='?action=${actRep}&command=${commDel}&id=${report.id}' />">
                <input type="hidden" name="${AttributeConst.REP_ID.getValue()}" value="${report.id}" />
                <input type="hidden" name="${AttributeConst.TOKEN.getValue()}" value="${_token}" />
            </form>
            <script>
                function confirmDestroy() {
                    if (confirm("本当に削除してよろしいですか？")) {
                        document.forms[1].submit();
                    }
                }
            </script>

        <p>
            <a href="<c:url value='?action=${actRep}&command=${commIdx}' />">一覧に戻る</a>
        </p>
    </c:param>
</c:import>