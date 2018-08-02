<?php
@session_start();
if(@$_SESSION['hos_mail']!='')
{
    header("Location:http://localhost/healthcare/hospital.php");
}
?>
<html>
<head>
    <style>
        #form
        {
            margin-top:10%;
            height: 300px;
            width: 350px;
            background: rgba(0,0,0,0.8);
            -webkit-border-radius: 5px;
            -moz-border-radius: 5px;
            border-radius: 5px;
            display: inline-block;
        }
        #input
        {
            height:30px;
            width:250px;
            border-radius: 3px;
            -webkit-box-shadow: 0 0 20px #fff;
            -moz-box-shadow: 0 0 20px #fff;
            box-shadow: 0 0 20px #fff;
            margin: 15px;
        }
        #input:hover, #input:focus
        {
            -webkit-box-shadow: none;
            -moz-box-shadow:none;
            box-shadow: none;
        }

        h1
        {
            color: #fff;
            font-family:Calibri;
        }
        #button
        {
            margin-top: 250px;
            margin-left:150px;
            margin-right: 150px;
            height:60px;
            width: 150px;
            -webkit-box-shadow: 0 0 30px #fff ;
            -moz-box-shadow: 0 0 30px #fff ;
            box-shadow: 0 0 30px #fff ;
            font-size:20px;

        }
        #submit
        {
            height:30px;
            width:250px;
            font-size:15px;
            border-radius: 3px;
            -webkit-box-shadow: 0 0 20px #fff;
            -moz-box-shadow: 0 0 20px #fff;
            box-shadow: 0 0 20px #fff;
            margin: 15px;
        }
        #button,#submit
        {

            background: #000882;
            color: white;
            border-color: #000882;
            border-radius: 5px;
        }
        #button:hover, #submit:hover
        {
            background: #fff;
            color: #000882;
            font-size:20px;
            border-color: #fff;
            -webkit-box-shadow: 0 0 30px #000 ;
            -moz-box-shadow: 0 0 30px #000 ;
            box-shadow: 0 0 30px #000 ;
            transition: .5s ease;
            border-radius: 5px;
        }
    </style>
</head>
<body style="background: url(images/investigacion-farmaceutica.jpg) no-repeat;overflow: hidden;">
    <center>
        <div style="display: inline-block;float: left">
            <a href="Blood_Bank.php"><button id="button" title="Blood Bank">Blood Bank</button></a>
        </div>
        <div id="form">
            <h1>Admin Login</h1>
            <form action="login.php" method="post">
                <input id="input" type="text" name="email" placeholder="Email-ID" title="Email-ID" required>
                <input id="input" type="password" name="password" placeholder="Password" title="Password" required>
                <input id="submit" type="submit" name="Login" title="Login" value="Login">

            </form>
        </div>
        <div style="display: inline-block;float: right">
            <a href="pharmacy.php"><button id="button" title="Pharmacy">Pharmacy</button></a>

        </div>


    </center>
</body>
</html>