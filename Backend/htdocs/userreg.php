<?php

include 'db_user_details.php';

if($_SERVER['REQUEST_METHOD']=='POST')
{
	$u_name=$_POST['name'];
	$pass=$_POST['pass'];
	$email=$_POST['email'];
	$phone=$_POST['phone'];
	
		
	$sql="INSERT INTO user_reg (u_name,pass,email,phone) VALUES ('$u_name','$pass','$email',$phone)";
	
	$res=mysqli_query($con,$sql);
	if($res)
	{
		$response["success"] = 1;
        $response["message"] = "The user is created. You can login now!";

        // echoing JSON response
        echo json_encode($response);// Here you are echoing
		
		//echo "The user is created. You can log in now";
	}
	else
	{
		$response["Failure"] = 0;
        $response["message"] = mysqli_error($con);

        // echoing JSON response
        echo json_encode($response);// Here you are echoing
		//echo "Try again".mysqli_error($con);
	}
}
mysqli_close($con);
?>