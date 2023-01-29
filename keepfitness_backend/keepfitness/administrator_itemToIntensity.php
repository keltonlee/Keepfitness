<?php
require_once "firstdb.php";
if(isset($_POST['item'])){
    $item = $_POST['item'];
    $stmt=$pdo->query("SELECT fit_intensity FROM fit_menu WHERE item='$item'");
    $info = array();
    $i = 0;
    while($row=$stmt->fetch(PDO::FETCH_ASSOC)){
       $info[$i] = array('fit_intensity'=>$row["fit_intensity"]);
       $i++;
    }
    echo json_encode($info,JSON_UNESCAPED_UNICODE);
}
?>