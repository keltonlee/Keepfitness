<?php
require_once "firstdb.php";


if(("" == trim($_POST['sex'])) || ("" == trim($_POST['age'])) || ("" == trim($_POST['height'])) || ("" == trim($_POST['weight'])) || ("" == trim($_POST['body_fat']))){
    echo "Update Failed";
}

else if(isset($_POST['sex']) && isset($_POST['age']) && isset($_POST['height']) && isset($_POST['weight']) && isset($_POST['body_fat']) && isset($_POST['date'])){
    $Name=$_POST['Name'];
    $email=$_POST['email'];
    $sql = "UPDATE `basic_information` SET `sex`=:sex,`age`= :age,`height`=:height,`weight`=:weight,`body_fat`=:body_fat,`BMI`=:BMI,`fit_intensity`=:fit_intensity,`eat_intensity`=:eat_intensity,`BMR`=:BMR,`date`= :date  WHERE (Name = :Name) and (email = :email)";
    $BMI = round($_POST['weight']/(($_POST['height']/100)**2),2);

    if($_POST['sex'] == "B"){
        $BMR = round(66 + ((13.7 * $_POST['weight']) + (5 * $_POST['height']) - (6.8 * $_POST['age'])),2);
    }else{
        $BMR = round(665 + ((9.6 * $_POST['weight']) + (1.8 * $_POST['height']) - (4.7 * $_POST['age'])),2);
    }

    if($BMI<18.5){
        $bmi_range = 1;
    }
    elseif((18.5<=$BMI) && ($BMI<24)){
        $bmi_range = 2;
    }
    else if(24<=$BMI){
        $bmi_range = 3;
    }

    if($_POST['sex'] == "B"){
        if($_POST['body_fat']<15){
            $body_fat_range = 1;
        }
        elseif((15<=$_POST['body_fat']) && ($_POST['body_fat']<25)){
            $body_fat_range = 2;
        }
        else{
            $body_fat_range = 3;
        }
    }
    if($_POST['sex'] == "G"){
        if($_POST['body_fat']<20){
            $body_fat_range = 1;
        }
        if((20<=$_POST['body_fat']) && ($_POST['body_fat']<30)){
            $body_fat_range = 2;
        }
        if(30<=$_POST['body_fat']){
            $body_fat_range = 3;
        }
    }

    
    if(($_POST['sex'] == "G") && ($bmi_range == 3) && ($body_fat_range == 3)){
        $fit_intensity = 1;
    }
    elseif(($_POST['sex'] == "B") && ($bmi_range == 3) && ($body_fat_range == 3)){
        $fit_intensity = 2;
    }
    elseif(($_POST['sex'] == "G") && ($bmi_range == 1) && ($body_fat_range == 3)){
        $fit_intensity = 3;
    }
    elseif(($_POST['sex'] == "B") && ($bmi_range == 1) && ($body_fat_range == 3)){
        $fit_intensity = 4;
    }
    elseif(($_POST['sex'] == "G") && ($bmi_range == 2) && ($body_fat_range == 3)){
        $fit_intensity = 5;
    }
    elseif(($_POST['sex'] == "B") && ($bmi_range == 2) && ($body_fat_range == 3)){
        $fit_intensity = 6;
    }
    elseif(($_POST['sex'] == "G") && ($bmi_range == 3) && ($body_fat_range == 1)){
        $fit_intensity = 7;
    }
    elseif(($_POST['sex'] == "B") && ($bmi_range == 3) && ($body_fat_range == 1)){
        $fit_intensity = 8;
    }
    elseif(($_POST['sex'] == "G") && ($bmi_range == 1) && ($body_fat_range == 1)){
        $fit_intensity = 9;
    }
    elseif(($_POST['sex'] == "B") && ($bmi_range == 1) && ($body_fat_range == 1)){
        $fit_intensity = 10;
    }
    elseif(($_POST['sex'] == "G") && ($bmi_range == 2) && ($body_fat_range == 1)){
        $fit_intensity = 11;
    }
    elseif(($_POST['sex'] == "B") && ($bmi_range == 2) && ($body_fat_range == 1)){
        $fit_intensity = 12;
    }
    elseif(($_POST['sex'] == "G") && ($bmi_range == 3) && ($body_fat_range == 2)){
        $fit_intensity = 13;
    }
    elseif(($_POST['sex'] == "B") && ($bmi_range == 3) && ($body_fat_range == 2)){
        $fit_intensity = 14;
    }
    elseif(($_POST['sex'] == "G") && ($bmi_range == 1) && ($body_fat_range == 2)){
        $fit_intensity = 15;
    }
    elseif(($_POST['sex'] == "B") && ($bmi_range == 1) && ($body_fat_range == 2)){
        $fit_intensity = 16;
    }
    elseif(($_POST['sex'] == "G") && ($bmi_range == 2) && ($body_fat_range == 2)){
        $fit_intensity = 17;
    }
    else{
        $fit_intensity = 18;
    }

   
    if(($fit_intensity == 1) || ($fit_intensity == 2)){
        $eat_intensity = 1;
    }
    else if(($fit_intensity == 3) || ($fit_intensity == 4)){
        $eat_intensity = 2;
    }
    else if(($fit_intensity == 5) || ($fit_intensity == 6)){
        $eat_intensity = 3;
    }
    else if(($fit_intensity == 7) || ($fit_intensity == 8)){
        $eat_intensity = 4;
    }
    else if(($fit_intensity == 9) || ($fit_intensity == 10)){
        $eat_intensity = 5;
    }
    else if(($fit_intensity == 11) || ($fit_intensity == 12)){
        $eat_intensity = 6;
    }
    else if(($fit_intensity == 13) || ($fit_intensity == 14)){
        $eat_intensity = 7;
    }
    else if(($fit_intensity == 15) || ($fit_intensity == 16)){
        $eat_intensity = 8;
    }
    else if(($fit_intensity == 17) || ($fit_intensity == 18)){
        $eat_intensity = 9;
    }

    $stmt = $pdo->prepare($sql);
    $stmt->execute(array(
        ':Name'=>$_POST['Name'],
        ':email'=>$_POST['email'],
        ':sex'=>$_POST['sex'],
        ':age'=>$_POST['age'],
        ':height'=>$_POST['height'],
        ':weight'=>$_POST['weight'],
        ':body_fat'=>$_POST['body_fat'],
        ':BMI'=>$BMI,
        ':fit_intensity'=>$fit_intensity,
        ':eat_intensity'=>$eat_intensity,
        ':BMR'=>$BMR,
        ':date'=>$_POST['date'],
    ));
    echo "Update Success";
}


?>