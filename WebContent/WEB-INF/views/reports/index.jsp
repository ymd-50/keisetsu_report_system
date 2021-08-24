<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="constants.ForwardConst" %>
<%@ page import="constants.AttributeConst" %>


<c:set var="actRep" value="${ForwardConst.ACT_REP.getValue()}" />
<c:set var="commIdx" value="${ForwardConst.CMD_INDEX.getValue()}" />
<c:set var="commShow" value="${ForwardConst.CMD_SHOW.getValue()}" />
<c:set var="commNew" value="${ForwardConst.CMD_NEW.getValue()}" />
<c:set var="commUnread" value="${ForwardConst.CMD_UNREAD.getValue()}" />

<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>

        <h2>指導報告 一覧</h2>

        <form method="post" action="<c:url value='?action=${actRep}&command=${commIdx}' />">
            <p>絞り込み<br>
            <input type="radio" name="${AttributeConst.REP_LESSON_STYLE.getValue()}" value=""
                <c:if test="${lesson_style != AttributeConst.GROUP.getIntegerValue() && lesson_style != AttributeConst.PERSONAL.getIntegerValue()}">checked="checked"</c:if>
                > すべて
            <input type="radio" name="${AttributeConst.REP_LESSON_STYLE.getValue()}" value="${AttributeConst.GROUP.getIntegerValue()}"
                <c:if test="${lesson_style == AttributeConst.GROUP.getIntegerValue()}">checked="checked"</c:if>
                > 授業のみ
            <input type="radio" name="${AttributeConst.REP_LESSON_STYLE.getValue()}" value="${AttributeConst.PERSONAL.getIntegerValue()}"
                <c:if test="${lesson_style == AttributeConst.PERSONAL.getIntegerValue()}">checked="checked"</c:if>
                > 個別のみ
            </p>

            <p>
            <input type="radio" name="${AttributeConst.REP_ORDER_BY.getValue()}" value="${AttributeConst.ORDER_BY_REP_DATE.getIntegerValue()}"
            <c:if test="${order_by != AttributeConst.ORDER_BY_UPDATE.getIntegerValue()}">checked="checked"</c:if>
            > 授業日時順
            <input type="radio" name="${AttributeConst.REP_ORDER_BY.getValue()}" value="${AttributeConst.ORDER_BY_UPDATE.getIntegerValue()}"
            <c:if test="${order_by == AttributeConst.ORDER_BY_UPDATE.getIntegerValue()}">checked="checked"</c:if>
            > 最終更新日時順
            </p>


            <p><input type="submit" value="表示"></p>
        </form>

         <table id="report_list">
            <tbody>
                <tr>
                    <th class="report_date">日付</th>
                    <c:if test="${sessionScope.login_employee.workStyle == AttributeConst.FULL_TIME.getIntegerValue()}">
                        <th class="report_name">講師名</th>
                    </c:if>
                    <th class="report_lesson_style">指導形態</th>
                    <th class="report_class_name">クラス,生徒名</th>
                    <th class="report_title">単元名</th>
                    <th class="report_action">操作</th>
                </tr>
                <c:forEach var="report" items="${reports}" varStatus="status">
                    <fmt:parseDate value="${report.reportDate}" pattern="yyyy-MM-dd" var="reportDay" type="date" />

                    <tr class="row${status.count % 2}">
                        <td class="report_date">
                            <c:choose>
                                <c:when test="${report.readFlag == AttributeConst.UNREAD.getIntegerValue()}">
                                    ●
                                </c:when>
                                <c:otherwise>
                                    &emsp;
                                </c:otherwise>
                            </c:choose>
                            <fmt:formatDate value='${reportDay}' pattern='MM/dd' />
                        </td>
                        <c:if test="${sessionScope.login_employee.workStyle == AttributeConst.FULL_TIME.getIntegerValue()}">
                            <td class="report_name"><c:out value="${report.employee.name}" /></td>
                        </c:if>
                        <td class="report_lesson_style">
                            <c:choose>
                                <c:when test="${report.lessonStyle == AttributeConst.GROUP.getIntegerValue()}">
                                    授業
                                </c:when>
                                <c:otherwise>
                                    個別
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td class="report_class_name"><c:out value="${report.className}" /></td>
                        <td class="report_title"><c:out value="${report.title}" /></td>
                        <td class="report_action">
                            <a href="<c:url value='?action=${actRep}&command=${commShow}&id=${report.id}' />">詳細を見る</a>
                            <c:if test="${sessionScope.login_employee.id != report.employee.id && report.readFlag == AttributeConst.READ.getIntegerValue()}">
                                <a href="<c:url value='?action=${actRep}&command=${commUnread}&id=${report.id}' />">未読にする</a>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
         </table>

        <div id="pagination">
            （全 ${reports_count} 件）<br />
            <c:forEach var="i" begin="1" end="${((reports_count - 1) / maxRow) + 1}" step="1">
                <c:choose>
                    <c:when test="${i == page}">
                        <c:out value="${i}" />&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a href="<c:url value='?action=${actRep}&command=${commIdx}&page=${i}&order_by=${order_by}&lesson_style=${lesson_style}' />"><c:out value="${i}" /></a>&nbsp;
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>

        <c:choose>
            <c:when test="${sessionScope.login_employee.workStyle == AttributeConst.FULL_TIME.getIntegerValue()}">

            </c:when>
            <c:otherwise>
                <p><a href="<c:url value='?action=${actRep}&command=${commNew}&lessonStyle=${AttributeConst.GROUP.getIntegerValue()}' />">授業報告 新規作成</a></p>
                <p><a href="<c:url value='?action=${actRep}&command=${commNew}&lessonStyle=${AttributeConst.PERSONAL.getIntegerValue()}' />">個別報告 新規作成</a></p>
            </c:otherwise>
        </c:choose>

    </c:param>
</c:import>

