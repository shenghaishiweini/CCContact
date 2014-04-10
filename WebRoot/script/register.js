$(document).ready(function() {

jQuery.extend(jQuery.validator.messages, {
		   required: "��ѡ�ֶ�",
		   remote: "���������ֶ�",
		   email: "��������ȷ��ʽ�ĵ����ʼ�",
		   url: "������Ϸ�����ַ",
		   date: "������Ϸ�������",
		   dateISO: "������Ϸ������� (ISO).",
		   number: "����������",
		   digits: "ֻ����������",
		   creditcard: "������Ϸ������ÿ���",
		   equalTo: "���ٴ�������ͬ��ֵ",
		   accept: "������ӵ�кϷ���׺�����ַ���",
		   maxlength: jQuery.validator.format("������һ�� ��������� {0} ���ַ���"),
		   minlength: jQuery.validator.format("������һ�� ���������� {0} ���ַ���"),
		   rangelength: jQuery.validator.format("������ һ�����Ƚ��� {0} �� {1} ֮����ַ���"),
		   range: jQuery.validator.format("������һ������ {0} �� {1} ֮���ֵ"),
		   max: jQuery.validator.format("������һ�����Ϊ{0} ��ֵ"),
		   min: jQuery.validator.format("������һ����СΪ{0} ��ֵ")
	});
	$("#register").validate({
		 rules: {
			  "product_inventory":{
			    	number: "[0-9*.0-9*]"
			  },
			  "inventory":{
			    	number: "[0-9*.0-9*]"
			  },
			  "pricePerUnit":{
			    	number: "[0-9*.0-9*]"
			  }
			 },
			 messages: {
				 "product_inventory":{
					 rangelength:"����������"
				    }
			 },
		submitHandler:function(form){
			submit();//������Ʒ
		}
	});
	
	var submit=function(){	

		$.ajax({  
            url : '', 
            type:'post', 
            dataType:'json',  
            data:{"product.shopId":shopId,"product.productId" :productId ,"product.productType" :productType ,"product.version" :productVersion , "cartListVo.productNumber" :parseInt(amount),"classId":productClassId},
            success: function(data) {
            	alert("��ӳɹ���");
            	showActionMsg($("#action_msg"), data);
            }
            		
		 });
	};
	
});