<?php  
	
    header("Content-Type: text/html; charset=UTF-8");  
	
	include("conn.php");
	
    // $type = $_GET['type'];  
    // if($type == 1)  
    // {  
        // $obj->title = "Test";  
        // $obj->id = 1;  
        // $obj->value = urlencode("TTT");  
        // echo urldecode ( json_encode ($obj));  
        // $array = array( 'title'=>'TTT', 'id'=>1, 'value'=>urlencode("测试"));  
        // echo urldecode(json_encode($array));  
    // }  
    // else  
    // {  
        // $con = mysql_connect ( "localhost", "root", "windows" );  
        // if (!$con)  
        // {  
            // die ( 'Could not connect: ' . mysql_error () );  
        // }  
        // mysql_select_db ( "dg", $con );  
        // mysql_query ( "set names utf8" );  
		
		
		//Searching
	if(empty($_GET['searchParam'])){
		$query = "select * from books order by title ";
		
	}else{
		$keys = $_GET['searchParam'];
		$tempKey = explode(' ',$keys); 
				
		$key = Array();
		for($i=0;$i<count($tempKey);$i++){
			if($tempKey[$i]!=""){
			    Array_push($key,$tempKey[$i]);
			}
		}
		
		//+++++++++++++the following is the new method +++++++++++
		
		$sql3 = "";
		$no = count($key);
		
		for($i=0;$i<$no;$i++) 
		{ 
			$strKey = "%";
			$l = 0;
			for($k=$i;$k<$no;$k++){
				
				$sql1 = "";
				$strKey = $strKey.$key[$k]."%" ;
				// echo "<br/>";
				$sql1 = "select *,$l+1 as length from books where title like '$strKey'";
				$query1 = mysql_query($sql1);
				$rs1 = mysql_fetch_array($query1);
				if($rs1){
					$sql3 .= $sql1." union ";
				}
				$l++;
				// echo "<br/>";
				// echo "<br/>";
			}
		}
		$sqlLen = count($sql3);
		$sql4 = substr($sql3,0,$sqlLen-7);
		// echo "<br/>";
		$sql4 = "select *,max(length) as sorting from (".$sql4.")temp group by title order by sorting desc,title";
		$query = $sql4;
		// echo $sql4;
		// $result = mysql_query($sql4);
	}
		
        // $query = "select * from books order by title";  
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
		
		// echo urldecode ( json_encode ( $newData,true));
		
                  // mysql_free_result($result); 

				  
        // mysql_close ( $con );  
    // }  
?>  
