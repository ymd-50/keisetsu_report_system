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
        <link rel="stylesheet"  type="text/css" href="${pageContext.request.contextPath}/css/reset.css">
        <link rel="stylesheet"  type="text/css" href="${pageContext.request.contextPath}/css/style.css">
        <style type="text/css" >
            @CHARSET "UTF-8";

html{color:#000;background:#FFF}body,div,dl,dt,dd,ul,ol,li,h1,h2,h3,h4,h5,h6,pre,code,form,fieldset,legend,input,textarea,p,blockquote,th,td{margin:0;padding:0}table{border-collapse:collapse;border-spacing:0}fieldset,img{border:0}address,caption,cite,code,dfn,em,strong,th,var{font-style:normal;font-weight:normal}ol,ul{list-style:none}caption,th{text-align:left}h1,h2,h3,h4,h5,h6{font-size:100%;font-weight:normal}q:before,q:after{content:''}abbr,acronym{border:0;font-variant:normal}sup{vertical-align:text-top}sub{vertical-align:text-bottom}input,textarea,select{font-family:inherit;font-size:inherit;font-weight:inherit;*font-size:100%}legend{color:#000}#yui3-css-stamp.cssreset{display:none}


body {
    color: #333333;
    font-family: "Hiragino Kaku Gothic Pro",Meiryo,"MS PGothic",Helvetica,Arial,sans-serif;
}

a{
    text-decoration: none;
}

#header {
    position:fixed;
    top: 0px;
    color: #ffffff;
    width: 100%;
    height: 110px;
    background-color: #4169e1;
    border-bottom: solid 3px #ffffff;
    display: flex;
    z-index: 5;
}

#header a{
    color: #ffffff;
}

#content {
    width: 94%;
    margin-top: 15px;
    padding-left: 3%;
}

#content {
    width: 94%;
    margin-top: 15px;
    padding-left: 3%;
}

h1 {
    font-size: 24px;
    display: inline;
}

h2 {
    font-size: 36px;
    margin-bottom: 15px;
}

li {
    margin-top: 10px;
    margin-bottom: 10px;
}

p {
    margin-top: 15px;
    margin-bottom: 15px;
}

a {
    text-decoration: none;
    color: #24738e;
}

table, tr, th, td {
    border: 1px solid #cccccc;
}

table {
    width: 100%;
    table-layout: fixed;
}

th {
    width: 26%;
    padding: 10px 2%;
}

td {
    width: 66%;
    padding: 10px 2%;
}

button {
    font-size: 14px;
    padding: 5px 10px;
}

#flush_success{
    width:100%;
    padding-top:28px;
    padding-left:2%;
    padding-bottom:28px;
    margin-bottom:15px;
    color:#31708f;
    background-color:#d9edf7;
}

#flush_error{
    width:100%;
    padding-top:28px;
    padding-left:2%;
    padding-bottom:28px;
    margin-bottom:15px;
    color:#721c24;
    background-color:#f8d7da;
}

th{
    background-color: #4169e1;
    color: #ffffff;
}

tr.row1{
    background-color: #b0c4de;
}

tr.row0 {
        background-color: #f0f8ff;
}

pre {
    font-family: "Hiragino Kaku Gothic Pro",Meiryo,"MS PGothic",Helvetica,Arial,sans-serif;
    white-space: pre-wrap ;
}

#header_menu {
    width: 100%;
    padding-top: 17px;
    padding-left: 3%;

}

#header_menu h1{
    display: inline-block;
    font-size: 30px;
}

#employee_name {
    position:fixed;
    top: 33px;
    right: 20px;
    font-size: 17px;
    display: inline-block;
}

#full_or_part{
    position:fixed;
    top: 60px;
    display: inline-block;

}

#menu_var{
    position:fixed;
    top: 60px;
    display: inline-block;
}

#sort_menu{
    padding: 2px 15px;
    background-color: #b0c4de;
}

#title{
    font-size:17px;
}

.btn_blue, button{
    appearance: none;
  border: 0;
  border-radius: 5px;
  background: #4676D7;
  color: #fff;
  padding: 8px 16px;
  font-size: 16px;
}

th.report_date{
    width:120px;
}

th.report_action{
    width:180px;
}

th.report_lesson_style{
    width:120px;
    white-space: pre;
}

td.report_action, td.report_date{
    padding:10px 20px;
}

.point{
    color: #da523a;
    font-size:15px;
}

#comment{
    position: relative;
  margin: 1.5em 0 1.5em 15px;
  padding: 7px 10px;
  min-width: 120px;
  max-width: 100%;
  color: #555;
  font-size: 16px;
  background: #FFF;
  border: solid 2px #555;
  box-sizing: border-box;
}

#comment:before{
    content: "";
  position: absolute;
  top: 50%;
  left: -24px;
  margin-top: -12px;
  border: 12px solid transparent;
  border-right: 12px solid #FFF;
  z-index: 2;
}

#comment:after{
    content: "";
  position: absolute;
  top: 50%;
  left: -30px;
  margin-top: -14px;
  border: 14px solid transparent;
  border-right: 14px solid #555;
  z-index: 1;
}

#comment_index button{
    margin: 0px 10px;
}

#comment_name{
    font-size:18px;
    font-weight:bolder;
}

#pagination{
    padding-top:20px;
    font-size:17px;
}

        </style>
    </head>
    <body>

        <div id="wrapper">
            <div id="header">
                <div id="header_menu">

                <h1><a href="<c:url value='/?action=${actRep}&command=${commIdx}' />">蛍雪ゼミナール 指導報告 管理</a></h1>

                <c:if test="${sessionScope.login_employee != null}">
                    <div id="employee_name">
                        <c:out value="${sessionScope.login_employee.name}" />
                        &nbsp;さん&nbsp;&nbsp;&nbsp;
                        <a href="<c:url value='?action=${actAuth}&command=${commOut}' />">ログアウト</a>
                    </div>
                </c:if>
                <br>


                <c:if test="${sessionScope.login_employee != null}">
                    <div id="full_or_part">
                        <c:choose>
                            <c:when test="${sessionScope.login_employee.workStyle == AttributeConst.FULL_TIME.getIntegerValue()}">
                                常勤講師ページ&nbsp;&nbsp;&nbsp;&nbsp;
                            </c:when>
                            <c:otherwise>
                                非常勤講師ページ&nbsp;&nbsp;&nbsp;&nbsp;
                            </c:otherwise>
                        </c:choose>

                        <c:if test="${sessionScope.login_employee.workStyle == AttributeConst.FULL_TIME.getIntegerValue()}">
                            <a href="<c:url value='?action=${actEmp}&command=${commIdx}' />">▼講師 一覧</a>&nbsp;
                        </c:if>
                        <a href="<c:url value='?action=${actRep}&command=${commIdx}' />">▼指導報告 一覧</a>&nbsp;
                    </div>
                </c:if>


            </div>
            </div>
            <div id="content">
                <br><br><br><br><br><br>
                ${param.content}
            </div>
            <div id="footer">
                <br>
                <br>
            </div>
        </div>
    </body>
</html>
