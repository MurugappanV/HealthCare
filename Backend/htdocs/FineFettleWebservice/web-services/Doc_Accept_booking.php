<?php
include_once("../config.php");
$a=array();
$entry_date = date("Y-m-d h:i:s");

if(isset($_POST['doc_id']))
{
	$d_id = $_POST['doc_id'];
//$d_id=1;
	
	if($d_id != "")
	{   

	$accept_status = $_POST['accept_status'];
	    $p_id = $_POST['patient_id'];
	  //$accept_status=1;
	  //$p_id=1;


if($accept_status==1)
{
	
		$appointment_status = "Accepted";
		$sql_emp = mysqli_query($acn_con,"update user_req set status='".$accept_status."',appointment_status='".$appointment_status."' WHERE p_id=$p_id" );
			    $a['status'] = 200;
			    $a['Accept_status'] = 1;
				 $a['message'] = "Accepted";
				 
}
else 
{
	   $appointment_status = "Rejected";
		$sql_emp = mysqli_query($acn_con,"update user_req set status='".$accept_status."',appointment_status='".$appointment_status."' WHERE p_id=$p_id" );
			    $a['status'] = 201;
				 $a['Accept_status'] = 2;

				$a['message'] = "Rejected";

}
	}else{
			$a['status'] = 204;
	}
}
	
//else
//{
	//$a['status'] = 500;
//}
//Post JSON response back to Android Application
echo json_encode($a,JSON_UNESCAPED_SLASHES);

?>