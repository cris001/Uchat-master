function checkPasswords() {
           var pass1 = document.getElementById("password1");
           var pass2 = document.getElementById("password2");
		   if (pass1.value != pass2.value)
               pass1.setCustomValidity("两次输入的密码不匹配");
           else
               pass1.setCustomValidity("");
       }
   
function checkSelet(){
	   var option=document.getElementById("optionGroup");
	   var group=document.getElementById("selectGroup");
	   if(option.selected==true)
		 group.setCustomValidity("请选择"); 
	   else
		   group.setCustomValidity("");
   }
function checkSend(){
	   var option=document.getElementById("sendemail");
	   if(option.length<'4' || option>'21')
		 option.setCustomValidity("快递单号长度大于4小于21"); 
	   else
		   option.setCustomValidity("");
	
}



function trueauthority(){

	var i=0;
	var a = new Array(5);
$("#auth option:selected").each(function(){

	a[i]=$(this).val();
	i++;
});
	var flag1=0;
	for(i=0;i<5;i++){
		if(a[i]=="staffAuth"){
			flag1=1;
		}
	}
	if(flag1==1){
		$("#staffAuth").val('1');
	}else{
		$("#staffAuth").val('0');
	}
	
	var flag2=0;
	for(i=0;i<5;i++){
		if(a[i]=="frontAuth"){
			flag2=1;
		}
	}
	if(flag2==1){
		$("#frontAuth").val('1');
	}else{
		$("#frontAuth").val('0');
	}
	
	var flag3=0;
	for(i=0;i<5;i++){
		if(a[i]=="goodsAuth"){
			flag3=1;
		}
	}
	if(flag3==1){
		$("#goodsAuth").val('1');
	}else{
		$("#goodsAuth").val('0');
	}

	var flag4=0;
	for(i=0;i<5;i++){
		if(a[i]=="stockAuth"){
			flag4=1;
		}
	}
	if(flag4==1){
		$("#stockAuth").val('1');
	}else{
		$("#stockAuth").val('0');
	}

	var flag5=0;
	for(i=0;i<5;i++){
		if(a[i]=="sellAuth"){
			flag5=1;
		}
	}
	if(flag5==1){
		$("#sellAuth").val('1');
	}else{
		$("#sellAuth").val('0');
	}

}


function changpho(){
	document.getElementById("showpho").style.display="block";
}

function showornone(show,none){
	document.getElementById(show).style.display="block";
	document.getElementById(none).style.display="none";
}

function changrede(flag){
	if(flag=='yes'){
		document.getElementById("changred").value='1';
		alert(document.getElementById("changred").value);
		document.getElementById("needred").style.display="block";
	}else if(flag=='no'){
		document.getElementById("changred").value='0';
		alert(document.getElementById("changred").value);
		document.getElementById("needred").value='0';
		alert(document.getElementById("needred").value);
		document.getElementById("needred").style.display="none";
	}
}


function sendsucc(){
	document.getElementById("emailState").value=1;
}

function opendetai(opendiv){
	$(opendiv).modal(options);
}

var chem=0;
var chadd=0;

function showornone(show,none){
	document.getElementById(show).style.display="block";
	document.getElementById(none).style.display="none";
	if(show=="showposi"){
		document.getElementById("forposition").value=1;
	}else if(show=="changadd"){
		document.getElementById("foradd").value=1;
		document.getElementById("add-address").required="required";
	}else if(show=="emailchang"){
		document.getElementById("foremail").value=1;
		document.getElementById("tochangemil").required="required";
	}else if(show=="changauth"){
		document.getElementById("staffAuth").value=0;
		document.getElementById("frontAuth").value=0;
		document.getElementById("goodsAuth").value=0;
		document.getElementById("stockAuth").value=0;
		document.getElementById("sellAuth").value=0;
	}else if(show=="showsto"){
		document.getElementById("toupdatesto").value = document.getElementById("updatesupp").value;
	}
}

function updateSta(){
	if(document.getElementById("foremail").value==1){
		document.getElementById("changemil").value=document.getElementById("tochangemil").value+document.getElementById("emailadd").value;
	}
	if(document.getElementById("foradd").value==1){
		document.getElementById("changaddre").value=document.getElementById("province").value+document.getElementById("city").value+document.getElementById("add-address").value;
	}
	if(document.getElementById("forposition").value==1){
		document.getElementById("toaddposi").name="staff.position.positionId";
		document.getElementById("thispos").name="none";
	}
}
function addSta(){
	if(document.getElementById("foremail").value==0){
	document.getElementById("add-email").value+=document.getElementById("emailadd").value;
	document.getElementById("foremail").value=1;
	}
	if(document.getElementById("foradd").value==0){
	document.getElementById("add-address").value=document.getElementById("province").value+document.getElementById("city").value+document.getElementById("add-address").value;
	document.getElementById("foradd").value=1;
	}
}


