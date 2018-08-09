<?php
include_once("../config.php");
$a=array();
$entry_date = date("Y-m-d h:i:s");
$a['comment_list'] = array();
if(isset($_REQUEST['mobilenumber']))
{
	$mobile_number = $_REQUEST['mobilenumber'];
	
	$customer_data_array = array("mobilenumber"=>$mobile_number);
		$request_data = json_encode($customer_data_array,JSON_UNESCAPED_SLASHES);
		$request_action = "Customer Comments View";
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
		
	if($mobile_number != "")
	{
		
		//Check if the Customer Code alreday exists 
		
		$sql_emp = mysqli_query($acn_con,"SELECT * FROM customer_master WHERE integratedid = '".$mobile_number."' and status='1'  " );
		
		$fetch_emp = mysqli_fetch_array($sql_emp);
		 
		 
		//Fetch Customer Details
		$sql_vendor = mysqli_query($acn_con,"SELECT * FROM customer_feedback WHERE customer_id = '".$fetch_emp['customer_id']."'  " );
		$count_emp = mysqli_num_rows($sql_vendor);
		while($fetch_vendor = mysqli_fetch_array($sql_vendor))
		{
		
		
			if($count_emp > 0)
			{
			   	$comment_list = array();
				$comment_list['comments'] = $fetch_vendor['comments'];
				$comment_list['customername'] =  $fetch_vendor['customer_name'];
				$comment_list['customermobilenumber'] =  $fetch_vendor['mobile_no'];
				$comment_list['date'] =  date("Y-m-d",strtotime($fetch_vendor['entry_date']));
			
				array_push($a['comment_list'],$comment_list);	
				$a['status'] = 802;
			}
				
			else
			{	   
			    $a['status'] = 803;
			}
		}//While Ends
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


		//$customer_response_data_array = array("status"=>$a['status'],"Mobile number"=>$mobile_number);
		$response_data = json_encode($a,JSON_UNESCAPED_SLASHES);
		$response_action = "Comments Response";
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