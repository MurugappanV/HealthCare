<?php
include_once("../config.php");
$a=array();
$entry_date = date("Y-m-d h:i:s");
$a['vendor_complete'] = array();
$a['subvendor_complete'] = array();
if(isset($_REQUEST['vendor_id']))
{
	$vendor_id = $_REQUEST['vendor_id'];
	
	$customer_data_array = array("vendor_id"=>$vendor_id);
		$request_data = json_encode($customer_data_array,JSON_UNESCAPED_SLASHES);
		$request_action = "Vendor Completed Request View";
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
		
	if($vendor_id != "")
	{
		//Vendor completed request and forward reject completed		
		$sql_emp = mysqli_query($acn_con,"SELECT * FROM customer_request_product WHERE (vendor_id = '".$vendor_id."')  and service_status=2 " );
		$count_emp = mysqli_num_rows($sql_emp);
		 while($fetch_emp = mysqli_fetch_array($sql_emp))
		 { 
		//Fetch Vendor Details
		$sql_vendor = mysqli_query($acn_con,"SELECT * FROM customer_master WHERE customer_id = '".$fetch_emp['customer_id']."' and status='1'  " );
		$fetch_vendor = mysqli_fetch_array($sql_vendor);
		//Fetch Product Details 
		$sql_product = mysqli_query($acn_con,"SELECT * FROM products_master WHERE product_id = '".$fetch_emp['product_id']."'  " );
		$fetch_product = mysqli_fetch_array($sql_product);
		
			if($fetch_emp['request_type']==1)
			{
				$service = "New Installation";
			}
			else if($fetch_emp['request_type']==2)
			{
				$service = "Service Request";
			}
		
			if($count_emp > 0)
			{
			  
				$vendor_complete = array();
				$vendor_complete['request_id'] = $fetch_emp['request_id'];
				$vendor_complete['customername'] =  $fetch_vendor['name'];
				$vendor_complete['mobilenumber'] =  $fetch_vendor['mobile_number'];
				$vendor_complete['address'] =  $fetch_vendor['address'];
				$vendor_complete['requestproduct'] =  $fetch_product['product_name'];
				$vendor_complete['request_date'] =  date("d-m-Y",strtotime($fetch_emp['request_date']));
				$vendor_complete['completeddate'] =  date("d-m-Y",strtotime($fetch_emp['completed_date']));
				$vendor_complete['completedcomments'] =  $fetch_emp['comments'];
				
					$a['status'] = 707;		
				array_push($a['vendor_complete'],$vendor_complete);	
				
			}
				
			else
			{	   
			    $a['status'] = 708;
			}
		 }//While Loop Ends
		 
		 //Forward Accept and completed
		 $sql_emp1 = mysqli_query($acn_con,"SELECT * FROM customer_request_product WHERE (sub_vendor_id='".$vendor_id."') and service_status=2 and (forward_status=2)" );
		$count_emp1 = mysqli_num_rows($sql_emp1);
		 while($fetch_emp1 = mysqli_fetch_array($sql_emp1))
		 {
		//Fetch Vendor Details
		$sql_vendor = mysqli_query($acn_con,"SELECT * FROM customer_master WHERE customer_id = '".$fetch_emp1['customer_id']."' and status='1'  " );
		$fetch_vendor = mysqli_fetch_array($sql_vendor);
		//Fetch Product Details 
		$sql_product = mysqli_query($acn_con,"SELECT * FROM products_master WHERE product_id = '".$fetch_emp1['product_id']."'  " );
		$fetch_product = mysqli_fetch_array($sql_product);
				
			if($count_emp1 > 0)
			{
			   $subvendor_complete = array();
				$subvendor_complete['request_id'] = $fetch_emp1['request_id'];
				//$subvendor_complete['customername'] =  $fetch_vendor['name'];
//				$subvendor_complete['mobilenumber'] =  $fetch_vendor['mobile_number'];
//				$subvendor_complete['address'] =  $fetch_vendor['address'];
				$subvendor_complete['requestproduct'] =  $fetch_product['product_name'];
				$subvendor_complete['request_date'] =  date("d-m-Y",strtotime($fetch_emp1['request_date']));
				$subvendor_complete['completeddate'] =  date("d-m-Y",strtotime($fetch_emp1['completed_date']));
				$subvendor_complete['completedcomments'] =  $fetch_emp1['comments'];
				$a['status'] = 707;		
				array_push($a['subvendor_complete'],$subvendor_complete);	
			
			}
				
			else
			{	   
			    $a['status'] = 708;
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
		$response_action = "Completed Profile View";
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