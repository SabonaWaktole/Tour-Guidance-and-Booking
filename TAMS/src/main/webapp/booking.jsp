<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Booking Page</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
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

        .hero .buttons a {
            display: inline-block;
            margin: 10px 20px;
            padding: 12px 25px;
            font-size: 1rem;
            font-weight: bold;
            text-decoration: none;
            border-radius: 25px;
            transition: background-color 0.3s, transform 0.2s;
        }

        .hero .buttons a.login {
            background-color: #007bff;
            color: white;
        }

        .hero .buttons a.signup {
            background-color: #28a745;
            color: white;
        }

        .hero .buttons a:hover {
            transform: translateY(-2px);
            opacity: 0.9;
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
    <div class="container mt-5">
        <h2 class="text-center">Create a Booking</h2>
        <form action="BookingsServlet" method="post" class="mt-4">
            <input type="hidden" name="action" value="create">

            <div class="form-group">
                <label for="place">Place:</label>
                <select class="form-control" id="place" name="place" required>
                    <option value="Bale Mountains">Bale Mountains</option>
                    <option value="Yayu biosphere and coffee forest">Yayu biosphere and coffee forest</option>
                    <option value="Aba sena Mountain">Aba sena Mountain</option>
                </select>
            </div>

            <!-- The 'Days' field -->
			<div class="form-group">
			    <label for="days">Days:</label>
			    <select class="form-control" id="days" name="days" required>
			        <option value="7">7 Days</option>
			        <option value="5">5 Days</option>
			        <option value="3">3 Days</option>
			    </select>
			</div>
			
			<!-- The 'Level' field, renamed for clarity -->
			<div class="form-group">
			    <label for="level">Level:</label>
			    <select class="form-control" id="level" name="level" required>
			        <option value="1">Level 1</option>
			        <option value="2">Level 2</option>
			        <option value="3">Level 3</option>
			    </select>
			</div>

            
            <div class="form-group">
                <label for="people">PEOPLE:</label>
                <select class="form-control" id="people" name="people" required>
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                    <option value="5">5</option>
                    <option value="6">6</option>
                    <option value="7">7</option>
                    <option value="8">8</option>
                    <option value="9">9</option>
                    <option value="10">10</option>
                </select>
            </div>

            <button type="submit" class="btn btn-primary">Submit</button>
        </form>
    </div>
    <a href="me.jsp" class="changeme">Edit profile</a>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
