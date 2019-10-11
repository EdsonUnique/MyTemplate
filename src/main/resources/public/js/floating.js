// 飘窗
<script type="text/javascript">

    (function($) {
        $.fn.floatAd = function(options) {
            var defaults = {
                imgSrc:"",
                url:"",
                openStyle: 0,
                speed: 30
            };
            var itl ="";
            var options = $.extend(defaults, options);
            var _target = "target='_blank'";
            var html = "<div id='float_ad' style='position:absolute;left:0px;top:0px;border:1px;width:220px;height:130px;z-index:100;'>";
            html +="  <a href='" +options.url + "' " + _target + "><img style='height:130px;width:220px;z-index: 102' src='" + options.imgSrc + "'  class='float_ad_img'/>"+"<span style='position:absolute;color: white; z-index:222;left: 10px;top:20px'>"+$("#floatingWords").text()+"</span>"+"</a>";
            html += "<div id='close_f_ad' style='position:absolute;width:30px;height:16px;top:-18px;right:0px;cursor:pointer;float:right;font-size:14px'>关闭</div></div>";
            $('body').append(html);
            function init() {
                var x = 0,
                    y = 0;
                var xin = true,
                    yin = true;
                var step = 1;
                var delay = 10;
                var obj = $("#float_ad");
                obj.find('img.float_ad_img').load(function() {
                    var float = function() {
                        var L = T = 0;
                        var OW = obj.width();
                        var OH = obj.height();
                        var DW = $(window).width();
                        var DH = $(window).height();
                        x = x + step * (xin ? 1 : -1);
                        if (x < L) {
                            xin = true;
                            x = L;
                        }
                        if (x > DW - OW - 1) {
                            xin = false;
                            x = DW - OW - 1;
                        }
                        y = y + step * (yin ? 1 : -1);
                        if (y > DH - OH - 10) {
                            yin = false;
                            y = DH - OH - 10;
                        }
                        if (y < T) {
                            yin = true;
                            y = T;
                        }
                        var left = x;
                        var top = y;
                        obj.css({
                            'top': top,
                            'left': left
                        })
                    };
                    itl = setInterval(float, options.speed);
                    $('#float_ad').mouseover(function() {
                        clearInterval(itl)
                    });
                    $('#float_ad').mouseout(function() {
                        itl = setInterval(float, options.speed)
                    })
                })
            }
            init();
            $('#close_f_ad').click(function(){
                $('#float_ad').css('display','none');
                clearInterval(itl);
            });
        }
    })(jQuery);

$(document).ready(function() {

    $(function() {
        $("body").floatAd({
            imgSrc: '../public/img/admin-bg.jpg',
            url: $("#floatingUrl").text(),

        });
    })
});
</script>

// <span style="display:block;color: white" id="floatingWords">${homePageSetting.floatingWords!}</span>
//     <span style="display:block;color: white" id="floatingUrl">${homePageSetting.floatingUrl!}</span>