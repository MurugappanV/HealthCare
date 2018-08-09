<?php
include_once("../config.php");
$a=array();
$entry_date = date("Y-m-d h:i:s");
if(isset($_POST['mobilenumber']))
{
	$Mobilenumber = $_POST['mobilenumber'];
	
	$customer_data_array = array("mobilenumber"=>$Mobilenumber);
		$request_data = json_encode($customer_data_array,JSON_UNESCAPED_SLASHES);
		$request_action = "Profile View";
		$request_id = 0;
		
		$sql_ins = mysqli_query($acn_con,"INSERT INTO acn_webservice_request_log
						(
						 request_data,
						 request_id,
						 request_action,
						 request_date,
						 status
						 )
						VALUES
						(
						 '".$request_data."',
						 '".$request_id."',
						 '".$request_action."',
						 '".$entry_date."',
						 '1'
						 )
						");
		
	if($Mobilenumber != "")
	{
		
		//Check if the Customer Code alreday exists 
		
		$sql_emp = mysqli_query($acn_con,"SELECT * FROM customer_master WHERE mobile_number = '".$Mobilenumber."' and status='1'  " );
		$count_emp = mysqli_num_rows($sql_emp);
		
		if($count_emp > 0)
		{
		   $fetch_emp = mysqli_fetch_array($sql_emp);
		  
		    $a['customer_id'] = $fetch_emp['customer_id'];
			$a['name'] = $fetch_emp['name'];
			$a['email'] = $fetch_emp['email'];
			$a['mobilenumber'] = $fetch_emp['mobile_number'];
			$a['address'] = $fetch_emp['address'];
			$a['integratedid'] = $fetch_emp['integratedid'];
			$a['businessname'] = $fetch_emp['businessname'];
			$a['type'] = $fetch_emp['req_type'];
		    $a['otp'] = $fetch_emp['password'];
			
		    $a['status'] = 501;
		  		  
		}
		else
		{	   
		   //Send Response to Android
		   $a['status'] = 502;
		}
	}
	else
	{
		$a['status'] = 500;	
	}
}
	
else
{
	$a['status'] = 500;
}


		$customer_response_data_array = array("mobilenumber"=>$Mobilenumber);
		$response_data = json_encode($customer_response_data_array,JSON_UNESCAPED_SLASHES);
		$response_action = "Profile View";
		$response_id = 0;

$sql_ins = mysqli_query($acn_con,"INSERT INTO acn_webservice_response_log
						(
						 request_data,
						 request_id,
						 request_action,
						 request_date,
						 status
						 )
						VALUES
						(
						 '".$response_data."',
						 '".$response_id."',
						 '".$response_action."',
						 '".$entry_date."',
						 '1'
						 )
						");


//Post JSON response back to Android Application
echo json_encode($a,JSON_UNESCAPED_SLASHES);

?>