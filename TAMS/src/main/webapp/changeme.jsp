<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Authentication</title>
    <style>
        body {
            margin: 0;
            font-family: 'Arial', sans-serif;
            color: #333;
            background: url('images/01.jpg') no-repeat center center/cover;
        }
        .container {
            width: 100%;
            max-width: 400px;
            margin: 50px auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        h2 {
            text-align: center;
            color: #333;
        }
        form {
            display: flex;
            flex-direction: column;
        }
        label {
            margin-bottom: 5px;
            font-weight: bold;
        }
        input {
            margin-bottom: 15px;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        button {
            padding: 10px;
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        button:hover {
            background-color: #0056b3;
        }
        .login-link {
            text-align: center;
            margin-top: 10px;
        }
        .login-link a {
            color: #007bff;
            text-decoration: none;
        }
        .login-link a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>

    <div class="container" align="center">
        <h1>UserSignup</h1>
        <form action="UsersServlet" method="POST">
            <input type="hidden" name="action" value="change">
            <label for="firstname">First name:</label>
            <input type="text" id="firstname" name="firstname" >
            
            <label for="username">Last name:</label>
            <input type="text" id="lastname" name="lastname" >

            <label for="password">newPassword:</label>
            <input type="password" id="newPassword" name="password" >

            <label for="email">Email:</label>
            <input type="email" id="email" name="email" >

            <button type="submit">Change</button>
        </form>
        
    </div>

</body>
</html>