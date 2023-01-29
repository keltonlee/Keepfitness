<?php
require_once "firstdb.php";

if( isset($_POST['type'])) {
    $type=$_POST['type'];
    $sql = "select food_name, `calories(kcal)` from eat_detail where type  = '$type' ";
    $stmt = $pdo->query($sql);
    $food = array();
    $i = 0;
    while($row = $stmt->fetch(PDO::FETCH_ASSOC)){
        $food[$i] = array('food' => $row['food_name'], 'calories' => $row['calories(kcal)']);
        $i++;
    }
    echo json_encode($food,JSON_UNESCAPED_UNICODE);
}
?>
