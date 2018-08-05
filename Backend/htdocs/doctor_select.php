<?php
include "db_user_details.php";
// if($_SERVER['REQUEST_METHOD']=='POST')
// {
    $hos_name=$_POST['hos_name'];
    // $hos_name='KGH';
    $sql1="SELECT * FROM doc_login where d_hospital='$hos_name'";
    $result1=mysqli_query($con,$sql1);
    $response = array();
    if(mysqli_num_rows($result1)>0)
    {
        while($row1=mysqli_fetch_assoc($result1))
        {	
        
            array_push($response,array(
                "name"=>$row1['d_name'],
                "gender"=>$row1['gender'],
                "hospital_name"=>$row1['d_hospital'],
                "specialization"=>$row1['d_specialization'],
                "mobile_number"=>$row1['d_mobile']
            ));
        }
    }	
    //displaying in json format 
        echo json_encode(array('result'=>$response));
// }
?>
