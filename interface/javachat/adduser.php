
<?php

	echo "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />";

	include 'connect.php';
	
	$nfirstname=$_POST['nfirstname'];
	$npassword=$_POST['npassword'];
	$npassword2=$_POST['npassword2'];
	$authorizelevel=$_POST['authorizelevel'];
	
	$con=mysqli_connect("localhost", "root","", "javachat");
	if(checkadmin($_POST['firstname'] , $_POST['password'])){
		
		if($npassword==$npassword2){
		    $sql="insert into user (nick,password,authorizelevel) values (\"".$nfirstname."\",".$npassword.",".$authorizelevel.");";
			
			mysqli_query($con,$sql);
			
			echo "yeni kullanici eklendi ". $nfirstname;
		}
		else {echo "sifreler uyusmuyor  <br> geri yönlendiriliyorsunuz...";header('refresh: 3; url=index.html');}
	}
		
		
		
	

	else { 
		echo "giris basarisiz<br>geri yonlendiriliyorsunuz...<br>"; 
		header('refresh: 3; url=index.html');
			}

?>