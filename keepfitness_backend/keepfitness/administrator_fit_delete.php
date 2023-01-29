<?php
require_once "firstdb.php";
if(isset($_POST['item']) && isset($_POST['fit_intensity'])){
    $sql = "DELETE from fit_menu where (item = :item) and (fit_intensity = :fit_intensity)";
    $stmt = $pdo->prepare($sql);
    $stmt->execute(array(
        ':item'=>$_POST['item'],
        ':fit_intensity'=>$_POST['fit_intensity'],
    ));
	echo"delete success";
}
?>