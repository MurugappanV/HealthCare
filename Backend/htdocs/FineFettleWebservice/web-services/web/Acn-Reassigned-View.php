<?php
include_once("../config.php");
$a=array();
$entry_date = date("Y-m-d h:i:s");
$a['request_list'] = array();
if(isset($_POST['vendor_id']))
{
	$vendorid = $_POST['vendor_id'];
	//service status whether service completed or not
	$statusreport = 2;
	
	$customer_data_array = array("vendor_id"=>$vendorid,"statusreport"=>$statusreport);
		$request_data = json_encode($customer_data_array,JSON_UNESCAPED_SLASHES);
		$request_action = "Vendor Reassign Ticket Request View";
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
		
		//Check if the Customer Code alreday exists 
		
		$sql_emp = mysqli_query($acn_con,"SELECT * FROM customer_request_product WHERE vendor_id = '".$vendorid."' and service_status=2 and forward_status=2" );
		
		$count_emp = mysqli_num_rows($sql_emp);
		 while($fetch_emp = mysqli_fetch_array($sql_emp))
		 {
		//Fetch Customer Details
		$sql_vendor = mysqli_query($acn_con,"SELECT * FROM customer_master WHERE customer_id = '".$fetch_emp['customer_id']."' and status='1'  " );
		
		$fetch_vendor = mysqli_fetch_array($sql_vendor);
		
		//Fetch Vendor Details
		$sql_ven = mysqli_query($acn_con,"SELECT * FROM customer_master WHERE customer_id = '".$fetch_emp['sub_vendor_id']."' and status='1'  " );
		
		$fetch_ven = mysqli_fetch_array($sql_ven);
		
		//Fetch Product Details 
		$sql_product = mysqli_query($acn_con,"SELECT * FROM products_master WHERE product_id = '".$fetch_emp['product_id']."'  " );
		$fetch_product = mysqli_fetch_array($sql_product);
		
			//if($fetch_emp['request_type']==1)
//			{
//				$service = "New Installation";
//			}
//			if($fetch_emp['request_type']==2)
//			{
//				$service = "Service Request";
//			}
		
			if($count_emp > 0)
			{
			   $request_list = array();
				$request_list['request_id'] = $fetch_emp['request_id'];
				$request_list['customername'] =  $fetch_vendor['name'];
				$request_list['customermobilenumber'] =  $fetch_vendor['mobile_number'];
				$request_list['customeraddress'] =  $fetch_vendor['address'];
				$request_list['requestproduct'] =  $fetch_product['product_name'];
				//request_list['request_type'] =  $service;
				$request_list['request_date'] =  $fetch_emp['request_date'];
				
				$request_list['subvendorname'] =  $fetch_ven['name'];
				$request_list['subvendormobilenumber'] =  $fetch_ven['mobile_number'];
				$request_list['subvendoraddress'] =  $fetch_ven['address'];
			
				array_push($a['request_list'],$request_list);	
				$a['status'] = 800;
			}
				
			else
			{	   
			    $a['status'] = 801;
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
		$response_action = "Vendor Reassign Ticket View Response";
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