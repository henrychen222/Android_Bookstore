
  <?php 
	include("conn.php");//import database connection
	
	$isbn= $_GET['isbn'];
	$title= $_GET['title'];
	$price= $_GET['price'];
	
	$sql_a = "select * from books where isbn = '$isbn'";
	$result_a = mysql_query ( $sql_a );  
	
	$sql_b = "select * from books where title = '$title'";
	$result_b = mysql_query ( $sql_b );  
	
	if(($rs_a = mysql_fetch_array ( $result_a ))&& ($rs_b = mysql_fetch_array ( $result_b ))){
		// $newData [$key] ['isSuccess'] = urlencode ( "false_error3" ); 
		echo urldecode ( json_encode ( array("data"=>"false_error3") )); 
	}elseif($rs_b = mysql_fetch_array ( $result_b )){
		// $newData [$key] ['isSuccess'] = urlencode ( "false_error2" ); 
		echo urldecode ( json_encode ( array("data"=>"false_error2") )); 
	}elseif($rs_a = mysql_fetch_array ( $result_a )){
		echo urldecode ( json_encode ( array("data"=>"false_error1") ));
	}else{
		$sql2 = "Select   IF((max(id)+1)  IS NULL, 1, (max(id)+1) ) as id  From  books";
		$result2 = mysql_query ( $sql2 );  
		$rs2 = mysql_fetch_array ( $result2 );
		$id = $rs2['id'];
		
		$query = " insert into books ( id,isbn,title,price) values ($id,'$isbn','$title','$price')";
		// echo $query;
        $result = mysql_query ( $query );  
		
		if( $result)  
        {  
            // $newData [$key] ['isSuccess'] = urlencode ( "success" ); 
			echo urldecode ( json_encode ( array("data"=>"success") )); 
        }else{
			// $newData [$key] ['isSuccess'] = urlencode ( "false" ); 
			echo urldecode ( json_encode ( array("data"=>"false") ));
		}
	}
	
	

?>
 
