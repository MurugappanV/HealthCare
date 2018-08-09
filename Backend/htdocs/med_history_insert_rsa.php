<?php
include 'db_user_details.php';
include 'sha256hash.php';
include 'rsaencrypt.php';
//$json=file_get_contents("php://input");

//$_POST=json_decode($json);
/*if($_SERVER['REQUEST_METHOD']=='POST')
{
	
	$p_name=$_POST['u_name'];
	$p_id=$_POST['p_id'];
	
	$ppname=shahash($p_name);
	$ppid=shahash($p_id);
	
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
	
*/	
	
	// or write like $p_name = $data->{'name'};

	list($privKey,$pubKey)=setup();
	
	$p_name="sneha";
	$p_id=2;
	
	$ppid=shahash($p_id);
	$ppname=shahash($p_name);
	
	$hospital1=rsa_encrypt("KG Hospital, coimbatore",$pubKey);
	$date1=rsa_encrypt("2012-05-09",$pubKey);
	$medic1=rsa_encrypt("Amoxilin capsules",$pubKey);
	$hospital2=rsa_encrypt("KG Hospital, coimbatore",$pubKey);
	$date2=rsa_encrypt("2012-05-09",$pubKey);
	$medic2=rsa_encrypt("Amoxilin capsules",$pubKey);
	$hospital3=rsa_encrypt("KG Hospital, coimbatore",$pubKey);
	$date3=rsa_encrypt("2012-05-09",$pubKey);
	$medic3=rsa_encrypt("Amoxilin capsules",$pubKey);
	$hospital4=rsa_encrypt("KG Hospital, coimbatore",$pubKey);
	$date4=rsa_encrypt("2012-05-09",$pubKey);
	$medic4=rsa_encrypt("Amoxilin capsules",$pubKey);
	$hospital5=rsa_encrypt("KG Hospital, coimbatore",$pubKey);
	$date5=rsa_encrypt("2012-05-09",$pubKey);
	$medic5=rsa_encrypt("Amoxilin capsules",$pubKey);
	
	
echo	$sql="INSERT INTO user_med_history (p_id,crypt_p_id,p_name,hospital1,date1,medic1,hospital2,date2,medic2,hospital3,date3,medic3,hospital4,date4,medic4,hospital5,date5,medic5,privKey,pubKey) VALUES ($p_id,'$ppid','$ppname','$hospital1','$date1','$medic1','$hospital2','$date2','$medic2','$hospital3','$date3','$medic3','$hospital4','$date4','$medic4','$hospital5','$date5','$medic5','$privKey','$pubKey')";
	
	$res=mysqli_query($con,$sql);
	if($res)
	{
		$response["success"] = 1;
        $response["message"] ="Data inserted into database." ;

        // echoing JSON response
        echo json_encode($response);// Here you are echoing
		
		//echo "Data Submitted Successfully";
	}
	else
	{
		$response["Failure"] = 0;
        $response["message"] = $con->error;

        // echoing JSON response
        echo json_encode($response);// Here you are echoing
		//echo "Try again";
	}
	
//}
mysqli_close($con);
?>