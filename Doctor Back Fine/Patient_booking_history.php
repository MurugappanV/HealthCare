<?php
include_once("../config.php");
$a=array();
$entry_date = date("Y-m-d h:i:s");
$a['booking_list'] = array();
if(isset($_POST['d_id']))
{
	$d_id = $_POST['d_id'];
	//$d_id=2;
	if($d_id != "")
	{
		
		$sql_emp = mysqli_query($acn_con,"SELECT * FROM user_req WHERE d_id = $d_id ");
		
		$count_emp = mysqli_num_rows($sql_emp);
		 while($fetch_emp = mysqli_fetch_array($sql_emp))
		 {
	
			if($count_emp > 0)
			{
			   $booking_list = array();
				$booking_list['d_id'] = $fetch_emp['d_id'];
				$booking_list['p_name'] =  $fetch_emp['p_name'];
				$booking_list['p_age'] =  $fetch_emp['p_age'];
				$booking_list['date'] =  $fetch_emp['date'];
				$booking_list['slot'] =  $fetch_emp['slot'];
				$booking_list['h_issue'] =  $fetch_emp['h_issue'];
				$booking_list['appointment_status'] =  $fetch_emp['appointment_status'];
							
						
							rsort($a['booking_list']);
						
				array_push($a['booking_list'],$booking_list);	
				$a['status'] = 200;
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

//Post JSON response back to Android Application
echo json_encode($a,JSON_UNESCAPED_SLASHES);

?>