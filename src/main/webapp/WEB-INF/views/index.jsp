<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<html>
<head>
    <title>Index</title>
</head>

<style>
    .jumbotron {
        background-color: #51b2dc;
    }
</style>
<body>
<div class="jumbotron text-center">
    <h1>Welcome to the CINEMA</h1>
    <p>Your the best cinema!</p>
</div>
<button type="button" onclick="location.href='${pageContext.request.contextPath}/login'">Login</button>
<button type="button" onclick="location.href='${pageContext.request.contextPath}/register'">Registration</button>
<button type="button" onclick="location.href='${pageContext.request.contextPath}/logout'">Logout</button>

</body>
</html>
