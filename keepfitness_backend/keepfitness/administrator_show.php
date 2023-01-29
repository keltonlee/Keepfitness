<?php
require_once "firstdb.php";
$stmt=$pdo->query("select Name, email, date from basic_information");
    $info=array();
    $i = 0;
    while($row=$stmt->fetch(PDO::FETCH_ASSOC)){
       $info[$i] = array('Name'=>$row["Name"],'email'=>$row["email"],'date'=>$row["date"]);
       $i++;
    }
    echo json_encode($info,JSON_UNESCAPED_UNICODE);

?>






