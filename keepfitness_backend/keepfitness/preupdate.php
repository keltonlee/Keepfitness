<?php
require_once "firstdb.php";

if(isset($_POST['Name']) && isset($_POST['email'])){
    $Name=$_POST['Name'];
    $email=$_POST['email'];
    $sql = "select sex, age, height, weight, body_fat from basic_information where (Name = '$Name') and (email = '$email')";
    $stmt = $pdo->query($sql);
    $info=array();
    while($row = $stmt->fetch(PDO::FETCH_ASSOC)){
    $info["sex"] = $row["sex"];
    $info["age"] = $row["age"];
    $info["height"] = $row["height"];
    $info["weight"] = $row["weight"];
    $info["body_fat"] = $row["body_fat"];
    }
    echo json_encode($info);
}
?>