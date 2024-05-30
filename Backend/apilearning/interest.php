<?php

require 'conn.php';

$username=$_POST['username'];
$one=$_POST['one'];
$two=$_POST['two'];
$three=$_POST['three'];
$four=$_POST['four'];


$insertQuery="INSERT INTO interest(username,one,two,three,four) VALUES('$username','$one','$two','$three','$four')";
$result=mysqli_query($conn,$insertQuery);

if($result){

    $response['error']="200";
    $response['message']="save data successful!";
}
else
{
    $response['error']="400";
    $response['message']="save failed!";
}






echo json_encode($response);

?>