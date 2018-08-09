<?php
include "db_user_details.php";
include "sha256hash.php";
$p_id=$_POST['p_id'];
//$p_name=$_POST['p_name'];
//$p_id=2;
//$p_name="sneha";
$ppid=shahash($p_id);
//$ppname=shahash($p_name);
$sql="SELECT * FROM user_med_history where crypt_p_id='$ppid' and status=1 order by p_id desc limit 1";
$result=mysqli_query($con,$sql);	
$sql1="SELECT p_name FROM user_req WHERE p_id=$p_id";
$result1=mysqli_query($con,$sql1);
$row1=mysqli_fetch_assoc($result1);	
$response = array();
	$row = mysqli_fetch_array($result);
	array_push($response,array(
			"id"=>$row['p_id'],
			"p_name"=>$row1['p_name'],
			//"u_name"=>$row['u_name'],
			"hopital1"=>$row['hospital1'],
			"date1"=>$row['date1'],
			"medic1"=>$row['medic1'],
			"hopital2"=>$row['hospital2'],
			"date2"=>$row['date2'],
			"medic2"=>$row['medic2'],
			"hopital3"=>$row['hospital3'],
			"date3"=>$row['date3'],
			"medic3"=>$row['medic3'],
			"hopital4"=>$row['hospital4'],
			"date4"=>$row['date4'],
			"medic4"=>$row['medic4'],
			"hopital5"=>$row['hospital5'],
			"date5"=>$row['date5'],
			"medic5"=>$row['medic5'],
				));

	//displaying in json format 
	echo json_encode(array('result'=>$response));
	
?>