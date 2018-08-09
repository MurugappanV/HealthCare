
<?php
include 'log_redirect.php';

include 'connection.php';
if(isset($_POST['special']) ) {
    $special=$_POST['special'];
    $_SESSION['special']=$special;
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
            color: #000;
            font-size:50px;
            font-family:Calibri;
            text-shadow:  0 0 50px #fff;
        }
        #button
        {
            margin-left:10px;
            height:60px;
            width: auto;
            -webkit-box-shadow: 0 0 30px #fff ;
            -moz-box-shadow: 0 0 30px #fff ;
            box-shadow: 0 0 30px #fff ;
            font-size:20px;
            padding: 10px;

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
            margin:10px;
            margin-left: 120px;

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
        td{
            height:50px;
            width:250px;
            background: rgba(255,255,255,0.7);
            -webkit-box-shadow: inset 0 0 20px#000882;
            -moz-box-shadow: inset 0 0 20px#000882;
            box-shadow: inset 0 0 20px#000882;
            color: #000;
            font-size: 25px;
            border-radius: 3px;
            padding-left: 20px;
        }
    </style>
</head>
<body style="background: url(images/investigacion-farmaceutica.jpg) no-repeat;overflow: hidden;">
<center>
    <div >
        <h1>Doctors</h1>
            <?php

                if($special=='')
                {
                    $special=$_SESSION['special'];
                }
                 @$sql = "select d_id,d_name from doc_login where confirmation=1 and d_specialization='$special'";
                @$exe_sql = $con->query($sql);
                while (@$row = mysqli_fetch_array($exe_sql)) {
                     $id = $row['d_id'];
                     $name=$row['d_name'];

                    echo '<form action="View_Doctor.php" method="post"><input type="hidden" value="'.$special.'" name="special"><button id="button" name="id" value='. $id .'>'. $name .'</button></form>';
                }

            ?>

    </div>


</center>
</body>
</html>