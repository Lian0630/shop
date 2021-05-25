<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	
	<link rel="stylesheet" type="text/css" href="./css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="./css/datatables.min.css">
	
	<script type="text/javascript" src="./js/datatables.min.js"></script>
	<script type="text/javascript" src="./js/jquery-3.3.1.min.js"></script>
	<script type="text/javascript" src="./js/bootstrap.min.js"></script>

<meta charset="UTF-8">
<title>首頁</title>
</head>
<body>
	
	<div class="container-fluid">
		<div class="row">
		<div class="col-sm-6">
				<div class="inputgroup">
					<label for="id">身分證字號</label>
					<input id="id" type="text" class="form-control">
				</div>
				<div class="inputgroup">
					<label for="name">姓名</label>
					<input id="name" type="text" class="form-control">
				</div>
				<div class="inputgroup">
					<label for="phone">電話</label>
					<input id="phone" type="text" class="form-control">
				</div>
				<div class="inputgroup">
					<label for="address">地址</label>
					<input id="address" type="text" class="form-control">
				</div>
				<div class="inputgroup">
					<label for="description">備註</label>
					<input id="description" type="text" class="form-control">
				</div>
				<div class="inputgroup">
					<button class="mx-2 btn btn-info">查詢</button>
					<button class="mx-2 btn btn-primary">新增</button>
					<button class="mx-2 btn btn-warning">修改</button>
					<button class="mx-2 btn btn-danger">刪除</button>
					<button class="mx-2 btn btn-info" onclick="getData()">列表</button>
				</div>
			</div>
		
		
			<div class="col-sm-6">
				<div id="resultTable_wrapper" class="dataTables_wrapper container-fluid dt-bootstrap4 no-footer">
					<div class="row">
						<div class="col-sm-12 col-md-6">
							<div class="dataTables_length" id="resultTable_length">
								<label>
									顯示
									<select name="resultTable_length" aria-controls="resultTable" class="form-control form-control-sm">
										<option value="10">10</option>
										<option value="25">25</option>
										<option value="50">50</option>
										<option value="100">100</option>
									</select>
									筆
								</label>
							</div>
						</div>
						<div class="col-sm-12 col-md-6">
							<div id="resultTable_filter" class="dataTables_filter">
								<label>
									搜尋表格
									<input type="search" class="form-control form-control-sm" placeholder="" aria-controls="resultTable">
								</label>
							</div>
						</div>
					</div>
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
                <td>A123456789</td>
                <td class="sorting_1">ddd</td>
                <td>1245780</td>
                <td>士林</td>
                <td>45612</td>
              </tr></tbody>
          </table>
						</div>
					</div>
					<div class="row">
						<div class="row">
							<div class="col-sm-12 col-md-5">
							<div class="dataTables_info" id="resultTable_info" role="status" aria-live="polite">
								顯示第 1 至 1 筆結果，共 1 筆
								</div>
								</div>
								<div class="col-sm-12 col-md-7">
								<div class="dataTables_paginate paging_simple_numbers" id="resultTable_paginate">
								<ul class="pagination">
								<li class="paginate_button page-item previous disabled" id="resultTable_previous">
								<a href="#" aria-controls="resultTable" data-dt-idx="0" tabindex="0" class="page-link">
								上一頁
							</a>
							</li>
							<li class="paginate_button page-item active">
							<a href="#" aria-controls="resultTable" data-dt-idx="1" tabindex="0" class="page-link">
							1
							</a>
							</li>
							<li class="paginate_button page-item next disabled" id="resultTable_next">
							<a href="#" aria-controls="resultTable" data-dt-idx="2" tabindex="0" class="page-link">
								下一頁
								</a>
								</li>
								</ul>
								</div>
								</div>
								</div>
					</div>
				</div>
			</div>
		
			
		</div>
	</div>



</body>
</html>