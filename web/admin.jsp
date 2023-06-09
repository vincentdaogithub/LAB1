<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="main.css" />
        <title>Admin</title>
    </head>
    <body>
        <header>
            <h1 class="title">Admin</h1>
        </header>

        <main>
            <section>
                <a href="/LAB1/c?p=index">Back</a>
            </section>

            <c:choose>
                <c:when test="${sessionScope.user != null && sessionScope.user.role == 'AD'}">
                    <section>
                        <h2>Welcome, ${f:escapeXml(sessionScope.user.fullName)}</h2>
                    </section>

                    <section>
                        <form class="form-normal" action="/LAB1/c?a=search&p=admin" method="post">
                            <h2>Search</h2>

                            <div class="input">
                                <label for="full-name">Full name</label>
                                <input id="full-name" type="text" name="txtFullName" placeholder="full name..." required />
                            </div>

                            <input type="submit" value="Search" />
                        </form>

                        <a href="/LAB1/c?p=create">Create user</a>
                    </section>

                    <section>
                        <c:choose>
                            <c:when test="${empty requestScope.users}">
                                <h2>No user was found</h2>
                            </c:when>

                            <c:otherwise>
                                <table>
                                    <tr>
                                        <th>No.</th>
                                        <th>User ID</th>
                                        <th>Full Name</th>
                                        <th>Role</th>
                                        <th>Delete User</th>
                                        <th>Update User</th>
                                    </tr>

                                    <c:forEach items="${requestScope.users}" var="u" varStatus="i">
                                        <tr>
                                            <form action="/LAB1/c?a=update&uid=${f:escapeXml(u.userID)}&p=admin" method="post">
                                                <td>${i.count}</td>
                                                <td>${f:escapeXml(u.userID)}</td>
                                                <td>${f:escapeXml(u.fullName)}</td>
                                                <td>
                                                    <select name="txtRole">
                                                        <option value="AD" ${u.role == 'AD' ? 'selected' : ''}>AD</option>
                                                        <option value="US" ${u.role == 'US' ? 'selected' : ''}>US</option>
                                                    </select>
                                                </td>
                                                <td><a href="/LAB1/c?a=delete&uid=${f:escapeXml(u.userID)}&p=admin">Delete</a></td>
                                                <td><input type="submit" value="Update" /></td>
                                            </form>
                                        </tr>
                                    </c:forEach>
                                </table>
                            </c:otherwise>
                        </c:choose>
                </c:when>

                <c:otherwise>
                    <section>
                        <h2>Who are you?</h2>
                    </section>
                </c:otherwise>
            </c:choose>
        </main>
    </body>
</html>
