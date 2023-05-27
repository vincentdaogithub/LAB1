<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="main.css" />
        <title>Create</title>
    </head>
    <body>
        <header>
            <h1>Create</h1>
            <hr />

        </header>

        <main>
            <section>
                <a href="/LAB1/c?p=index">Back</a>
            </section>

            <section>
                <c:choose>
                    <c:when test="${sessionScope.user != null && sessionScope.user.role == 'AD'}">
                        <form class="form-normal" action="/LAB1/c?a=create&ct=ad" method="post">
                            <h2>Create User</h2>

                            <div class="input">
                                <label for="user-id">User ID</label>
                                <input id="user-id" type="text" name="txtUserID" pattern="(^US\d{4}$)|(^AD\d{4}$)" placeholder="format AD/US#### (ex. US1234)" required />
                            </div>

                            <div class="input">
                                <label for="full-name">Full name</label>
                                <input id="full-name" type="text" name="txtFullName" pattern="^.{0,40}$" placeholder="full name..." required />
                            </div>

                            <div class="input">
                                <label for="password">Password</label>
                                <input id="password" type="password" name="txtPassword" pattern="^.{0,20}$" placeholder="password..." required />
                            </div>

                            <div class="input">
                                <label for="role">Role</label>
                                <select id="role" name="txtRole">
                                    <option value="AD">AD</option>
                                    <option value="US">US</option>
                                </select>
                            </div>

                            <input type="submit" value="Create" />
                        </form>
                    </c:when>

                    <c:when test="${sessionScope.user == null}">
                        <form class="form-normal" action="/LAB1/c?a=create&ct=us" method="post">
                            <h2>Register User</h2>

                            <div class="input">
                                <label for="user-id">User ID</label>
                                <input id="user-id" type="text" name="txtUserID" pattern="^US\d{4}$" placeholder="format US#### (ex. US1234)" required />
                            </div>

                            <div class="input">
                                <label for="full-name">Full name</label>
                                <input id="full-name" type="text" name="txtFullName" pattern="^.{0,40}$" placeholder="full name..." required />
                            </div>

                            <div class="input">
                                <label for="password">Password</label>
                                <input id="password" type="password" name="txtPassword" pattern="^.{0,20}$" placeholder="password..." required />
                            </div>

                            <input type="submit" value="Create" />
                        </form>
                    </c:when>

                    <c:otherwise>
                        <h2>Why are you here?</h2>
                    </c:otherwise>
                </c:choose>
            </section>
        </main>
    </body>
</html>
