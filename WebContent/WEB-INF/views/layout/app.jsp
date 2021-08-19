<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="constants.ForwardConst" %>
<%@ page import="constants.AttributeConst" %>

<c:set var="actTop" value="${ForwardConst.ACT_TOP.getValue()}" />
<c:set var="actEmp" value="${ForwardConst.ACT_EMP.getValue()}" />
<c:set var="actRep" value="${ForwardConst.ACT_REP.getValue()}" />
<c:set var="actAuth" value="${ForwardConst.ACT_AUTH.getValue()}" />

<c:set var="commIdx" value="${ForwardConst.CMD_INDEX.getValue()}" />
<c:set var="commOut" value="${ForwardConst.CMD_LOGOUT.getValue()}" />

<!DOCTYPE html>
<html lang="ja">
    <head>
        <meta charset="UTF-8">
        <title>指導報告システム</title>
        <link rel="stylesheet" href="<c:url value='/css/reset.css' />">
        <link rel="stylesheet" href="<c:url value='/css/style.css' />">
    </head>
    <body>
        <div>
            <div id="header">
                <div id="header_menu">
                <h1><a href="<c:url value='/?action=${actRep}&command=${commIdx}' />">蛍雪ゼミナール 指導報告 管理</a></h1>&nbsp;&nbsp;&nbsp;

                <c:if test="${sessionScope.login_employee != null}">
                    <c:choose>
                        <c:when test="${sessionScope.login_employee.workStyle == AttributeConst.FULL_TIME.getIntegerValue()}">
                            <p>常勤講師ページ</p>
                        </c:when>
                        <c:otherwise>
                            <p>非常勤講師ページ</p>
                        </c:otherwise>
                    </c:choose>
                    <c:if test="${sessionScope.login_employee.workStyle == AttributeConst.FULL_TIME.getIntegerValue()}">
                        <a href="<c:url value='?action=${actEmp}&command=${commIdx}' />">▼講師 一覧</a>&nbsp;
                    </c:if>
                    <a href="<c:url value='?action=${actRep}&command=${commIdx}' />">▼指導報告 一覧</a>&nbsp;
                </c:if>

                <c:if test="${sessionScope.login_employee != null}">
                <div id="employee_name">
                    <c:out value="${sessionScope.login_employee.name}" />
                    &nbsp;さん&nbsp;&nbsp;&nbsp;
                    <a href="<c:url value='?action=${actAuth}&command=${commOut}' />">ログアウト</a>
                </div>
            </c:if>
            </div>
            </div>
            <div id="content">
                ${param.content}
            </div>
            <div id="footer">

            </div>
        </div>
    </body>
</html>
