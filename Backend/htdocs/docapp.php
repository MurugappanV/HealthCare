

<?php

//Priya the changes in this file is receiving u_id from you and updating in db. Since i am having a foreign key, you have to send only the u_id already exists in user_reg (user reggistration)
//so send u_id from user interface



include 'db_user_details.php';

if($_SERVER['REQUEST_METHOD']=='POST')
{
    $u_id=$_POST['u_id'];
    $d_id=$_POST['d_id'];
	$p_age=$_POST['age'];
	$p_name=$_POST['name'];
	$doc_name=$_POST['d_name'];
	$slot=$_POST['slot'];
	$date=$_POST['date'];
	$hos_name=$_POST['h_name'];
	$p_name=$_POST['name'];
	$h_issue=$_POST['h_issue'];
	$p_addr=$_POST['addr'];
	$doc_specialization=$_POST['d_special'];
	
	$date=$_POST['date'];
	
	
	// or write like $p_name = $data->{'name'};

	/*
	$u_id=2;
	$p_name="ajay";
	$p_age=20;
	$p_addr="12A, Velar street, kovai";
	$h_issue="Fever";
	$doc_specialization="General";
	$doc_name="Sivaram";
	$hos_name="K.G";
	$date="2018-07-05";
	$slot="7-9am";*/
	
	$sql="INSERT INTO user_req (u_id,d_id,p_name,p_age,p_addr,h_issue,doc_specialization,doc_name,hos_name,date,slot, appointment_status) VALUES ($u_id,$d_id,'$p_name',$p_age,'$p_addr','$h_issue','$doc_specialization','$doc_name','$hos_name','$date','$slot', 'PENDING')";
	
	$res=mysqli_query($con,$sql);
	if($res)
	{
		$response["success"] = 1;
        $response["message"] = "Data inserted into database.";

        // echoing JSON response
        echo json_encode($response);// Here you are echoing
		
		//echo "Data Submitted Successfully";
	}
	else
	{
		$response["Failure"] = 0;
        $response["message"] = "INSERT INTO user_req (u_id,p_name,p_age,p_addr,h_issue,doc_specialization,doc_name,hos_name,date,slot) VALUES ($u_id,'$p_name',$p_age,'$p_addr','$h_issue','$doc_specialization','$doc_name','$hos_name','$date','$slot')";

        // echoing JSON response
        echo json_encode($response);// Here you are echoing
		//echo "Try again";
	}
	
}
mysqli_close($con);
?>

{"Failure":0,"message":"INSERT INTO user_req (u_id,p_name,p_age,p_addr,h_issue,doc_specialization,doc_name,hos_name,date,slot) VALUES (1,'Murugappan',52,'34,678','fff','lungs','Dr. Naresh Trehan','State Hospital','2018-08-10','1pm')"}