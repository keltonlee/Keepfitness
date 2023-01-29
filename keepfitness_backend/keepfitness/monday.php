<?php
require_once "firstdb.php";
if(isset($_POST['Name']) && isset($_POST['email'])){
    $day = array();
    $Name=$_POST['Name'];
    $email=$_POST['email'];
    $mon_sql = "select item, group_num from (fit_menu natural join fit_detail) natural join basic_information where (Name = '$Name') and (email = '$email') and day = 1";
    $mon_stmt = $pdo->query($mon_sql);
    $monday = array();
    $i = 0;
    while($mon_row = $mon_stmt->fetch(PDO::FETCH_ASSOC)){
        $monday[$i] = array('item'=>$mon_row["item"],'group_num'=>$mon_row["group_num"]);
        $i++;
    }
    echo json_encode($monday,JSON_UNESCAPED_UNICODE);
}
?>