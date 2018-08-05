<?php
include "db_user_details.php";

$sql1="SELECT * FROM tblTips";
$result1=mysqli_query($con,$sql1);
$response = array();
if(mysqli_num_rows($result1)>0)
{
	while($row1=mysqli_fetch_assoc($result1))
	{	
	
	    array_push($response,array(
			"mTitle"=>$row1['fld_title'],
			"mDescription"=>$row1['fld_description'],
			"mContent"=>$row1['fld_content'],
            "link"=>$row1['fld_link'],
            "mImageUrl"=>$row1['fld_img_url']
        ));
	}
}	
//displaying in json format 
	echo json_encode(array('result'=>$response));
?>