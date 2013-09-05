var count = new Count(0);
$(function(){
	//Í¼Æ¬ÇÐ»»
    $(".silde-box .lst-nav li").each(function (index) {
        $(this).hover(function () {
            if (count.time) {
                clearTimeout(count.time);
            }
            count.index = index;
            $(this).addClass("current").siblings("li").removeClass("current");
            $(".silde-box .lst-content li").eq(index).fadeIn().siblings("li").hide();
        }, function () {
            count.time = setTimeout("fadeImg()", 3000);
        });
    });
    fadeImg();
});
//Í¼Æ¬ÇÐ»»
function fadeImg() {
    if (count.time) {
        clearTimeout(count.time);
    }
    if (count.index > count.max) {
        count.index = 0;
    }
    $(".silde-box .lst-nav li").eq(count.index).addClass("current").siblings("li").removeClass("current");
    $(".silde-box .lst-content li").eq(count.index).fadeIn().siblings("li").hide();
    count.index += 1;
    count.time = setTimeout("fadeImg()", 3000);
}
function Count(index) {
    this.time = null;
    this.index = index;
    this.max = 3;
}