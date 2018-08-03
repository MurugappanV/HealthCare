<?php
include_once("../config.php");
$a=array();
$entry_date = date("Y-m-d h:i:s");
$a['message_list'] = array();
if(isset($_POST['d_id']))
{
	$doctor_id = $_POST['d_id'];
	
	if($doctor_id != "")
	{
		
		$sql_emp = mysqli_query($acn_con,"SELECT * FROM alaram_emergency_message_master WHERE status='1' " );
		
		$count_emp = mysqli_num_rows($sql_emp);
		 while($fetch_emp = mysqli_fetch_array($sql_emp))
		 {
	
			if($count_emp > 0)
			{
			   $message_list = array();
				$message_list['message_title'] =  $fetch_emp['emergency_message_title'];
				$message_list['message_descrition'] =  $fetch_emp['message_desc'];
				$message_list['date'] =  $fetch_emp['entry_date'];
			
							
				array_push($a['message_list'],$message_list);	
				$a['status'] = 200;
			}
				
			else
			{	   
			    $a['status'] = 201;
			}
		 }//While Loop Ends
	}
	else
	{
		$a['status'] = 203;	
	}
}
	
else
{
	$a['status'] = 500;
}

//Post JSON response back to Android Application
echo json_encode($a,JSON_UNESCAPED_SLASHES);

?>