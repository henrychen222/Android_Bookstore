
  <?php 
	include("conn.php");//import database connection
	
	$userId = $_GET['userId'];
	
	$query =  "select * from customer where id = $userId";
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
 
