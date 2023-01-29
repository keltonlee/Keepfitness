<?php
require_once "firstdb.php";
if( isset($_POST['Name']) && isset($_POST['email'])) {
    $Name = $_POST['Name'];
    $email = $_POST['email'];

    $sql = "select Name from basic_information where Name = '$Name' AND email = '$email'";

    $stmt = $pdo->query($sql);
    $row = $stmt->fetch(PDO::FETCH_ASSOC);
    if($row === false){
        echo "Login Incorrect";
    }
    else{
        echo "Login Success";
    }
}
?>
