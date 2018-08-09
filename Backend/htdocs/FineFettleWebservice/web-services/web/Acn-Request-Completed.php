<?php
include_once("../config.php");
$a=array();
$entry_date = date("Y-m-d h:i:s");

if(isset($_POST['request_id']))
{
	$requestid = $_POST['request_id'];
	$comments = $_POST['comments'];
	
	$customer_data_array = array("request_id"=>$requestid,"comments"=>$comments);
		$request_data = json_encode($customer_data_array,JSON_UNESCAPED_SLASHES);
		$request_action = "Vendor Request Completed";
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
		
	if($requestid != "")
	{
		$sql_ins = mysqli_query($acn_con,"update customer_request_product set service_status='2',completed_date='".date("Y-m-d")."',comments='".$comments."' where request_id='".$requestid."'");
		
		$a['status'] = 703;	
	}
	else
	{
		$a['status'] = 704;	
	}
}
	
else
{
	$a['status'] = 500;
}


		$customer_response_data_array = array("request_id"=>$requestid,"status"=>$a['status']);
		$response_data = json_encode($customer_response_data_array,JSON_UNESCAPED_SLASHES);
		$response_action = "Vendor Completed Response";
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