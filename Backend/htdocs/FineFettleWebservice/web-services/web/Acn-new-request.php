<?php
include_once("../config.php");

if(isset($_POST['mobilenumber']))
{
	$a=array();
	$mobilenumber = $_POST['mobilenumber'];
	$product_id = $_POST['product_id'];
	$request_type = $_POST['request_type'];
	$service_comments = $_POST['service_comments'];
	
	$entry_date = date("Y-m-d h:i:s");
	
	$customer_data_array = array("mobilenumber"=>$mobilenumber,"product_id"=>$product_id,"request_type"=>$request_type,"service_comments"=>$service_comments);
		$request_data = json_encode($customer_data_array,JSON_UNESCAPED_SLASHES);
		$request_action = "Customer New Request";
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
		
		$sql_cus = mysqli_query($acn_con,"select * from customer_master where mobile_number = '".$mobilenumber."' and status='1'");
		$fet_cus = mysqli_fetch_array($sql_cus);
		
		$sql_ven = mysqli_query($acn_con,"select * from customer_master where mobile_number = '".$fet_cus['integratedid']."'  and status='1' ");
		$fet_ven = mysqli_fetch_array($sql_ven);
		
		$sql_ins = mysqli_query($acn_con,"INSERT INTO customer_request_product
						(
						 vendor_id,
						 customer_id,
						 product_id,
						 request_type,
						 service_comments,
						 request_date,
						 entry_date,
						 service_status,
						 status
						 )
						VALUES
						(
						 '".$fet_ven['customer_id']."',
						 '".$fet_cus['customer_id']."',
						 '".$product_id."',
						 '".$request_type."',
						 '".addslashes($service_comments)."',
						 '".date("Y-m-d")."',
						 '".$entry_date."',
						 '1',
						 '1'
						 )
						");


			$customer_ref_id = mysqli_insert_id($acn_con);
					$cuss_idd = "REQ000".$customer_ref_id;
					
					$update_cus = mysqli_query($acn_con,"UPDATE customer_request_product SET request_id = '".$cuss_idd."' where id = '".$customer_ref_id."' ");  
			
			if($customer_ref_id!='')
			{
			$message = "New Request Raised.Please check the app";
				
                          $url = "http://appsms.in/api/sendsms?api_key=A4591041491484291083&to=".$fet_ven['mobile_number']."&message=".urlencode($message)."&senderid=MDEVDR";	
			
			
			   $ch=curl_init($url);
			   curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
			   $output=curl_exec($ch);
			   curl_close($ch);	
			   $a['status'] =700; 
			   $a['request_id'] = $cuss_idd;
			}
			else
			{
				$a['status'] =500; 
				$a['request_id'] = $cuss_idd;
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

		$customer_response_data_array = array("mobilenumber"=>$mobilenumber,"status"=>$a['status'],"request_id"=>$a['request_id']);
		$response_data = json_encode($customer_response_data_array,JSON_UNESCAPED_SLASHES);
		$response_action = "New Request";
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