<?php
include_once("../config.php");
$a=array();
$entry_date = date("Y-m-d h:i:s");
$a['vendor_list'] = array();
if(isset($_POST['vendor_id']))
{
	$vendorid = $_POST['vendor_id'];
	
	//service status whether service completed or not
	
	$customer_data_array = array("vendorid"=>$vendorid);
		$request_data = json_encode($customer_data_array,JSON_UNESCAPED_SLASHES);
		$request_action = "Vendor Request View";
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
		
		$sql_emp = mysqli_query($acn_con,"SELECT * FROM customer_master WHERE req_type=2 and status=1 and customer_id!='".$vendorid."' ORDER BY address " );
		
		$count_emp = mysqli_num_rows($sql_emp);
		 while($fetch_emp = mysqli_fetch_array($sql_emp))
		 {
	
			if($count_emp > 0)
			{
			    $vendor_list = array();
				$vendor_list['subvendor_id'] = $fetch_emp['customer_id'];
				$vendor_list['vendorname'] =  $fetch_emp['name'];
				$vendor_list['vendormobilenumber'] =  $fetch_emp['mobile_number'];
				$vendor_list['vendoraddress'] =  $fetch_emp['address'];
							
				array_push($a['vendor_list'],$vendor_list);	
				$a['status'] = 705;
			}
				
			else
			{	   
			    $a['status'] = 706;
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
		$response_action = "Vendor List Response";
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