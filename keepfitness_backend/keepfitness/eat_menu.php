<?php
require_once "firstdb.php";
if(isset($_POST['Name']) && isset($_POST['email'])){
    $Name=$_POST['Name'];
    $email=$_POST['email'];
    $sql = "select carbohydrates, protein, fat from eat_menu natural join basic_information where (Name = '$Name') and (email = '$email')";
    $stmt = $pdo->query($sql);
    $tol = array();
    while($row = $stmt->fetch(PDO::FETCH_ASSOC)){
        $tol['carbohydrates'] = $row["carbohydrates"];
        $tol['protein'] = $row["protein"];
        $tol['fat'] = $row["fat"];
    }
    echo json_encode($tol);
}
?>