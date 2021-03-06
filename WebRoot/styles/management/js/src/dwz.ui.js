function initEnv() {
	$("body").append(DWZ.frag["dwzFrag"]);

	if ($.browser.msie && /6.0/.test(navigator.userAgent)) {
		try {
			document.execCommand("BackgroundImageCache", false, true);
		} catch (e) {
		}
	}
	// 清理浏览器内存,只对IE起效
	if ($.browser.msie) {
		window.setInterval("CollectGarbage();", 10000);
	}

	$(window).resize(
			function() {
				initLayout();
				$(this).trigger("resizeGrid");
				$('#baiduMap:visible').height(
						$("#container .tabsPageContent").height()).width(
						$("#container .tabsPageContent").width());

			});

	var ajaxbg = $("#background,#progressBar");
	ajaxbg.hide();
	$(document).ajaxStart(function() {
		ajaxbg.show();
	}).ajaxStop(function() {
		ajaxbg.hide();
	});

	$("#leftside").jBar( {
		minW : 150,
		maxW : 700
	});

	if ($.taskBar)
		$.taskBar.init();
	navTab.init();
	if ($.fn.switchEnv)
		$("#switchEnvBox").switchEnv();
	if ($.fn.navMenu)
		$("#navMenu").navMenu();

	setTimeout(function() {
		initLayout();
		initUI();
		// navTab styles
			var jTabsPH = $("div.tabsPageHeader");
			jTabsPH.find(".tabsLeft").hoverClass("tabsLeftHover");
			jTabsPH.find(".tabsRight").hoverClass("tabsRightHover");
			jTabsPH.find(".tabsMore").hoverClass("tabsMoreHover");

			lastLayout();
		}, 10);

}
/**
 * 只在index.jsp初始化的时候加载这里的函数。还有在window.resize()的时候也会调用.
 */
function initLayout() { 
	var iContentW = $(window).width()
			- (DWZ.ui.sbar ? $("#sidebar").width() + 10 : 34) - 5;
	var iContentH = $(window).height() - $("#header").height() - 34;
	$("#container").width(iContentW); 
	$("#container .tabsPageContent").height(iContentH - 34).find("[layoutH]")
			.layoutH();
	$("#sidebar, #sidebar_s .collapse, #splitBar, #splitBarProxy").height(
			iContentH - 5);
	$("#taskbar").css( {
		top : iContentH + $("#header").height() + 5,
		width : $(window).width()
	});

	// 下面对表格树进行控制高度自适应.
	var gridTreeHeight = $("#container .tabsPageContent").height()
			- $('#container div.pageHeader').height() - 56;
	$('#newtableTree').resetHeight(gridTreeHeight);
	initMyUI(); 
}
/**
 * 在全部结束完成之后再执行一次layout布局修改.
 * 
 * @returns
 */
function lastLayout() {

}
/**
 * 可以在自定义的jsp里面再查询修正某些样式...
 */
function initMyUI() {
}


function initHightCharts($char,arr){ 
	var _credits = {
								href : 'http://www.thinksafari.com',
								text : '思程工作室'
							};
	
	if($char.attr('type')=='pie'){ 
		showPie(arr,$char.attr('title'),$char.attr('id'),$char.attr('serialName'),$char.attr('format')); 
	}
	//正负图
	else if($char.attr('type')=='columnrange') {
		if(arr.length<1)
			return false;
		var categories = [];
		var dataes = [];
		var _len = arr.length;
		for(var _i=0;_i<_len;_i++){
			categories.push(arr[_i][0]);
			var _ar = [];
			_ar.push(arr[_i][1]);
			_ar.push(arr[_i][2]);
			dataes.push(_ar);
		} 
		$char.highcharts({ 
		    chart: {
		        type: 'columnrange',
		        inverted:$char.attr('inverted')=='true'?true:false //是否横向显示，默认不是
		    },
		    
		    title: {
		        text: $char.attr('title') 
		    },
		    
			subtitle: {
		        text: $char.attr('subtitle')  
		    },
		
		    xAxis: {
		        categories:categories
		    },   
		    plotOptions: {
		        columnrange: {
		        	dataLabels: {
		        		enabled: true,
		        		formatter: function () {
		        			return this.y + '次';
		        		}
		        	}
		        }
		    }, 
		
		    series: [{
		        name: $char.attr('series')  ,
		        data: dataes
		    }]
		
		});
	}
	else if($char.attr('type')=='column'){  
		//alert("列的行数："+arr.length);
		if(arr.length<1)
			return false;
		//如果是普通的二维列表
		if(arr[0].length==2){    
			show2Column_two(arr,$char.attr('title'),$char.attr('id'),$char.attr('serialName'));
		}else if(arr[0].length==3){  
			show3Column(arr,$char.attr('title'),$char.attr('id'));
  		}
	}
}
function initUI(_box) {
	var $p = $(_box || document);

	// tables
	$("table.table", $p).jTable();

	// css tables
	$('table.list', $p).cssTable();

	// auto bind tabs
	$("div.tabs", $p).each(function() {
		var $this = $(this);
		var options = {};
		options.currentIndex = $this.attr("currentIndex") || 0;
		options.eventType = $this.attr("eventType") || "click";
		$this.tabs(options);
	});

	$("li[roleId]").each(function(){
		if(!window.roles)
			window.roles= $('#roleStr').val(); 
		var $this = $(this);
		if(window.roles.indexOf($this.attr('roleId'))==-1)
			$this.remove();
	});
	
	$("ul.tree", $p).jTree();
	$('div.accordion', $p).each(function() {
		var $this = $(this);
		$this.accordion( {
			fillSpace : $this.attr("fillSpace"),
			alwaysOpen : true,
			active : 0
		});
	});

	$(":button.checkboxCtrl, :checkbox.checkboxCtrl", $p).checkboxCtrl($p);

	if ($.fn.combox)
		$("select.combox", $p).combox();

	if ($.fn.xheditor) {
		$("textarea.editor", $p).each(
				function() {
					var $this = $(this);
					var op = {
						html5Upload : false,
						skin : 'vista',
						tools : $this.attr("tools") || 'full'
					};
					var upAttrs = [
							[ "upLinkUrl", "upLinkExt", "zip,rar,txt" ],
							[ "upImgUrl", "upImgExt", "jpg,jpeg,gif,png" ],
							[ "upFlashUrl", "upFlashExt", "swf" ],
							[ "upMediaUrl", "upMediaExt", "avi" ] ];

					$(upAttrs).each(function(i) {
						var urlAttr = upAttrs[i][0];
						var extAttr = upAttrs[i][1];

						if ($this.attr(urlAttr)) {
							op[urlAttr] = $this.attr(urlAttr);
							op[extAttr] = $this.attr(extAttr) || upAttrs[i][2];
						}
					});

					$this.xheditor(op);
				});
	}

	if ($.fn.uploadify) {
		// alert('$(":file[uploader]", $p)=='+$(":file[uploader]", $p).size());
		$(":file[uploader]", $p).each(
				function() {
					var $this = $(this);
					var options = {
						uploader : $this.attr("uploader"),
						script : $this.attr("script"),
						cancelImg : $this.attr("cancelImg"),
						queueID : $this.attr("fileQueue") || "fileQueue",
						fileDesc : $this.attr("fileDesc")
								|| "*.jpg;*.jpeg;*.gif;*.png;*.pdf",
						fileExt : $this.attr("fileExt")
								|| "*.jpg;*.jpeg;*.gif;*.png;*.pdf",
						folder : $this.attr("folder"),
						auto : true,
						multi : true,
						onError : uploadifyError,
						onComplete : uploadifyComplete,
						onAllComplete : uploadifyAllComplete
					};
					if ($this.attr("onComplete")) {
						options.onComplete = DWZ.jsonEval($this
								.attr("onComplete"));
					}
					if ($this.attr("onAllComplete")) {
						options.onAllComplete = DWZ.jsonEval($this
								.attr("onAllComplete"));
					}
					if ($this.attr("scriptData")) {
						options.scriptData = DWZ.jsonEval($this
								.attr("scriptData"));
					}
					$this.uploadify(options);
				});
	}

	// init styles
	$("input[type=text], input[type=password], textarea", $p).addClass(
			"textInput").focusClass("focus");

	$("input[readonly], textarea[readonly]", $p).addClass("readonly");
	$("input[disabled=true], textarea[disabled=true]", $p).addClass("disabled");

	$("input[type=text]", $p).not("div.tabs input[type=text]", $p).filter(
			"[alt]").inputAlert();

	// Grid ToolBar
	$("div.panelBar li, div.panelBar", $p).hoverClass("hover");

	// Button
	$("div.button", $p).hoverClass("buttonHover");
	$("div.buttonActive", $p).hoverClass("buttonActiveHover");

	// tabsPageHeader
	$(
			"div.tabsHeader li, div.tabsPageHeader li, div.accordionHeader, div.accordion",
			$p).hoverClass("hover");

	$("div.panel", $p).jPanel();

	// validate form
	$("form.required-validate", $p).each(function() {
		$(this).validate( {
			focusInvalid : false,
			focusCleanup : true,
			errorElement : "span",
			ignore : ".ignore",
			invalidHandler : function(form, validator) {
				var errors = validator.numberOfInvalids();
				if (errors) {
					var message = DWZ.msg("validateFormError", [ errors ]);
					alertMsg.error(message);
				}
			}
		});
	});

	if ($.fn.datepicker) {
		$('input.date', $p).each(function() {
			var $this = $(this);
			var opts = {};
			if ($this.attr("format"))
				opts.pattern = $this.attr("format");
			if ($this.attr("yearstart"))
				opts.yearstart = $this.attr("yearstart");
			if ($this.attr("yearend"))
				opts.yearend = $this.attr("yearend");
			$this.datepicker(opts);
		});
	}

	// navTab
	$("a[target=navTab]", $p).each(
			function() {
				$(this).click(
						function(event) {
							var $this = $(this);
							var title = $this.attr("title") || $this.text();
							var tabid = $this.attr("rel") || "_blank";
							var fresh = eval($this.attr("fresh") || "true");
							var external = eval($this.attr("external")
									|| "false");
							var url = unescape($this.attr("href"))
									.replaceTmById(
											$(event.target).parents(
													".unitBox:first"));
							DWZ.debug(url);
							if (!url.isFinishedTm()) {
								alertMsg.error($this.attr("warn")
										|| DWZ.msg("alertSelectMsg"));
								return false;
							}
							navTab.openTab(tabid, url, {
								title : title,
								fresh : fresh,
								external : external
							});

							event.preventDefault();
						});
			});

	$("a[target=myDialog]", $p).each(
			function() {
				$(this)
						.click(
								function(event) {
									var $this = $(this);
									var title = $this.attr("title")
											|| $this.text();
									var w = $this.attr("width")||400;
									var h = $this.attr("height")||300;
									var url = unescape($this.attr("href"));
									window.open (url, 'newwindow', 'height='+w+', width='+h+', top=0, left=0, toolbar=no,'
											+' menubar=no, scrollbars=no, resizable=no,location=no, status=no') ;
									return false;
								})}
	);
	// dialogs
	$("a[target=dialog]", $p).each(
			function() {
				$(this)
						.click(
								function(event) {
									var $this = $(this);
									var title = $this.attr("title")
											|| $this.text();
									var rel = $this.attr("rel") || "_blank";
									var options = {};
									var w = $this.attr("width");
									var h = $this.attr("height");
									if (w)
										options.width = w;
									if (h)
										options.height = h;
									options.max = eval($this.attr("max")
											|| "false");
									options.mask = eval($this.attr("mask")
											|| "false");
									options.maxable = eval($this
											.attr("maxable")
											|| "true");
									options.minable = eval($this
											.attr("minable")
											|| "true");
									options.fresh = eval($this.attr("fresh")
											|| "true");
									options.resizable = eval($this
											.attr("resizable")
											|| "true");
									options.drawable = eval($this
											.attr("drawable")
											|| "true");
									options.close = eval($this.attr("close")
											|| "");
									options.param = $this.attr("param") || "";

									var url = unescape($this.attr("href"))
											.replaceTmById(
													$(event.target).parents(
															".unitBox:first"));
									DWZ.debug(url);
									if (!url.isFinishedTm()) {
										alertMsg.error($this.attr("warn")
												|| DWZ.msg("alertSelectMsg"));
										return false;
									}
									$.pdialog.open(url, rel, title, options);

									return false;
								});
			});
	$("a[target=ajax]", $p).each(function() {
		$(this).click(function(event) {
			var $this = $(this);
			var rel = $this.attr("rel");
			if (rel) {
				var $rel = $("#" + rel);
				$rel.loadUrl($this.attr("href"), {}, function() {
					$rel.find("[layoutH]").layoutH();
				});
			}

			event.preventDefault();
		});
	});

	$("div.pagination", $p).each(function() {
		var $this = $(this);
		$this.pagination( {
			targetType : $this.attr("targetType"),
			rel : $this.attr("rel"),
			totalCount : $this.attr("totalCount"),
			numPerPage : $this.attr("numPerPage"),
			pageNumShown : $this.attr("pageNumShown"),
			currentPage : $this.attr("currentPage")
		});
	});

	if ($.fn.uploadify) {
		$(":file[uploaderOption]", $p).each(function() {
			var $this = $(this);
			var options = {
				fileObjName : $this.attr("name") || "file",
				auto : true,
				multi : true,
				onUploadError : uploadifyError
			};

			var uploaderOption = DWZ.jsonEval($this.attr("uploaderOption"));
			$.extend(options, uploaderOption);

			// DWZ.debug("uploaderOption: "+DWZ.obj2str(uploaderOption));

				$this.uploadify(options);
			});
	}

	// dwz.ajax.js
	if ($.fn.ajaxTodo)
		$("a[target=ajaxTodo]", $p).ajaxTodo($p);
	if ($.fn.dwzExport)
		$("a[target=dwzExport]", $p).dwzExport($p);

	if ($.fn.lookup)
		$("a[lookupGroup]", $p).lookup();
	if ($.fn.multLookup)
		$("[multLookup]:button").multLookup();
	if ($.fn.suggest)
		$("input[suggestFields]", $p).suggest();
	if ($.fn.itemDetail)
		$("table.itemDetail", $p).itemDetail();
	if ($.fn.selectedTodo)
		$("a[target=selectedTodo]", $p).selectedTodo();
	if ($.fn.pagerForm)
		$("form[rel=pagerForm]", $p).pagerForm( {
			parentBox : $p
		});
	if ($.fn.pagerForm)
		$("form[rel=pagerForm]", $p).pagerForm( {
			parentBox : $p
		});

if ($('.highcharts', $p).size() > 0) { 
		$(".highcharts", $p).each(function() {
			var $char = $(this);
			$.ajax({
				type : 'post',
				url : $char.attr('url'),
				success : function(msg) { 
					eval("var arr =" + msg); 
					initHightCharts($char,arr);
				}//end of ajax function.

			});
		});
	}

	// 加载zTree!!
	if ($('.ztree', $p).size() > 0) {
		$(".ztree", $p).each(
				function() {
					var $tree = $(this);
					if (!$tree.attr('url'))
						alert('树形加载失败，缺少url！id：' + $tree.attr('id'));
					if ($tree.attr('lazy') != 'true') {
						// 不是懒加载树
						$.ajax({
							type : 'post',
							url : $tree.attr('url'),
							success : function(msg) {
								eval("var zNodes =" + msg);
								var setting = {
									data : {
										simpleData : {
											enable : true
										}
									},
									callback : {
										onClick : onClick
									}
								};

								if ($tree.attr('checkable') == 'true') {
									setting = {
										check : {
											enable : true
										},
										data : {
											simpleData : {
												enable : true
											}
										},
										callback : {
											onClick : onClick
										}
									};
								}
								$.fn.zTree.init($tree, setting, zNodes);
								if ($tree.attr('height') != null)
									$tree.height($tree.attr('height'));
								if ($tree.attr('noScroll') != 'true')
									$tree.css('backgroundColor', 'white').css(
											'overflowX', 'hidden').css(
											'overflowY', 'scroll');
								else {
									$tree.css('backgroundColor', 'white').css(
											'overflow', 'hidden');
								}
							}
						});
					} else {
						eval("var autoP = " + $tree.attr('autoParam'));
						var setting = {
							async : {
								enable : true,
								url : $tree.attr('url'),
								autoParam : autoP,
								dataFilter : filter
							},
							callback : {
								onClick : onClick
							}
						};
						$.fn.zTree.init($tree, setting);
					}
				});
	}

	if ($('#baiduMap:visible').size() > 0) {
		$('#baiduMap:visible')
				.height($("#container .tabsPageContent").height()).width(
						$("#container .tabsPageContent").width());
	}
	// 这里放其他第三方jQuery插件...
	// 下面对表格树进行处理.
	if ($('#newtableTree', $p).size() > 0) {
		// 计算得到表格树的高度..不采用设置的高度!!
		var gridTreeHeight = $("#container .tabsPageContent").height()
				- $('#container div.pageHeader').height() - 56;
		var GridColumnType = [ {
			header : '标示列',
			headerIndex : 'disid',
			width : '100'
		}, {
			header : '名称',
			headerIndex : 'disname',
			width : '400'
		}, {
			header : '上级标示',
			headerIndex : 'disparentId',
			width : '100'
		} ];
		var content = {
			columnModel : GridColumnType,
			dataUrl : appPath + "/gridtree/gridTree!initGridTree.do",
			lazyLoadUrl : appPath + "/gridtree/gridTree!lazyLoad.do",
			idColumn : 'disid',// id所在的列,一般是主键(不一定要显示出来)
			parentColumn : 'disparentId', // 父亲列id
			pageSize : 1,
			height : gridTreeHeight + 'px',
			pageBar : true,
			toolBar : [ {
				name : "查询",
				cls : "delete",
				clk : function() {
					var str = $("#gtForm").serialize();
					$('#newtableTree', $p).search({
						url : appPath + "/gridtree/gridTree!initGridTree.do",
						param : str
					});
					return false;
				}
			} ],
			onSuccess : function() {
				// $('div.gridThead').find('th.center').css('border','1px'));
			},
			rowCount : true,
			tableId : 'testTable',// 表格树的id
			checkOption : 'multi',
			exchangeColor : false,
			debug : true,
			onError : function(msg) {
				alertMsg.error(msg);
			},
			beforeAjax : function() {
			},
			afterAjax : function() {
			},
			lazy : true,// 使用懒加载模式
			leafColumn : 'isLeaf'
		};
		$('#newtableTree', $p).gridTree(content);
		// window.parent.onresizeend = gridRessetSize();
	}

	// 个性化的样式修改.
	initMyUI();
}
