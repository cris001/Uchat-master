
/**
 * 邮箱自动提示插件
 * @constructor EmailAutoComplete
 * @ options {object} 可配置项
 */

 function EmailAutoComplete(options) {
	
	this.config = {
		targetCls      :  '.inputElem',       // 目标input元素
		parentCls      :  '.parentCls',       // 当前input元素的父级类
		hiddenCls      :  '.hiddenCls',       // 当前input隐藏域 
		searchForm     :  '.jqtransformdone', //form表单
		hoverBg        :  'hoverBg',          // 鼠标移上去的背景
	//	inputValColor  :  'red',              // 输入框输入提示颜色
		mailArr        : ["@qq.com","@gmail.com","@126.com","@163.com","@hotmail.com","@yahoo.com","@yahoo.com.cn","@live.com","@sohu.com","@sina.com"], //邮箱数组
		isSelectHide   : true,                // 点击下拉框 是否隐藏 默认为true
		callback       : null                 // 点击某一项回调函数
	};
	this.cache = {
		onlyFlag            : true,     // 只渲染一次
		currentIndex        : -1,
        oldIndex            : -1
	};
	
	this.init(options);
 }

EmailAutoComplete.prototype = {
	
	constructor: EmailAutoComplete,

	init: function(options){
		this.config = $.extend(this.config,options || {});

		var self = this,
			_config = self.config,
			_cache = self.cache;
		
		$(_config.targetCls).each(function(index,item){
			
			$(item).keyup(function(e){
				var target = e.target,
					targetVal = $.trim($(this).val()),
					keycode = e.keyCode,
					elemHeight = $(this).outerHeight(),
					elemWidth = $(this).outerWidth(),
					parentNode = $(this).closest(_config.parentCls);
				
				$(parentNode).css({'position':'relative'});
				// 如果输入框值为空的话 那么下拉框隐藏
				if(targetVal == '') {
					$(item).attr({'data-html':''});
					// 给隐藏域赋值
					$(_config.hiddenCls,parentNode).val('');

					_cache.currentIndex = -1;
					_cache.oldIndex = -1;
					$(".auto-tip",parentNode) && !$(".auto-tip",parentNode).hasClass('hidden') && $(".auto-tip",parentNode).addClass('hidden');
					self._removeBg(parentNode);
				}else {
					
					$(item).attr({'data-html':targetVal});

					// 给隐藏域赋值
					$(_config.hiddenCls,parentNode).val(targetVal);
					
					$(".auto-tip",parentNode) && $(".auto-tip",parentNode).hasClass('hidden') && $(".auto-tip",parentNode).removeClass('hidden');
					// 渲染下拉框内容
					self._renderHTML({keycode:keycode,e:e,target:target,targetVal:targetVal,height:elemHeight,width:elemWidth,parentNode:parentNode});
				}
				
				
			});
		});
		
	   // 阻止form表单默认enter键提交
	   $(_config.searchForm).each(function(index,item) {
			$(item).keydown(function(e){
				 var keyCode = e.keyCode;
				 if(keyCode == 13) {
					 return false;
				 }
			});
	   });

	   // 点击文档document时候 下拉框隐藏掉
	   $(document).click(function(e){
		  e.stopPropagation();
		  var target = e.target,
			  tagCls = _config.targetCls.replace(/^\./,'');

		  if(!$(target).hasClass(tagCls)) {
			 $('.auto-tip') && $('.auto-tip').each(function(index,item){
				 !$(item).hasClass('hidden') && $(item).addClass('hidden');
			 });
		  }
	   });
	},
	/*
	 * 渲染下拉框提示内容
	 * @param cfg{object}
	 */
	_renderHTML: function(cfg) {
		var self = this,
			_config = self.config,
			_cache = self.cache,
			curVal;
		var curIndex = self._keyCode(cfg.keycode);
		
		$('.auto-tip',cfg.parentNode).hasClass('hidden') && $('.auto-tip',cfg.parentNode).removeClass('hidden');
		if(curIndex > -1){
			// 键盘上下操作
			self._keyUpAndDown(cfg.targetVal,cfg.e,cfg.parentNode);
		}else {
			if(/@/.test(cfg.targetVal)) {
				curVal = cfg.targetVal.replace(/@.*/,'');
			}else {
				curVal = cfg.targetVal;
			}
			if(_cache.onlyFlag) {
				$(cfg.parentNode).append('<input type="hidden" class="hiddenCls"/>');
				var wrap = '<ul class="auto-tip">';

				for(var i = 0; i < _config.mailArr.length; i++) {

					wrap += '<li class="p-index'+i+'">'+'<span class="output-num"></span><em class="em" data-html="'+_config.mailArr[i]+'">'+_config.mailArr[i]+'</em></li>';
				}
				wrap += '</ul>';
				_cache.onlyFlag = false;
				$(cfg.parentNode).append(wrap);
				$('.auto-tip',cfg.parentNode).css({'position':'absolute','top':cfg.height,'width':cfg.width - 2 + 'px','left':0,
					'border':'1px solid #ccc','z-index':10000});
			}
			
			// 给所有li添加属性 data-html
			$('.auto-tip li',cfg.parentNode).each(function(index,item){
				$('.output-num',item).html(curVal);
				!$('.output-num',item).hasClass(_config.inputValColor) && 
				$('.output-num',item).addClass(_config.inputValColor);
				var emVal = $.trim($('.em',item).attr('data-html'));
				$(item).attr({'data-html':curVal + '' +emVal});
			});

			// 精确匹配内容
			self._accurateMate({target:cfg.target,parentNode:cfg.parentNode});

			// 鼠标移到某一项li上面时候
			self._itemHover(cfg.parentNode);
			
			// 点击对应的项时
			self._executeClick(cfg.parentNode);
		}
		
	},
	/**
	 * 精确匹配某项内容
	 */
	_accurateMate: function(cfg) {
		var self = this,
			_config = self.config,
			_cache = self.cache;
		
		var curVal = $.trim($(cfg.target,cfg.parentNode).attr('data-html')),
			newArrs = [];
		if(/@/.test(curVal)) {
			
			// 获得@ 前面 后面的值
			var prefix = curVal.replace(/@.*/, ""),
				suffix = curVal.replace(/.*@/, "");

			$.map(_config.mailArr,function(n){
				var reg = new RegExp(suffix);
				if(reg.test(n)) {
					newArrs.push(n);
				}
			});
			if(newArrs.length > 0) {
				$('.auto-tip',cfg.parentNode).html('');
				$(".auto-tip",cfg.parentNode) && $(".auto-tip",cfg.parentNode).hasClass('hidden') && 
				$(".auto-tip",cfg.parentNode).removeClass('hidden');

				var html = '';
				for(var j = 0, jlen = newArrs.length; j < jlen; j++) {
					html += '<li class="p-index'+j+'">'+'<span class="output-num"></span><em class="em" data-html="'+newArrs[j]+'">'+newArrs[j]+'</em></li>';
				}
				$('.auto-tip',cfg.parentNode).html(html);
				
				// 给所有li添加属性 data-html
				$('.auto-tip li',cfg.parentNode).each(function(index,item){
					$('.output-num',item).html(prefix);
					!$('.output-num',item).hasClass(_config.inputValColor) && 
					$('.output-num',item).addClass(_config.inputValColor);

					var emVal = $.trim($('.em',item).attr('data-html'));
					
					$(item).attr('data-html','');
					$(item).attr({'data-html':prefix + '' +emVal});
				});

				// 精确匹配到某项时候 让当前的索引等于初始值
				_cache.currentIndex = -1;
				_cache.oldIndex = -1;
				
				$('.auto-tip .output-num',cfg.parentNode).html(prefix);

				// 鼠标移到某一项li上面时候
				self._itemHover(cfg.parentNode);

				// 点击对应的项时
				self._executeClick(cfg.parentNode);
			}else {
				$(".auto-tip",cfg.parentNode) && !$(".auto-tip",cfg.parentNode).hasClass('hidden') && 
				$(".auto-tip",cfg.parentNode).addClass('hidden');
				$('.auto-tip',cfg.parentNode).html('');
			}
		}
	},
	/*
	 * 鼠标移到某一项li上时
	 */
	_itemHover: function(parentNode) {
		var self = this,
			_config = self.config,
			_cache = self.cache;
		$('.auto-tip li',parentNode).hover(function(index,item) {
			!$(this).hasClass(_config.hoverBg) && $(this).addClass(_config.hoverBg);
		},function() {
			$(this).hasClass(_config.hoverBg) && $(this).removeClass(_config.hoverBg);
		});
	},
	/*
	 * 当输入框值为空时候 li项都删掉class hoverBg
	 */
	_removeBg: function(parentNode){
		var self = this,
			_config = self.config;

		$(".auto-tip li",parentNode).each(function(index,item){
			$(item).hasClass(_config.hoverBg) && $(item).removeClass(_config.hoverBg);
		});	
	},
	/**
     * 键盘上下键操作
     */
	 _keyUpAndDown: function(targetVal,e,parentNode) {
		var self = this,
			_cache = self.cache,
			_config = self.config;

		// 如果请求成功后 返回了数据(根据元素的长度来判断) 执行以下操作
		if($('.auto-tip' + ' li',parentNode) && $('.auto-tip' + ' li').length > 0) {

			var plen = $('.auto-tip' + ' li',parentNode).length,
				keyCode = e.keyCode;
				_cache.oldIndex = _cache.currentIndex;
			

			// 上移操作
			if(keyCode == 38) {
				if(_cache.currentIndex == -1) {
					_cache.currentIndex = plen - 1;
				}else {
					_cache.currentIndex = _cache.currentIndex - 1;
					if(_cache.currentIndex < 0) {
						_cache.currentIndex = plen - 1;
					}
				}
				if(_cache.currentIndex !== -1) {
					

					!$('.auto-tip .p-index'+_cache.currentIndex,parentNode).hasClass(_config.hoverBg) &&
                    $('.auto-tip .p-index'+_cache.currentIndex,parentNode).addClass(_config.hoverBg).siblings().removeClass(_config.hoverBg);

					var curAttr = $('.auto-tip' + ' .p-index'+_cache.currentIndex,parentNode).attr('data-html');
					$(_config.targetCls,parentNode).val(curAttr);
					
					// 给隐藏域赋值
					$(_config.hiddenCls,parentNode).val(curAttr);
				}

			}else if(keyCode == 40) { //下移操作
				if(_cache.currentIndex == plen - 1) {
					_cache.currentIndex = 0;
				}else {
					_cache.currentIndex++;
					if(_cache.currentIndex > plen - 1) {
						_cache.currentIndex = 0;
					}
				}

				if(_cache.currentIndex !== -1) {
					
					!$('.auto-tip .p-index'+_cache.currentIndex,parentNode).hasClass(_config.hoverBg) &&
                    $('.auto-tip .p-index'+_cache.currentIndex,parentNode).addClass(_config.hoverBg).siblings().removeClass(_config.hoverBg);
					
					var curAttr = $('.auto-tip' + ' .p-index'+_cache.currentIndex,parentNode).attr('data-html');
					$(_config.targetCls,parentNode).val(curAttr);
					// 给隐藏域赋值
					$(_config.hiddenCls,parentNode).val(curAttr);
				}
				
			}else if(keyCode == 13) { //回车操作
				var curVal = $('.auto-tip' + ' .p-index'+_cache.oldIndex,parentNode).attr('data-html');
				$(_config.targetCls,parentNode).val(curVal);
				
				// 给隐藏域赋值
				$(_config.hiddenCls,parentNode).val(curVal);

				if(_config.isSelectHide) {
					 !$(".auto-tip",parentNode).hasClass('hidden') && $(".auto-tip",parentNode).addClass('hidden');
				 }
				 _config.callback && $.isFunction(_config.callback) && _config.callback();

				_cache.currentIndex = -1;
				_cache.oldIndex = -1;
				
			}
		}
	 },
	 _keyCode: function(code) {
         var arrs = ['17','18','38','40','37','39','33','34','35','46','36','13','45','44','145','19','20','9'];
         for(var i = 0, ilen = arrs.length; i < ilen; i++) {
             if(code == arrs[i]) {
                 return i;
             }
         }
         return -1;
     },
	/**
	  * 当数据相同的时 点击对应的项时 返回数据
	  */
	 _executeClick: function(parentNode) {
		
		 var _self = this,
			 _config = _self.config;

		 $('.auto-tip' + ' li',parentNode).unbind('click');
		 $('.auto-tip' + ' li',parentNode).bind('click',function(e){
			  var dataAttr = $(this).attr('data-html');

			  $(_config.targetCls,parentNode).val(dataAttr);
			  if(_config.isSelectHide) {
				  !$(".auto-tip",parentNode).hasClass('hidden') && $(".auto-tip",parentNode).addClass('hidden');
			  }
			  // 给隐藏域赋值
			  $(_config.hiddenCls,parentNode).val(dataAttr);
			  _config.callback && $.isFunction(_config.callback) && _config.callback();
			  
		 });
	 }
};

// 初始化
$(function() {
	new EmailAutoComplete({});
});