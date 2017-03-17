var arr = [ "#id01", "#id02", "#id03" ];

function function1(a) {
	for (var i = 0; i < 3; i++) {
		$(arr[i]).removeClass("active");
	}
	$(a).addClass("active");
}

function function2(a) {
	$('.m-btn').removeClass("active");
}