
<?php

	echo "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />";

	include 'connect.php';
	
	$nfirstname=$_POST['nfirstname'];
	
	
	
	
	$con=mysqli_connect("localhost", "root","", "javachat");
	if(checkadmin($_POST['firstname'] , $_POST['password'])){
		
		
		    $sql="delete from  user where nick=\"".$nfirstname."\";";
			
			mysqli_query($con,$sql);
			
			echo "silindi ". $nfirstname;
	}
	

	else { 
		echo "giris basarisiz<br>geri yonlendiriliyorsunuz...<br>"; 
		header('refresh: 3; url=index.html');
			}

?>