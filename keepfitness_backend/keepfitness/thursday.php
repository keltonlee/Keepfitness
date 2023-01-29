<?php
require_once "firstdb.php";
if(isset($_POST['Name']) && isset($_POST['email'])){
    $day = array();
    $Name=$_POST['Name'];
    $email=$_POST['email'];
    $thu_sql = "select item, group_num from (fit_menu natural join fit_detail) natural join basic_information where (Name = '$Name') and (email = '$email') and day = 3";
    $thu_stmt = $pdo->query($thu_sql);
    $thursday = array();
    $i = 0;
    while($thu_row = $thu_stmt->fetch(PDO::FETCH_ASSOC)){
        $thursday[$i] = array('item'=>$thu_row["item"],'group_num'=>$thu_row["group_num"]);
        $i++;
    }
    echo json_encode($thursday,JSON_UNESCAPED_UNICODE);
}
?>