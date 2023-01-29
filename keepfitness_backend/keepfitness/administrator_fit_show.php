<?php
require_once "firstdb.php";
$stmt=$pdo->query("SELECT DISTINCT item FROM fit_menu");
    $info=array();
    $i = 0;
    while($row=$stmt->fetch(PDO::FETCH_ASSOC)){
       $info[$i] = array('item'=>$row["item"]);
       $i++;
    }
    echo json_encode($info,JSON_UNESCAPED_UNICODE);
?>
