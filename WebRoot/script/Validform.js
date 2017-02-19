/*
	ͨ�ñ���֤����
	Validform version 2.0
	For more information, you can visit http://www.rjboy.cn
	By sean at April 7, 2010 - April 22, 2011
	
	Demo:
	$(".demoform").Validform({//$(".demoform")ָ������һ����Ҫ��֤,���������from����;
		btnSubmit:"#btn_sub", //#btn_sub�Ǹñ���Ҫ�󶨵���ύ���¼��İ�ť;���form�ں���submit��ť�ò�����ʡ��;
		tiptype:1, //��ѡ�� 1=>pop box,2=>side tip��Ĭ��Ϊ1;
		postonce:true, //��ѡ�� �Ƿ���������ʱ�Ķ����ύ������true������������Ĭ�Ϲر�;
		ajaxurl:"ajax_post.php", //ajax�ύ������;
		callback:function(data){
			//��������data��json��ʽ��{"info":"demo info","status":"y"}
			//info: �����ʾ��Ϣ;
			//status: �����ύ���ݵ�״̬,�Ƿ��ύ�ɹ����������"y"��ʾ�ύ�ɹ���"n"��ʾ�ύʧ�ܣ���ajax_post.php�ļ������������Զ��ַ�����Ҫ����callback��������ݸ�ִֵ����Ӧ�Ļص�����;
			//��Ҳ������ajax_post.php�ļ����ظ�����Ϣ�������ȡ��������Ӧ������
			
			//����ִ�лص�����;
		}
	});
*/

(function($){
	var errorobj=null,//ָʾ��ǰ��֤ʧ�ܵı�Ԫ��;
		msgobj,//pop box object 
		msghidden=true, //msgbox hidden?
		tipmsg={//Ĭ����ʾ����;
			w:"��������ȷ��Ϣ��",
			r:"ͨ����Ϣ��֤��",
			c:"���ڼ����Ϣ��",
			s:"��������Ϣ��",
			v:"������Ϣû�о�����֤�����Ժ�",
			p:"�����ύ���ݡ�"
		},
		creatMsgbox=function(){
			if($("#Validform_msg").length!=0){return false;}
			msgobj=$('<div id="Validform_msg"><div class="Validform_title">��ʾ��Ϣ<a class="Validform_close" href="javascript:void(0);">&chi;</a></div><div class="Validform_info"></div><div class="iframe"><iframe frameborder="0" scrolling="no" height="100%" width="100%"></iframe></div></div>').appendTo("body");//��ʾ��Ϣ��;
			msgobj.find("a.Validform_close").click(function(){
				msgobj.hide();
				msghidden=true;
				if(errorobj){
					errorobj.focus().addClass("Validform_error");
				}
				return false;
			}).focus(function(){this.blur();});

			$(window).bind("scroll resize",function(){
				if(!msghidden){				  
					var left=($(window).width()-msgobj.width())/2;
					var top=($(window).height()-msgobj.height())/2;
					var topTo=(document.documentElement.scrollTop?document.documentElement.scrollTop:document.body.scrollTop)+(top>0?top:0);
					msgobj.animate({
						left : left,
						top : topTo
					},{ duration:400 , queue:false });
				}
			})
		};
	
	$.fn.Validform=function(settings){
		var defaults={};
		settings=$.extend({},$.fn.Validform.sn.defaults,settings);
		
		this.each(function(){
			var $this=$(this);
			var posting=false; //��ֹ����ť˫���ύ����;
			$this.find("[tip]").each(function(){//tip�Ǳ�Ԫ�ص�Ĭ����ʾ��Ϣ,���ǵ�����Ч��;
				var defaultvalue=$(this).attr("tip");
				var altercss=$(this).attr("altercss");
				$(this).focus(function(){
					if($(this).val()==defaultvalue){
						$(this).val('');
						if(altercss){$(this).removeClass(altercss);}
					}
				}).blur(function(){
					if($.trim($(this).val())==''){
						$(this).val(defaultvalue);
						if(altercss){$(this).addClass(altercss);}
					}
				});
			});
			
			//��blur�¼�;
			$this.find("[datatype]").blur(function(){
				var flag=true;
				flag=$.fn.Validform.sn.checkform($(this),$this,settings.tiptype,"hide");

				if(!flag){return false;}
				if(typeof(flag)!="boolean"){//�����radio, checkbox, select������ִ������Ĵ���;
					$(this).removeClass("Validform_error");
					return false;
				}
										
				flag=$.fn.Validform.sn.regcheck($(this).attr("datatype"),$(this).val());
				if(!flag){
					if($(this).attr("ignore")=="ignore" && ( $(this).val()=="" || $(this).val()==$(this).attr("tip") )){
						if(settings.tiptype==2){
							$(this).parent().next().find(".Validform_checktip").removeClass().addClass("Validform_checktip").text($(this).attr("tip"));
						}
						flag=true;
						return true;
					}
					errorobj=$(this);
					$.fn.Validform.sn.showmsg($(this).attr("errormsg")||tipmsg.w,settings.tiptype,{obj:$(this)},"hide"); //��tiptype=1������£�����"hide"������ʾ�򲻵���,tiptype=2������¸��Ӳ�����hide����������;
				}else{
					if($(this).attr("ajaxurl")){
						var inputobj=$(this);
						inputobj.attr("valid",tipmsg.c);
						$.fn.Validform.sn.showmsg(tipmsg.c,settings.tiptype,{obj:inputobj,type:1},"hide");
						$.ajax({
							type: "POST",
							url: inputobj.attr("ajaxurl"),
							data: "param="+$(this).val(),
							dataType: "text",
							success: function(s){
								if(s=="y"){
									inputobj.attr("valid","true");
									$.fn.Validform.sn.showmsg(tipmsg.r,settings.tiptype,{obj:inputobj,type:2},"hide");
								}else{
									inputobj.attr("valid",s);
									errorobj=inputobj;
									$.fn.Validform.sn.showmsg(s,settings.tiptype,{obj:inputobj});
								}
							}
						});
					}else{
						errorobj=null;
						$.fn.Validform.sn.showmsg(tipmsg.r,settings.tiptype,{obj:$(this),type:2},"hide");
					}
				};
				
			});
			
			//subform
			var subform=function(){
				var flag=true;
				if(posting){return false;}
				
				$this.find("[datatype]").each(function(){
					flag=$.fn.Validform.sn.checkform($(this),$this,settings.tiptype);

					if(!flag){
						errorobj.focus();
						return false;
					}
					
					if(typeof(flag)!="boolean"){
						flag=true;
						return true;
					}
					
					flag=$.fn.Validform.sn.regcheck($(this).attr("datatype"),$(this).val());
					
					if(!flag){
						if($(this).attr("ignore")=="ignore" && ( $(this).val()=="" || $(this).val()==$(this).attr("tip") )){
							flag=true;
							return true;
						}
						errorobj=$(this);
						errorobj.focus();
						$.fn.Validform.sn.showmsg($(this).attr("errormsg")||tipmsg.w,settings.tiptype,{obj:$(this)});
						return false;
					}
					
					if($(this).attr("ajaxurl")){
						if($(this).attr("valid")!="true"){
							flag=false;
							var thisobj=$(this);
							errorobj=thisobj;
							errorobj.focus();
							$.fn.Validform.sn.showmsg(thisobj.attr("valid") || tipmsg.v,settings.tiptype,{obj:thisobj});
							if(!msghidden || settings.tiptype==2){
								setTimeout(function(){
									thisobj.trigger("blur");
								},2000);
							}
							return false;
						}else{
							$.fn.Validform.sn.showmsg(tipmsg.r,settings.tiptype,{obj:$(this),type:2},"hide");
							flag=true;
						}
					}
				})
				
				if(flag && !posting){
					errorobj=null;
					if(settings.postonce){posting=true;}
					if(settings.ajaxurl){
						$.fn.Validform.sn.showmsg(tipmsg.p,settings.tiptype,{obj:$(this)},"alwaysshow");//���롰alwaysshow��������ʾ�򲻹ܵ�ǰtiptyeΪ1����2������;
						$.ajax({
							type: "POST",
							dataType:"json",
							url: settings.ajaxurl,
							data: $this.serialize(),
							success: function(data){
								$.fn.Validform.sn.showmsg(data.info,settings.tiptype,{obj:$(this)},"alwaysshow");
								(settings.callback)(data);
							}
						});
						return false;
					}else{
						$this.get(0).submit();
					}
				}
				
			}
			
			$this.find(settings.btnSubmit).bind("click",subform);
			$this.submit(function(){
				subform();
				return false;
			});
		})
		
		//Ԥ����pop box;
		if(settings.tiptype!=2 || settings.ajaxurl){		
			creatMsgbox();
		}
		
	}
	
	$.fn.Validform.sn={
		defaults:{
			tiptype:1
		},
		
		regcheck:function(type,gets){
			switch(type){
				case "*":
					return true;
				case "*4-16":
					var repost= /[^\s]{4,16}/;
					return repost.test(gets);
				case "n":
					return !isNaN(gets);
				case "s":
					return isNaN(gets);
				case "s4-18":
					var repost= /^[\u4E00-\u9FA5\uf900-\ufa2d\w]{4,18}$/;
					return repost.test(gets);
				case "p":
					var repost= /^[0-9]{6}$/;
					return repost.test(gets);
				case "m":
					var repost= /^13[0-9]{1}[0-9]{8}$|15[0189]{1}[0-9]{8}$|18[2689]{1}[0-9]{8}$/;
					return repost.test(gets);
				case "e":
					var repost = /\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*/;
					return repost.test(gets);
				case "v":
					var repost = /<<[δ]>>/;
					return repost.test(gets);
				default:
					return false;
			}
		},
		
		showmsg:function(msg,type,o,show){//o:{obj:��ǰ����, type:1=>���ڼ�� | 2=>ͨ��}, show�����ж�tiptype=1��������Ƿ񵯳���Ϣ��;
			if(errorobj){errorobj.addClass("Validform_error");}
			
			if(type==1 || show=="alwaysshow"){
				msgobj.find(".Validform_info").text(msg);
			}

			if(type==1 && show!="hide" || show=="alwaysshow"){
				msghidden=false;
				msgobj.find(".iframe").css("height",msgobj.height());
				var left=($(window).width()-msgobj.width())/2;
				var top=($(window).height()-msgobj.height())/2;
				top=(document.documentElement.scrollTop?document.documentElement.scrollTop:document.body.scrollTop)+(top>0?top:0);
				msgobj.css({
					"left":left
				}).show().animate({
					top:top
				},100);
			}
			
			if(type==2){
				if(o.type){
					switch(o.type){
						case 1://���ڼ��;
							o.obj.parent().next().find(".Validform_checktip").removeClass().addClass("Validform_checktip Validform_loading").text(msg);
							break;
						case 2://���ͨ��;
							o.obj.parent().next().find(".Validform_checktip").removeClass().addClass("Validform_checktip Validform_right").text(msg);	
					}
				}else{
					o.obj.parent().next().find(".Validform_checktip").removeClass().addClass("Validform_wrong Validform_checktip").text(msg);
				}
			}
			
		},
		
		checkform:function(obj,parentobj,tiptype,show){//show�����ж��Ǳ���ύ����blur�¼������ļ��;
			var errormsg=obj.attr("errormsg") || tipmsg.w;
			
			if(obj.is("[datatype='radio']")){  //�ж�radio��Ԫ��;
				var inputname=obj.attr("name");
				var radiovalue=parentobj.find(":radio[name="+inputname+"]:checked").val();
				if(!radiovalue){
					errorobj=obj;
					this.showmsg(errormsg,tiptype,{obj:obj},show);
					return false;
				}
				errorobj=null;
				this.showmsg(tipmsg.r,tiptype,{obj:obj,type:2},"hide");
				return "radio";
			}

			if(obj.is("[datatype='checkbox']")){  //�ж�checkbox��Ԫ��;
				var inputname=obj.attr("name");
				var checkboxvalue=parentobj.find(":checkbox[name="+inputname+"]:checked").val();
				if(!checkboxvalue){
					errorobj=obj;
					this.showmsg(errormsg,tiptype,{obj:obj},show);
					return false;
				}
				errorobj=null;
				this.showmsg(tipmsg.r,tiptype,{obj:obj,type:2},"hide");
				return "checkbox";
			}

			if(obj.is("[datatype='select']")){  //�ж�select��Ԫ��;
				if(!obj.val()){
				  errorobj=obj;
				  this.showmsg(errormsg,tiptype,{obj:obj},show);
				  return false;
				}
				errorobj=null;
				this.showmsg(tipmsg.r,tiptype,{obj:obj,type:2},"hide");
				return "select";
			}
			
			var defaultvalue=obj.attr("tip");
			if((obj.val()=="" || obj.val()==defaultvalue) && obj.attr("ignore")!="ignore"){
				errorobj=obj;
				this.showmsg(obj.attr("nullmsg") || tipmsg.s,tiptype,{obj:obj},show);
				return false;
			}
			
			if(obj.attr("recheck")){
				var theother=parentobj.find("input[name="+obj.attr("recheck")+"]:first");
				if(obj.val()!=theother.val()){
					errorobj=obj;
					this.showmsg(errormsg,tiptype,{obj:obj},show);
					return false;
				}
			}
			
			obj.removeClass("Validform_error");
			errorobj=null;
			return true;
		}
		
	}
	
	//���÷�����ʾ&�ر���Ϣ��ʾ��;
	$.Showmsg=function(msg){
		creatMsgbox();
		$.fn.Validform.sn.showmsg(msg,1);
	};
	$.Hidemsg=function(){
		msgobj.hide();
		msghidden=true;
	}

})(jQuery);