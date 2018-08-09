<?php
include_once("../config.php");
$a=array();
$entry_date = date("Y-m-d h:i:s");

if(isset($_POST['vendor_id']))
{
	$vendorid = $_POST['vendor_id'];
	
	$typestatus = 5;
	
	$customer_data_array = array("vendorid"=>$vendorid,"request_id"=>$request_id,"typestatus"=>$typestatus);
		$request_data = json_encode($customer_data_array,JSON_UNESCAPED_SLASHES);
		$request_action = "Vendor Accept/Reject Forward Ticket";
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
		
	if($vendorid != "")
	{   
	$request_id = $_POST['request_id'];
				$sql_emp = mysqli_query($acn_con,"update customer_request_product set sub_vendor_id='".$vendorid."',service_status='".$typestatus."',reject_date='".date("Y-m-d H:i:s")."',forward_status=3 WHERE request_id='".$request_id."' " );
				
				//send sms to vendor reg: Reject Ticket
				$sql_vendor = mysqli_query($acn_con,"select vendor_id from  customer_request_product WHERE request_id='".$request_id."' " );
				$fet_vendor = mysqli_fetch_array($sql_vendor);
				
				//To get Vendor Number
				$sql_ven = mysqli_query($acn_con,"select * from customer_master where customer_id = '".$fet_vendor['vendor_id']."' and status='1' ");
				$fet_ven = mysqli_fetch_array($sql_ven);
				
				//Forward Ticket Message
				$message = "Vendor Rejected a ticket you have forwarded.please check the app";
				
				
                          $url = "http://appsms.in/api/sendsms?api_key=A4591041491484291083&to=".$fet_ven['mobile_number']."&message=".urlencode($message)."&senderid=MDEVDR";	
			
			
			   $ch=curl_init($url);
			   curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
			   $output=curl_exec($ch);
			   curl_close($ch);	
				
			    $a['status'] = 805;
		
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

		$customer_response_data_array = array("status"=>$a['status']);
		$response_data = json_encode($customer_response_data_array,JSON_UNESCAPED_SLASHES);
		$response_action = "Vendor Reject Forward Ticket Response";
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