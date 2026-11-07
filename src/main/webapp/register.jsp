<!DOCTYPE html>
<html>
<head>
    <title>User Registration</title>
</head>
<body>

<h2>User Registration</h2>

<p style="color:red">${error}</p>

<form action="register" method="post">

    Username:
    <input type="text" name="username" required>
    <br><br>

    Email:
    <input type="email" name="email" required>
    <br><br>

    Phone:
    <input type="text" name="phone" required>
    <br><br>

    <button type="submit">Register</button>

</form>

</body>
</html>