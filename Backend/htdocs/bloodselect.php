<?php
include "db_user_details.php";
if($_SERVER['REQUEST_METHOD']=='GET')
{
	$u_id=$_GET['u_id'];
	//$u_id=1;
$sql="SELECT status FROM blood_req where u_id=$u_id limit 1";
$result=mysqli_query($con,$sql);	
$row=mysqli_fetch_assoc($result);
$status=$row["status"];
if($status==0)
{
	$response = array();
	$var="The request is under process. Kindly wait for sometime.";
	array_push($response,array("text"=>$var));
	
	echo json_encode(array('result'=>$response));
}
else
{
	$sql1="SELECT b_bank_phone FROM blood_req where u_id=$u_id limit 1";
	$result1=mysqli_query($con,$sql1);	
$row1=mysqli_fetch_assoc($result1);
$b_bank_phone=$row1["b_bank_phone"];

	$response = array();
	$var="The request is successfully processed. The blood group you requested is available. Kindly contact to $b_bank_phone";
	
	array_push($response,array("text"=>$var));
	
	echo json_encode(array('result'=>$response));
}
}
?>