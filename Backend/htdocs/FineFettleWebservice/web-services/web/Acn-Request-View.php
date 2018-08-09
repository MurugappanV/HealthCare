<?php
include_once("../config.php");
$a=array();
$entry_date = date("Y-m-d h:i:s");
$a['request_list'] = array();
if(isset($_POST['customer_id']))
{
	$customer_id = $_POST['customer_id'];
	
	//service status whether service completed or not
	$statusreport = $_POST['statusreport'];
	
	$customer_data_array = array("customer_id"=>$customer_id,"statusreport"=>$statusreport);
		$request_data = json_encode($customer_data_array,JSON_UNESCAPED_SLASHES);
		$request_action = "Customer Request View";
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
		
	if($customer_id != "")
	{
		
		//Check if the Customer Code alreday exists 
		
		$sql_emp = mysqli_query($acn_con,"SELECT * FROM customer_request_product WHERE customer_id = '".$customer_id."' and service_status='".$statusreport."'  " );
		$count_emp = mysqli_num_rows($sql_emp);
		 while($fetch_emp = mysqli_fetch_array($sql_emp))
		 {
		//Fetch Vendor Details
		$sql_vendor = mysqli_query($acn_con,"SELECT * FROM customer_master WHERE customer_id = '".$fetch_emp['vendor_id']."' " );
		$fetch_vendor = mysqli_fetch_array($sql_vendor);
		//Fetch Product Details 
		$sql_product = mysqli_query($acn_con,"SELECT * FROM products_master WHERE product_id = '".$fetch_emp['product_id']."'  " );
		$fetch_product = mysqli_fetch_array($sql_product);
		
			if($fetch_emp['request_type']==1)
			{
				$service = "New Installation";
			}
			if($fetch_emp['request_type']==2)
			{
				$service = "Service Request";
			}
		
			if($count_emp > 0)
			{
			   $request_list = array();
				$request_list['request_id'] = $fetch_emp['request_id'];
				$request_list['serviceprovidername'] =  $fetch_vendor['name'];
				$request_list['requestproduct'] =  $fetch_product['product_name'];
				$request_list['service_comments'] =  $fetch_emp['service_comments'];
				$request_list['request_type'] =  $service;
				$request_list['request_date'] =  $fetch_emp['request_date'];
				$request_list['completed_date'] =  $fetch_emp['completed_date'];
				$request_list['service_status'] =  $fetch_emp['service_status'];
			
				array_push($a['request_list'],$request_list);	
				$a['status'] = 701;
			}
				
			else
			{	   
			    $a['status'] = 702;
			}
		 }//While Loop Ends
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
		$response_action = "Profile View Response";
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