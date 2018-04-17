
  <?php 
	include("conn.php");//import database connection
	
	$user= $_GET['user'];
	$password= $_GET['password'];
	
	$sql2 = "Select   IF((max(id)+1)  IS NULL, 1, (max(id)+1) ) as id  From customer";
	$result2 = mysql_query ( $sql2 );  
	$rs2 = mysql_fetch_array ( $result2 );
	$id = $rs2['id'];
	
	$query = " insert into customer ( id,NAME,PASSWORD) values ($id,'$user','$password')";
	// echo $query;
	$result = mysql_query ( $query ); 
	
	if( $result)  
	{  
		echo urldecode ( json_encode ( array("data"=>"success") )); 
	}else{
		echo urldecode ( json_encode ( array("data"=>"false") ));
	}
	
	
	

?>
 
