<?php
require_once "firstdb.php";
if(isset($_POST['Name']) && isset($_POST['email'])){
    $day = array();
    $Name=$_POST['Name'];
    $email=$_POST['email'];
    $tues_sql = "select item, group_num from (fit_menu natural join fit_detail) natural join basic_information where (Name = '$Name') and (email = '$email') and day = 2";
    $tues_stmt = $pdo->query($tues_sql);
    $tuesday = array();
    $i = 0;
    while($tues_row = $tues_stmt->fetch(PDO::FETCH_ASSOC)){
        $tuesday[$i] = array('item'=>$tues_row["item"],'group_num'=>$tues_row["group_num"]);
        $i++;
    }
    echo json_encode($tuesday,JSON_UNESCAPED_UNICODE);
}
?>