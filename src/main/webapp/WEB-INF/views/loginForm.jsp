<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">

<html>
<head>
    <title>Login Form</title>
</head>

<style>
    .text-center {
        margin-top: revert;
    }
</style>

<body>
<form action="${pageContext.request.contextPath}/login" style="text-align: center" method="post">
    <div class="container col-lg-4 border" style="border-radius: 20px; margin-top: 130px">
        <h1 class="text-center">LOGIN</h1>
        <p class="text-center">Please fill in this form to sign into account.</p>
        <hr>

        <div class="form-group">
            <input type="text" class="form-control" placeholder="Enter email" name="email" id="email" required>
        </div>

        <div class="form-group">
            <input type="password" class="form-control" placeholder="Enter password" name="password" id="password" required>
        </div>

        <hr>
        <button type="submit" class="registerbtn btn-primary btn-lg" style="text-align: center">Login</button>
        <hr>

        <div class="container signin">
            <p>Don't have an account? <a href="/register">Sign up</a>.</p>
        </div>
    </div>
</form>
</body>
</html>
