<?php
include_once("../config.php");
$a=array();
$entry_date = date("Y-m-d h:i:s");
$a['request_list'] = array();
$a['service_list'] = array();
if(isset($_POST['customer_id']))
{
	$customer_id = $_POST['customer_id'];
	$mobile_number = $_POST['mobilenumber'];
	
	$customer_data_array = array("customer_id"=>$customer_id,"mobilenumber"=>$mobile_number);
		$request_data = json_encode($customer_data_array,JSON_UNESCAPED_SLASHES);
		$request_action = "Customer Home screen ";
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
		
		$sql_emp = mysqli_query($acn_con,"SELECT * FROM customer_request_product WHERE customer_id = '".$customer_id."' and request_type=1 ORDER BY id DESC LIMIT 3 " );
		$count_emp = mysqli_num_rows($sql_emp);
		 while($fetch_emp = mysqli_fetch_array($sql_emp))
		 {
		//Fetch Vendor Details
		$sql_vendor = mysqli_query($acn_con,"SELECT * FROM customer_master WHERE customer_id = '".$fetch_emp['vendor_id']."' and status='1' " );
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
				
				$request_list['requestproduct'] =  $fetch_product['product_name'];
					$a['status'] = 902;		
				array_push($a['request_list'],$request_list);	
				
			}
				
			else
			{	   
			    $a['status'] = 901;
			}
		 }//While Loop Ends
		 
		 $sql_emp1 = mysqli_query($acn_con,"SELECT * FROM customer_request_product WHERE customer_id = '".$customer_id."' and request_type=2  ORDER BY id DESC LIMIT 3 " );
		$count_emp1 = mysqli_num_rows($sql_emp1);
		 while($fetch_emp1 = mysqli_fetch_array($sql_emp1))
		 {
		//Fetch Vendor Details
		$sql_vendor = mysqli_query($acn_con,"SELECT * FROM customer_master WHERE customer_id = '".$fetch_emp1['vendor_id']."' and status='1'  " );
		$fetch_vendor = mysqli_fetch_array($sql_vendor);
		//Fetch Product Details 
		$sql_product = mysqli_query($acn_con,"SELECT * FROM products_master WHERE product_id = '".$fetch_emp1['product_id']."'  " );
		$fetch_product = mysqli_fetch_array($sql_product);
		
			if($fetch_emp['request_type']==1)
			{
				$service = "New Installation";
			}
			if($fetch_emp['request_type']==2)
			{
				$service = "Service Request";
			}
		
			if($count_emp1 > 0)
			{
			   $service_list = array();
				
				$service_list['requestproduct'] =  $fetch_product['product_name'];
				$a['status'] = 902;		
				array_push($a['service_list'],$service_list);	
			
			}
				
			else
			{	   
			    $a['status'] = 901;
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
		$response_action = "Customer Home screen Response";
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