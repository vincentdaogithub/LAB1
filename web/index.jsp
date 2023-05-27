<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="main.css" />
        <title>Welcome</title>
    </head>
    <body>
        <header>
            <h1 class="title">Login</h1>
        </header>

        <main>
            <c:choose>
                <c:when test="${sessionScope.user != null && sessionScope.user.role == 'AD'}">
                    <section>
                        <a href="/LAB1/c?p=admin">To Admin</a>
                        <a href="/LAB1/c?a=logout">Log out</a>
                    </section>
                </c:when>

                <c:when test="${sessionScope.user != null && sessionScope.user.role == 'US'}">
                    <section>
                        <a href="/LAB1/c?p=user">To User</a>
                        <a href="/LAB1/c?a=logout">Log out</a>
                    </section>
                </c:when>

                <c:otherwise>
                    <section>
                        <form class="form-normal" action="/LAB1/c?a=login&p=index" method="post">
                            <h2>Login form</h2>
    
                            <div class="input">
                                <label for="user-id">User ID</label>
                                <input id="user-id" type="text" name="txtUserID" placeholder="user id..." required />
                            </div>
    
                            <div class="input">
                                <label for="password">Password</label>
                                <input id="password" type="password" name="txtPassword" placeholder="password..." required />
                            </div>
    
                            <input type="submit" value="Log in" />
                        </form>
    
                        <a href="/LAB1/c?p=create">Create new account</a>
                    </section>
                </c:otherwise>
            </c:choose>
        </main>
    </body>
</html>
