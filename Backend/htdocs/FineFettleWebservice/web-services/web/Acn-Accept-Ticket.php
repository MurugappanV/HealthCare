<?php
include_once("../config.php");
$a=array();
$entry_date = date("Y-m-d h:i:s");

if(isset($_POST['vendor_id']))
{
	$vendorid = $_POST['vendor_id'];
	
	$customer_data_array = array("vendorid"=>$vendorid);
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
	{   $request_id = $_POST['request_id'];
		$typestatus = 4;
		
			$sql_state = mysqli_query($acn_con,"select status,service_status,forward_status from customer_request_product WHERE request_id='".$request_id."' " );
			$fet_status = mysqli_fetch_array($sql_state);
			
			if(($fet_status['service_status']==2)&&($fet_status['forward_status']==3))
			{
				$a['fwd_status'] = 11; //Reject Ticket and Completed
			}
			elseif(($fet_status['service_status']==2)&&($fet_status['forward_status']==2))
			{
				$a['fwd_status'] = 12; //Accept Ticket and Completed
			}
			elseif(($fet_status['service_status']==4)&&($fet_status['forward_status']==2))
			{
				$a['fwd_status'] = 13; //Accept Ticket and Not Completed
			}
		
				$sql_emp = mysqli_query($acn_con,"update customer_request_product set sub_vendor_id='".$vendorid."',service_status='".$typestatus."',forward_status=2 WHERE request_id='".$request_id."' " );
			    $a['status'] = 804;
		
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

		$customer_response_data_array = json_encode($a,JSON_UNESCAPED_SLASHES);
		$response_data = json_encode($a,JSON_UNESCAPED_SLASHES);
		$response_action = "Vendor Accept Forward Ticket Response";
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