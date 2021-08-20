<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="constants.ForwardConst" %>
<%@ page import="constants.AttributeConst" %>


<c:set var="actRep" value="${ForwardConst.ACT_REP.getValue()}" />
<c:set var="commIdx" value="${ForwardConst.CMD_INDEX.getValue()}" />
<c:set var="commShow" value="${ForwardConst.CMD_SHOW.getValue()}" />
<c:set var="commNew" value="${ForwardConst.CMD_NEW.getValue()}" />

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
            <input type="radio" name="${AttributeConst.REP_LESSON_STYLE.getValue()}" value=""  checked="checked"> すべて
            <input type="radio" name="${AttributeConst.REP_LESSON_STYLE.getValue()}" value="${AttributeConst.GROUP.getIntegerValue()}" > 授業のみ
            <input type="radio" name="${AttributeConst.REP_LESSON_STYLE.getValue()}" value="${AttributeConst.PERSONAL.getIntegerValue()}"  > 個別のみ
            </p>

            <p>
            <input type="radio" name="${AttributeConst.REP_ORDER_BY.getValue()}" value="${AttributeConst.ORDER_BY_REP_DATE.getIntegerValue()}"  checked="checked"> 授業日時順
            <input type="radio" name="${AttributeConst.REP_ORDER_BY.getValue()}" value="${AttributeConst.ORDER_BY_UPDATE.getIntegerValue()}" > 最終更新日時順
            </p>


            <p><input type="submit" value="表示"></p>
        </form>

         <table id="report_list">
            <tbody>
                <tr>
                    <th class="report_date">日付</th>
                    <th class="report_name">講師名</th>
                    <th class="report_lesson_style">指導形態</th>
                    <th class="report_class_name">クラス,生徒名</th>
                    <th class="report_title">単元名</th>
                    <th class="report_action">操作</th>
                </tr>
                <c:forEach var="report" items="${reports}" varStatus="status">
                    <fmt:parseDate value="${report.reportDate}" pattern="MM/dd" var="reportDay" type="date" />

                    <tr class="row${status.count % 2}">
                        <td class="report_date"><fmt:formatDate value='${reportDay}' pattern='MM/dd' /></td>
                        <td class="report_name"><c:out value="${report.employee.name}" /></td>
                        <td class="report_lesson_style"><c:out value="${report.lessonStyle}" /></td>
                        <td class="report_class_name"><c:out value="${report.className}" /></td>
                        <td class="report_title"><c:out value="${report.title}" /></td>
                        <td class="report_action"><a href="<c:url value='?action=${actRep}&command=${commShow}&id=${report.id}' />">詳細を見る</a></td>
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
                        <a href="<c:url value='?action=${actRep}&command=${commIdx}&page=${i}' />"><c:out value="${i}" /></a>&nbsp;
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>

        <p><a href="<c:url value='?action=${actRep}&command=${commNew}&lessonStyle=${AttributeConst.GROUP.getIntegerValue()}' />">授業報告 新規作成</a></p>
        <p><a href="<c:url value='?action=${actRep}&command=${commNew}&lessonStyle=${AttributeConst.PERSONAL.getIntegerValue()}' />">個別報告 新規作成</a></p>


    </c:param>
</c:import>