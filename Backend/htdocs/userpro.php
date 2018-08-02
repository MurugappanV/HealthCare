<?php
include 'db_user_details.php';

if($_SERVER['REQUEST_METHOD']=='POST')
{
	$u_name=$_POST['user'];
	$first_name=$_POST['firstname'];
	$last_name=$_POST['lastname'];
	$age=$_POST['age'];
	$gender=$_POST['gender'];
	$dob=$_POST['dob'];
	$bloodgroup=$_POST['bgroup'];
	$address=$_POST['address'];
	$city=$_POST['city'];
	$pincode=$_POST['pin'];
	
	
	// or write like $p_name = $data->{'name'};

	/*$u_name="Ramya";
	$first_name="Ramya";
	$last_name="vijay";
	$age=20;
	$gender="female";
	$dob="2018-05-07";
	$bloodgroup="B+ve";
	$address="6, pattinathar street";
	$city="karur";
	$pincode=639114;*/
	
	
	$sql="UPDATE user_reg SET first_name='$first_name', last_name='$last_name', age=$age, gender='$gender', dob='$dob', bloodgroup='$bloodgroup', address='$address', city='$city', pincode='$pincode' WHERE u_name='$u_name'";
	
	$res=mysqli_query($con,$sql);
	if($res)
	{
		$response["success"] = 1;
        $response["message"] = "Your profile has been updated successfully";

        // echoing JSON response
        echo json_encode($response);// Here you are echoing
		
		//echo "Your profile has been updated successfully";
	}
	else
	{
		$response["Failure"] = 0;
        $response["message"] = "Data does not inserted into database.";

        // echoing JSON response
        echo json_encode($response);// Here you are echoing
		//echo "Try again";
	}
}
mysqli_close($con);


?>