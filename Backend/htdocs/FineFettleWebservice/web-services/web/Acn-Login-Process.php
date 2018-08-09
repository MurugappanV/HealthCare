<?php
include_once("../config.php");
$a=array();
$entry_date = date("Y-m-d h:i:s");
if($_SERVER['REQUEST_METHOD']=='POST')
{
	$Mobilenumber = $_POST['mobilenumber'];
	
	$customer_data_array = array("mobilenumber"=>$Mobilenumber);
		$request_data = json_encode($customer_data_array,JSON_UNESCAPED_SLASHES);
		$request_action = "Trying to login";
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
		
		$sql_emp = mysqli_query($acn_con,"SELECT * FROM customer_master WHERE mobile_number = '".$Mobilenumber."' and status='1' " );
		$count_emp = mysqli_num_rows($sql_emp);
		
		if($count_emp > 0)
		{
			//Generate OTP
					$length=4;
					
					//$otp = 1234;
					$characters = '0123456789';
					$charactersLength = strlen($characters);
					$otp = '';
					for ($i = 0; $i < $length; $i++) 
					{
						$otp.=$characters[rand(0, $charactersLength - 1)];
					}
			
		   $fetch_emp = mysqli_fetch_array($sql_emp);
		   
			$message = "OTP:'".$otp."' FOR LOGIN INTO YOUR ACCOUNT";
			

                          $url = "http://appsms.in/api/sendsms?api_key=A4591041491484291083&to=".$Mobilenumber."&message=".urlencode($message)."&senderid=MDEVDR";	
						
			
			
			   $ch=curl_init($url);
			   curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
			   $output=curl_exec($ch);
			   curl_close($ch);	
			   
			$sql_emp = mysqli_query($acn_con,"update customer_master set password='".$otp."' WHERE mobile_number = '".$Mobilenumber."' and status='1' " );   
		   
		   $a['otp'] = $otp;
		   $a['type'] = $fetch_emp['req_type'];
		   $a['customer_id'] = $fetch_emp['customer_id'];	
		   $a['customer_name'] = $fetch_emp['name'];
		   $a['status'] = 100;
		  		  
		}
		else
		{	   
		   //Send Response to Android
		   $a['status'] = 101;
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

		
		$response_data = json_encode($a,JSON_UNESCAPED_SLASHES);
		$response_action = "Trying to login";
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