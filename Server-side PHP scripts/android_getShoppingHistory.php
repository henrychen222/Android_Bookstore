
  <?php 
	include("conn.php");//import database connection
	
	$userId = $_GET['userId'];
	
	
	$query = " select a.* ,  b.title 
				  from (select customerId ,bookId ,sum(quentity) quentity
						  from shopping_history group by customerId,bookId)a ,books b 
				  where a.bookId = b.ISBN and a.customerId = $userId order by b.title";
	// $query = "select s.*, b.title
				  // from shopping_history s
				  // join books b
					// on s.bookId = isbn
				 // where customerId = $userId";  
        $result = mysql_query ( $query );  
        while ( $row = mysql_fetch_array ( $result ) )  
        {  
            $response [] = $row;  
        }  
        foreach ( $response as $key => $value )  
        {  
            $newData[$key] = $value;  
        }  
		
        echo urldecode ( json_encode ( array("data"=>$newData) ));  
	
	
		
	

?>
 
