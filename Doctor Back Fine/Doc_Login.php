<?php
include_once("../config.php");
$a=array();
$entry_date = date("Y-m-d h:i:s");
if(isset($_POST['username']))
{
	$username = $_POST['username'];
	$password = $_POST['password'];
	if($username != "" && $password!="")
	{
		//echo "SELECT * FROM doc_login WHERE username = '".$username."'and  password = '".$password."' and confirmation='1' ";
		
		$sql_emp = mysqli_query($acn_con,"SELECT * FROM doc_login WHERE username = '".$username."'and  password = '".$password."' and confirmation='1' " );
		
		$count_emp = mysqli_num_rows($sql_emp);
		
		if($count_emp > 0)
		{
		  
		  $fetch_emp = mysqli_fetch_array($sql_emp);
		   
		   $a['d_id'] = $fetch_emp['d_id'];
		   $a['d_mobile'] = $fetch_emp['d_mobile'];
		   $a['d_name'] = $fetch_emp['d_name'];
		   $a['status'] = 100;
		   $a['message'] = "Login Sucess";

		}
		else
		{	   
		   //Send Response to Android
		   $a['status'] = 101;
		   $a['message'] = "Invalid Username or Password";
		}
	}
	else
	{
		$a['status'] = 209;	
	}
}
	
else
{
	$a['status'] = 500;
}

//Post JSON response back to Android Application
echo json_encode($a,JSON_UNESCAPED_SLASHES);

?>