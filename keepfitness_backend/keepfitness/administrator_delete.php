<?php
require_once "firstdb.php";
if(isset($_POST['Name']) && isset($_POST['email'])){
    $sql = "DELETE from basic_information where (Name = :Name) and (email = :email)";
    $stmt = $pdo->prepare($sql);
    $stmt->execute(array(
        ':Name'=>$_POST['Name'],
        ':email'=>$_POST['email'],
    ));
	echo"delete success";
}

?>