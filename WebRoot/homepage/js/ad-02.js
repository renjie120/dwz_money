<!--  Written by Myhyli, 2003/07/21. myhyli.blueidea.com  -->
<!--  Member Of Blueidea Web Team. -->
<!--  Welcome to www.blueidea.com. -->

	var delta=0.8;
	var collection;
	var closeB=false;
	function floaters() { 
		this.items	= [];
		this.addItem	= function(id,x,y,content)
				  { 
					document.write('<DIV id='+id+' style="Z-INDEX: 10; POSITION: absolute;  width:80px; height:60px;left:'+(typeof(x)=='string'?eval(x):x)+';top:'+(typeof(y)=='string'?eval(y):y)+'">'+content+'</DIV>');
					 var newItem				= {};
					newItem.object			= document.getElementById(id);
					if(typeof(x)=='string'){
						eval("newItem.x="+x);
					}
					else
						newItem.x				= x;
					newItem.y				= y;

					this.items[this.items.length]		= newItem;
				  }
		this.play	= function()
				  {
					collection				= this.items; 
					/* play();
					if(!window.aaaaaa){
						window.onscroll=play;
						window.aaaaaa =1;
					} */
 	                setInterval('play()',50);
				  }
		} 
		function play()
		{ 
			if(screen.width<=800 || closeB)
			{
				for(var i=0;i<collection.length;i++)
				{
					collection[i].object.style.display	= 'none';
				}
				return;
			} 
			for(var i=0;i<collection.length;i++)
			{
			 	var followObj		= collection[i].object;
				var followObj_x		= (typeof(collection[i].x)=='string'?eval(collection[i].x):collection[i].x);
				var followObj_y		= (typeof(collection[i].y)=='string'?eval(collection[i].y):collection[i].y); 
				var $this = $(followObj);
				var offset = $this.offset(); 
				 if(offset.left!=(document.documentElement.scrollLeft+followObj_x)) {
					var dx=(document.documentElement.scrollLeft+followObj_x-offset.left)*delta;
					dx=(dx>0?1:-1)*Math.ceil(Math.abs(dx));
					 $this.css('left',offset.left+dx); 
					}
				 
				 if(offset.top!=(document.documentElement.scrollTop+followObj_y)) {
					var dy=(document.documentElement.scrollTop+followObj_y-offset.top)*delta;
					 dy=(dy>0?1:-1)*Math.ceil(Math.abs(dy)); 
					 $this.css('top',offset.top+dy); 
					} 
				followObj.style.display	= '';
			}
		}	
		function closeBanner()
		{
			closeB=true;
			return;
		}

	var theFloaters		= new floaters();
	// 
	//theFloaters.addItem('followDiv1','$("body").width()-106',120,'<a href="http://www.thinksafari.com/" target="_blank"><img src="img/ad-01.gif" ></a><br><br><img src="img/close.gif" onClick="closeBanner();">');
	//theFloaters.addItem('followDiv2',6,120,'<a href="http://www.thinksafari.com/" target="_blank"><img src="img/ad-01.gif"></a><br><br><img src="img/close.gif" onClick="closeBanner();">');
	// theFloaters.play();
