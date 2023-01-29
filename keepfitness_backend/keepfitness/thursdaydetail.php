<?php
require_once "firstdb.php";
if(isset($_POST['Name']) && isset($_POST['email'])){
    $Name=$_POST['Name'];
    $email=$_POST['email'];
    $mon_ins_sql = "select item, bodypart, comment from ((fit_menu natural join fit_detail) natural join fit_comment) natural join basic_information where (Name = '$Name') and (email = '$email') and day = 3";
    $mon_ins = $pdo->query($mon_ins_sql);
    $monday_ins = array();
    $q = 0;
    while($mon_ins_row = $mon_ins->fetch(PDO::FETCH_ASSOC)){
        $monday_ins[$q] = array('item'=>$mon_ins_row["item"],'bodypart'=>$mon_ins_row["bodypart"],'comment'=>$mon_ins_row["comment"]);
        $q++;
    }
    echo json_encode($monday_ins,JSON_UNESCAPED_UNICODE);
}
?>