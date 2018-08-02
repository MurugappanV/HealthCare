<?php
include "db_user_details.php";
if($_SERVER['REQUEST_METHOD']=='POST')
{
	$city=$_POST['city'];
	//$city="karur";
$sql="SELECT doc_name FROM doc_login where city='$city'";
$result=mysqli_query($con,$sql);	
$response = array();
if(mysqli_num_rows($result)>0)
{
	while($row=mysqli_fetch_assoc($result))
	{	
	
	array_push($response,array(
			"hos_name"=>$row['hos_name']
				));
	}
}	
//displaying in json format 
	echo json_encode(array('result'=>$response));
}
?>