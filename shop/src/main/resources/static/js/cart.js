
/*全選全不選*/
function checkall(ckbtn) {
	$(".ckitem").prop("checked", $(ckbtn).prop("checked"));
	//calcTotal();
}
//刪除按鈕
function delCartItem(btn) {
	
	$(btn).parents("tr").remove();
	//calcTotal();
}
//批量删除按钮
function selDelCart() {
	//遍歷所有按钮
	for (var i = $(".ckitem").length - 1; i >= 0; i--) {
		//如果選種
		if ($(".ckitem")[i].checked) {
			//刪除
			$($(".ckitem")[i]).parents("tr").remove();
		}
	}
	//calcTotal();
}
$(function() {

	//單選一個價格
	$(".ckitem").click(function() {
			//calcTotal();
		})
		//开始时计算价格
		//calcTotal();

})

//计算单行小计价格的方法
function calcRow(rid) {
	//單價
	var vprice = parseFloat($("#moneyPrice"+rid).html());
	//數量
	var vnum = parseFloat($("#moneyCount"+rid).val());
	//金額
	var vtotal = vprice * vnum;
	//賦值
	$("#moneyCast"+rid).html("$" + vtotal);



}

