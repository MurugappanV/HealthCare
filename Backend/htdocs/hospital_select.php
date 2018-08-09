<?php
include "db_user_details.php";

$sql1="SELECT * FROM hos_login";
$result1=mysqli_query($con,$sql1);
$response = array();
if(mysqli_num_rows($result1)>0)
{
	while($row1=mysqli_fetch_assoc($result1))
	{	
	
	    array_push($response,array(
			"hospital_name"=>$row1['hos_name'],
			"hospital_address"=>$row1['address'],
			"hospital_city"=>$row1['city'],
            "hospital_pincode"=>$row1['pincode'],
            "rating"=>$row1['ratings'],
            "longg"=>$row1['longitude'],
            "lat"=>$row1['latitude'],
            "img"=>$row1['img_url']
        ));
	}
}	
//displaying in json format 
	echo json_encode(array('result'=>$response));
?>