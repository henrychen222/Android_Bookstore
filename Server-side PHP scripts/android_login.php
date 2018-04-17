<?php
  $username = $_GET['username'];
  $password = $_GET['password'];
  $key = 0;
  
  include("conn.php");
	$sql  = "SELECT * FROM customer WHERE ";
    $sql .= " name='$username' and password='$password'";
    $result = mysql_query ( $sql );  
        while ( $row = mysql_fetch_array ( $result ) )  
        {  
            $response [] = $row;  
			$key = 1;
        }  
		if($key == 1){
			foreach ( $response as $key => $value )  
			{  
				$newData[$key] = $value; 
				$newData [$key] ['isSuccess'] = urlencode ( "success" );  
			} 
			echo urldecode ( json_encode ( array("data"=>$newData) )); 
		}
		else{
			$newData [$key] ['isSuccess'] = urlencode ( "false" ); 
			echo urldecode ( json_encode ( array("data"=>$newData) )); 
		}
         
		
        
    // mysql_close( $conn );
	// echo " 333";
?>
