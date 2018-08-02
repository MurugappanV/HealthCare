<?php
include "db_user_details.php";
if($_SERVER['REQUEST_METHOD']=='GET')
{
$uname=$_GET['user'];
$sql="SELECT * FROM user_reg WHERE u_name='$uname'";
$result=mysqli_query($con,$sql);	
$response = array();
	//$row = mysqli_fetch_array($result);
	$row=mysqli_fetch_assoc($result);
	array_push($response,array(
			"u_id"=>$row['u_id'],
			"u_name"=>$row['u_name'],
			"email"=>$row['email'],
			"phone"=>$row['phone'],
			"first_name"=>$row['first_name'],
			"last_name"=>$row['last_name'],
			"age"=>$row['age'],
			"gender"=>$row['gender'],
			"dob"=>$row['dob'],
			"bloodgroup"=>$row['bloodgroup'],
			"address"=>$row['address'],
			"city"=>$row['city'],
			"pincode"=>$row['pincode'],
			));

	//displaying in json format 
	echo json_encode(array('result'=>$response));
}
?>