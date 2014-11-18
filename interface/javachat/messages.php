<?php 
echo "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />";
include 'connect.php';
		$firstname=$_POST['firstname'];
		$password=$_POST['password'];
 echo "~MESAJLAR~<br>";
		
//header('refresh: 5; url=messages.php');

if(checkuser($firstname,$password)){
	$con=mysqli_connect("localhost", "root","", "javachat");
	$resultset=mysqli_query($con,"select  nick, content from message");
	while ($row=mysqli_fetch_array($resultset)){
		echo $row['nick']." : ".$row['content']."<br>";
	}
}

else { 
	echo "giris basarisiz<br>geri yonlendiriliyorsunuz...<br>"; 
	header('refresh: 5; url=index.html');
					}
?>