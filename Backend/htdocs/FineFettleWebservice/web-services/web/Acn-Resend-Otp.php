<?php
include_once("../config.php");
$a=array();
$entry_date = date("Y-m-d h:i:s");
if(isset($_POST['mobilenumber']))
{
	$mobilenumber = $_POST['mobilenumber'];
	
	$customer_data_array = array("mobilenumber"=>$mobilenumber);
		$request_data = json_encode($customer_data_array,JSON_UNESCAPED_SLASHES);
		$request_action = "Resend OTP Request";
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
		
		$sql_emp = mysqli_query($acn_con,"SELECT * FROM customer_master WHERE mobile_number = '".$mobilenumber."' and status='1'  " );
		
		$count_emp = mysqli_num_rows($sql_emp);
				
		if($count_emp > 0)
		{
		   $fetch_emp = mysqli_fetch_array($sql_emp);
		   	
			$otp = $fetch_emp['password'];
					
			$message = "OTP:'".$otp."' FOR LOGIN INTO YOUR ACCOUNT";

			$url = "http://appsms.in/api/sendsms?api_key=A4591041491484291083&to=".$mobilenumber."&message=".urlencode($message)."&senderid=MDEVDR";	
											
			
		
			   $ch=curl_init($url);
			   curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
			   $output=curl_exec($ch);
			   curl_close($ch);	
			
			$a['otp'] = $fetch_emp['password'];
		   $a['status'] =400;
		  		  
		}
		else
		{	   
		   $a['status'] = 401;
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


		$customer_response_data_array = array("mobilenumber"=>$mobilenumber,"otp"=>$fetch_emp['password'],"status"=>$a['status']);
		$response_data = json_encode($customer_response_data_array,JSON_UNESCAPED_SLASHES);
		$response_action = "Resend OTP Response";
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