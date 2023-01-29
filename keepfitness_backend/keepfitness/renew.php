<?php
require_once "firstdb.php";
if(isset($_POST['item']) && isset($_POST['fit_intensity']) && isset($_POST['group_num']) && isset($_POST['bodypart']) && isset($_POST['day']) && isset($_POST['comment'])){
    $fit_menu_sql = "insert into fit_menu values (:fit_intensity, :item, :group_num)";
    $fit_menu_stmt = $pdo->prepare($fit_menu_sql);
    $fit_menu_stmt->execute(array(
        ':fit_intensity'=>$_POST['fit_intensity'],
        ':item'=>$_POST['item'],
        ':group_num'=>$_POST['group_num'],
    ));

    $fit_detail_sql = "insert into fit_detail values (:item, :bodypart, :day)";
    $fit_detail_stmt = $pdo->prepare($fit_detail_sql);
    $fit_detail_stmt->execute(array(
        ':item'=>$_POST['item'],
        ':bodypart'=>$_POST['bodypart'],
        ':day'=>$_POST['day'],
    ));

    $fit_comment_sql = "insert into fit_comment values (:item, :comment)";
    $fit_comment_stmt = $pdo->prepare($fit_comment_sql);
    $fit_comment_stmt->execute(array(
        ':item'=>$_POST['item'],
        ':comment'=>$_POST['comment'],
    ));
    
    echo "renew success";
}
?>