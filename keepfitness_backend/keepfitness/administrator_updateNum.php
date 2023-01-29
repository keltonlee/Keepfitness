<?php
require_once "firstdb.php";
if(isset($_POST['item']) && isset($_POST['fit_intensity']) && isset($_POST['bodypart']) && isset($_POST['group_num']) && isset($_POST['comment'])){
    $numsql = "UPDATE fit_menu SET group_num=:group_num WHERE (item = :item) and (fit_intensity = :fit_intensity)";
    $numstmt = $pdo->prepare($numsql);
    $numstmt->execute(array(
        ':group_num'=>$_POST['group_num'],
        ':item'=>$_POST['item'],
        ':fit_intensity'=>$_POST['fit_intensity'],
    ));

    $comsql = "UPDATE fit_comment SET comment=:comment WHERE (item = :item)";
    $comstmt = $pdo->prepare($comsql);
    $comstmt->execute(array(
        ':comment'=>$_POST['comment'],
        ':item'=>$_POST['item'],
    ));

    $partsql = "UPDATE fit_detail SET bodypart=:bodypart WHERE (item = :item)";
    $partstmt = $pdo->prepare($partsql);
    $partstmt->execute(array(
        ':bodypart'=>$_POST['bodypart'],
        ':item'=>$_POST['item'],
    ));
    echo "update success";
}
?>