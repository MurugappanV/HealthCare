<?php
error_reporting(0);


$acn_con = mysqli_connect('localhost','root', 'JTKhpFB8zdbL', 'user_details');
if($acn_con->connect_errno > 0)
{
    die('Unable to connect to database' . $acn_con->connect_error);
	
}
else
{
}

//Time Zone Declare
 date_default_timezone_set("Asia/Kolkata");
 date_default_timezone_get();



?>