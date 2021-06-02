<%@ page language="java" contentType="text/html; charset=UTF-8" 
    pageEncoding="UTF-8"%>
    
    
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
		<!-- table開始 -->
		<table id="resultTable" class="table table-bordered table-hover dataTable no-footer" role="grid" aria-describedby="resultTable_info">
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
                <td>身分</td>
                <td>姓名</td>
                <td>電話</td>
                <td>地址</td>
                <td>備註</td>
             </tr>            
             </tbody>    
          </table>
          
       <!-- table結束 -->


<script type="text/javascript" src="./js/datatables.min.js"></script>

<script type="text/javascript">

var data = [
    [
        "Tiger Nixon",
        "System Architect",
        "Edinburgh",
        "5421",
        "2011/04/25",
    ],
    [
        "Garrett Winters",
        "Director",
        "Edinburgh",
        "8422",
        "2011/07/25",
    ]
];


	
//Datatable
$(function () {
 $("#resultTable").DataTable({
   order: [1, "asc"],
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
 //getData();
});
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
     //Datatable
    /* $(function () {
      $("#resultTable").DataTable({
        order: [1, "asc"],
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
        url: "UserServlet?method=list",
        type: "GET",
      }).done((data) => {
        $("#resultTable").DataTable({
          data: data,
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