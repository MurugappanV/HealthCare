<?php
include_once("../config.php");
$a=array();
$entry_date = date("Y-m-d h:i:s");
if(isset($_POST['d_id']))
{
	$d_id = $_POST['d_id'];
		//$d_id=5;
	if($d_id != "")
	{
		
		//Check if the Customer Code alreday exists 
		
		$sql_emp = mysqli_query($acn_con,"SELECT * FROM doc_login WHERE d_id = $d_id and confirmation='1'  " );
		$count_emp = mysqli_num_rows($sql_emp);
	
		if($count_emp > 0)
		{
		   $fetch_emp = mysqli_fetch_array($sql_emp);
		  
		    $a['d_id'] = $fetch_emp['d_id'];
			$a['d_name'] = $fetch_emp['d_name'];
			// priya note u have to add it in the UI regarding d_degree otherwise it will show error
			$a['d_degree']=$fetch_emp['d_degree'];
			$a['d_email'] = $fetch_emp['d_email'];
			$a['d_mobile'] = $fetch_emp['d_mobile'];
			$a['gender'] = $fetch_emp['gender'];
			$a['dob'] = $fetch_emp['dob'];
			$a['d_specialization'] = $fetch_emp['d_specialization'];
			$a['h_name'] = $fetch_emp['h_name'];
			
		
		    $a['status'] = 200;
		  		  
		}
		else
		{	   
		   //Send Response to Android
		   $a['status'] = 201;
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



//Post JSON response back to Android Application
echo json_encode($a,JSON_UNESCAPED_SLASHES);

?>