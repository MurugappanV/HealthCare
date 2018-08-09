<?php
include 'db_user_details.php';
include "sha256hash.php";
if($_SERVER['REQUEST_METHOD']=='POST')
{
	$u_id=$_POST['id'];
	$u_name=$_POST['u_name'];
	$hospital6=$_POST['hospital6'];
	$date6=$_POST['date6'];
	$medic6=$_POST['medic6'];
	$doc6=$_POST['doc6'];
	$issue6=$_POST['issue6'];
	
	
	// or write like $p_name = $data->{'name'};

/*	$p_id=2;
	$ppid=shahash($p_id);
	$p_name="sneha";
	$ppname=shahash($p_name);
	$hospital6="KG Hospital, karur";
	$date6="2012-05-09";
	$medic6="Dollo6501";
*/	
	$sql="SELECT * FROM user_med_history WHERE crypt_p_id='$ppid' and p_name='$ppname'";

$result=mysqli_query($con,$sql);

if(mysqli_num_rows($result)>0)
{
	while($row=mysqli_fetch_assoc($result))
	{
		$p_id=$row["p_id"];
		$p_name=$row["p_name"];
		$hospital1=$row["hospital1"];
		$date1=$row["date1"];
		$medic1=$row["medic1"];
		$doc1=$row["doc1"];
		$issue1=$row["issue1"];
		$hospital2=$row["hospital2"];
		$date2=$row["date2"];
		$medic2=$row["medic2"];
		$doc2=$row["doc2"];
		$issue2=$row["issue2"];
		$hospital3=$row["hospital3"];
		$date3=$row["date3"];
		$medic3=$row["medic3"];
		$doc3=$row["doc3"];
		$issue3=$row["issue3"];
		$hospital4=$row["hospital4"];
		$date4=$row["date4"];
		$medic4=$row["medic4"];
		$doc4=$row["doc4"];
		$issue4=$row["issue4"];
		$hospital5=$row["hospital5"];
		$date5=$row["date5"];
		$medic5=$row["medic5"];
		$doc5=$row["doc5"];
		$issue5=$row["issue5"];
	}
}	
	
	$sql1="UPDATE user_med_history SET hospital1='$hospital2', date1='$date2', medic1='$medic2',doc1="doc2",issue1="issue2", hospital2='$hospital3', date2='$date3', medic2='$medic3',doc2="doc3",issue2="issue3", hospital3='$hospital4', date3='$date4', medic3='$medic4',doc3="doc4",issue3="issue4", hospital4='$hospital5', date4='$date5', medic4='$medic5',doc4="doc5",issue4="issue5", hospital5='$hospital6', date5='$date6', medic5='$medic6',doc5="doc6",issue5="issue6", WHERE crypt_p_id='$ppid'";
	
	$res=mysqli_query($con,$sql1);
	if($res)
	{
		$response["success"] = 1;
        $response["message"] = "Your profile has been updated successfully";

        // echoing JSON response
        echo json_encode($response);// Here you are echoing
		
		//echo "Your profile has been updated successfully";
	}
	else
	{
		$response["Failure"] = 0;
        $response["message"] = "Data does not inserted into database.";

        // echoing JSON response
        echo json_encode($response);// Here you are echoing
		//echo "Try again";
	}
}
mysqli_close($con);


?>