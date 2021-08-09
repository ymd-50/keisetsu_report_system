<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
                <h1>蛍雪ゼミナール 指導報告</h1>
            </div>
            <div id="content">
                ${param.content}
            </div>
            <div id="footer">

            </div>
        </div>
    </body>
</html>
