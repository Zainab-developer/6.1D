<?php

require 'conn.php';

$username=$_POST['username'];
$email=$_POST['email'];
$phone_no=$_POST['phone_no'];
$password=md5($_POST['password']);



$checkUser="SELECT * from creat_user WHERE email='$email'";
$checkQuery=mysqli_query($conn,$checkUser);

if(mysqli_num_rows($checkQuery)>0){

     $response['error']="403";
    $response['message']="User exist";
}
else
{
   $insertQuery="INSERT INTO creat_user(username,email,password,phone_no) VALUES('$username','$email','$password','$phone_no')";
$result=mysqli_query($conn,$insertQuery);

if($result){

    $response['error']="200";
    $response['message']="Register successful!";
}
else
{
    $response['error']="400";
    $response['message']="Registeration failed!";
}



}


echo json_encode($response);

?>