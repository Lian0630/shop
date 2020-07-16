$(function() {

	//商品數+1
	$("#numUp").click(function() {
		var n = parseInt($("#num").val());
		$("#num").val(n + 1);
	})

	//商品數-1
	$("#numDown").click(function() {
		var n = parseInt($("#num").val());
		if (n == 1) {
			return;
		}
		$("#num").val(n - 1);
	})

	$(".img-big:eq(0)").show();

})