<?php
include 'connection.php';
$exe_count=$con->query("select count(*) from pharmacy_req where status=0");
$count=mysqli_fetch_array($exe_count);
?>
<html>
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
        #button
        {
            margin:10px;
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
        a{
            color: #fff;
        }
    </style>
</head>
<body style="background:url(images/colortablets.jpg) fixed;-webkit-background-size: 100% 100%;background-size: 100% 120%;">

<div style="width: 101.3%;height: 100px;background: rgba(0,0,0,0.6); margin-top: -10px; margin-left: -10px;float: left" >
    <h1>Pharmacy</h1>
    <div>
        <ul>
            <a href="index.php"> <li>Home</li></a>
            <a><li>Requests:<st style="background: #f00;padding-left: 5px;padding-right: 5px;"><?php echo $count[0];?></st></li></a>
        </ul>
    </div>

</div>
<center>
    <div>
        <?php
        if(isset($_POST['pharmacy_req']))
        {
            $avilable=$_POST['pharmacy_req'];
            $id=$_POST['u_id'];
            $set_available=$con->query("update pharmacy_req set status=$avilable WHERE u_id=$id");
            if($set_available)
            {
                echo '<script>window.alert("Updated Successfully.");window.location("pharmacy.php");</script>';
            }
        }
        if(isset($_POST['pharmacy']))
        {
            $id=$_POST['pharmacy'];
            $sel_det=$con->query("select * from pharmacy_req where u_id=$id");
            $fetch_det=mysqli_fetch_array($sel_det);
            $name=$fetch_det['p_name'];
            $prescription=$fetch_det['prescription'];
            $p_id=$fetch_det['p_id'];
            echo '<form method="post" >
<table style="padding-top: 50px">
<tr>
<td>ID</td>
<td>'.$id.'</td>
</tr>
<tr>
<td>Patient ID</td>
<td>'.$p_id.'</td>
<input type="hidden" name="u_id" value="'.$id .'">
</tr>
<tr>
<td>Name</td>
<td>'.$name.'</td>
</tr>
<tr>
<td>Blood Group</td>
<td>'.$prescription.'</td>
</tr>
<tr>
<td><button id="submit" name="pharmacy_req" value="1">Available</button></td>
<td><button id="submit" name="pharmacy_req" value="2">Not Available</button></td>
</tr>
</table>
</form>';

        }
        else {
            $exe_sql = $con->query("select * from pharmacy_req WHERE status=0");
            while ($fetch_sql = mysqli_fetch_array($exe_sql)) {
                $id = $fetch_sql['u_id'];
                $req_name=$fetch_sql['p_name'];
                $prescription = $fetch_sql['prescription'];
                echo '<form method="post" style="display: inline-block">
<button id="button" name="pharmacy" value="' . $id . '">' .$req_name.' requires '. $prescription . '</button>
</form>';
            }
        }
        ?>
    </div>
</center>
</body>
</html>