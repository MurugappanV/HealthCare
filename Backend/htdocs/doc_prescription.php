<?php
include 'db_user_details.php';

if(is_array($data))
{
	$u_id=$_POST['u_id'];
	$d_id=$_POST['d_id'];
	$p_id=$_POST['id'];
	$p_name=$_POST['name'];
	$p_age=$_POST['age'];
	$p_disease=$_POST['disease'];
	$p_condition=$_POST['p_condition'];
	$prescription=$_POST['prescription'];

	
	/*// or write like $p_name = $data->{'name'};
	$u_id=1;
	$d_id=1;
	$p_id=2;
	$p_name="ajay";
	$p_age=20;
	$p_disease="fever";
	$p_condition="bad";
	$prescription="dolo650";*/
	
	$sql="INSERT INTO doc_prescription (u_id,d_id,p_id,p_name,p_age,p_disease,p_condition,prescription) VALUES ($u_id,$d_id,$p_id,'$p_name',$p_age,'$p_disease','$p_condition','$prescription')";
	
	$res=mysqli_query($con,$sql);
	if($res)
	{
		$response["success"] = 1;
       // $response["message"] = "Data inserted into database.";

        //echoing JSON response
        echo json_encode($response);// Here you are echoing 
		
		//echo "Data Submitted Successfully";
	}
	else
	{
		$response["Failure"] = 0;
        $response["message"] =$con->error;

        // echoing JSON response
        echo json_encode($response);// Here you are echoing 
		//echo "Try again";
	}
	
}
mysqli_close($con);
?>