<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<jsp:include page="../include/header.jsp"/>

	<h1>API 숙제를 해보자</h1>
	
	<button onclick="call()">요청 보내기</button>
	
	<div class="container" style="width : 1000px; height :500px; margin:auto;">
		<table>
			<tr>
				<td width="200">병원명</td>	
				<td width="200">주소지</td>	
				<td width="200">오시는길</td>	
				<td width="200">전화번호</td>	
		
			</tr>
		</table>
			
	</div>
		
	<script>
		function(){
			
			
			$.ajax({
				url : 'infection',
				type: 'get',
				success : result => {
					
					const infection = result.body;
					
					const resultEl=	infection.map(e => 
					` 
						<tr>
							<td>\${e.INST_NM}</td>
							<td>\${e.ADDR}</td>
							<td>\${e.ESNS_RGHMP}</td>
							<td>\${e.RPRS_TLHN_1}</td>
						
						</tr>
					
					`		).join('');
							
				document.querySelector('tbody').innerHTML =resultEl	
					
					
					
					
				}
				
			})
		}
			
	
	
	
	</script>
	
	
	
	<jsp:include page="../include/footer.jsp"/>

</body>
</html>