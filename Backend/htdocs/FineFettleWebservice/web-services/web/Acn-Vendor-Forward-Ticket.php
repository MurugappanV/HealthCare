<?php
include_once("../config.php");
$a=array();
$entry_date = date("Y-m-d h:i:s");

if(isset($_POST['vendor_id']))
{
	$vendorid = $_POST['vendor_id'];
	$subvendor_id = $_POST['subvendor_id'];
	$request_id = $_POST['request_id'];
	//service status whether service completed or not
	
	$customer_data_array = array("vendorid"=>$vendorid,"subvendor_id"=>$subvendor_id,"request_id"=>$request_id);
		$request_data = json_encode($customer_data_array,JSON_UNESCAPED_SLASHES);
		$request_action = "Vendor Forward Ticket To Another Vendor";
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
	{   $request_id = $_POST['request_id'];
		
				$sql_emp = mysqli_query($acn_con,"update customer_request_product set sub_vendor_id='".$subvendor_id."',service_status=3,forward_date='".date("Y-m-d h:i:s")."',forward_status=1 WHERE request_id='".$request_id."' " );
		
				$forward_status =1;
				
				$sql_ins = mysqli_query($acn_con,"INSERT INTO forward_vendor_ticket
						(
						 request_id,
						 vendor_id,
						 subvendor_id,
						 forward_date,
						 forward_status,
						 status
						 )
						VALUES
						(
						 '".$request_id."',
						 '".$vendorid."',
						 '".$subvendor_id."',
						 '".$entry_date."',
						 '".$forward_status."',
						 '1'
						 )
						");
				
				$vendor_ref_id = mysqli_insert_id($acn_con);
				$cuss_idd = "FWD000".$vendor_ref_id;
					
				$update_cus = mysqli_query($acn_con,"UPDATE forward_vendor_ticket SET forward_id = '".$cuss_idd."' where id = '".$vendor_ref_id."' ");  
				
				//To get Vendor Number
				$sql_ven = mysqli_query($acn_con,"select * from customer_master where customer_id = '".$subvendor_id."' and status='1' ");
				$fet_ven = mysqli_fetch_array($sql_ven);
				
				//Forward Ticket Message
				$message = "Vendor forwarded ticket to you.please check the app";
								

                $url = "http://appsms.in/api/sendsms?api_key=A4591041491484291083&to=".$fet_ven['mobile_number']."&message=".urlencode($message)."&senderid=MDEVDR";	
								
			
			   $ch=curl_init($url);
			   curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
			  $output=curl_exec($ch);
			   curl_close($ch);	
				
			    $a['status'] = 705;
				$a['forward_ticket'] = $cuss_idd;
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
		$response_action = "Vendor Forward Ticket To Another Vendor Response";
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