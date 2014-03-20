$(function() {
	$("#nav>ul>li").hover(function() {
		$(this).addClass("sfhover");
		$("#nav ul ul").slideUp("fast");
		$(this).find("a:first").animate({"top": "12px"},"fast");
		$(this).find("span").animate({"top": "-30px"},"fast");
		if (!$(this).find("ul").is(":animated")) $(this).find("ul").slideDown("fast")
	},
	function() {
		$(this).removeClass("sfhover");
		$(this).find("a:first").animate({"top": "0px"},"fast");
		$(this).find("span").animate({"top": "0px"},"fast");
		if (!$(this).find("ul").is(":animated")) $(this).find("ul").slideUp("fast");
		$("#nav ul ul").slideUp("fast")
	})
});