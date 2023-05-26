<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create</title>
    </head>
    <body>
        <header>
            <h1>Create</h1>
            <hr />
        </header>

        <main>
            <c:choose>
                <c:when test="${sessionScope.user != null && sessionScope.user.role == 'AD'}">
                    <form action="/LAB1/c?a=create&ct=ad" method="post">
                        <div class="input-container">
                            <label for="user-id">User ID</label>
                            <input id="user-id" type="text" name="txtUserID" pattern="(^US\d{4}$)|(^AD\d{4}$)" placeholder="format AD/US#### (ex. US1234)" required />
                        </div>

                        <div class="input-container">
                            <label for="full-name">Full name</label>
                            <input id="full-name" type="text" name="txtFullName" pattern="^.{0,40}$" placeholder="full name..." required />
                        </div>

                        <div class="input-container">
                            <label for="password">Password</label>
                            <input id="password" type="password" name="txtPassword" pattern="^.{0,20}$" placeholder="password..." required />
                        </div>

                        <div class="input-container">
                            <label for="role">Role</label>
                            <select id="role" name="txtRole">
                                <option value="AD">AD</option>
                                <option value="US">US</option>
                            </select>
                        </div>

                        <input type="submit" value="Create" />
                    </form>
                </c:when>

                <c:when test="${sessionScope.user != null && sessionScope.user.role == 'US'}">
                    <form action="/LAB1/c?a=create&ct=us" method="post">
                        <div class="input-container">
                            <label for="user-id">User ID</label>
                            <input id="user-id" type="text" name="txtUserID" pattern="^US\d{4}$" placeholder="format US#### (ex. US1234)" required />
                        </div>

                        <div class="input-container">
                            <label for="full-name">Full name</label>
                            <input id="full-name" type="text" name="txtFullName" pattern="^.{0,40}$" placeholder="full name..." required />
                        </div>

                        <div class="input-container">
                            <label for="password">Password</label>
                            <input id="password" type="password" name="txtPassword" pattern="^.{0,20}$" placeholder="password..." required />
                        </div>

                        <input type="submit" value="Create" />
                    </form>
                </c:when>

                <c:otherwise>
                    <p>Why are you even here?</p>
                    <a href="/LAB1/c?a=create">Back to Index</a>
                </c:otherwise>
            </c:choose>
        </main>
    </body>
</html>
