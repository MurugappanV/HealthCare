<?php
include 'db_user_details.php';

//$json=file_get_contents("php://input");

//$_POST=json_decode($json);
/*if($_SERVER['REQUEST_METHOD']=='POST')
{
	$u_id=$_POST['id'];
	$u_name=$_POST['u_name'];
	$hospital1=$_POST['hospital1'];
	$date1=$_POST['date1'];
	$medic1=$_POST['medic1'];
	$hospital2=$_POST['hospital2'];
	$date2=$_POST['date2'];
	$medic2=$_POST['medic2'];
	$hospital3=$_POST['hospital3'];
	$date3=$_POST['date3'];
	$medic3=$_POST['medic3'];
	$hospital4=$_POST['hospital4'];
	$date4=$_POST['date4'];
	$medic4=$_POST['medic4'];
	$hospital5=$_POST['hospital5'];
	$date5=$_POST['date5'];
	$medic5=$_POST['medic5'];
	
	
	
	// or write like $p_name = $data->{'name'};
*/
	$u_id=3;
	$u_name="ajay";
	$hospital1="KG Hospital, coimbatore";
	$date1="2012-05-09";
	$medic1="Amoxilin capsules";
	$hospital2="KG Hospital, coimbatore";
	$date2="2012-05-09";
	$medic2="Amoxilin capsules";
	$hospital3="KG Hospital, coimbatore";
	$date3="2012-05-09";
	$medic3="Amoxilin capsules";
	$hospital4="KG Hospital, coimbatore";
	$date4="2012-05-09";
	$medic4="Amoxilin capsules";
	$hospital5="KG Hospital, coimbatore";
	$date5="2012-05-09";
	$medic5="Amoxilin capsules";
	
	
	$sql="INSERT INTO user_med_history (u_id,u_name,hospital1,date1,medic1,hospital2,date2,medic2,hospital3,date3,medic3,hospital4,date4,medic4,hospital5,date5,medic5) VALUES ($u_id,'$u_name','$hospital1','$date1','$medic1','$hospital2','$date2','$medic2','$hospital3','$date3','$medic3','$hospital4','$date4','$medic4','$hospital5','$date5','$medic5')";
	
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
        $response["message"] = "Data does not inserted into database.";

        // echoing JSON response
        echo json_encode($response);// Here you are echoing
		//echo "Try again";
	}
	
//}
mysqli_close($con);
?>