
<?php
include 'log_redirect.php';

include 'connection.php';
if(isset($_POST['special']))
{
    $special=$_POST['special'];
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
        img
        {
            height: 150px;
            width: 150px;
            border-radius: 50%;
            margin: 10px;
        }
    </style>
</head>
<body style="background: url(images/investigacion-farmaceutica.jpg) no-repeat;">
<center>
    <div >
        <h1>New Doctors Requests</h1>
        <form method="post">
        <?php
        if(isset($_POST['login']))
        {
            $id=$_POST['con_id'];
            $update="update doc_login set confirmation=1 where d_id=$id";
            if($exe_update=$con->query($update))
            {
                echo '<script>alert("Login provided successfully.");window.history.go(-3);</script>';
            }

        }
        if(isset($_POST['id']))
        {
            $id=$_POST['id'];
            $det_select="select * from doc_login where d_id=$id ";
            $exe_det_select=$con->query($det_select);
            $det_fetch=mysqli_fetch_array($exe_det_select);
            $doc_name=$det_fetch['d_name'];
            $username=$det_fetch['username']; 
           $hospital=$det_fetch['d_hospital'];
            $mobile=$det_fetch['d_mobile'];
            $age=$det_fetch['age'];
            $experience=$det_fetch['d_experience'];
            $specialization=$det_fetch['d_specialization'];
            $gender=$det_fetch['gender'];
            $blood=$det_fetch['bloodgroup'];
            $address=$det_fetch['address'];
            $profile=$det_fetch['profile_pic'];
            $d_degree=$det_fetch['d_degree'];
echo '<div>
<form action="#" method="post">
<img src="images/'.$profile.'">
<table>
<tr>
<td>ID</td>
<td>'.$id.'</td>                        
</tr>
<tr>
<td>Doctor Name</td>
<td>'.$doc_name.'</td>                        
</tr>
<tr>
<td>
Doctor Qualification
</td>
<td>
'.$d_degree.';
</td>
</tr>
<tr>
<td>User Name</td>
<td>'.$username.'</td>                        
</tr>
<tr>
<td>Hospital</td>
<td>'.$hospital.'</td>                        
</tr>
<tr>
<td>Specialization</td>
<td>'.$specialization.'</td>                        
</tr>
<tr>
<td>Experience</td>
<td>'.$experience.'</td>                        
</tr>
<tr>
<td>Age</td>
<td>'.$age.'</td>                        
</tr>
<tr>
<td>Gender</td>
<td>'.$gender.'</td>                        
</tr>
<tr>
<td>Blood Group</td>
<td>'.$blood.'</td>                        
</tr>
<tr>
<td>Address</td>
<td>'.$address.'</td>                        
</tr>
<tr>
<center>
<td colspan="2"><input type="hidden" name="con_id" value="'.$id.'"><input id="submit" type="submit" name="login" value="Provide Login"></td>                        
</center></tr>
</table>
</form>
                  </div>';
        }
        else {
            @$sql = "select d_id,d_name from doc_login where confirmation=0 and d_specialization='$special'";
            @$exe_sql = $con->query($sql);
            while (@$row = mysqli_fetch_array($exe_sql)) {
                $id = $row['d_id'];
                $name=$row['d_name'];
                echo '<button id="button" name="id" value=' . $id . '>' . $name . '</button>';
            }
        }
        ?>
        </form>

    </div>


</center>
</body>
</html>