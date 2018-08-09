<?php
include "db_user_details.php";
if($_SERVER['REQUEST_METHOD']=='POST')
{
	$hos_name=$_POST['hos_name'];
	//$hos_name='KGH';
$sql="SELECT h_id FROM doc_login where hos_name='$d_hospital'";
$result=mysqli_query($con,$sql);
$row=mysqli_fetch_assoc($result);
$h_id=$row["h_id"];
$sql1="SELECT d_name,d_specialization FROM doc_login WHERE h_id=$h_id";
$result1=mysqli_query($con,$sql1);
$response = array();
if(mysqli_num_rows($result1)>0)
{
	while($row1=mysqli_fetch_assoc($result1))
	{	
	
	array_push($response,array(
			"d_name"=>$row1['d_name'],
			"d_specialization"=>$row1['d_specialization']
				));
	}
}	
//displaying in json format 
	echo json_encode(array('result'=>$response));
}
?>