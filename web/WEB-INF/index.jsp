<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
            <form action="/LAB1/c?a=login">
                <h2>Login form</h2>

                <div class="input-container">
                    <label for="user-id">User ID</label>
                    <input id="user-id" type="text" placeholder="user id..." required />
                </div>

                <div class="input-container">
                    <label for="password">Password</label>
                    <input id="password" type="password" placeholder="password..." required />
                </div>

                <input type="submit" value="Log in" />
            </form>
        </main>
    </body>
</html>
