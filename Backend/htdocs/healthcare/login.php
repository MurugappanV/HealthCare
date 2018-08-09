<html>
<body>
<?php
include 'connection.php';
if($con)
{
    $email=$_POST['email'];
    $pass=$_POST['password'];
    $sql="select * from hos_login WHERE h_id='$email' and confirmation=1";
    $exe_sql=$con->query($sql);

    if($row=mysqli_fetch_array($exe_sql))
    {
        $doc_pass=$row['pass'];
        if($pass==$doc_pass)
        {
            session_start();
            $_SESSION['hos_mail']=$email;
            header("Location:http://finefettle.ooo/healthcare/hospital.php");
        }
        else
        {
            echo '<script> window.alert("Please Enter correct Password.");window.history.go(-1);</script>';
        }
    }
    else
    {
        echo '<script>window.alert("You do not have any account. (or) Your login is not confirmed.");window.history.go(-1);</script>';
    }
}
else
    echo 'not';
?>
</body>
</html>
