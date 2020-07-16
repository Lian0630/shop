$(function(){
	showFooter();
})


function showFooter(){
	var html =  '<div class="foot">'
	+'<span class="foot-left">'
	+'<a href="customerService.html">'
	+'	<img src="../images/index/reg1.jpg" width="20%" height="20%">'
	+'	</a>'
	+'	安全保護	'
	+'</span>'
	+'<span class="foot-mid">'
	+'	<a href="customerService.html">'
	+'		<img src="../images/index/reg2.jpg" width="20%" height="20%">'
	+'	</a>'
	+'	銀行匯款'
	+'</span>'
	+'<span class="foot-right">'
	+'	<a href="customerService.html">'
	+'		<img src="../images/index/reg3.jpg" width="20%" height="20%">'
	+'	</a>'
	+'	客戶服務'
	+'</span>'
	+'</div>'
	+'<div class="bottom" >'
	+'<div class="bottom-mid" >'
	+'<div>'
	+'		<ul>'
	+'		<li><h5>公告</h5></li>'
	+'		<li><a href="customerAnnouncement.html" style="color:white;text-decoration:none;">新手指南</a></li>'
	+'		<li><a href="customerAnnouncement.html" style="color:white;text-decoration:none;">服務保障</a></li>'
	+'		<li><a href="customerAnnouncement.html" style="color:white;text-decoration:none;">常見問題</a></li>'
	+'	</ul>'
	+'	</div>'
	+'<div>'
	+'	<ul>'
	+'		<li><h5>常見問題</h5></li>'
	+'			<li><a href="customerProblem.html" style="color:white;text-decoration:none;">個人戶實名驗證審核流程</a></li>'
	+'			<li><a href="customerProblem.html" style="color:white;text-decoration:none;">有哪些現行支付方法可以購買數位資產？</a></li>'
	+'			<li><a href="customerProblem.html" style="color:white;text-decoration:none;">如何購買數位資產？</a></li>'
	+'		</ul>'
	+'	</div>'
	+'	<div>'
	+'	<ul>'
	+'		<li><h5>聯繫我們</h5></li>'
	+'		<li>線上客服：週一至週五(例假日除外)上午十點至下午六點</li>'
	+'		<li>電話:(02)2722-xxxx</li>'
	+'		<li>信箱：sky79316@gmail.com</li>'
	+'		<li>門市地址：台北市中正區八德路X段 XXX號</li>'
	+'	</ul>'
	+'</div>'
	+'</div>'
	+'</div>'
	+'<div class="bottom-down" align="center">'
	+'<i>關於LianExchanges&nbsp/&nbsp使用條款&nbsp/&nbsp隱私權政策&nbsp/&nbsp常見問題</i>'
	+'</div>';

$(".footer").html(html);
	  
}




