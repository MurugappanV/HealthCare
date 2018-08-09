<?php
include_once("../config.php");
$a=array();
$entry_date = date("Y-m-d h:i:s");
if(isset($_POST['mobilenumber']))
{
	$mobilenumber = $_POST['mobilenumber'];
	$email = $_POST['email'];
	$name = $_POST['name'];
	$address = $_POST['address'];
	$type = $_POST['type_status'];
	if($type==1)
	{
		$businessname = $_POST['businessname'];
		$integratedid = $_POST['integratedid'];
	}
	if($type==2)
	{
		$businessname = $_POST['storename'];
                $integratedid = "";
	}
	
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
	 //End OTP Generation
	
		$message = "OTP:'".$otp."' FOR LOGIN INTO YOUR ACCOUNT";
				
 $url = "http://appsms.in/api/sendsms?api_key=A4591041491484291083&to=".$mobilenumber."&message=".urlencode($message)."&senderid=MDEVDR";	
								
			
			
			   $ch=curl_init($url);
			   curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
			   $output=curl_exec($ch);
			   curl_close($ch);	
	
		$customer_data_array = array("mobilenumber"=>$mobilenumber,"email"=>$email,"name"=>$name,"address"=>$address,"businessname"=>$businessname,"type"=>$type,"otp"=>$otp);
		$request_data = json_encode($customer_data_array,JSON_UNESCAPED_SLASHES);
		$request_action = "Registration Request";
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
		
		$sql_emp = mysqli_query($acn_con,"SELECT * FROM customer_master WHERE mobile_number = '".$mobilenumber."' and status='1' " );
		$count_emp = mysqli_num_rows($sql_emp);	
			if($count_emp > 0) //Already Registered 
			{
			  	 $fetch_emp = mysqli_fetch_array($sql_emp);
			
					   $update_cus = mysqli_query($acn_con,"UPDATE customer_master SET password = '".$otp."' where mobile_number = '".$mobilenumber."' ");  
					   
                       $a['name'] = $fetch_emp['name'];
					   $a['otp'] = $otp;
					   $a['status'] = 200;
					   $a['type_status']= $fetch_emp['req_type'];
					   $a['customer_id'] = $fetch_emp['customer_id'];
					   $type = $fetch_emp['req_type'];
			   
			}
			else //New Registration
			{
				if($integratedid != "" )
				{
					//Check if the Vendor Code alreday exists 
					$sql_vendor = mysqli_query($acn_con,"SELECT * FROM customer_master WHERE mobile_number = '".$integratedid."' and req_type=2 and status='1' " );
					$fet_vendor = mysqli_num_rows($sql_vendor);
					if($fet_vendor>0)
					{ // Valid Vendor
						$sql_reg = mysqli_query($acn_con,"INSERT INTO customer_master
									(
									 name,
									 email,
									 mobile_number,
									 password,
									 address,
									 integratedid,
									 businessname,
									 req_type,
									 date,
									 status
									 )
									VALUES
									(
									 '".$name."',
									 '".$email."',
									 '".$mobilenumber."',
									 '".$otp."',
									 '".$address."',
									 '".$integratedid."',
									 '".$businessname."',
									 '".$type."',
									 '".$entry_date."',
									 '1'
									 )
									");
					  
					$customer_ref_id = mysqli_insert_id($acn_con);
					$cuss_idd = "CU0000".$customer_ref_id;
					
					$update_cus = mysqli_query($acn_con,"UPDATE customer_master SET customer_id = '".$cuss_idd."' where id = '".$customer_ref_id."' ");  
					  
			
					    $a['name'] = $name;
					   $a['otp'] = $otp;
					   $a['status'] = 201;
					   $a['type_status'] = $type;
					   $a['customer_id'] = $cuss_idd;
					}
					else
					{ // Invalid Vendor
						$a['status'] = 202;
					}
					
				}
				else
				{
					$sql_reg = mysqli_query($acn_con,"INSERT INTO customer_master
									(
									 name,
									 email,
									 mobile_number,
									 password,
									 address,
									 integratedid,
									 businessname,
									 req_type,
									 date,
									 status
									 )
									VALUES
									(
									 '".$name."',
									 '".$email."',
									 '".$mobilenumber."',
									 '".$otp."',
									 '".$address."',
									 '".$integratedid."',
									 '".$businessname."',
									 '".$type."',
									 '".$entry_date."',
									 '1'
									 )
									");
					  
					$customer_ref_id = mysqli_insert_id($acn_con);
					$cuss_idd = "VN0000".$customer_ref_id;
					
					$update_cus = mysqli_query($acn_con,"UPDATE customer_master SET customer_id = '".$cuss_idd."' where id = '".$customer_ref_id."' ");  
			
					   $a['name'] = $name; 
					   $a['otp'] = $otp;
					   $a['status'] = 201;
					   $a['type_status'] = $type;
					   $a['customer_id'] = $cuss_idd;
					   
					
				}
				
			}
		
		
}
	
else
{
	$a['status'] = 500;
}


	
		$response_data = json_encode($a,JSON_UNESCAPED_SLASHES);
		$response_action = "Registration Response";
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