<?php
include 'db_user_details.php';


if($_SERVER['REQUEST_METHOD']=='POST')
{
	$u_id=$_POST['id'];
	$p_id=$_POST['p_id'];
	$p_name=$_POST['p_name'];
	$prescription=$_POST['prescription'];
	$d_id=$_POST['d_id'];

/*	$u_id=1;
	$p_name="ramya";
	$p_id=1;
	$prescription="amoxilin, dollo";
	$d_id=1;
	*/
	
	$sql="INSERT INTO pharmacy_req(u_id,p_name,p_id,prescription,d_id) VALUES ($u_id,'$p_name',$p_id,'$prescription',$d_id)";
	
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