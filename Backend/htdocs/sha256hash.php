<?php
/*
echo "Enter the value:";
echo "<form action='' method='POST'> 
<input type='text' name='hi' id='hi'/>
<input type='submit' name='find'/>
</form>";
$cipher=shahash("hello");
$c=shahash($_POST['hi']);
if($c==$cipher)
	echo "Matched";
else
	echo "Not Matched";
*/

function shahash($val)
{
$c=hash("sha256","$val");
return $c;
}
?>