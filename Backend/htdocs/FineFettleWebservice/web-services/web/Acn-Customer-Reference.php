<?php
include_once("../config.php");
$a=array();
$entry_date = date("Y-m-d h:i:s");
if(isset($_POST['mobilenumber']))
{
	$Mobilenumber = $_POST['mobilenumber'];
	$customer_id = $_POST['customer_id'];
	$name = $_POST['name'];
	$comments = $_POST['comments'];
	
	$customer_data_array = array("mobilenumber"=>$Mobilenumber,"name"=>$name,"comments"=>$comments,"customer_id"=>$customer_id);
		$request_data = json_encode($customer_data_array,JSON_UNESCAPED_SLASHES);
		$request_action = "Customer Feedback Request";
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
		
	if($Mobilenumber != "")
	{
		//Check if the Customer Code alreday exists 
		
		$sql_emp = mysqli_query($acn_con,"SELECT * FROM customer_master WHERE mobile_number = '".$Mobilenumber."' and status='1'  " );
		$count_emp = mysqli_num_rows($sql_emp);
		
		if($count_emp > 0)
		{
		   
		   $sql_ins =  mysqli_query($acn_con,"INSERT INTO customer_feedback
						(
						 customer_id,
						 customer_name,
						 mobile_no,
						 comments,
						 entry_date,
						 status
						 )
						VALUES
						(
						 '".$customer_id."',
						 '".$name."',
						 '".$Mobilenumber."',
						  '".addslashes($comments)."',
						 '".$entry_date."',
						 '1'
						 )
						");
		   			
		    $a['status'] = 600;
		  		  
		}
		else
		{	   
		   //Send Response to Android
		   $a['status'] = 601;
		}
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


		//$customer_response_data_array = array("mobilenumber"=>$Mobilenumber,"name"=>$name,"comments"=>$comments,"status"=>$a['status']);
		$response_data = json_encode($a,JSON_UNESCAPED_SLASHES);
		$response_action = "Customer Feedback Response";
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