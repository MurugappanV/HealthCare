<?php
include 'db_user_details.php';
if($_SERVER['REQUEST_METHOD']=='POST')
{
	$u_id=$_POST['u_id'];
	$message=$_POST['message'];
	$vehicle_no=$_POST['vehicle_no'];

	
	// or write like $p_name = $data->{'name'};

	$u_id=1;
	$message="There is an emergency near kulathur junction";
	$vehicle_no="TN-74-D-900";
	
	
	$sql="INSERT INTO ambulance_req (u_id,message,vehicle_no,status) VALUES ($u_id,'$message','$vehicle_no',0)";
	
	$res=mysqli_query($con,$sql);
	if($res)
	{
		/*$response["success"] = 1;
        $response["message"] = "The user is created. You can login now!";

        // echoing JSON response
        echo json_encode($response);// Here you are echoing*/ 
		
		echo "The request has been made. Please wait till any one of the ambulance drivers acknowledge your message";
	}
	else
	{
		/*$response["Failure"] = 0;
        $response["message"] = mysqli_error($con);

        // echoing JSON response
        echo json_encode($response);// Here you are echoing*/ 
		echo "Try again".mysqli_error($con);
	}
	
}
mysqli_close($con);
?>
