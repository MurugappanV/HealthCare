<?php
include 'db_user_details.php';


if($_SERVER['REQUEST_METHOD']=='POST')
{
	$u_id=$_POST['id'];
	$u_name=$_POST['u_name'];
	$req_blood_grp=$_POST['req_blood_grp'];
	$rate=$_POST['rate'];

	/*$u_id=1;
	$u_name="ramya";
	$req_blood_grp="B+ve";
	$rate=4.5;*/


	if($rate<=2.5)
	$priority='low';
	else if($rate<=4 && $rate>2.5)
	$priority='medium';
	else if($rate>4 && $rate<=5)
	$priority='high';
	
	$sql="INSERT INTO blood_req(u_id,u_name,req_blood_grp,priority) VALUES ($u_id,'$u_name','$req_blood_grp','$priority')";
	
	$res=mysqli_query($con,$sql);
	if($res)
	{
		$response["success"] = 1;
        $response["message"] ="Data inserted into database." ;

        // echoing JSON response
        echo json_encode($response);// Here you are echoing
		
		//echo "Data Submitted Successfully";
	}
	else
	{
		$response["Failure"] = 0;
        $response["message"] = $con->error;

        // echoing JSON response
        echo json_encode($response);// Here you are echoing
		//echo "Try again";
	}
	
}
mysqli_close($con);
?>