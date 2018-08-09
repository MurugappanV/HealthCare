<?php
include_once("../config.php");
$a=array();
$entry_date = date("Y-m-d h:i:s");
if(isset($_POST['h_id']))
{
	
	//$doctor_id = $_POST['doc_id'];
	$h_id=$_POST['h_id'];
	$d_name = $_POST['d_name'];
	$d_hospital = $_POST['d_hospital'];
	$username = $_POST['username'];
	$password = $_POST['password'];
	$d_email = $_POST['d_email'];
	$gender = $_POST['gender'];
	$d_mobile = $_POST['d_mobile'];
	$dob = $_POST['dob'];
	$type = $_POST['type_status'];
	
	// priya here u have to add a field called d_degree in UI otherwise it will show error
	
	$degree=$_POST['d_degree'];
	$d_specialization=$_POST['d_specialization'];
	$d_experience=$_POST['d_experience'];
	$age=$_POST['age'];
	$bloodgroup=$_POST['bloodgroup'];
	$address=$_POST['address'];
	$city=$_POST['city'];
	$pincode=$POST['pincode'];
	
	/*$h_id='2';
	$d_name = 'sri';
	$d_hospital = 'HL';
	$username = 'sri';
	$password = 'hello';
	$d_email = 'sri@gmail.com';
	$gender = 'female';
	$d_mobile = 9087654321;
	$dob = '2000-05-09';
	$type = 0;
	
	$d_specialization='cardio';
	$d_experience='5yrs';
	$age=30;
	$bloodgroup='B-ve';
	$address='gandhipuram';
	$city='cbe';
	$pincode='630914';*/
	
		$sql_emp = mysqli_query($acn_con,"SELECT * FROM doc_login WHERE d_email = '$d_email'" );
		$count_emp = mysqli_num_rows($sql_emp);	
			if($count_emp > 0) //Already Registered 
			{
			  	 $fetch_emp = mysqli_fetch_array($sql_emp);
			
					   $update_cus = mysqli_query($acn_con,"UPDATE doc_login SET password = '$password' where d_email= '$d_email' ");  
					   
                       $a['name'] = $fetch_emp['d_name'];
					   $a['password'] = $password;
					   $a['status'] = 202;
					   $a['type_status']= $fetch_emp['req_type'];
					   $a['d_id'] = $fetch_emp['d_id'];
					   $type = $fetch_emp['req_type'];
			   		   $a['message'] = "Already Registered";

			}
			else //New Registration
			{
				
					//Check if the Vendor Code alreday exists 
				
					$sql_reg = mysqli_query($acn_con,"INSERT INTO doc_login
									(
									 h_id,
									 d_name,
									 d_hospital,
									 username,
									 password,
									 d_email,
									 gender,
									 d_mobile,
									 dob,
									 d_specialization,
									 d_experience,
									 age,
									 bloodgroup,
									 address,
									 city,
									 pincode,
									 req_type,
									 date,
									 d_degree,
									 confirmation
									 )
									VALUES
									(
									 $h_id,
									 '$d_name',
									 '$d_hospital',
									 '$username',
									 '$password',
									 '$d_email',
									 '$gender',
									 $d_mobile,
									 '$dob',
									 '$d_specialization',
									 '$d_experience',
									 $age,
									 '$bloodgroup',
									 '$address',
									 '$city',
									 $pincode,
									 '$type',
									 '$entry_date',
									 '$d_degree';
									 '0'
									 )
									");
					  
					//$customer_ref_id = mysqli_insert_id($acn_con);
					//$cuss_idd = "DR000".$customer_ref_id;
					
					//$update_cus = mysqli_query($acn_con,"UPDATE doctor_master SET customer_id = '".$cuss_idd."' where id = '".$customer_ref_id."' ");  
						$sql = mysqli_query($acn_con,"SELECT d_id FROM doc_login WHERE username = '".$username."'and  password = '".$password."'" );
						$did=mysqli_fetch_assoc($sql);
						$d_id=$did['d_id'];
					   $a['name'] = $d_name; 
					   $a['password'] = $password;
					   $a['status'] = 200;
					   $a['type_status'] = $type;
					   $a['d_id'] = $d_id;
					   $a['message'] = "Registered Successfully";

					
		
			}
		
		
}
	
else
{
$a['status'] = 500;}



//Post JSON response back to Android Application
echo json_encode($a,JSON_UNESCAPED_SLASHES);

?>