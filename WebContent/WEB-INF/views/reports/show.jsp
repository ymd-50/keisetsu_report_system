<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="constants.ForwardConst" %>
<%@ page import="constants.AttributeConst" %>

<c:set var="actRep" value="${ForwardConst.ACT_REP.getValue()}" />
<c:set var="commIdx" value="${ForwardConst.CMD_INDEX.getValue()}" />
<c:set var="commEdt" value="${ForwardConst.CMD_EDIT.getValue()}" />
<c:set var="commDel" value="${ForwardConst.CMD_DESTROY.getValue()}" />

<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:choose>
            <c:when test="${report.lessonStyle == AttributeConst.GROUP.getIntegerValue()}">
                <h2>授業報告 詳細</h2>
            </c:when>
            <c:otherwise>
                <h2>個別報告 詳細</h2>
            </c:otherwise>
        </c:choose>

        <table>
            <tbody>
                <tr>
                    <th>日付</th>
                    <fmt:parseDate value="${report.reportDate}" pattern="yyyy-MM-dd" var="reportDay" type="date" />
                    <td><fmt:formatDate value='${reportDay}' pattern='yyyy年 MM月 dd日' /></td>
                </tr>
                <tr>
                    <th>コマ数</th>
                    <td>
                        <c:choose>
                            <c:when test="${report.classNumber == AttributeConst.CLASS1.getIntegerValue()}">
                                1コマ目
                            </c:when>
                            <c:when test="${report.classNumber == AttributeConst.CLASS2.getIntegerValue()}">
                                2コマ目
                            </c:when>
                            <c:otherwise>
                                3コマ目
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
                <tr>
                    <th>学年</th>
                    <td>
                        <c:choose>
                            <c:when test="${report.grade == AttributeConst.GRADE1.getIntegerValue()}">
                                高1
                            </c:when>
                            <c:when test="${report.grade == AttributeConst.GRADE2.getIntegerValue()}">
                                高2
                            </c:when>
                            <c:otherwise>
                                高3
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
                <tr>
                    <c:choose>
                        <c:when test="${report.lessonStyle == AttributeConst.GROUP.getIntegerValue()}">
                            <th>クラス名</th>
                        </c:when>
                        <c:otherwise>
                            <th>学校名 / 生徒名</th>
                        </c:otherwise>
                    </c:choose>
                    <td><c:out value="${report.className}"/></td>
                </tr>
                <tr>
                    <th>教科</th>
                    <td><c:out value="${report.employee.subject}" /></td>
                </tr>
                <tr>
                    <th>単元名・問題番号</th>
                    <td><c:out value="${report.title}" /></td>
                </tr>
                <tr>
                    <th>指導内容</th>
                    <td><pre><c:out value="${report.content}" /></pre></td>
                </tr>
                <c:if test="${report.lessonStyle == AttributeConst.GROUP.getIntegerValue()}">
                    <tr>
                        <th>欠席者</th>
                        <td><pre><c:out value="${report.absentee}" /></pre></td>
                    </tr>
               </c:if>
            </tbody>
        </table>
        <p>
            <fmt:parseDate value="${report.updatedAt}" pattern="yyyy-MM-dd'T'HH:mm:ss" var="updatedDay" type="date" />
            最終更新日時：<fmt:formatDate value="${updatedDay}" pattern="yyyy-MM-dd HH:mm" />
        </p>

        <c:if test="${sessionScope.login_employee.id == report.employee.id}">
            <p>
                <a href="<c:url value='?action=${actRep}&command=${commEdt}&id=${report.id}' />">この日報を編集する</a>
            </p>


        </c:if>

        <p>
            <a href="<c:url value='?action=${actRep}&command=${commIdx}' />">一覧に戻る</a>
        </p>
    </c:param>
</c:import>
