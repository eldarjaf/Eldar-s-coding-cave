<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>

    <title>Register User</title>

    <style>
        .error{color:red}
    </style>

</head>


<body>

<form action="registerUser" method="post">
    <pre>
<h2>User Registration:</h2>
    First Name: <input type="text" name="firstName"> <form:errors path="firstName" cssClass="error"/>
    Last Name: <input type="text" name="lastName"> <form:errors path="lastName" cssClass="error"/>
    User Name: <input type="text" name="email">
    Password: <input type="password" name="password">
    Confirm Password: <input type="password" name="confirmPassword">
    <input type="submit" value="register">

    </pre>
</form>


</body>
</html>