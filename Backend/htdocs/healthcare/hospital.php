<?php
include 'log_redirect.php';
include 'connection.php';
include 'reg_doctors.php';
?>
<html>
<link href="css/overlay.css" type="text/css" rel="stylesheet">
<head>
    <style>
        h1
        {
            color: #fff;
            font-family: Calibri;
            font-size:40px;
            padding-left: 20px;
            text-transform: uppercase;
            float: left;
        }
        ul{
            text-transform: uppercase;
            color: #fff;
            font-size:25px;
            font-family:Calibri;
            float: right;
        }
        a{
            text-transform: uppercase;
            color: #fff;
            font-size:25px;
            font-family:Calibri;
        }
        li{
            display: inline-block;
            padding: 10px;
            padding-right: 30px;
        }
        #special
        {
            float: left;
            margin: 50px;
            border-radius: 50%;
            height: 150px;
            width: 150px;
            display: inline-block;
            box-shadow: 0 0 50px #fff,inset 0 0 100px#fff;

        }
        #special:hover
        {
            box-shadow: inset 0 0 100px#000;
            height: 200px;
            width: 200px;
            transition: .5s ease ;
        }
    </style>
</head>

<body style="background:url(images/693674-top-hospital-wallpapers-1920x1080-full-hd.jpg) 100% 100%; fixed">
    <?php include 'header.php';?>
    <div>
        <a onclick="reg_cardio()"><img id="special" src="images/heart3.jpg" ></a>
        <a onclick="reg_lungs()"><img id="special" src="images/lungs1.jpg"></a>
        <a onclick="reg_kidney()"><img id="special" src="images/kidney1.jpg"></a>
        <a onclick="reg_brain()"><img id="special" src="images/brain.jpg"></a>

    </div>
    <div>

    </div>

</body>
</html>
