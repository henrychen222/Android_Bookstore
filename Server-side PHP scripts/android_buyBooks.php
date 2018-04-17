
  <?php 
	include("conn.php");//import database connection
	// $name = $_GET['name'];
	// $customerId = $_GET['customerId'];
	

	//获取当前页面的$sqlArray[],用于数据库支付操作
  
  // $json ={"sqlArray"=>[{"sql2":"insert into shopping_cart  (customerId,bookId,bookPrice,bookNum) values (1,'Web Development with LAMP',2,0)"},{"sql3":"insert into shopping_cart  (customerId,bookId,bookPrice,bookNum) values (1,'PHP and MySQL Web Development',2,0)"}]};

  // echo $json;
	$json = $_GET['sqlArray'];
	$sqlArray = json_decode($json,true);
	$booleanStr="";
	
	foreach($sqlArray as $k=>$v){
		// $resukt = 'key=' . $k . ', value=' . $v[$k];
		 $query =  $v[$k];
		 // echo $query;
        $boolean = mysql_query ( $query );
		$booleanStr+=$boolean;
	}
	
	
	
	// echo urldecode ( json_encode ( $sqlArray ));  
		// $arrlength=count($sqlArray);
		
		// for($x=0;$x<$arrlength;$x++){
			// $sqlDill = $sqlArray[$x];
			// mysql_query($sqlDill);
		// }
		
		// echo urldecode ( json_encode ( "succes"=>"222" ));  
		
		//TODO:了解  PHP怎么将数据转为jason
		
		// while ( $row = mysql_fetch_array ( $result ) )  
        // {  
            // $response [] = $row;  
        // }  
        // foreach ( $response as $key => $value )  
        // {  
            // $newData[$key] = $value;  
            // $newData [$key] ['title'] = urlencode ( $value ['title'] );  //key 重复则改值
            // $newData [$key] ['price'] = urlencode ( $value ['price'] );  
            // $newData [$key] ['ISBN'] = urlencode ( $value ['ISBN'] );  
			// $newData [$key] ['asasda'] = urlencode ( $value ['ISBN'] );//  key 不重复则$newData在数组新增键值对 
        // }  
		
		// echo "1";
        echo urldecode ( json_encode ( array("result"=>$booleanStr) )); 
		// echo urldecode ( json_encode ( array("data"=>$sqlArray) )); 
		
	

?>
 
