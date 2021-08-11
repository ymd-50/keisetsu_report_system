<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="constants.ForwardConst" %>
<%@ page import="constants.AttributeConst" %>

<c:set var="actEmp" value="${ForwardConst.ACT_EMP.getValue()}" />
<c:set var="commIdx" value="${ForwardConst.CMD_INDEX.getValue()}" />
<c:set var="commEdit" value="${ForwardConst.CMD_EDIT.getValue()}" />

<c:import url="../layout/app.jsp">
    <c:param name="content">
        <h2>講師 詳細</h2>

        <table>
            <tbody>
                <tr>
                    <th>氏名</th>
                    <td><c:out value="${employee.name}"></c:out></td>
                </tr>
                <tr>
                    <th>勤務</th>
                    <td>
                        <c:choose>
                            <c:when test="${employee.workStyle == AttributeConst.FULL_TIME.getIntegerValue()}">
                                常勤
                            </c:when>
                            <c:otherwise>
                                非常勤
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
                <tr>
                    <th>担当科目</th>
                    <td>
                        <c:choose>
                            <c:when test="${employee.subject == AttributeConst.SUB_HUM.getIntegerValue()}">
                                文系
                            </c:when>
                            <c:otherwise>
                                理系
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
                <tr>
                    <th>メールアドレス</th>
                    <td><c:out value="${employee.mailAddress}"/></td>
                </tr>
            </tbody>
        </table>
        <p>
            最終更新日時：
            <fmt:parseDate value="${employee.updatedAt}" pattern="yyyy-MM-dd'T'HH:mm:ss" var="updateDay" type="date" />
            <fmt:formatDate value="${updateDay}" pattern="yyyy-MM-dd HH:mm" />
        </p>

        <p>
            <a href="<c:url value='?action=${actEmp}&command=${commEdit}&id=${employee.id}' />">この講師を編集する</a>
        </p>

        <p>
            <a href="<c:url value='?action=${actEmp}&command=${commIdx}' />">講師一覧に戻る</a>
        </p>
    </c:param>
</c:import>