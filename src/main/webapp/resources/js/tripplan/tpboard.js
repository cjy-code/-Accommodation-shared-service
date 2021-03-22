	//버튼 1개만 선택 가능하게 하기
	var currentMenu;
	var menuLinks = document.querySelectorAll('.btn-general');
	
	function clickMenuHandler() {
		if (currentMenu) {
			currentMenu.classList.remove('menu-active');
		}
		this.classList.add('menu-active');
		currentMenu = this;
	}
	
	for (var i = 0; i < menuLinks.length; i++) {
		menuLinks[i].addEventListener('click', clickMenuHandler);
	}
	
	
	//필터에 들어간 태그 클릭하면 삭제하기
	$(document).on('click', '#selectedTag .tag', function() {
		//자신 삭제
		$(this).remove();
		//선택되어있는 태그 배경색 초기화
		//console.log($(this).attr('class'));
		let tag = $(this).attr('class');
	
		$('.tag').parent().children().each(function(e) {
	
			if ($(this).hasClass(tag)) {
				$(this).css('background-color', 'initial');
			}
	
		})
	});
	
	
	//필터에 태그 동적으로 추가하기
	$('.tag').on('click', function() {
	
		let temp = '';
		temp += '<span class="selected ' + $(this).attr("class") + '">';
		temp += $(this).text() + '<span>X</span></span>\r\n';
	
		console.log(temp);
	
		if ($(this).parents('tr').hasClass('city')) {
			//동적으로 생성될 태그 temp에 담기
	
			//이미 선택되는 태그는 삭제
			$('#selectedTag').children('.citytag').detach();
		} else if ($(this).parents('tr').hasClass('season')) {
			$('#selectedTag').children('.seasontag').detach();
		} else if ($(this).parents('tr').hasClass('period')) {
			$('#selectedTag').children('.periodtag').detach();
		}
	
		//필터칸에 동적으로 태그(temp) 생성
		$('#selectedTag').append(temp);
	
		//citytd의 다른 자식들 배경색 초기화
		$(this).parent().children().css('background-color', 'initial');
	
		//선택한 태그 배경색 변경
		$(this).css('background-color', '#ccc');
	
	});