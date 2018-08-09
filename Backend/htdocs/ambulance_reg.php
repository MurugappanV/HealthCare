<?php
include 'db_user_details.php';
if($_SERVER['REQUEST_METHOD']=='POST')
{
	$a_name=$_POST['a_name'];
	$pass=$_POST['pass'];
	$vehicle_no=$_POST['vehicle_no'];
	$a_phone=$_POST['a_phone'];
	
	// or write like $p_name = $data->{'name'};

/*	$a_name="sethu";
	$pass="hello";
	$vehicle_no="TN-74-D-900";
	$a_phone=9876543210;
*/	
	$sql="INSERT INTO ambulance_reg (a_name,pass,vehicle_no,a_phone) VALUES ('$a_name','$pass','$vehicle_no',$a_phone)";
	
	$res=mysqli_query($con,$sql);
	if($res)
	{
		/*$response["success"] = 1;
        $response["message"] = "The user is created. You can login now!";

        // echoing JSON response
        echo json_encode($response);// Here you are echoing*/ 
		
		echo "The user is created. You can log in now";
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
