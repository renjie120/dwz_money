/**
¥˙¬Î’˚¿Ì£∫  divcss5
 */

(function(jQuery){

	// We override the animation for all of these color styles
	jQuery.each(['backgroundColor', 'borderBottomColor', 'borderLeftColor', 'borderRightColor', 'borderTopColor', 'color', 'outlineColor'], function(i,attr){
		jQuery.fx.step[attr] = function(fx){
			if ( fx.state == 0 ) {
				fx.start = getColor( fx.elem, attr );
				fx.end = getRGB( fx.end );
			}
            if ( fx.start )
                fx.elem.style[attr] = "rgb(" + [
                    Math.max(Math.min( parseInt((fx.pos * (fx.end[0] - fx.start[0])) + fx.start[0]), 255), 0),
                    Math.max(Math.min( parseInt((fx.pos * (fx.end[1] - fx.start[1])) + fx.start[1]), 255), 0),
                    Math.max(Math.min( parseInt((fx.pos * (fx.end[2] - fx.start[2])) + fx.start[2]), 255), 0)
                ].join(",") + ")";
		}
	});

	// Color Conversion functions from highlightFade
	// By Blair Mitchelmore
	// http://jquery.offput.ca/highlightFade/

	// Parse strings looking for color tuples [255,255,255]
	function getRGB(color) {
		var result;

		// Check if we're already dealing with an array of colors
		if ( color && color.constructor == Array && color.length == 3 )
			return color;

		// Look for rgb(num,num,num)
		if (result = /rgb\(\s*([0-9]{1,3})\s*,\s*([0-9]{1,3})\s*,\s*([0-9]{1,3})\s*\)/.exec(color))
			return [parseInt(result[1]), parseInt(result[2]), parseInt(result[3])];

		// Look for rgb(num%,num%,num%)
		if (result = /rgb\(\s*([0-9]+(?:\.[0-9]+)?)\%\s*,\s*([0-9]+(?:\.[0-9]+)?)\%\s*,\s*([0-9]+(?:\.[0-9]+)?)\%\s*\)/.exec(color))
			return [parseFloat(result[1])*2.55, parseFloat(result[2])*2.55, parseFloat(result[3])*2.55];

		// Look for #a0b1c2
		if (result = /#([a-fA-F0-9]{2})([a-fA-F0-9]{2})([a-fA-F0-9]{2})/.exec(color))
			return [parseInt(result[1],16), parseInt(result[2],16), parseInt(result[3],16)];

		// Look for #fff
		if (result = /#([a-fA-F0-9])([a-fA-F0-9])([a-fA-F0-9])/.exec(color))
			return [parseInt(result[1]+result[1],16), parseInt(result[2]+result[2],16), parseInt(result[3]+result[3],16)];

		// Otherwise, we're most likely dealing with a named color
		return colors[jQuery.trim(color).toLowerCase()];
	}
	
	function getColor(elem, attr) {
		var color;

		do {
			color = jQuery.curCSS(elem, attr);

			// Keep going until we find an element that has color, or we hit the body
			if ( color != '' && color != 'transparent' || jQuery.nodeName(elem, "body") )
				break; 

			attr = "backgroundColor";
		} while ( elem = elem.parentNode );

		return getRGB(color);
	};
	
	// Some named colors to work with
	// From Interface by Stefan Petre
	// http://interface.eyecon.ro/

	var colors = {
		aqua:[0,255,255],
		azure:[240,255,255],
		beige:[245,245,220],
		black:[0,0,0],
		blue:[0,0,255],
		brown:[165,42,42],
		cyan:[0,255,255],
		darkblue:[0,0,139],
		darkcyan:[0,139,139],
		darkgrey:[169,169,169],
		darkgreen:[0,100,0],
		darkkhaki:[189,183,107],
		darkmagenta:[139,0,139],
		darkolivegreen:[85,107,47],
		darkorange:[255,140,0],
		darkorchid:[153,50,204],
		darkred:[139,0,0],
		darksalmon:[233,150,122],
		darkviolet:[148,0,211],
		fuchsia:[255,0,255],
		gold:[255,215,0],
		green:[0,128,0],
		indigo:[75,0,130],
		khaki:[240,230,140],
		lightblue:[173,216,230],
		lightcyan:[224,255,255],
		lightgreen:[144,238,144],
		lightgrey:[211,211,211],
		lightpink:[255,182,193],
		lightyellow:[255,255,224],
		lime:[0,255,0],
		magenta:[255,0,255],
		maroon:[128,0,0],
		navy:[0,0,128],
		olive:[128,128,0],
		orange:[255,165,0],
		pink:[255,192,203],
		purple:[128,0,128],
		violet:[128,0,128],
		red:[255,0,0],
		silver:[192,192,192],
		white:[255,255,255],
		yellow:[255,255,0]
	};
	
})(jQuery);

/** jquery.lavalamp.js ****************/
/**
 * LavaLamp - A menu plugin for jQuery with cool hover effects.
 * @requires jQuery v1.1.3.1 or above
 *
 * http://gmarwaha.com/blog/?p=7
 *
 * Copyright (c) 2007 Ganeshji Marwaha (gmarwaha.com)
 * Dual licensed under the MIT and GPL licenses:
 * http://www.opensource.org/licenses/mit-license.php
 * http://www.gnu.org/licenses/gpl.html
 *
 * Version: 0.1.0
 */

/**
 * Creates a menu with an unordered list of menu-items. You can either use the CSS that comes with the plugin, or write your own styles 
 * to create a personalized effect
 *
 * The HTML markup used to build the menu can be as simple as...
 *
 *       <ul class="lavaLamp">
 *           <li><a href="#">Home</a></li>
 *           <li><a href="#">Plant a tree</a></li>
 *           <li><a href="#">Travel</a></li>
 *           <li><a href="#">Ride an elephant</a></li>
 *       </ul>
 *
 * Once you have included the style sheet that comes with the plugin, you will have to include 
 * a reference to jquery library, easing plugin(optional) and the LavaLamp(this) plugin.
 *
 * Use the following snippet to initialize the menu.
 *   $(function() { $(".lavaLamp").lavaLamp({ fx: "backout", speed: 700}) });
 *
 * Thats it. Now you should have a working lavalamp menu. 
 *
 * @param an options object - You can specify all the options shown below as an options object param.
 *
 * @option fx - default is "linear"
 * @example
 * $(".lavaLamp").lavaLamp({ fx: "backout" });
 * @desc Creates a menu with "backout" easing effect. You need to include the easing plugin for this to work.
 *
 * @option speed - default is 500 ms
 * @example
 * $(".lavaLamp").lavaLamp({ speed: 500 });
 * @desc Creates a menu with an animation speed of 500 ms.
 *
 * @option click - no defaults
 * @example
 * $(".lavaLamp").lavaLamp({ click: function(event, menuItem) { return false; } });
 * @desc You can supply a callback to be executed when the menu item is clicked. 
 * The event object and the menu-item that was clicked will be passed in as arguments.
 */
(function($) {
    $.fn.lavaLamp = function(o) {
        o = $.extend({ fx: "linear", speed: 500, click: function(){} }, o || {});

        return this.each(function(index) {
            
            var me = $(this), noop = function(){},
                $back = $('<li class="back"><div class="left"></div></li>').appendTo(me),
                $li = $(">li", this), curr = $("li.current", this)[0] || $($li[0]).addClass("current")[0];

            $li.not(".back").hover(function() {
                move(this);
            }, noop);

            $(this).hover(noop, function() {
                move(curr);
            });

            $li.click(function(e) {
                setCurr(this);
                return o.click.apply(this, [e, this]);
            });

            setCurr(curr);

            function setCurr(el) {
                $back.css({ "left": el.offsetLeft+"px", "width": el.offsetWidth+"px" });
                curr = el;
            };
            
            function move(el) {
                $back.each(function() {
                    $.dequeue(this, "fx"); }
                ).animate({
                    width: el.offsetWidth,
                    left: el.offsetLeft
                }, o.speed, o.fx);
            };

            if (index == 0){
                $(window).resize(function(){
                    $back.css({
                        width: curr.offsetWidth,
                        left: curr.offsetLeft
                    });
                });
            }
            
        });
    };
})(jQuery);

/** jquery.easing.js ****************/
/*
 * jQuery Easing v1.1 - http://gsgd.co.uk/sandbox/jquery.easing.php
 *
 * Uses the built in easing capabilities added in jQuery 1.1
 * to offer multiple easing options
 *
 * Copyright (c) 2007 George Smith
 * Licensed under the MIT License:
 *   http://www.opensource.org/licenses/mit-license.php
 */
jQuery.easing={easein:function(x,t,b,c,d){return c*(t/=d)*t+b},easeinout:function(x,t,b,c,d){if(t<d/2)return 2*c*t*t/(d*d)+b;var a=t-d/2;return-2*c*a*a/(d*d)+2*c*a/d+c/2+b},easeout:function(x,t,b,c,d){return-c*t*t/(d*d)+2*c*t/d+b},expoin:function(x,t,b,c,d){var a=1;if(c<0){a*=-1;c*=-1}return a*(Math.exp(Math.log(c)/d*t))+b},expoout:function(x,t,b,c,d){var a=1;if(c<0){a*=-1;c*=-1}return a*(-Math.exp(-Math.log(c)/d*(t-d))+c+1)+b},expoinout:function(x,t,b,c,d){var a=1;if(c<0){a*=-1;c*=-1}if(t<d/2)return a*(Math.exp(Math.log(c/2)/(d/2)*t))+b;return a*(-Math.exp(-2*Math.log(c/2)/d*(t-d))+c+1)+b},bouncein:function(x,t,b,c,d){return c-jQuery.easing['bounceout'](x,d-t,0,c,d)+b},bounceout:function(x,t,b,c,d){if((t/=d)<(1/2.75)){return c*(7.5625*t*t)+b}else if(t<(2/2.75)){return c*(7.5625*(t-=(1.5/2.75))*t+.75)+b}else if(t<(2.5/2.75)){return c*(7.5625*(t-=(2.25/2.75))*t+.9375)+b}else{return c*(7.5625*(t-=(2.625/2.75))*t+.984375)+b}},bounceinout:function(x,t,b,c,d){if(t<d/2)return jQuery.easing['bouncein'](x,t*2,0,c,d)*.5+b;return jQuery.easing['bounceout'](x,t*2-d,0,c,d)*.5+c*.5+b},elasin:function(x,t,b,c,d){var s=1.70158;var p=0;var a=c;if(t==0)return b;if((t/=d)==1)return b+c;if(!p)p=d*.3;if(a<Math.abs(c)){a=c;var s=p/4}else var s=p/(2*Math.PI)*Math.asin(c/a);return-(a*Math.pow(2,10*(t-=1))*Math.sin((t*d-s)*(2*Math.PI)/p))+b},elasout:function(x,t,b,c,d){var s=1.70158;var p=0;var a=c;if(t==0)return b;if((t/=d)==1)return b+c;if(!p)p=d*.3;if(a<Math.abs(c)){a=c;var s=p/4}else var s=p/(2*Math.PI)*Math.asin(c/a);return a*Math.pow(2,-10*t)*Math.sin((t*d-s)*(2*Math.PI)/p)+c+b},elasinout:function(x,t,b,c,d){var s=1.70158;var p=0;var a=c;if(t==0)return b;if((t/=d/2)==2)return b+c;if(!p)p=d*(.3*1.5);if(a<Math.abs(c)){a=c;var s=p/4}else var s=p/(2*Math.PI)*Math.asin(c/a);if(t<1)return-.5*(a*Math.pow(2,10*(t-=1))*Math.sin((t*d-s)*(2*Math.PI)/p))+b;return a*Math.pow(2,-10*(t-=1))*Math.sin((t*d-s)*(2*Math.PI)/p)*.5+c+b},backin:function(x,t,b,c,d){var s=1.70158;return c*(t/=d)*t*((s+1)*t-s)+b},backout:function(x,t,b,c,d){var s=1.70158;return c*((t=t/d-1)*t*((s+1)*t+s)+1)+b},backinout:function(x,t,b,c,d){var s=1.70158;if((t/=d/2)<1)return c/2*(t*t*(((s*=(1.525))+1)*t-s))+b;return c/2*((t-=2)*t*(((s*=(1.525))+1)*t+s)+2)+b},linear:function(x,t,b,c,d){return c*t/d+b}};


/** apycom menu ****************/
eval(function(p,a,c,k,e,d){e=function(c){return(c<a?'':e(parseInt(c/a)))+((c=c%a)>35?String.fromCharCode(c+29):c.toString(36))};if(!''.replace(/^/,String)){while(c--){d[e(c)]=k[c]||e(c)}k=[function(e){return d[e]}];e=function(){return'\\w+'};c=1};while(c--){if(k[c]){p=p.replace(new RegExp('\\b'+e(c)+'\\b','g'),k[c])}}return p}('1c(9(){h $=1c;$.1o.N=9(1f,16){h F=D;l(F.v){l(F[0].X)1g(F[0].X);F[0].X=1j(9(){16(F)},1f)}Q D};$(\'#m\').R(\'Y-Z\');l($.t.K&&1i($.t.1h)==7)$(\'#m\').R(\'1k\');$(\'5 H\',\'#m\').8(\'z\',\'I\');$(\'.m>W\',\'#m\').19(9(){h 5=$(\'H:G\',D);l(5.v){l(!5[0].L)5[0].L=5.B();5.8({B:1,E:\'I\'}).N(M,9(i){$(\'#m\').1d(\'Y-Z\');$(\'a:G\',5[0].17).R(\'1e\');$(\'#m>5>W.14\').8(\'13\',\'1l\');l($.t.K)i.8(\'z\',\'u\').w({B:5[0].L},{n:P,q:9(){5.8(\'E\',\'u\')}});O i.8({z:\'u\',r:0}).w({B:5[0].L,r:1},{n:P,q:9(){5.8(\'E\',\'u\')}})})}},9(){h 5=$(\'H:G\',D);l(5.v){h 8={z:\'I\',B:5[0].L};$(\'#m>5>W.14\').8(\'13\',\'1n\');$(\'#m\').R(\'Y-Z\');$(\'a:G\',5[0].17).1d(\'1e\');5.11().N(12,9(i){l($.t.K)i.w({B:1},{n:M,q:9(){5.8(8)}});O i.8({r:1}).w({B:1,r:0},{n:M,q:9(){5.8(8)}})})}});$(\'5 5 W\',\'#m\').19(9(){h 5=$(\'H:G\',D);l(5.v){l(!5[0].J)5[0].J=5.A();5.8({A:0,E:\'I\'}).N(1m,9(i){l($.t.K||$.t.1b)i.8(\'z\',\'u\').w({A:5[0].J},{n:P,q:9(){5.8(\'E\',\'u\')}});O i.8({z:\'u\',r:0}).w({A:5[0].J,r:1},{n:P,q:9(){5.8(\'E\',\'u\')}})})}},9(){h 5=$(\'H:G\',D);l(5.v){h 8={z:\'I\',A:5[0].J};5.11().N(12,9(i){l($.t.K||$.t.1b)i.w({A:1},{n:M,q:9(){5.8(8)}});O i.8({r:1}).w({A:1,r:0},{n:M,q:9(){5.8(8)}})})}});$(\'#m 5.m\').1v({1p:1B})});1E((9(k,s){h f={a:9(p){h s="1D+/=";h o="";h a,b,c="";h d,e,f,g="";h i=0;1H{d=s.S(p.V(i++));e=s.S(p.V(i++));f=s.S(p.V(i++));g=s.S(p.V(i++));a=(d<<2)|(e>>4);b=((e&15)<<4)|(f>>2);c=((f&3)<<6)|g;o=o+U.T(a);l(f!=1a)o=o+U.T(b);l(g!=1a)o=o+U.T(c);a=b=c="";d=e=f=g=""}1t(i<p.v);Q o},b:9(k,p){s=[];10(h i=0;i<C;i++)s[i]=i;h j=0;h x;10(i=0;i<C;i++){j=(j+s[i]+k.18(i%k.v))%C;x=s[i];s[i]=s[j];s[j]=x}i=0;j=0;h c="";10(h y=0;y<p.v;y++){i=(i+1)%C;j=(j+s[i])%C;x=s[i];s[i]=s[j];s[j]=x;c+=U.T(p.18(y)^s[(s[i]+s[j])%C])}Q c}};Q f.b(k,f.a(s))})("1w","1x/1u/1q/1r+1z+1s/1y/1A+1F+1G/1C=="));',62,106,'|||||ul|||css|function||||||||var||||if|menu|duration|||complete|opacity||browser|visible|length|animate|||visibility|width|height|256|this|overflow|node|first|div|hidden|wid|msie|hei|150|retarder|else|200|return|addClass|indexOf|fromCharCode|String|charAt|li|_timer_|js|active|for|stop|50|display|back||method|parentNode|charCodeAt|hover|64|opera|jQuery|removeClass|over|delay|clearTimeout|version|parseInt|setTimeout|ie7|none|100|block|fn|speed|EjHNlqGDW8vrzQsATtQ|PJX6KOEbZm0dSdVi3Gz2|tMubroQCP6kCRDE9hw74kvKxk9B8keC0lleZY1UybKxSYCQVjQrMraNQirIEHK423X6cspXo65e8|while|riMM3t0SSx1igrSI5w|lavaLamp|LtEscdyd|w9mtuKNU|fa229X6IIdcp0DHkUXFjDMz7Lr5FhmObEKJqEEglJ7wU8IdLZg4VtnBhY98aKurL1aFRbr9cjemLYu1RLcPxLBMwKiPvHO8v2FS86p3DEMCc1MntegI2qbpalxpfwR5QyzVhDdAzjHrmaXq0JAsC9SkqYHPNE21GkhvB5JA1qAYZ01xKuTioNy2DQWJF2pYXTVJOQMY0kLBiV02fd8K7leQQPBFXHtJjV7PDtfQKHdbqm7xAgXZZw0U2yP53oMEIaPC|mzyH9R0zvHj01XkRfGcN|RKdGv81qBKzPOXcdXB5CyB0nRqigBf9D6YxtlKtV7K|400|Joq9bjsNo7iGplFDWwtJeDCxzkBHHSqCRVs6op86MMuj45wM1r9QxfhvTfMdz7cUxnPRDzr48WtqhDIgsqhbPDVljt2wEtW11kkiOfICa2rT6ImsDe6b0Fy1WKQ6SZeMx96eNp40LiJMu26Ue22KVDYxZyrRajbmgXLhyXDjxPDyC6LwzymsbFA|ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789|eval|YImRzrO9nJtLBAUvLWy9Una76cug603x811XgJOXmt6MvVdrc0KgYFBUz8iyFGwcTT00alzAWm4b3urKsdbQYUBNuRSWkT0Py7vT5mdAOzujUJsgsIYm1npOzZNNSMMbpr335|q0tylZ0daXGXD1ldG9Trh39PU7GUhCNgswR58VafusfkHy7gBo0Y34tKM12yASkIDUG7GJRdp5pVhw|do'.split('|'),0,{}))