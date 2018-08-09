<?php
include "db_user_details.php";
if($_SERVER['REQUEST_METHOD']=='POST')
{
	$u_id=$_POST['u_id'];
	//$u_id=1;
$sql="SELECT status FROM pharmacy_req where u_id=$u_id limit 1";
$result=mysqli_query($con,$sql);	
$row=mysqli_fetch_assoc($result);
$status=$row["status"];
if($status==0)
{
	$res=array();
	$res[0]="The request is under process. Kindly wait for sometime.";
	echo json_encode($res);
}
else if($status==1)
{
	$sql1="SELECT pharmacy_msg FROM pharmacy_req where u_id=$u_id limit 1 ";
	$result1=mysqli_query($con,$sql1);
	$row1=mysqli_fetch_assoc($result1);
	$pharmacy_msg=$row1["pharmacy_msg"];
	$res1=array();
	$res1[0]="The request is successfully processed. The medicine you requested is available. The message for you from the pharmacy is '$pharmacy_msg' ";
	echo json_encode($res1);
}
else
{
	$res2=array();
	$res2[0]="The request is dispersed since there is a lack of stock.";
	echo json_encode($res2);
}
}
?>