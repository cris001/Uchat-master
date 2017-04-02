// JavaScript Document


function removertr(idval){
 $("#lastIndex").val( $("#lastIndex").val()-1);
//document.getElementById("lastIndex").value = parseInt(document.getElementById("lastIndex").value) - 1;
$("#tt"+idval).remove();
document.getElementById("intt"+idval).style.display="";
}


function addRow(idval){
	var listIndex = $("#forList").val();
	var instockId = $("#instockId").val();
	document.getElementById("intt"+idval).style.display="none";
	var goodsId = $("#goodsId"+idval).text();
	var goodsname = $("#goodsname"+idval).text();
	var inprice = $("#inprice"+idval).text();
	var lastIndex = $("#lastIndex").val();		//获得行数
    var addrow = $("#planTable")[0].insertRow();    //新增的一行
	addrow.setAttribute("id","tt"+idval);  		//设置ID值	
  	//addrow.className="bgcolor";			//设置class值
	
	
	var addcellone = addrow.insertCell();		//表的一行的指定位置插入一个空的 <td> 元素。
    //addcellone.className="zi13";				//td的class值
	addcellone.setAttribute("id","namegoods"+idval); 
    addcellone.setAttribute("align","center");		//td的align值
	addcellone.innerHTML = goodsname+"<input type='hidden' value="+instockId+" name='instockDetailList["+listIndex+"].instock.instockId'><input type='hidden' name='instockDetailList["+listIndex+"].goods.goodsId' id='inpnameg"+idval+"' value="+goodsId+">" ;//插入内容
	

	 var addcellone = addrow.insertCell();
   // addcellone.className="zi13";
	addcellone.setAttribute("id","inprgoods"+idval); 
    addcellone.setAttribute("align","center");
	addcellone.innerHTML = "&nbsp;"+inprice+"<input type='hidden' id='inpinpr"+idval+"' name='instockDetailList["+listIndex+"].purchasePrice' value="+inprice+" >" ;
	
	var addcellone = addrow.insertCell();
    //addcellone.className="zi13";
	addcellone.setAttribute("id","numgoods"+idval); 
    addcellone.setAttribute("align","center");
	addcellone.innerHTML = "<input style='width:70px; text-align:center;' type='text' name='instockDetailList["+listIndex+"].inNumber' id='inpnum"+idval+"' onChange='checknum("+idval+")' value='1' required='required' pattern='[1-9]|[1-9][0-9]{1,5}'/>" ;
	
	var addcellone = addrow.insertCell();
   // addcellone.className="zi13";
	addcellone.setAttribute("id","sumpr"+idval); 
    addcellone.setAttribute("align","center");
	addcellone.innerHTML = "<input style='width:70px; text-align:center;' type='text' id='inpsumpr"+idval+"' name='instockDetailList["+listIndex+"].sumPrice' readonly='readonly' value="+inprice+">" ;
	
	
	var addcellone = addrow.insertCell();
   // addcellone.className="zi13";
	addcellone.setAttribute("id","sumpr"+idval); 
	addcellone.setAttribute("align","center");
	addcellone.innerHTML = "<button class='btn btn-danger' onClick='removertr("+idval+")'>删除</button>";
	
	//alert("前："+lastIndex);

	document.getElementById("lastIndex").value = parseInt(lastIndex) + 1;
	 $("#forList").val(parseInt(listIndex)+1);   
	 
}

function checknum(numId){
	
	var option=document.getElementById("inpnum"+numId);
	var reg=/^[1-9]\d{0,5}$/;
	   if(option.value.trim().match(reg)==null)     
		 option.setCustomValidity("请输入首位非0的1-6位正整数"); 
	   else
		   option.setCustomValidity("");
	var inprice=document.getElementById("inpinpr"+numId).value;
	document.getElementById("inpsumpr"+numId).value=inprice*option.value;
}

/*function befsubmit(){
	var forList = $("#forList").val();
	var instockDetailList = new Array();  
	for(var k=0;k<forList;k++){
		instockDetailList[k]=new Array(); 
		instockDetailList[k][0]=$(".goodsId"+k).html() ;
		instockDetailList[k][1]=$(".purchasePrice"+k).html(); 
		instockDetailList[k][2]=$("#inpnum"+k).val(); 
		instockDetailList[k][3]=$("#inpsumpr"+k).val();  
	}
	
	var instockId = $("#instockId").val();
	
	var url="instock/updateAction!saveInstockSec?instockDetailList="+instockDetailList+'&instockId='+instockId;
	document.form1.action = url;	
	document.form1.submit();
}*/
