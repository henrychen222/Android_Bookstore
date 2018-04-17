 <?php 
 include("conn.php");//import database connection
	$name = $_GET['name'];
	// $customerId = $_GET['customerId'];
	// $keyWords = trim($_GET['keyWords']);
	
		$sql = "select book_name from books order by title" ;
		$query = mysql_query($sql);
		while($rs = mysql_fetch_array($query)){
			
			echo $rs['book_name']+",";
		}
	
?>
 
		
