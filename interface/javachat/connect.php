
<?php
	//$sql= "insert into user values (\"". "yenikullanýcý adi" ."\"" . sifre . ");";
function checkuser($firstname,$password){
		$con=mysqli_connect("localhost", "root","", "javachat");
		if (mysqli_connect_errno()) {
			echo "Failed to connect to MySQL: " . mysqli_connect_error();
		}
		$resultset=mysqli_query($con,"select nick,password from user");
		while($row=mysqli_fetch_array($resultset)){
			if ( $firstname==$row['nick']  &&  $password==$row['password']) {
				echo "giris basarili <br>";
				return true;
				break;}
		}
	}
	
	
function checkadmin($firstname,$password){
		$con=mysqli_connect("localhost", "root","", "javachat");
		if (mysqli_connect_errno()) {
			echo "Failed to connect to MySQL: " . mysqli_connect_error();
		}
		$resultset=mysqli_query($con,"select nick,password, authorizelevel from user");
		while($row=mysqli_fetch_array($resultset)){
			if ( $firstname==$row['nick']  &&  $password==$row['password'] && $row['authorizelevel']==1) {
				echo "giris basarili <br>";
				return true;
				break;}
		}
	}
?>