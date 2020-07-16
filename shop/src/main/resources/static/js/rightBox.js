$(function(){
	getUsername();
	showRightBox(); 
})
function showRightBox(){
	var html =   '<p class="logout-title"><a href="index.html" style="text-decoration:none;color: white">首頁</a></p>'
				+'<p class="logout-title"><a href="register.html" style="text-decoration:none;color: white">註冊</a></p>'
				+'<p class="logout-title"><a href="login.html" style="text-decoration:none;color: white">登入</a></p>'
				+'<p class="login-title"><a id="title-username" style="text-decoration:none;color: white">#{username}</a></p>'
				+'<p class="login-title"><a href="index.html" style="text-decoration:none;color: white">首頁</a></p>'
				+'<p class="login-title"><a href="userdata.html" style="text-decoration:none;color: white">個資</a></p>'
				+'<p class="login-title"><a href="cart.html" style="text-decoration:none;color: white">購物車</a></p>'
				+'<p class="login-title"><a onclick="logout()" href="" style="text-decoration:none;color: white">登出</a></p>';
	
		$(".right_box").html(html);

	  
}



function getUsername(){
	var url = "../user/info.do";
	$.ajax({
		"url":url,
		"type":"POST",
		"dataType":"json",
		"success":function(json){
			if(json.data.username != null){
				$("#title-username").html(json.data.username);
				$(".logout-title,.login-title").toggle();
			}
			
		}
	})
}

function logout(){
	var url = "../user/logout.do";

    // 發出ajax請求並處理結果
    $.ajax({
        "url": url,
        "type": "POST",
        "dataType": "json",
        "success": function(json) {
            if (json.state == 200) {
                alert("登出成功！");
                location.href = "login.html";
            } else {
            	alert("未登入！");
                location.href = "login.html";
            }
        }
    })
}
