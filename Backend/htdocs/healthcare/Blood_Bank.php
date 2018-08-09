<?php
include 'connection.php';
$exe_count=$con->query("select count(*) from blood_req where status=0");
$count=mysqli_fetch_array($exe_count);

?>
<html>
<head>
    <style>
        #form
        {
            margin-top:4%;
            height: 350px;
            width: 400px;
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
        textarea
        {
            height: 150px;
            width: 250px;
            border-radius: 3px;
        }
        a{
            color: #fff;
        }
    </style>
</head>


<body style="background:url(images/bloodcells_1_by_kacheron-d3004eo.png) fixed;-webkit-background-size: 100% 100%;background-size: 100% 100%;">

<div style="width: 101.3%;height: 100px;background: rgba(0,0,0,0.6); margin-top: -10px; margin-left: -10px;float: left" >
    <h1>Blood Bank</h1>
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
    if(isset($_POST['blood_req']))
    {
        $avilable=$_POST['blood_req'];
        $id=$_POST['u_id'];
        $set_available=$con->query("update blood_req set status=$available WHERE u_id=$id");
        if($set_available)
        {
            echo '<script>window.alert("Updated Successfully.");window.location("Blood_Bank.php");</script>';
        }
    }
    else if(isset($_POST['Available']))
    {
        $avilable=$_POST['Available'];
        $id=$_POST['u_id'];
        echo '<form method="post" id="form"><br>
    <input type="number" name="number" placeholder="Phone Number" id="input" required><br>
    <textarea rows="10" cols="20" name="announce" placeholder="Announcement"></textarea><br><br>
    <input type="hidden" name="id" value="'.$id.'">
    <input type="submit" id="submit" name="phone" value="Submit">
</form>';

    }
    else if(isset($_POST['phone']))
    {
        $id=$_POST['id'];
        $phone=$_POST['number'];
        $announce=$_POST['announce'];
        $set_available=$con->query("update blood_req set status='1',b_bank_msg='$phone.$announce' WHERE u_id=$id");

        if($set_available)
        {
            echo '<script>window.alert("Updated Successfully.");window.location("Blood_Bank.php");</script>';
        }
    }
    else if(isset($_POST['blood']))
    {
        $id=$_POST['blood'];
        $sel_det=$con->query("select * from blood_req where u_id=$id");
        $fetch_det=mysqli_fetch_array($sel_det);
        $name=$fetch_det['u_name'];
        $blood=$fetch_det['req_blood_grp'];
        $priority=$fetch_det['priority'];
echo '<table style="padding-top: 50px">
<tr>
<td>ID</td>
<td>'.$id.'</td>
</tr>
<tr>
<td>Name</td>
<td>'.$name.'</td>
</tr>
<tr>
<td>Blood Group</td>
<td>'.$blood.'</td>
</tr>
<form method="post" >
<tr>
<td>Priority</td>
<td>'.$priority.'</td>
<input type="hidden" name="u_id" value="'.$id .'">
</tr>
<tr>
<td><button id="submit" name="blood_req" value="2" >Not Available</button></td>

<td><button id="submit" name="Available" value="1">Available</button></td>

</tr>
</form>
</table>';

    }
    else {

        $exe_sql = $con->query("select * from blood_req WHERE status=0");
        while ($fetch_sql = mysqli_fetch_array($exe_sql)) {
            $id = $fetch_sql['u_id'];
            $req_name=$fetch_sql['u_name'];
            $blood = $fetch_sql['req_blood_grp'];
            echo '<form method="post" style="display: inline-block">
<button id="button" name="blood" value="' . $id . '">' .$req_name.' requires '.$blood . '</button>
</form>';
        }
    }
    ?>
</div>
</center>
</body>
</html>