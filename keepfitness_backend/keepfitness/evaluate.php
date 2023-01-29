<?php
require_once "firstdb.php";
if( isset($_POST['Name']) && isset($_POST['email'])){
    $Name=$_POST['Name'];
    $email=$_POST['email'];
    $sql = "select BMI, height, weight, body_fat, fit_intensity from basic_information where (Name = '$Name') and (email = '$email')";
    $stmt = $pdo->query($sql);
    //$row = $stmt->fetch(PDO::FETCH_ASSOC);
    $eva = array();
    while($row = $stmt->fetch(PDO::FETCH_ASSOC)){
        $eva["BMI"] = $row["BMI"];
        $eva["height"] = $row["height"];
        $eva["weight"] = $row["weight"];
        $eva["body_fat"] = $row["body_fat"];
        $eva["fit_intensity"] = $row["fit_intensity"];
    }
    echo json_encode($eva);
}
?>
