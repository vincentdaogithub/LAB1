<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User</title>
    </head>
    <body>
        <header>
            <h1>User</h1>
            <hr />

            <a href="/LAB1/c?p=index">Back</a>
        </header>

        <main>
            <c:choose>
                <c:when test="${sessionScope.user != null && sessionScope.user.role == 'US'}">
                    <h2>Welcome, ${f:escapeXml(sessionScope.user.fullName)}</h2>
                </c:when>

                <c:otherwise>
                    <h2>Who are you?</h2>
                </c:otherwise>
            </c:choose>
        </main>
    </body>
</html>
