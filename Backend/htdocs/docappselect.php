<?php
include 'db_user_details.php';
$sql="SELECT * FROM user_req order by p_id desc limit 1";
$result=mysqli_query($con,$sql);	
$response = array();

	$row = mysqli_fetch_array($result);
	array_push($response,array(
			"id"=>$row['p_id'],
			"p_name"=>$row['p_name'],
			"p_age"=>$row['p_age'],
			"p_addr"=>$row['p_addr'],
			"h_issue"=>$row['h_issue'],
		"doc_specialization"=>$row['doc_specialization'],
		"doc_name"=>$row['doc_name'],
		"hos_name"=>$row['hos_name'],
		"date"=>$row['date'],
		"slot"=>$row['slot'],
		"status"=>$row['status']));

	//displaying in json format 
	echo json_encode(array('result'=>$response));
	
?>