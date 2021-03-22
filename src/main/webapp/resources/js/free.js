/**
 * 자유게시판 Java Script
 */


  	
/**
 * 자유게시판 검색 script
 * @param e
 * @returns
 */
$(document).on('click', '#btnSearch', function(e){
	
	
	e.preventDefault();

	var url = "list.action";

	url = url + "?searchType=" + $('#searchType').val();

	url = url + "&keyword=" + $('#keyword').val();

	location.href = url;

	console.log(url);

});	


	
	

