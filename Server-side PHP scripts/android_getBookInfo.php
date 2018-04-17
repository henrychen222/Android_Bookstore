
  <?php 
	include("conn.php");//import database connection
	
	$title = $_GET['title'];
	
	$query =  "select * from books where title = '$title'";
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
 
