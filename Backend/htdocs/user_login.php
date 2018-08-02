<?php
include "db_user_details.php";
$u_name = $_POST['user'];
$pass = $_POST['pass'];
//$u_name="Ramya";
//$pass="hello";
$sql = "Select * from user_reg where u_name= '$u_name' and pass = '$pass'";
$result=mysqli_query($con,$sql);
$response=array();
if(mysqli_num_rows($result)>0)
{
	while($row=mysqli_fetch_assoc($result))
	{
		$response["u_id"]=$row["u_id"];
		$response["password"]=$row["pass"];
		$response["uname"]=$row["u_name"];
		
	}
}
echo json_encode($response);


?>

