<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
       
<!DOCTYPE html>
<html>
<head>
	<script type="text/javascript" src="./js/jquery-3.3.1.min.js"></script>
	<link rel="stylesheet" type="text/css" href="./css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="./css/datatables.min.css">	
	<script type="text/javascript" src="./js/bootstrap.min.js"></script>
	

<meta charset="UTF-8">
<title>首頁</title>
	
</head>

<body>			
	<div class="container-fluid">
		<div class="row">
		<div class="col-sm-6">
			<form id="form-user" >
				<div class="inputgroup" >
					<label for="id">身分證字號</label>
					<input id="id" name="id" type="text" class="form-control">
				</div>
				<div class="inputgroup">
					<label for="name">姓名</label>
					<input id="name" name="name" type="text" class="form-control">
				</div>
				<div class="inputgroup">
					<label for="phone">電話</label>
					<input id="phone" name="phone" type="text" class="form-control">
				</div>
				<div class="inputgroup">
					<label for="address">地址</label>
					<input id="address" name="address" type="text" class="form-control">
				</div>
				<div class="inputgroup">
					<label for="description">備註</label>
					<input id="description" name="description" type="text" class="form-control">
				</div>
				<div class="inputgroup">
					<button type="button" class="mx-2 btn btn-info" onclick="search()" >查詢</button>
					<button type="button" class="mx-2 btn btn-primary" onclick="add()">新增</button>
					<button type="button" class="mx-2 btn btn-warning" onclick="update()" >修改</button>
					<button type="button" class="mx-2 btn btn-danger" onclick="del()" >刪除</button>
					<button type="button" class="mx-2 btn btn-info" onclick="getData()">列表</button>
				</div>	
			</form>
		</div>
				
			<div class="col-sm-6">
				<div id="resultTable_wrapper" class="dataTables_wrapper container-fluid dt-bootstrap4 no-footer">				
					<div class="row">
						<div class="col-sm-12">
		<table class="table table-bordered table-hover dataTable no-footer" id="resultTable" role="grid" aria-describedby="resultTable_info">
            <thead>
              <tr role="row">
	              	<th class="sorting" tabindex="0" aria-controls="resultTable" rowspan="1" colspan="1" aria-label="身分證字號: activate to sort column ascending" style="width: 70px;">
	              		身分證字號
	              	</th>
	              	<th class="sorting_asc" tabindex="0" aria-controls="resultTable" rowspan="1" colspan="1" aria-sort="ascending" aria-label="姓名: activate to sort column descending" style="width: 16px;">
	              		姓名
	              	</th>
	              	<th class="sorting" tabindex="0" aria-controls="resultTable" rowspan="1" colspan="1" aria-label="電話: activate to sort column ascending" style="width: 43px;">
	              		電話
	              	</th>
	              	<th class="sorting" tabindex="0" aria-controls="resultTable" rowspan="1" colspan="1" aria-label="地址: activate to sort column ascending" style="width: 16px;">
	              		地址
	              	</th>
	              	<th class="sorting" tabindex="0" aria-controls="resultTable" rowspan="1" colspan="1" aria-label="備註: activate to sort column ascending" style="width: 26px;">
	              		備註
	              	</th>
              </tr>
            </thead>            
            <tbody>             
            <tr role="row" class="odd">
                <td>身分證字號</td>
                <td>姓名</td>
                <td>電話</td>
                <td>地址</td>
                <td>備註</td>
             </tr>            
             </tbody>           
          </table>
				</div>
					</div>				
				</div>
			</div>	
		</div>
	</div>






<script type="text/javascript" src="./js/datatables.min.js"></script>
<script type="text/javascript">

	
	function search(){
		// 將請求提交到哪
		var url = "search";
		
		// 發出ajax請求，並處理結果
		$.ajax({
			"url": url,
			"data": data,
			"type": "GET",
			"dataType": "json",
			"success": function(json) {
				alert("成功");
			},
			"error": function() {
				alert("錯誤");

			}			
		});	
	}
	
		
		
	function add() {						
		// 將請求提交到哪
		var url = "add";
		// 請求參數
		var data = $("#form-user").serialize();
		console.log("參數：" + data);
		// 發出ajax請求，並處理結果
		$.ajax({
			"url": url,
			"data": data,
			"type": "GET",
			"dataType": "text",
			"success": function(data) {
				alert("add成功");
			},
			"error": function() {
				alert("add錯誤");

			}			
		});		
      }
      	        
	
     function del(){
    	// 將請求提交到哪
 		var url = "delete";
 		var data = $("#form-user").serialize();
		console.log("參數：" + data);
 		// 發出ajax請求，並處理結果
 		$.ajax({
 			"url": url,
 			"data": data,
 			"type": "GET",
 			"dataType": "text",
 			"success": function(json) {
 				alert("delete成功");
 			},
 			"error": function() {
				alert("delete錯誤");

			}
 		});	
    	 
     }
	
	function update(){
		// 將請求提交到哪
 		var url = "update";
 		var data = $("#form-user").serialize();
		console.log("參數：" + data);
 		// 發出ajax請求，並處理結果
 		$.ajax({
 			"url": url,
 			"data": data,
 			"type": "GET",
 			"dataType": "text",
 			"success": function(json) {
 				alert("update成功");
 			},
 			"error": function() {
				alert("update錯誤");

			}
 		});	
	}
     
	function getData(){
		// 將請求提交到哪
 		var url = "alllist";
 		// 發出ajax請求，並處理結果
 		$.ajax({
 			"url": url,
 			"type": "GET",
 			"dataType": "text",
 			"success": function(json) {
				$("#list").empty();
 				alert("getData成功");
 			},
 			"error": function() {
				alert("getData錯誤");

			}
 		});	
	}
 		 
	
	
	
	
     
     //Datatable
     
    const titleList = [
      [
        {
          title: "身分證字號",
          data: "id",
        },
        {
          title: "姓名",
          data: "name",
        },
        {
          title: "電話",
          data: "phone",
        },
        {
          title: "地址",
          data: "address",
        },
        {
          title: "備註",
          data: "description",
        },
      ],
    ];
     
     
     //顯示datatable
   /* $(function(){
        $("#resultTable").DataTable({
          order: [1, "asc"],
          retrieve:"true",
          language: {
            search: "搜尋表格:",  
            info: "顯示第 _START_ 至 _END_ 筆結果，共 _TOTAL_ 筆",
            infoFiltered: "",
            infoEmpty: "無相關資料",
            zeroRecords: "沒有符合的資料",
            lengthMenu: "顯示 _MENU_ 筆",
            paginate: {
              first: "第一頁",
              previous: "上一頁",
              next: "下一頁",
              last: "最後一頁",
            },
          },
        });
        getData();
        });
   
     
     function getData() {
         $.ajax({
           url:"UserServlet?method=list",
           type:"GET",       
         }).done((data) => {
           $("#resultTable").DataTable({
             data:data,
             retrieve:"true",
             language: {
               search: "搜尋表格:",           
               info: "顯示第 _START_ 至 _END_ 筆結果，共 _TOTAL_ 筆",
               infoFiltered: "",
               infoEmpty: "無相關資料",
               zeroRecords: "沒有符合的資料",
               lengthMenu: "顯示 _MENU_ 筆",
               paginate: {
                 first: "第一頁",
                 previous: "上一頁",
                 next: "下一頁",
                 last: "最後一頁",
               },
             },
             order: [1, "asc"],
           });
         });
       }*/
	

</script>
</body>
</html>