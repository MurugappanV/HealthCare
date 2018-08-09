<?php
include_once("../config.php");
$a=array();
$entry_date = date("Y-m-d h:i:s");
$a['request_list'] = array();
if(isset($_POST['customer_id']))
{
	$vendorid = $_POST['customer_id'];
	$mobile_number = $_POST['mobilenumber'];
		
	$customer_data_array = array("vendorid"=>$vendorid,"mobile_number"=>$mobile_number);
		$request_data = json_encode($customer_data_array,JSON_UNESCAPED_SLASHES);
		$request_action = "Vendor Request Count";
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
		
		$sql_emp = mysqli_query($acn_con,"SELECT * FROM customer_master WHERE integratedid = '".$mobile_number."' and status='1' " );
		$count_emp = mysqli_num_rows($sql_emp);
		
		$sql_rqst = mysqli_query($acn_con,"SELECT * FROM customer_request_product WHERE vendor_id = '".$vendorid."' and (service_status=1 or service_status=5 )" );
		$count_rqst = mysqli_num_rows($sql_rqst);
		
				$a['request_count'] = $count_rqst;
				$a['customer_count'] = $count_emp;
				$a['status'] = 900;
			
	}
	else
	{
		$a['status'] = 901;	
	}
}
	
else
{
	$a['status'] = 500;
}


		$customer_response_data_array = json_encode($a,JSON_UNESCAPED_SLASHES);
		$response_data = json_encode($a,JSON_UNESCAPED_SLASHES);
		$response_action = "Vendor Request Count Response";
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