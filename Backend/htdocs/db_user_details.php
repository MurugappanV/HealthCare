<?php

$server="localhost";
$user="root";
$pass="rhMJ6MSXx9Oc";
$db="user_details";
$con=mysqli_connect($server, $user, $pass,$db);
if($con->connect_error)
	die("Connection failed:".mysqli_connect_errno);
//else
	//echo "Connected";

?>