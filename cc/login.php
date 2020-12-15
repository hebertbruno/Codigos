<?php

session_start();

$_SESSION['login']=$_POST['login'];
 
$login = $_POST['login'];
$senha = $_POST['senha'];


if (($login != "demo") || ($senha != "demo")) {   
    session_destroy();
    echo"
           <script>
                alert('Usuario ou senha incorretos!')       
           </script>";      
}
else {       
    $_SESSION['login'] = "demo";
    $_SESSION['senha'] = "demo";
    header('location: formulario.html');
}

?> 