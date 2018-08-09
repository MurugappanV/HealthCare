<?php
include_once("../config.php");
$a=array();
$entry_date = date("Y-m-d h:i:s");
if(isset($_POST['d_id']))
{
	$d_id = $_POST['d_id'];
	$u_id=$_POST['u_id'];
	$p_id=$_POST['p_id'];
	$p_name=$_POST['p_name'];
	$p_age=$_POST['p_age'];
	$p_disease = $_POST['p_disease'];
	$p_condition=$_POST['p_condition'];
	$prescription=$_POST['prescription'];
	$mobile = $_POST['mobile'];
	$entry_date = $_POST['entry_date'];
	
	
	/*$d_id = 1;
	$u_id=1;
	$p_id=2;
	$p_name="sivani";
	$p_age=20;
	$p_disease = "fever";
	$p_condition="good";
	$prescription="dollo capsules 2";
	$mobile = 8769504321;
	$entry_date = "2018-09-09";*/

					$sql_reg = mysqli_query($acn_con,"INSERT INTO doc_prescription
									(
									 d_id,
									 u_id,
									 p_id,
									 p_name,
									 p_age,
									 p_disease,
									 p_condition,
									 prescription,
									 mobile,
									 entry_date,
									 status
									 )
									VALUES
									(
									 '".$d_id."',
									 '".$u_id."',
									 '".$p_id."',
									 '".$p_name."',
									 '".$p_age."',
									 '".$p_disease."',
									 '".$p_condition."',
									 '".$prescription."',
									 '".$mobile."',
									 '".$entry_date."',
									 '1' )
									");
					  
					//$customer_ref_id = mysqli_insert_id($acn_con);
					
					$sql_emp = mysqli_query($acn_con,"SELECT * FROM doc_prescription WHERE d_id = '".$d_id."' and status='1'  " );
					   $fetch_emp = mysqli_fetch_array($sql_emp);
  
						$a['d_id'] = $fetch_emp['d_id'];
						$a['p_name'] = $fetch_emp['p_name'];
						$a['p_condition'] = $fetch_emp['p_condition'];
					    $a['status'] = 200;
					    $a['message'] = "Sent Sucessfully";

					
		
		
		
}
	else
{
	$a['status'] = 500;
}



//Post JSON response back to Android Application
echo json_encode($a,JSON_UNESCAPED_SLASHES);

?>