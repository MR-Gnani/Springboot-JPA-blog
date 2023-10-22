let index = {
		init: function(){
			$("#btn-save").on("click", ()=>{ // function(){}, ()=>{} this를 바인딩하기 위해서..??!
				this.save();
			});
		},
		
		save: function(){
			// alert('user의 save함수 호출됨');
			let data = {
					username: $("#username").val(),
					password: $("#password").val(),
					email: $("#email").val()
			};
			
			// console.log(data);
			
			// ajax 호출 시 default가 비동기 호출
			//ajax통신을 이용해서 3개의 데이터를 json으로 변경하고 insert요청
			$.ajax({
				type: "POST",
				url: "/blog/api/user",
				data: JSON.stringify(data), // http body 데이터 (MIME 타입이 필요)
				contentType: "application/json; charset=utf-8", // body 데이터가 어떤 타입인지(MIME)
				dataType: "json" // 서버에 요청을 하고 응답이 왔을 때 기본적으로 모든 것이 문자열 (생긴게 JSON이라면 -> javascript오브 젝트로 변경)
			}).done(function(resp){
				alert("회원가입이 완료되었습니다.");
				// console.log(resp);
				location.href = "/blog";
			}).fail(function(error){
				alert(JSON.stringify(error));
			}); 
			
		}
}

index.init();