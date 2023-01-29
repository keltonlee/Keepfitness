<?php
require_once "firstdb.php";
    $food = array('calories'=>0,'protein'=>0,'fat'=>0,'carbohydrates'=>0,'dietary_fiber'=>0, 'cholesterol'=>0);
    if(isset($_POST['see'])){
        $food1 = $_POST['food1'];
        $times1 = $_POST['times1'];
        $food2 = $_POST['food2'];
        $times2 = $_POST['times2'];
        $food3 = $_POST['food3'];
        $times3 = $_POST['times3'];
        $food4 = $_POST['food4'];
        $times4 = $_POST['times4'];
        $food5 = $_POST['food5'];
        $times5 = $_POST['times5'];
        $food6 = $_POST['food6'];
        $times6 = $_POST['times6'];
        $food7 = $_POST['food7'];
        $times7 = $_POST['times7'];
        $food8 = $_POST['food8'];
        $times8 = $_POST['times8'];
        $food9 = $_POST['food9'];
        $times9 = $_POST['times9'];
        $food10 = $_POST['food10'];
        $times10 = $_POST['times10'];

        $food1_sql = "SELECT `calories(kcal)`, `protein(g)`, `fat(g)`, `carbohydrates(g)`, `dietary_fiber(g)`, `cholesterol(mg)` FROM `eat_detail` WHERE food_name = '$food1'";
        $food1_stmt = $pdo->query($food1_sql);
        while($food1_row = $food1_stmt->fetch(PDO::FETCH_ASSOC)){
            $food['calories'] += $food1_row["calories(kcal)"] * $times1;
            $food['protein'] += $food1_row["protein(g)"] * $times1;
            $food['fat'] += $food1_row["fat(g)"] * $times1;
            $food['carbohydrates'] += $food1_row["carbohydrates(g)"] * $times1;
            $food['dietary_fiber'] += $food1_row["dietary_fiber(g)"] * $times1;
            $food['cholesterol'] += intval($food1_row["cholesterol(mg)"]) * $times1;
        }
        
        $food2_sql = "SELECT `calories(kcal)`, `protein(g)`, `fat(g)`, `carbohydrates(g)`, `dietary_fiber(g)`, `cholesterol(mg)` FROM `eat_detail` WHERE food_name = '$food2'";
        $food2_stmt = $pdo->query($food2_sql);
        while($food2_row = $food2_stmt->fetch(PDO::FETCH_ASSOC)){
            $food['calories'] += $food2_row["calories(kcal)"] * $times2;
            $food['protein'] += $food2_row["protein(g)"] * $times2;
            $food['fat'] += $food2_row["fat(g)"] * $times2;
            $food['carbohydrates'] += $food2_row["carbohydrates(g)"] * $times2;
            $food['dietary_fiber'] += $food2_row["dietary_fiber(g)"] * $times2;
            $food['cholesterol'] += intval($food2_row["cholesterol(mg)"]) * $times2;
        }

        $food3_sql = "SELECT `calories(kcal)`, `protein(g)`, `fat(g)`, `carbohydrates(g)`, `dietary_fiber(g)`, `cholesterol(mg)` FROM `eat_detail` WHERE food_name = '$food3'";
        $food3_stmt = $pdo->query($food3_sql);
        while($food3_row = $food3_stmt->fetch(PDO::FETCH_ASSOC)){
            $food['calories'] += $food3_row["calories(kcal)"] * $times3;
            $food['protein'] += $food3_row["protein(g)"] * $times3;
            $food['fat'] += $food3_row["fat(g)"] * $times3;
            $food['carbohydrates'] += $food3_row["carbohydrates(g)"] * $times3;
            $food['dietary_fiber'] += $food3_row["dietary_fiber(g)"] * $times3;
            $food['cholesterol'] += intval($food3_row["cholesterol(mg)"]) * $times3;
        }

        $food4_sql = "SELECT `calories(kcal)`, `protein(g)`, `fat(g)`, `carbohydrates(g)`, `dietary_fiber(g)`, `cholesterol(mg)` FROM `eat_detail` WHERE food_name = '$food4'";
        $food4_stmt = $pdo->query($food4_sql);
        while($food4_row = $food4_stmt->fetch(PDO::FETCH_ASSOC)){
            $food['calories'] += $food4_row["calories(kcal)"] * $times4;
            $food['protein'] += $food4_row["protein(g)"] * $times4;
            $food['fat'] += $food4_row["fat(g)"] * $times4;
            $food['carbohydrates'] += $food4_row["carbohydrates(g)"] * $times4;
            $food['dietary_fiber'] += $food4_row["dietary_fiber(g)"] * $times4;
            $food['cholesterol'] += intval($food4_row["cholesterol(mg)"]) * $times4;
        }

        $food5_sql = "SELECT `calories(kcal)`, `protein(g)`, `fat(g)`, `carbohydrates(g)`, `dietary_fiber(g)`, `cholesterol(mg)` FROM `eat_detail` WHERE food_name = '$food5'";
        $food5_stmt = $pdo->query($food5_sql);
        while($food5_row = $food5_stmt->fetch(PDO::FETCH_ASSOC)){
            $food['calories'] += $food5_row["calories(kcal)"] * $times5;
            $food['protein'] += $food5_row["protein(g)"] * $times5;
            $food['fat'] += $food5_row["fat(g)"] * $times5;
            $food['carbohydrates'] += $food5_row["carbohydrates(g)"] * $times5;
            $food['dietary_fiber'] += $food5_row["dietary_fiber(g)"] * $times5;
            $food['cholesterol'] += intval($food5_row["cholesterol(mg)"]) * $times5;
        }

        $food6_sql = "SELECT `calories(kcal)`, `protein(g)`, `fat(g)`, `carbohydrates(g)`, `dietary_fiber(g)`, `cholesterol(mg)` FROM `eat_detail` WHERE food_name = '$food6'";
        $food6_stmt = $pdo->query($food6_sql);
        while($food6_row = $food6_stmt->fetch(PDO::FETCH_ASSOC)){
            $food['calories'] += $food6_row["calories(kcal)"] * $times6;
            $food['protein'] += $food6_row["protein(g)"] * $times6;
            $food['fat'] += $food6_row["fat(g)"] * $times6;
            $food['carbohydrates'] += $food6_row["carbohydrates(g)"] * $times6;
            $food['dietary_fiber'] += $food6_row["dietary_fiber(g)"] * $times6;
            $food['cholesterol'] += intval($food6_row["cholesterol(mg)"]) * $times6;
        }

        $food7_sql = "SELECT `calories(kcal)`, `protein(g)`, `fat(g)`, `carbohydrates(g)`, `dietary_fiber(g)`, `cholesterol(mg)` FROM `eat_detail` WHERE food_name = '$food7'";
        $food7_stmt = $pdo->query($food7_sql);
        while($food5_row = $food5_stmt->fetch(PDO::FETCH_ASSOC)){
            $food['calories'] += $food7_row["calories(kcal)"] * $times7;
            $food['protein'] += $food7_row["protein(g)"] * $times7;
            $food['fat'] += $food7_row["fat(g)"] * $times7;
            $food['carbohydrates'] += $food7_row["carbohydrates(g)"] * $times7;
            $food['dietary_fiber'] += $food7_row["dietary_fiber(g)"] * $times7;
            $food['cholesterol'] += intval($food7_row["cholesterol(mg)"]) * $times7;
        }

        $food8_sql = "SELECT `calories(kcal)`, `protein(g)`, `fat(g)`, `carbohydrates(g)`, `dietary_fiber(g)`, `cholesterol(mg)` FROM `eat_detail` WHERE food_name = '$food8'";
        $food8_stmt = $pdo->query($food8_sql);
        while($food8_row = $food8_stmt->fetch(PDO::FETCH_ASSOC)){
            $food['calories'] += $food8_row["calories(kcal)"] * $times8;
            $food['protein'] += $food8_row["protein(g)"] * $times8;
            $food['fat'] += $food8_row["fat(g)"] * $times8;
            $food['carbohydrates'] += $food8_row["carbohydrates(g)"] * $times8;
            $food['dietary_fiber'] += $food8_row["dietary_fiber(g)"] * $times8;
            $food['cholesterol'] += intval($food8_row["cholesterol(mg)"]) * $times8;
        }

        $food9_sql = "SELECT `calories(kcal)`, `protein(g)`, `fat(g)`, `carbohydrates(g)`, `dietary_fiber(g)`, `cholesterol(mg)` FROM `eat_detail` WHERE food_name = '$food9'";
        $food9_stmt = $pdo->query($food9_sql);
        while($food9_row = $food9_stmt->fetch(PDO::FETCH_ASSOC)){
            $food['calories'] += $food9_row["calories(kcal)"] * $times9;
            $food['protein'] += $food9_row["protein(g)"] * $times9;
            $food['fat'] += $food9_row["fat(g)"] * $times9;
            $food['carbohydrates'] += $food9_row["carbohydrates(g)"] * $times9;
            $food['dietary_fiber'] += $food9_row["dietary_fiber(g)"] * $times9;
            $food['cholesterol'] += intval($food9_row["cholesterol(mg)"]) * $times9;
        }

        $food10_sql = "SELECT `calories(kcal)`, `protein(g)`, `fat(g)`, `carbohydrates(g)`, `dietary_fiber(g)`, `cholesterol(mg)` FROM `eat_detail` WHERE food_name = '$food10'";
        $food10_stmt = $pdo->query($food10_sql);
        while($food10_row = $food10_stmt->fetch(PDO::FETCH_ASSOC)){
            $food['calories'] += $food10_row["calories(kcal)"] * $times10;
            $food['protein'] += $food10_row["protein(g)"] * $times10;
            $food['fat'] += $food10_row["fat(g)"] * $times10;
            $food['carbohydrates'] += $food10_row["carbohydrates(g)"] * $times10;
            $food['dietary_fiber'] += $food10_row["dietary_fiber(g)"] * $times10;
            $food['cholesterol'] += intval($food10_row["cholesterol(mg)"]) * $times10;
        }

        $food['calories'] = round($food['calories'],2);
        $food['protein'] = round($food['protein'],2);
        $food['fat'] = round($food['fat'],2);
        $food['carbohydrates'] = round($food['carbohydrates'],2);
        $food['dietary_fiber'] = round($food['dietary_fiber'],2);
        $food['cholesterol'] = round($food['cholesterol'],2);
    }
    echo json_encode($food,JSON_UNESCAPED_UNICODE);
    ?>
    