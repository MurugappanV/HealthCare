<?php
@session_start();
@$email=$_SESSION['hos_mail'];
if($email=='')
{
    header("Location:http://localhost/healthcare/index.php");
}
?>