<?php

 $user = "root";
 $senha = "root";

 try{
     $pdo = new PDO("mysql:host=localhost;dbname=pw", $user, $senha);
     echo "<b>Conexao Estabelecida</b></br>";
     $query = $pdo->prepare("INSERT INTO mensagem (nome, email, website, mensagem) VALUES (:nome, :email, :website, :mensagem)");
     $query->bindValue(":nome", $_POST["nome"]);
     $query->bindValue(":email", $_POST["email"]);
     $query->bindValue(":website",$_POST["website"]);
     $query->bindValue(":mensagem",$_POST["mensagem"]);
     $query->execute();

     echo "<b>Os seguintes dados foram inseridos no Banco de Dados:</b></br>";
     echo "<b>Nome: </b>" . $_POST["nome"] . "</br>";
     echo "<b>Email: </b>" . $_POST["email"] . "</br>";
     echo "<b>Website: </b>" . $_POST["website"] . "</br>";
     echo "<b>Mensagem: </b>" . $_POST["mensagem"] . "</br>";

 } catch (PDOException $e){
   echo $e->getMessage();
 }

?>
