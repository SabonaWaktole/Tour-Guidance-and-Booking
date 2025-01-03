<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>BOKKUU</title>
    <style>
        body {
            margin: 0;
            font-family: 'Arial', sans-serif;
            color: #333;
            background: url('images/bkgrnd.jpg') no-repeat center center/cover;
        }

        .hero {
            height: 100vh;
            background: url('images/bkgrnd.jpg') no-repeat center center/cover;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            text-align: center;
            color: white;
        }

        .hero h1 {
            font-size: 3rem;
            margin-bottom: 20px;
            text-shadow: 2px 2px 5px rgba(0, 0, 0, 0.7);
        }

        .hero p {
            font-size: 1.2rem;
            margin-bottom: 30px;
            text-shadow: 2px 2px 5px rgba(0, 0, 0, 0.5);
        }

        .gallery {
            display: grid;
            grid-template-columns: repeat(3, 1fr); /* 3 columns with equal width */
            gap: 15px; /* Space between the images */
            padding: 20px;
            justify-items: center; /* Center images in their grid cells */
        }

        .gallery img {
            width: 100%; /* Make images responsive within their grid cells */
            max-width: 300px; /* Optional: Limit the maximum width of images */
            height: auto; /* Maintain aspect ratio */
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
            transition: transform 0.3s;
        }

        .gallery img:hover {
            transform: scale(1.5); /* Slight zoom on hover */
        }
        footer {
            text-align: center;
            padding: 20px;
            background-color: #333;
            color: white;
        }
    </style>
</head>
<body>
    <!-- Hero Section -->
    <div class="hero">
        <h1>Your booking is successful! </h1>
        <h2>Thank you for choosing us!</h2>
        <p>BOKKUUYour one-stop solution for booking and payments!</p>
        
    </div>

    <!-- Gallery Section -->
    <div class="gallery">
        <img src="images/00.jpg">
        <img src="images/01.jpg">
        <img src="images/02.jpg">
        <img src="images/03.jpg">
        <img src="images/04.jpg">
        <img src="images/05.jpg">
        <img src="images/06.jpg">
        <img src="images/07.jpg">
        <img src="images/08.jpg">
        <img src="images/09.jpg">
        <img src="images/10.jpg">
        <img src="images/11.jpg">
        <img src="images/12.jpg">
        <img src="images/13.jpg">
        <img src="images/14.jpg">
        <img src="images/15.jpg">
     
    </div>

    <!-- Footer -->
    <footer>
    	<p>Interested in what you are seeing? <a href="login.jsp" class="login">Login</a> <a href="signup.jsp" class="login">Signin</a></p>
        <p>© 2024 BOKKUU. All Rights Reserved.</p>
        
    </footer>
</body>
</html>