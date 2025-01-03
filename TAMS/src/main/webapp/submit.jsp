<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Grid Layout</title>
    <style>

        /* General Reset */
* {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
}

/* Header Styling */
.header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 10px 20px;
    background-color: #f4f4f4;
    border-bottom: 2px solid #ddd;
}

/* Logo Styling */
.logo img {
    height: 50px;
    width: auto;
}

/* Search Bar Styling */
.search-bar {
    display: flex;
    align-items: left;
}

.search-bar input {
    padding: 5px;
    font-size: 16px;
    border: 1px solid #ccc;
    border-radius: 4px;
    outline: none;
}

.search-bar button {
    padding: 5px 10px;
    font-size: 16px;
    margin-left: 5px;
    background-color: #007BFF;
    color: white;
    border: none;
    border-radius: 4px;
    cursor: pointer;
}

.search-bar button:hover {
    background-color: #0056b3;
}

/* Grid Container Styling */
.grid-container {
    display: grid;
    grid-template-columns: repeat(3, 1fr);
    gap: 20px;
    padding: 20px;
}

/* Grid Item Styling */
.grid-item {
    display: flex;
    flex-direction: column;
    align-items: center;
    padding: 10px;
    background-color: #ffffff1e;
    border: 1px solid #dddddd72;
    border-radius: 8px;
    box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
    text-align: center;
}

.grid-item img {
    max-width: 100%;
    height: auto;
    border-radius: 4px;
    margin-bottom: 10px;
    width: 100%; /* Make images responsive within their grid cells */
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
    transition: transform 0.3s;
}
.grid-item img :hover {
    transform: scale(1.4);
    transition: transform 0.3s ease-in-out;
    
}

.grid-item p {
    font-size: 16px;
    color: #d14c4c;
}


    </style>
</head>
<body>
    <header class="header">
        <div class="logo">
            <img src="images/12.jpg" alt="Logo">
        </div>
        <div class="search-bar">
            <input type="text" placeholder="Search...">
            <button type="submit">Search</button>
        </div>
    </header>

    <main class="grid-container">
        <div class="grid-item">
            <img src="images/01.jpg" alt="Image 1">
            <p>Image one shown here</p>
        </div>
        <div class="grid-item">
            <img src="images/02.jpg" alt="Image 2">
            <p>Using grid property affect our image</p>
        </div>
        <div class="grid-item">
            <img src="images/03.jpg" alt="Image 3">
            <p>What will happen if we don't use grid?</p>
        </div>
        <div class="grid-item">
            <img src="images/04.jpg" alt="Image 4">
            <p>IDK why we choose flex sometimes</p>
        </div>
        <div class="grid-item">
            <img src="images/05.jpg" alt="Image 5">
            <p>I think it is better idea we use grid ussually</p>
        </div>
        <div class="grid-item">
            <img src="images/06.jpg" alt="Image 6">
            <p>Smile sometime lie can kick you when you are down</p>
        </div>
    </main>
</body>
</html>



    