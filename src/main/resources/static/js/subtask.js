/**
 * 
 */
 
 function onAddSubJob() {
	var detail = document.getElementById('dt');
   	if(detail.open === false) {
   		detail.open = true;
   	}
   	
    var subjobList = document.getElementById("subjob-list");
	var str ="";
 	var newTag = document.createElement('div');
 	newTag.setAttribute("id","sub"+Count);
 	newTag.setAttribute("class","subjob");
 		str+='<div class="col-md-7">'
 		str+=' <label class="control-label"><label class="star01">하위작업명</label></label>'
 		str+=' <label><span></span></label>'
 		str+='  <div class="form-group"> '
 		str+=' <div class="formInput">'
 		str+=' <input type="text" maxlength="50"  name="subTitle" id="subTitle" class="form-control"  placeholder="50자 이내로 입력해주세요."/>'
 		str+=' </div>'
 		str+='  </div>'
 		str+='  </div>'
 		str+='<div class="col-md-3">'
 		str+='<label class="control-label"><label class="star01">하위작업 기간</label></label>'
 		str+='<div class="form-group">'
 		str+='<div class="formInput">'
 		str+=' <div id="dateStart" class="dateStart">'
 		str+='   <div class="input-group input-daterange date">'
 		str+='  <input class="form-control date" type="datetime-local" name="subStartDate" id="subStartDate" required="required" >'
 		str+='  <div class="input-group-addon"> ~ </div>'
 		str+='  <input class="form-control date" type="datetime-local"  name="subEndDate" id="subEndDate" required="required" >'
		str+=' </div>'
 		str+=' </div>'
		str+='</div>'
		str+='</div>'
		str+='</div>'
		str+='<div class="col-md-1">'
 		str+=' <label class="control-label"><label class="">진행률</label></label>'
 		str+=' <div class="formInput text-center">'
 		str+='  <div class="input-group unit">'
		str+='<input type="text" class="form-control" id="sub_real_progress" id="subRealProgress" name="subRealProgress"  value="0"/>'
 		str+=' <span class="input-group-addon">%</span>'
 		str+=' </div>'
 		str+=' </div>'
 		str+='</div>'
 		str+='</div>'
		 str+='<div class="col-md-1 text-center">'

		str+='<div class="form-group">'
 		str+='<label class="control-label"><label class="">보고서 포함</label></label>'
 		str+='<div class="formInput">'
 		str+='<input type="checkbox" name="subReportRegistFlag" id="subReportRegistFlag" value="0" style="zoom:1.6;"/>'
 		str+='</div>'
 		str+='</div>'
 		str+='</div>'
 		str+='<div class="col-md-11">'
 		str+='<label class="control-label"><label class="">하위작업 내용</label></label>'
 		str+='<label><span id="contents_counter/>"></span></label>'
		str+='<div class="form-group form-group-block">'
		str+='<div class="formInput">'
		str+='<textarea class="form-control" maxlength="1000" rows="4" name="subContents" id="subContents" placeholder="1000자 이내로 입력해 주세요."></textarea>'
		str+= '</div>'
		str+='</div>'
		str+='</div>'
 		str+='<div class="col-md-1 text-center">'
	 	str+='<input class="btn btn-info" type="submit" value="하위작업 삭제" onclick="onDeleteSubjob('+Count+');" style="margin-top: 40px;"/>'
 		str+='</div>' ;
 
 		newTag.innerHTML = str;
 	subjobList.appendChild(newTag);
 	Count++;
  }
 
 

 (function() {
    // 코드

    
    
})();
