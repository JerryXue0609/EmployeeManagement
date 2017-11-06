$(function(){
	airBalloon('div.air-balloon');
	var $inp = $('input');
	$inp.keypress(function (e) { //这里给function一个事件参数命名为e，叫event也行，随意的，e就是IE窗口发生的事件。
		$('#ermsg').css("display", "none");
		var key = e.which; //e.which是按键的值
		if (key == 13) {
			login();
		}
	});
});

/*
@function 热气球移动
@update by julying , 2012/7/25
*/
function airBalloon(balloon){
	var viewSize = [] , viewWidth = 0 , viewHeight = 0 ;
	resize();	
	$(balloon).each(function(){
		$(this).css({top: rand(40, viewHeight * 0.5 ) , left : rand( 10 , viewWidth - $(this).width() ) });
		fly(this);
	});	
	$(window).resize(function(){
		resize()
		$(balloon).each(function(){
			$(this).stop().animate({top: rand(40, viewHeight * 0.5 ) , left : rand( 10 , viewWidth - $(this).width() ) } ,1000 , function(){
				fly(this);
			});
		});
	});
	function resize(){
		viewSize = getViewSize();
		viewWidth = $(document).width() ;
		viewHeight = viewSize[1] ;
	}
	function fly(obj){
		var $obj = $(obj);
		var currentTop = parseInt($obj.css('top'));
		var currentLeft = parseInt($obj.css('left') );
		var targetLeft = rand( 10 , viewWidth - $obj.width() );
		var targetTop = rand(40, viewHeight /2 );
		/*求两点之间的距离*/
		var removing = Math.sqrt( Math.pow( targetLeft - currentLeft , 2 )  + Math.pow( targetTop - currentTop , 2 ) );		
		/*每秒移动24px ，计算所需要的时间，从而保持 气球的速度恒定*/
		var moveTime = removing / 24;		
		$obj.animate({ top : targetTop , left : targetLeft} , moveTime * 1000 , function(){
			setTimeout(function(){
				fly(obj);
			}, rand(1000, 3000) );
		});	
	}
};

function login(){
	var username=$('#username').val();
	var password=$('#password').val();
	var indent = $("[name='ident']").filter(":checked").val();
	$.ajax({
		type: 'POST',
		url: './login.json',
		async: false,//同步
		data:{username:username, password:password,indent:indent},
		dataType: 'json',
		success: function (data) {
			if(data.code==0){
				location.href="./index.html";
			}else{
				$('#ermsg').css("display", "block");
			}
		},
		error: function (xhr, status, error) {
			$('#ermsg').css("display", "block");
		}
	});
}