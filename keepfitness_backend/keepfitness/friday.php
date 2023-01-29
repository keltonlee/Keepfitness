<?php
require_once "firstdb.php";
if(isset($_POST['Name']) && isset($_POST['email'])){
    $day = array();
    $Name=$_POST['Name'];
    $email=$_POST['email'];
    $fri_sql = "select item, group_num from (fit_menu natural join fit_detail) natural join basic_information where (Name = '$Name') and (email = '$email') and day = 4";
    $fri_stmt = $pdo->query($fri_sql);
    $friday = array();
    $i = 0;
    while($fri_row = $fri_stmt->fetch(PDO::FETCH_ASSOC)){
        $friday[$i] = array('item'=>$fri_row["item"],'group_num'=>$fri_row["group_num"]);
        $i++;
    }
    echo json_encode($friday,JSON_UNESCAPED_UNICODE);
}
?>