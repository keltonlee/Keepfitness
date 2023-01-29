<?php
try{
    	$pdo=new PDO('mysql:host=127.0.0.1;port=3306;dbname=keepfitness','root','max01218');
}catch(PDOException $e) {
    die ("Error!: " . $e->getMessage() . "<br/>");
}
$pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
$pdo->query("SET NAMES 'UTF8'");

?>