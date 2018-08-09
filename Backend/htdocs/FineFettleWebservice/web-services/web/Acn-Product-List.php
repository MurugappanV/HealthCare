<?php
include_once("../config.php");
$entry_date = date("Y-m-d h:i:s");
if(isset($_POST['mobilenumber']))
{
	$a=array();
	$a['product_list'] = array();
	$mobilenumber = $_POST['mobilenumber'];
	
	$sql_emp = mysqli_query($acn_con,"SELECT * FROM customer_master WHERE mobile_number = '".$mobilenumber."' and status='1' " );
		$count_emp = mysqli_num_rows($sql_emp);
		$fetch_cust = mysqli_fetch_array($sql_emp);
	
$sql_ven = mysqli_query($acn_con,"SELECT * FROM customer_master WHERE mobile_number = '".$fetch_cust['integratedid']."' and status='1' " );
		$count_ven = mysqli_num_rows($sql_ven);
		$fetch_ven = mysqli_fetch_array($sql_ven);

$sql_registration = mysqli_query($acn_con,"SELECT * FROM products_master WHERE FIND_IN_SET(id,'".$fetch_ven ['services']."') and status=1 ");
$count= mysqli_num_rows($sql_registration);	
$a['count'] = $count;		
                   while($fetch_registration = mysqli_fetch_array($sql_registration))
                   {
					   
                    $product_list = array();
                    $product_list['product_id'] =  $fetch_registration['product_id'];
                    $product_list['productname'] =  $fetch_registration['product_name'];
                    
                    array_push($a['product_list'],$product_list);
                   }

                    $a['status'] = 102;
	
}
else
{
	$a['status'] = 500;
}


		$customer_response_data_array = array("mobilenumber"=>$mobilenumber);
		$response_data = json_encode($customer_response_data_array,JSON_UNESCAPED_SLASHES);
		$response_action = "Trying to Get Product Name";
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