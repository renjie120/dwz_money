/**
 * @author Roger Wu
 */

(function($){
	var allSelectBox = [];
	var killAllBox = function(bid){
		$.each(allSelectBox, function(i){
			if (allSelectBox[i] != bid) {
				if (!$("#" + allSelectBox[i])[0]) {
					$("#op_" + allSelectBox[i]).remove();
					//allSelectBox.splice(i,1);
				} else {
					$("#op_" + allSelectBox[i]).css({ height: "", width: "" }).hide();
				}
				$(document).unbind("click", killAllBox);
			}
		});
	};
	
	$.extend($.fn, {
		comboxSelect: function(options){
			var op = $.extend({ selector: ">a" }, options);
			
			return this.each(function(){
				var box = $(this);
				var selector = $(op.selector, box);

				allSelectBox.push(box.attr("id"));
				$(op.selector, box).click(function(){ 
					var options = $("#op_"+box.attr("id"));
					if (options.is(":hidden")) {
						if(options.height() > 300) {
							options.css({height:"300px",overflow:"scroll"});
						}
						var top = box.offset().top+box[0].offsetHeight;
						if(top + options.height() > $(window).height() - 20) {
							top =  $(window).height() - 20 - options.height();
						}
						options.css({top:top,left:box.offset().left}).show();
						killAllBox(box.attr("id"));
						$(document).click(killAllBox);
					} else {
						$(document).unbind("click", killAllBox);
						killAllBoxkillAllBox();
					}
					return false;
				});
				$("#op_"+box.attr("id")).find(">li").comboxOption(selector, box);		
			});
		},
		comboxOption: function(selector, box){
			return this.each(function(){
				$(">a", this).click(function(){
					 var $this = $(this);
					$this.parent().parent().find(".selected").removeClass("selected");
					$this.addClass("selected");
					selector.text($this.text()); 
					var $input = $("select", box);
					if ($input.val() != $this.attr("value")) {
						$("select", box).val($this.attr("value")).trigger("refChange").trigger("change");
					} 
				});
			});
		},
		combox:function(){
			/* 清理下拉层 */
			var _selectBox = [];
			$.each(allSelectBox, function(i){ 
				if ($("#" + allSelectBox[i])[0]) {
					_selectBox.push(allSelectBox[i]);
				} else {
					$("#op_" + allSelectBox[i]).remove();
				}
			});
			allSelectBox = _selectBox;
			
			return this.each(function(i){
				var $this = $(this).removeClass("combox");
				var name = $this.attr("name");
				var value= $this.attr("value");
				var label = $("option[value=" + value + "]",$this).text();
				var ref = $this.attr("ref");
				var refUrl = $this.attr("refUrl") || ""; 
				//添加一个回调函数参数 .
				var refFunction = $this.attr("relFun") || ""; 
				//不请求的value
				var noRequest= $this.attr("noRequest") || ""; 
				var cid = $this.attr("id") || Math.round(Math.random()*10000000);
				var select = '<div class="combox"><div id="combox_'+ cid +'" class="select"' + (ref?' ref="' + ref + '"' : '') + '>';
				select += '<a href="javascript:" class="'+$this.attr("class")+'" name="' + name +'" value="' + value + '">' + label +'</a></div></div>';
				var options = '<ul class="comboxop" id="op_combox_'+ cid +'">';
				$("option", $this).each(function(){
					var option = $(this);
					options +="<li><a class=\""+ (value==option[0].value?"selected":"") +"\" href=\"javascript:return false;\" value=\"" + option[0].value + "\">" + option[0].text + "</a></li>";
				});
				options +="</ul>"; 
				$("body").append(options);
				$this.after(select);
				$("div.select", $this.next()).comboxSelect().append($this);
				
				if (ref && refUrl) {
					
					$this.unbind("refChange").bind("refChange", function(event){
						var $ref = $("#"+ref);
						if ($ref.size() == 0) return false;
						$.ajax({
							type:'GET', dataType:"json", url:refUrl.replace("{value}", $this.attr("value")), cache: false,
							data:{},
							success: function(json){
								if (!json) return;
								var html = '';
	
								$.each(json, function(i){
									if (json[i] && json[i].length > 1){
										html += '<option value="'+json[i][0]+'">' + json[i][1] + '</option>';
									}
								});
								
								var $refCombox = $ref.parents("div.combox:first");
								$ref.html(html).insertAfter($refCombox);
								$refCombox.remove();
								$ref.trigger("refChange").trigger("change").combox();
							},
							error: DWZ.ajaxError
						});
					});
				} 
				//如果只填写了url没有填写rel,但是填写了relFun回调函数，就只进行ajax请求.
				else if (  refUrl&&refFunction) {  
					$this.unbind("refChange").bind("refChange", function(event){
						//如果设置了不进行处理的值，就直接跳过.
						if(noRequest){
							if($this.attr("value")==noRequest)
								return false;
						} 
						$.ajax({
							type:'GET', dataType:"json", url:refUrl.replace("{value}", $this.attr("value")), cache: false,
							data:{},
							success: function(json){ 
								if (!json) return; 
								eval(refFunction+"("+json+")");  
							},
							error: function(json){  
								if(json.status==200){ 
									eval("var arr =" + json.responseText);    
									eval(refFunction+"(\""+json.responseText+"\")");  
								}else{
									alert("出现错误.");
								} 
							}
						});
					});
				}
				
			});
		}
	});
})(jQuery);
