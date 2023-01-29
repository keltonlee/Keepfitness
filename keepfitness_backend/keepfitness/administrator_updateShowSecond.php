<?php
require_once "firstdb.php";
if(isset($_POST['day']) && isset($_POST['fit_intensity']) && isset($_POST['item'])){
    $day = $_POST['day'];
    $fit_intensity = $_POST['fit_intensity'];
    $item = $_POST['item'];
    $stmt=$pdo->query("SELECT bodypart, group_num, comment FROM ((fit_menu natural join fit_detail) natural join fit_comment) WHERE day=$day and fit_intensity=$fit_intensity and item='$item'");
    $info;
    while($row=$stmt->fetch(PDO::FETCH_ASSOC)){
       $info = array('bodypart'=>$row["bodypart"], 'group_num'=>$row["group_num"], 'comment'=>$row["comment"]);
    }
    echo json_encode($info,JSON_UNESCAPED_UNICODE);
}
?>