
$(document).ready(function(){   
	

	//initSwiper();
	
	 	 
	$('body').mCustomScrollbar({
		height: '100%',
		scrollInertia:0,
    });



	$('.modal').appendTo("body");

	$(".touchspin").TouchSpin({
		verticalbuttons: true,
		buttondown_class: 'btn btn-white',
		buttonup_class: 'btn btn-white'
	});

	$(".touchspin01").TouchSpin({
		verticalbuttons: false,
		buttondown_class: 'btn btn-white',
		buttonup_class: 'btn btn-white'
	});

	/* ---------------------------------------------
	  textarea 글자수 제한
	--------------------------------------------- */
	$(function(){
		var checkBodyWrite = $('.checkBodyDetail .checkBodyWrite textarea');

		checkBodyWrite.each(function(e){		
			checkBodyWrite.keyup(function (e){
				var content = $(this).val();
				$(this).height(((content.split('\n').length + 1) * 1.5) + 'em');
				$('#counter').html(content.length + '/200');
			});
			checkBodyWrite.eq(e).keyup();
		});
	});



	$('.logoutArea ul li .language').on("click", function(e){
        e.preventDefault();
        $('.language-inner').toggleClass('active');
        if($('.language-inner').hasClass('active')){
            $(this).addClass('on');
        } else {
            $(this).removeClass('on');
        }
    });


	// MetisMenu
    var sideMenu = $('#side-menu').metisMenu();   
	

	// Minimalize menu
    $('.navbar-minimalize').on('click', function (e) {
        e.preventDefault();
        $("body").toggleClass("mini-navbar");		
        SmoothlyMenu();
		
    });


	$(function(){
		$('.modelSelectWrap .modelSelectCon').hide();
		//$('.tab_view01:first').show();	
		$('.modelSelectTabList .modelSelectIco a').each(function(i){		
			$(this).click(function(){
				$('.modelSelectTabList .modelSelectIco a').each(function(){
					$(this).parent().removeClass('active');
				});			
					$(this).parent().addClass('active');
					$('.modelSelectCon').hide();
					$('.modelSelectCon').eq(i).fadeIn(1000);
						return false;
			});	
		});
	});
    
});


function initSwiper(){ 
	var tabsSwiper = new Swiper('#stepContent', {
		calculateHeight:true,
		touchRatio: 0,//드래그 금지
		slidesPerView:'auto',
		observer: true,
		observeParents: true,
		spaceBetween : 30,
		//updateOnWindowResize: true,
		//resizeObserver: true,
	});
	
	var _tabList = $( ".panel-indicators" );
	var _tabTarget = _tabList.children( "li" );
	_tabTarget.find("a").on( "click", function(e){
		var itemIndex = $( this ).parent().index();
		tabsSwiper.slideTo(itemIndex); //index값으로 slideto에 넘김			
	});	
	
};


function SmoothlyMenu() {
    if (!$('body').hasClass('mini-navbar') || $('body').hasClass('tablet')) {
        // Hide menu in order to smoothly turn on when maximize menu
        $('#side-menu').hide();
        // For smoothly turn on menu
        setTimeout(
            function () {
                $('#side-menu').fadeIn(400);
            }, 200);
    } else if ($('body').hasClass('fixed-sidebar')) {
        $('#side-menu').hide();
        setTimeout(
            function () {
                $('#side-menu').fadeIn(400);
            }, 100);
    } else {
        // Remove all inline style from jquery fadeIn function to reset menu state
        $('#side-menu').removeAttr('style');
    }
};


$(window).bind("resize", function () {
    if ($(this).width() < 993) {
        $('body').addClass('tablet')
    } else {
        $('body').removeClass('tablet')
    }

	$('.tab_view').removeClass('active');
	$('.tab_view:first').addClass("active");
	$('#TabArea01 > li').removeClass("active");
	$('#TabArea01 > li:first').addClass("active");
});


$(document).ready(function(){
	if ($(this).width() < 993) {
        $('body').addClass('tablet');		
    } else {
        $('body').removeClass('tablet');
    }

	//탭 클릭 이벤트
	$(".TabArea > li > a").bind({
		click : function(e){
			e.preventDefault();

			$(this).parent().siblings().removeClass("active");
			$(this).parent().addClass("active");

			var idx = $(this).parent().index();

			$(this).parents(".subConView").find(".tab_view").removeClass("active");
			$(this).parents(".subConView").find(".tab_view").eq(idx).addClass("active");
		}
	});

	$(".PhilosophyTab .tabTitle > a").on({
		click:function() {
			$(this).parents('.tab_view').toggleClass('active').siblings().removeClass('active');
		}
	});

	$(".NewsTab .tabTitle > a").on({
		click:function() {
			$(this).parents('.tab_view').toggleClass('active').siblings().removeClass('active');
		}
	});

	$("#businessContent .tabTitle > a").on({
		click:function() {
			$(this).parents('.business_view').toggleClass('active').siblings().removeClass('active');
		}
	});

	$(".ourCultureTab .tab_view .tabTitle > a").on({
		click:function() {
			$(this).parents('.tab_view').toggleClass('active').siblings().removeClass('active');
		}
	});
});



/* gotoTop */
$(function() {
	$(window).scroll(function(){
		if ($(this).scrollTop() > 100) {
			$('#gototop').fadeIn();
			} else {
				$('#gototop').fadeOut();
			}
		});
		$('#gototop').click(function(){
			$("html, body").animate({ scrollTop: 0 }, 400);
				return false;
		});
	
});







var GlobalData = {
    isMain:$("body").hasClass("main")
}

$(function(){

    var HEADER_OPAQUE_SCROLL_TOP = 200;
    var pausedScrollTop = 0;
    var timer ;
    if(!GlobalData.isMain) $("#footer .anim-group").addClass("in");

    

    /* header */
    $('#header .language .current').on('click', function(e){
        e.preventDefault();
    });

    $('#header .language').on('click', function(e){
        $(this).toggleClass('active');
        if(GlobalData.isMain){
            var gnbSubMenuHeight = $('#header .gnb-sub-menus-container').height();
            if ( $(window).scrollTop() < gnbSubMenuHeight ) {

                $('html,body').stop().animate({'scrollTop': gnbSubMenuHeight}, 600, 'easeOutQuint');

            }
        }
        /*var languageHeight = $('#header .language .langs').height() - $('#header').height() / 2;
        if ( $(window).scrollTop() < languageHeight ) {
            $('html,body').stop().animate({'scrollTop': languageHeight}, 600, 'easeOutQuint');
        }*/
    });

    $(window).on('click', function(e){
        if (!$(e.target).closest('.language')[0]) {
            $('#header .language').removeClass('active');
        }
    });

    $('#header').on('mouseleave', function(e){
        $('#header').removeClass('gnb-sub-menu-opened');
        $('#header #nav .gnb li').removeClass('active inactive');
        $('#header .gnb-sub-menus .gnb-sub-menu').removeClass('active');
        clearTimeout(timer);
    });


    $('#header #nav a').on('mouseenter', function(e){
        $(this).closest('li').removeClass('inactive').addClass('active').siblings().removeClass('active').addClass('inactive');
        hideSearch();
        if(GlobalData.isMain){
            timer = setTimeout(function(){
                $('#header #gnbSubMenus').addClass('active');
            },200)
        }else{
            $('#header #gnbSubMenus').addClass('active');
        }

        //if ($('#header').hasClass('fixed')) {
        //  showGnbSubMenu();
        //}
    });

    $('#header #nav a').on('click', function(e){
        if(GlobalData.isMain){
            e.preventDefault();
            $(this).closest('li').removeClass('inactive').addClass('active').siblings().removeClass('active').addClass('inactive');
            showGnbSubMenu();
        }
    });

    function showGnbSubMenu() {
        hideSearch();
        $('#header #gnbSubMenus').addClass('active');
        if(GlobalData.isMain){
            var gnbSubMenuHeight = $('#header .gnb-sub-menus-container').height();
            if ( $(window).scrollTop() < gnbSubMenuHeight ) {

                $('html,body').stop().animate({'scrollTop': gnbSubMenuHeight}, 600, 'easeOutQuint');

            }
        }
    }

    function hideGnbSubMenu() {
        $('#header #gnbSubMenus').removeClass('active');
        $('#nav .gnb li').addClass('active').siblings().removeClass('active');
    }

    $('#header').on('mouseleave', function(e){
        hideGnbSubMenu();
    });

    $('#header .gnb-sub-menus .backdrop').on('mouseenter', function(e){
        hideGnbSubMenu();
    });

    $('#gnbSubMenus .menus > ul > li').on('mouseenter', function(e){
        var index = $(this).index();
        // console.log(index);
        $('#nav .gnb li:eq('+ index + ')').addClass('active').siblings().removeClass('active').addClass('inactive');
    });

    $('#gnbSubMenus .menus > ul > li').on('mouseleave', function(e){
        var index = $(this).index();
        // console.log(index);
        $('#nav .gnb li:eq('+ index + ')').removeClass('active').siblings().removeClass('inactive');
    });

    //search
    function showSearch() {
        hideGnbSubMenu();
        $('#header #search').addClass('active');
        setTimeout(function(){
            $('form[name="search_form"] input[name="query"]').focus();
        }, 1200);
        /*var searchBoxHeight = $('#header #search .search-box').innerHeight();
        if ( $(window).scrollTop() < searchBoxHeight ) {
            $('html,body').stop().animate({'scrollTop': searchBoxHeight}, 600, 'easeOutQuint');
        }*/

        $('#header .search .btn-close').show();
        $('#header .search .btn-close').css('opacity', '1');
        $('#header .search .btn-search').hide();
    }

    function hideSearch() {
        $('#header #search').removeClass('active');
        $('#header .search .btn-close').hide();
        $('#header .search .btn-search').show();
        $('form[name="search_form"] input[name="query"]').val('');
    }
    $('#header .btn-search').on('click', function(e){
        e.preventDefault();
        showSearch();

    });

    $('#header #search').on('click', function(e){
        if (!$(e.target).closest('.search-box')[0]) {
            hideSearch();
        }
    });

    $('form[name="search_form"] input[name="query"]').on('keydown keyup', function(){
        var val = $(this).val();
        if ( val != '') {
            $('#header #search form button').addClass('active');
        } else {
            $('#header #search form button').removeClass('active');
        }
    });

    $('#header .search .btn-close').on('click', function(e){
        e.preventDefault();
        hideSearch();
    });
    $('#header .search .btn-close').hide();

    /*mobileNav*/
    function showMobileNav() {
        pausedScrollTop = $(window).scrollTop();
        $('body').addClass('mobile-nav-opened');
        $('body').css({'top':-pausedScrollTop});
        if (pausedScrollTop > $(window).innerHeight() - $('#header .header-container').innerHeight()) {
            $('body').addClass('header-fixed-at-mobile-nav-opened');
        } else {
            $('body').removeClass('header-fixed-at-mobile-nav-opened');
        }
    }

    function hideMobileNav() {
        $('body').removeClass('mobile-nav-opened');
        $('body').removeClass('header-fixed-at-mobile-nav-opened');
        $(window).scrollTop(pausedScrollTop);
    }

    $('#header .btn-toggle-menu').on('click', function(e){
        e.preventDefault();
        showMobileNav();
    });


    $('#mobileNav .gnb .first-menu > a').on('click', function(e){
        e.preventDefault();
        if($(this).closest('li').hasClass('active') ) {
            $(this).closest('.first-menu').next().stop().slideUp();
            $(this).closest('li').removeClass('active').siblings().removeClass('active');
        } else {
            $(this).closest('.first-menu').next().stop().slideDown();
            $(this).closest('li').addClass('active').siblings().removeClass('active');
        }
        $(this).closest('li').siblings().find('.sub-menu').stop().slideUp();
    });


    $('#mobileNav .btn-toggle-menu').on('click', function(e){
        e.preventDefault();
        hideMobileNav();
    });

    $('#mobileNav .btn-search').on('click', function(e){
        e.preventDefault();
        $('.mobile-search').addClass('active');
        setTimeout(function(){
            $('.mobile-search form[name="search_form"] input[name="query"]').focus();
        }, 1200);
    });

    $('#mobileNav .mobile-search .btn-close').on('click', function(e) {
        e.preventDefault();
        $('.mobile-search').removeClass('active');
        $('form[name="search_form"] input[name="query"]').val('');
    });


    $('#mobileNav').on('click touchstart', function(e){
        if ( e.target == $('#mobileNav')[0]) {
            hideMobileNav();
        }
    });

    $('#mobileNav .gnb li .first-menu a.on').trigger('click');
    //$('#mobileNav .gnb li:first-child .first-menu > a').trigger('click');

    /* footer */
    $('select[name="family_site"]').on('change', function(e){
        e.preventDefault();
        var link = $(this).val();
        if ( link && link != '' ) {
            window.open(link);
            $('select[name="family_site"] option:selected').removeAttr('selected');
            $('select[name="family_site"] option:first-child').attr('selected', 'selected')
            $('select[name="family_site"]').val('');
        }
    });


    /* scroll */
    $(window).on('scroll', function(){
        if ($('body').hasClass('loaded') === false) return;
        onScroll();
    }).trigger('scroll');

    function onScroll() {
        var scrollTop = $(window).scrollTop();
        var windowHeight = $(window).innerHeight();
        var subHeaderCheck = $("#header").hasClass("sub_header");

        if(subHeaderCheck) return;

        if ( scrollTop > HEADER_OPAQUE_SCROLL_TOP) {
            $('#header').addClass('opaque');
            $('.home-slider').addClass('scrolled');
        } else {
            $('#header').removeClass('opaque');
            $('.home-slider').removeClass('scrolled');
        }


        if (scrollTop > windowHeight - $('#header .header-container').innerHeight()) {
            $('#header').addClass('fixed');
        } else {
            $('#header').removeClass('fixed');
        }


        var animOffsetHeight = windowHeight * .75;
        $('.anim-group').each(function(){
            var offsetTop = $(this).offset().top;
            if (  scrollTop + animOffsetHeight > offsetTop ) {
                $(this).addClass('in');
            }
        });

        // $('.home-slider-item .bg .back').css('transform', 'translateY(' + scrollTop * .7 + 'px)');
    }

    /* resize */
    $(window).on('resize', function(){
        if ($('body').hasClass('loaded') == false) return;
        onResize();
    }).trigger('resize');

    function onResize() {
        var windowHeight = $(window).innerHeight();
        var homeSliderMinHeight = parseInt($('.home-slider').css('min-height'));
        var sliderHeight = (homeSliderMinHeight > 0) ? Math.max(homeSliderMinHeight, windowHeight) : windowHeight;

        if($("#BAND_BANNER").length > 0){
            sliderHeight -= $("#BAND_BANNER").height();
        }

        $('#homeTop').height(sliderHeight);
        $('.home-slider').height(sliderHeight);
        $('.home-slider-item').height(sliderHeight);
    }




    /*start*/
    $(window).on('load', function() {
        start();
    });

    function start() {
        if ($('body').hasClass('loaded')) return;
        setTimeout(function() {
            //$('html, body').scrollTop(0);
            onScroll();
            onResize();
        }, 10);
        $(window).trigger('ds-loaded');
        $('body').addClass('loaded');

    }

    setTimeout(function(){
        start();
    }, 2000);

    /*setTimeout(function() {
        $('html, body').scrollTop(0);
    }, 10);*/
});



(function (Modernizr) {

    $('html').addClass(platform.name);
    // Here are all the values we will test. If you want to use just one or two, comment out the lines of test you don't need.
    var tests = [{
        name: 'svg',
        value: 'url(#test)'
    }, // False positive in IE, supports SVG clip-path, but not on HTML element
        {
            name: 'inset',
            value: 'inset(10px 20px 30px 40px)'
        }, {
            name: 'circle',
            value: 'circle(60px at center)'
        }, {
            name: 'ellipse',
            value: 'ellipse(50% 50% at 50% 50%)'
        }, {
            name: 'polygon',
            value: 'polygon(50% 0%, 0% 100%, 100% 100%)'
        }
    ];


    var t = 0, name, value, prop;

    for (; t < tests.length; t++) {
        name = tests[t].name;
        value = tests[t].value;
        Modernizr.addTest('cssclippath' + name, function () {

            // Try using window.CSS.supports
            if ('CSS' in window && 'supports' in window.CSS) {
                for (var i = 0; i < Modernizr._prefixes.length; i++) {
                    prop = Modernizr._prefixes[i] + 'clip-path'

                    if (window.CSS.supports(prop, value)) {
                        return true;
                    }
                }
                return false;
            }

            // Otherwise, use Modernizr.testStyles and examine the property manually
            return Modernizr.testStyles('#modernizr { ' + Modernizr._prefixes.join('clip-path:' + value + '; ') + ' }', function (elem, rule) {
                var style = getComputedStyle(elem),
                    clip = style.clipPath;

                if (!clip || clip == "none") {
                    clip = false;

                    for (var i = 0; i < Modernizr._domPrefixes.length; i++) {
                        test = Modernizr._domPrefixes[i] + 'ClipPath';
                        if (style[test] && style[test] !== "none") {
                            clip = true;
                            break;
                        }
                    }
                }

                return Modernizr.testProp('clipPath') && clip;
            });
        });
    }

})(Modernizr);



/** COMMON */
var CommonJs = (function() {
    var g_fHashIdx = 0,
        g_$gnbDepth1 = $('#nav .gnb'),
        g_$gnbDepth2 = $('#gnbSubMenus'),
        g_$header = $('#header'),
        g_headerHeight = g_$header.height(),
        g_prevSt = 0,
        g_isWheel = false,
        g_isWheelUp = false,
        g_$searchArea = $('#header #search'),
        g_$searchInput = g_$searchArea.find($('form[name="search_form"] input[name="query"]')),
        g_$searchBtn = g_$searchArea.find($('form[name="search_form"] button[type=submit]')),
        g_$searchAreaMobile = $('#mobileNav'),
        g_$searchInputMobile = g_$searchAreaMobile.find($('form[name="search_form"] input[name="query"]')),
        g_$searchBtnMobile = g_$searchAreaMobile.find($('form[name="search_form"] button[type=submit]')),
        _lang;

    function init() {
        //gnbActive();
        addEvents();
        loadCheckHash();
        if ($('#SUB_KEYVISUAL').length > 0) {
            motionKV();
        }
    }

    function addEvents() {
        if(!GlobalData.isMain){
            $(window).scroll(scroll);
            scroll();

            $('html, body').on('mousewheel DOMMouseScroll', wheel);
        }


        /* 3,4depth center, active */
        if ($('.gu_tab_depth3').length > 0) centerTab($('.gu_tab_depth3'));

        if ($('.gu_tab_depth4').length > 0) {
            /* mobile - scrollbar hide */
            if (isMobile() === true) $('.gu_tab_depth4').addClass('mobile');

            /* tab click */
            $('.gu_tab_depth4 ul li a').on('click',function() {
                g_fHashIdx = $(this).parent('li').index();
                tabActive(g_fHashIdx);
                setScrollIcon();
            });
        }


        // pc / mobile 이미지 분기
        // ex) <img class="gu_img" src="../img.jpg" alt="" data-tablet="../t_img.jpg" data-mobile="../m_img.jpg">
        $(".gu_img").each(function() {
            if (Modernizr.mq('all and (max-width: 767px)')) {
                $(this).attr("src", $(this).attr("data-mobile"));
            } else if (Modernizr.mq('all and (max-width: 1080px)')) {
                $(this).attr("src", $(this).attr("data-tablet"));
            }
        });


        // gnb search
        $(g_$searchInput).on('keydown', function(e){
            if(e.keyCode == 13){
                e.preventDefault();
                searchGnb('web');
            }
        });

        g_$searchBtn.on('click',function(e){
            e.preventDefault();
            searchGnb('web');
        });

        $(g_$searchInputMobile).on('keydown', function(e){
            if(e.keyCode == 13){
                e.preventDefault();
                searchGnb('mobile');
            }
        });

        g_$searchBtnMobile.on('click',function(e){
            e.preventDefault();
            searchGnb('mobile');
        });

    }

    function scroll(){
        var m_st = $(window).scrollTop();

        if(!g_isWheel) g_isWheelUp = false;

        if(m_st > g_headerHeight){
            g_$header.addClass("small");
            g_$header.find(".gnb-sub-menu.on").addClass("active");

            if(!g_isWheelUp && m_st < g_prevSt) g_isWheelUp = true;
            if(g_isWheelUp) g_$header.removeClass("small").addClass("up");
        }else{
            g_$header.removeClass("small").removeClass("up");
            g_$header.find(".gnb-sub-menu.on").removeClass("active");
        }

        g_prevSt = m_st;
    }

    function wheel(e){
        var m_event = e.originalEvent,
            m_delta = 0;

        g_isWheel = true;

        if (m_event.detail) m_delta = m_event.detail * -40;
        else m_delta = m_event.wheelDelta;

        if(m_delta > 0) g_isWheelUp = true;
        else g_isWheelUp = false;
    }

    function getLocale() {
        _lang = $('html').attr('lang');

        switch(_lang) {
            case "ko" : _lang = "kr"; break;
            case "zh" : _lang = "cn"; break;
            default   : _lang = "en";
        }

        return _lang;
    }

    function isMobile() {
        var UserAgent = navigator.userAgent;

        if (UserAgent.match(/iPhone|iPod|Android|Windows CE|BlackBerry|Symbian|Windows Phone|webOS|Opera Mini|Opera Mobi|POLARIS|IEMobile|lgtelecom|nokia|SonyEricsson/i) != null || UserAgent.match(/LG|SAMSUNG|Samsung/) != null) {
            return true;
        } else {
            return false;
        }
    }

    function loadCheckHash() {
        if(location.hash.length > 0) {
            g_fHashIdx =  $('.gu_tab_depth4 ul li.li_'+location.hash.replace('#','')).index();
            tabActive(g_fHashIdx);
        } else {
            return;
        }
    }

    function tabActive(_idx) {
        $('.gu_tab_depth4 ul li').removeClass('on');
        $('.gu_tab_depth4 ul li').eq(_idx).addClass('on');
        $('.gu_contents_depth4').removeClass('on');
        $('.gu_contents_depth4').eq(_idx).addClass('on');

        centerTab($('.gu_tab_depth4'));
    }
/*
    function gnbActive() {
        var m_$container = $(".container"),
            m_classStr = m_$container.attr("class"),
            m_className = m_classStr.split(" ")[1],
            m_bnavi = false;

        if(m_className) {
            m_bnavi = true;
        }

        if(m_bnavi) {
            g_$gnbDepth1.find('li').removeClass('on');
            g_$gnbDepth2.find('li > ul.sub-menu li').removeClass('on');

            var fixDepthArr = m_className.split('_');

            if(fixDepthArr[0]=='subNavi') {
                $('.naviDepth1_'+fixDepthArr[1]).addClass('on');
                $('.naviDepth1_'+fixDepthArr[1]).addClass('on');
                $('.naviDepth2_'+fixDepthArr[1]+'_'+fixDepthArr[2]).addClass('on');
                centerTab($('.gnb-sub-menu.naviDepth1_' + fixDepthArr[1]));
            }
        }
    }
*/
    function centerTab(_tab) {
        var scrollX = _tab.find("ul").scrollLeft(),
            currentBtn = _tab.find("ul>li.on"),
            currentX = currentBtn.offset().left,
            currentW = currentBtn.width(),
            posx = scrollX+currentX,
            halfW = ($(window).width() - currentW) / 2 + 13;

        TweenMax.to(_tab.find("ul"), 0.7, {scrollLeft: posx - halfW, ease: Expo.easeOut});
    }

    function searchGnb(_device){
        var searchKeyword,
            alertMessage = '';
            _lang = CommonJs.getLocale();

        if (_device == 'web') searchKeyword = g_$searchInput.val();
        else searchKeyword = g_$searchInputMobile.val();

        if ($.trim(searchKeyword) === '') {
            alertMessage = (_lang === 'ko') ? '검색어를 입력해 주세요.' : ((_lang === 'en') ? 'Input Keyword' : '搜索');
            alert(alertMessage);
            return;
        } else {
            if (_device == 'web') {
                g_$searchArea.find($('form[name="search_form"]')).attr("action", '/' + _lang + '/search');
                g_$searchArea.find($('form[name="search_form"]')).submit();
            } else {
                g_$searchAreaMobile.find($('form[name="search_form"]')).attr("action", '/' + _lang + '/search');
                g_$searchAreaMobile.find($('form[name="search_form"]')).submit();
            }
        }
    }

    /* 가로스크롤 드래그 아이콘 */
    function setScrollIcon() {
        $(window).off('scroll.table');
        if (!$('.gu_table.scrollX:visible').length) return;
        if (!$('.gu_ico_drag').length) {
            var dom = '<div class="gu_ico_drag active">' +
                      '   <img src="/img/common/ico_drag.png">' +
                      '</div>';
            $('.gu_table.scrollX').eq(0).append(dom);
        } else {
            $('.gu_ico_drag').addClass('active');
        }
        removeScrollIcon();
    }

    function removeScrollIcon() {
        var m_firstTableTop = $('.gu_table.scrollX').offset().top,
            m_icoDrag = $('.gu_ico_drag');

        $(window).on('scroll.table', function() {
            if (($(window).scrollTop()) > (m_firstTableTop - ($(window).height() / 2)) && m_icoDrag.hasClass('active')) {
                $(window).off('scroll.table');
                setTimeout(function() {
                    m_icoDrag.removeClass('active');
                }, 3000)
            }
        })
    }


    //keyvisual in motion (1 depth)
    function motionKV() {
        var m_$kvImgArea = $("#SUB_KEYVISUAL .kv_img_area"),
            m_checkOneDepth = ["/sustainability/overview","investors/governance/stocks", "ir/ir-report", "career/talent"],
            m_currentHref = location.href,
            m_isInMotion = false;

        for (var i = 0, len = m_checkOneDepth.length; i < len; ++i) {
            if (m_currentHref.indexOf(m_checkOneDepth[i]) > -1) m_isInMotion = true;
        }

        if (m_isInMotion) setTimeout(function() { m_$kvImgArea.addClass("on"); }, 10);
        else m_$kvImgArea.addClass("active");
    }

    init();

    return{
        getLocale : getLocale,
        isMobile : isMobile,
        setScrollIcon : setScrollIcon
    }

})();



/** SWIPER SLIDE */
var SwiperSlide = (function() {
    var g_$swiperWrap = $(".swiper-container"),
        g_swiper,
        booleanObserver;

    function init() {

        // swiper observer option true/false
        if (g_$swiperWrap.hasClass('multiple')) booleanObserver = true;
        else booleanObserver = false;

        // swiper touch drag true/false
        if (CommonJs.isMobile() === false) setSwiper(false);
        else setSwiper(true);

        if (g_$swiperWrap.length > 0) {
            createSwiperOver();
        }
    }

    function createSwiperOver() {
        var dom = "";

        for (var i = 0, len = g_$swiperWrap.length; i < len; ++i) {
            g_$swiperWrap.eq(i).addClass("swiper-container" + i);

            dom += '<div class="over_area" data-idx="'+i+'">',
            dom += '<a href="javascript:;" class="over_l" style="cursor: url(/img/common/gallery_slide_prev.jpg), url(/img/common/gallery_slide_prev.cur), auto;">prev slide</a>',
            dom += '<a href="javascript:;" class="over_r" style="cursor: url(/img/common/gallery_slide_next.jpg), url(/img/common/gallery_slide_next.cur), auto;">next slide</a>',
            dom += '</div>';

            g_$swiperWrap.eq(i).find(".direction_area .slide_empty").append(dom);
        }
        setSwiperOverBtns();
    }

    function setSwiperOverBtns() {
        g_$swiperWrap.find(".over_area a").on("click", function(e) {
            e.preventDefault();
            var m_idx = $(this).index(),
                m_swiperIdx = parseInt($(this).parent().attr("data-idx")),
                m_currentSwiper = $(".swiper-container"+m_swiperIdx)[0].swiper;

            if(m_idx == 0) m_currentSwiper.slidePrev();
            else m_currentSwiper.slideNext();
        })
    }

    function setSwiper(booleanTouch) {

        g_swiper = new Swiper(g_$swiperWrap, {
            spaceBetween: 64,
            parallax: true,
            autoplay: {
                delay: 3000,
                disableOnInteraction: false,
            },
            observer: booleanObserver,
            observeParents: booleanObserver,
            simulateTouch: booleanTouch,
            effect: 'fade',
            pagination: {
                el: '.swiper-pagination',
                type: 'custom',
                renderCustom: function (swiper, current, total) {
                    return '<span class="swiper-pagination-current">' + ('0' + current).slice(-2) + '</span> / <span class="swiper-pagination-total">' + ('0' + total).slice(-2) + '</span>';
                }
            },
            navigation: {
                nextEl: '.swiper-button-next',
                prevEl: '.swiper-button-prev'
            },
            on: {
                progress: function (_progress) {
                    TweenMax.to($(this)[0].$el.find(".progress_thumb"), 0.3, {width: (_progress * 100) + '%', ease: Power3.easeOut});
                }
            }
        });
    }

    return {
        init : init
    }
})();


