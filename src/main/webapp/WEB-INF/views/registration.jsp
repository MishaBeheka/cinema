<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<html>
<head>
    <title>Registration</title>
</head>
<body>
<form class="form-group" action="${pageContext.request.contextPath}/register" style="text-align: center" method=post>
    <div class="container col-lg-4 border" style="border-radius: 20px">
        <h2 class="text-center">REGISTRATION</h2>
        <p class="text-center">Please fill in this form to create an account.</p>
        <hr>

        <div class="form-group">
            <label for="email"></label>
            <input type="text" class="form-control" placeholder="Enter email" name="email" id="email" required>
        </div>

        <div class="form-group">
            <label for="password"></label>
            <input type="password" class="form-control" placeholder="Enter Password" name="password" id="password" required>
        </div>

        <div class="form-group">
            <label for="repeatPassword"></label>
            <input type="password" class="form-control" placeholder="Repeat Password" name="repeatPassword" id="repeatPassword" required>
        </div>
        <hr>
        <button type="submit" class="btn btn-lg btn-primary btn-block">Register</button>

        <div class="container singin">
            <p>Already have an account? <a href="/login">Sign in</a>.</p>
        </div>
    </div>
</form>
</body>
</html>
