<html>
<head>
    <title>Calculator</title>
    <style>
        body{
            width: 1366px;
        }
        input{
            width: 50px;
            height: 50px;
            margin-top: 2px;
            margin-bottom: 2px;
        }
        input:hover{
            background: moccasin;
        }
        .calculator{
            width: 300px;
            margin: auto;
        }
        .scoreboard{
            font-size: 20px;
        }
        .equals{
            width: 212px;
        }
    </style>
</head>
<body>
<div class = "calculator">

<div class="scoreboard"><#if valueOne!="0">${valueOne}</#if></div>
<div class="scoreboard">${result}</div>
<form action = "a" method="post">
        <div>
        <input type="submit" name="number" value="1">
        <input type="submit" name="number" value="2">
        <input type="submit" name="number" value="3">
        <input type="submit" name="number" value="+">
        </div>
        <div>
        <input type="submit" name="number" value="4">
        <input type="submit" name="number" value="5">
        <input type="submit" name="number" value="6">
        <input type="submit" name="number" value="-">
        </div>
        <div>
        <input type="submit" name="number" value="7">
        <input type="submit" name="number" value="8">
        <input type="submit" name="number" value="9">
        <input type="submit" name="number" value="*">
        </div>
        <div>
        <input type="submit" name="number" value="0">
        <input type="submit" name="number" value="C">
        <input type="submit" name="number" value="AC">
        <input type="submit" name="number" value="/">
        </div>
        <input class = "equals" type="submit" name="number" value="=">
</form>
</div>
</body>
</html>