<?php
include_once("../config.php");
$a=array();
$entry_date = date("Y-m-d h:i:s");
if(isset($_POST['mobilenumber']))
{
	$mobilenumber = $_POST['mobilenumber'];
	$otp = $_POST['otp'];
	
	
	$customer_data_array = array("mobilenumber"=>$mobilenumber,"otp"=>$otp);
		$request_data = json_encode($customer_data_array,JSON_UNESCAPED_SLASHES);
		$request_action = "Verify OTP Request";
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
		
	if($mobilenumber != "")
	{
		
		//Check if the Customer Code alreday exists 
		            
		$sql_emp = mysqli_query($acn_con,"SELECT * FROM customer_master WHERE mobile_number = '".$mobilenumber."' and password='".$otp."' and status='1'  " );
		
		$count_emp = mysqli_num_rows($sql_emp);
				
		if($count_emp > 0)
		{
		   $fetch_emp = mysqli_fetch_array($sql_emp);
				 
		   $a['status'] =300;
		  		  
		}
		else
		{	   
		   $a['status'] = 301;
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


		$customer_response_data_array = array("mobilenumber"=>$mobilenumber,"otp"=>$otp,"status"=>$a['status']);
		$response_data = json_encode($customer_response_data_array,JSON_UNESCAPED_SLASHES);
		$response_action = "Verify OTP Response";
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