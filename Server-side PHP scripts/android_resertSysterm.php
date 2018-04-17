
  <?php 
	include("conn.php");//import database connection
	
	
	$sql = "delete from shopping_cart ";
	$query = mysql_query($sql);
	$sql = "delete from shopping_history ";
	$query = mysql_query($sql);
	$sql = "delete from customer ";
	$query = mysql_query($sql);
	$sql = "delete from books ";
	$result = mysql_query ( $sql ); 
	
	if( $result)  
	{  
		echo urldecode ( json_encode ( array("data"=>"success") )); 
	}else{
		echo urldecode ( json_encode ( array("data"=>"false") ));
	}
	
	?>