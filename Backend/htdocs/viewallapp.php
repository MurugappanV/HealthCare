<?php
//include "db_doc_app.php";
include 'db_user_details.php';
$u_id=$_GET['ID'];
//$u_id=1;
$sql="SELECT * FROM user_req where u_id=$u_id order by p_id desc limit 5";
$result=mysqli_query($con,$sql);
$resp = array();
if(mysqli_num_rows($result)>0)
{
	while($row=mysqli_fetch_assoc($result))
	{	

		$id=$row["p_id"];
		$p_name=$row["p_name"];
		$p_age=$row["p_age"];
		$p_addr=$row["p_addr"];
		$h_issue=$row["h_issue"];
		$doc_specialization=$row["doc_specialization"];
		$doc_name=$row["doc_name"];
		$hos_name=$row["hos_name"];
		$date=$row["date"];
		$slot=$row["slot"];
		$status=$row["status"];
		
		$response["id"] = $id;
		$response["p_name"] = $p_name;
		$response["p_age"] = $p_age;
		$response["p_addr"]=$p_addr;
		$response["h_issue"]=$h_issue;
		$response["doc_specialization"]=$doc_specialization;
		$response["doc_name"]=$doc_name;
		$response["hos_name"]=$hos_name;
		$response["date"]=$date;
		$response["slot"] = $slot;
		$response["status"]=$status;

		array_push($resp,$response)	;	
	}
}

echo json_encode(array('response'=>$resp));
?>