<?php
require_once "firstdb.php";
if(isset($_POST['day']) && isset($_POST['fit_intensity'])){
    $day = $_POST['day'];
    $fit_intensity = $_POST['fit_intensity'];
    $stmt=$pdo->query("SELECT item FROM (fit_menu natural join fit_detail) WHERE day=$day and fit_intensity=$fit_intensity");
    $info = array();
    $i = 0;
    while($row=$stmt->fetch(PDO::FETCH_ASSOC)){
       $info[$i] = array('item'=>$row["item"]);
       $i++;
    }
    echo json_encode($info,JSON_UNESCAPED_UNICODE);
}
?>