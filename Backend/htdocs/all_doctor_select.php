<?php
include "db_user_details.php";

$sql1="SELECT * FROM doc_login";
$result1=mysqli_query($con,$sql1);
$response = array();
if(mysqli_num_rows($result1)>0)
{
	while($row1=mysqli_fetch_assoc($result1))
	{	
	
	    array_push($response,array(
            "id"=>$row1['d_id'],
			"name"=>$row1['d_name'],
			"gender"=>$row1['gender'],
			"hospital_name"=>$row1['d_hospital'],
            "specialization"=>$row1['d_specialization'],
            "mobile_number"=>$row1['d_mobile'],
            "rating"=>$row1['rating'],
            "degree"=>$row1['d_degree'],
            "exp"=>$row1['d_experience'],
            "img"=>$row1['profile_pic']
        ));
	}
}	
//displaying in json format 
	echo json_encode(array('result'=>$response));
?>