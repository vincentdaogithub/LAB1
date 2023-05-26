<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome</title>
    </head>
    <body>
        <header>
            <h1>Login</h1>
        </header>

        <main>
            <c:choose>
                <c:when test="${sessionScope.user != null && sessionScope.user.role == 'AD'}">
                    <a href="index.jsp">To Admin</a>
                </c:when>

                <c:when test="${sessionScope.user != null && sessionScope.user.role == 'US'}">
                    <a href="admin.jsp">To User</a>
                </c:when>

                <c:otherwise>
                    <form action="/LAB1/c?a=login&p=index" method="post">
                        <h2>Login form</h2>

                        <div class="input-container">
                            <label for="user-id">User ID</label>
                            <input id="user-id" type="text" name="txtUserID" placeholder="user id..." required />
                        </div>

                        <div class="input-container">
                            <label for="password">Password</label>
                            <input id="password" type="password" name="txtPassword" placeholder="password..." required />
                        </div>

                        <input type="submit" value="Log in" />
                    </form>
                </c:otherwise>
            </c:choose>
        </main>
    </body>
</html>
