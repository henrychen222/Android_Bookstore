<?php  
	
    header("Content-Type: text/html; charset=UTF-8");  
	
	include("conn.php");
	
    $customerId = $_GET['customerId'];
	
		$sql4 = "select  a.bookid ISBN,a.title,sum(quentity) quentity 
						from (select s.*, b.title
								from shopping_history s
								join books b
								on s.bookId = isbn
								where customerId = $customerId) a
						group by isbn";
						
		$query = $sql4;
        $result = mysql_query ( $query );  
        while ( $row = mysql_fetch_array ( $result ) )  
        {  
            $response [] = $row;  
        }  
        foreach ( $response as $key => $value )  
        {  
            $newData[$key] = $value;  
            // $newData [$key] ['title'] = urlencode ( $value ['title'] );  //key 重复则改值
            // $newData [$key] ['price'] = urlencode ( $value ['price'] );  
            // $newData [$key] ['ISBN'] = urlencode ( $value ['ISBN'] );  
			// $newData [$key] ['asasda'] = urlencode ( $value ['ISBN'] );//  key 不重复则$newData在数组新增键值对
        }  
		
		 // echo urldecode ( json_encode ( $sql));
        echo urldecode ( json_encode ( array("data"=>$newData) ));  
		
		
?>  
