$(document).ready(function(){
    dataGridCustion();
});
$(window).on('resize', function() {
    dataGridCustion();
}).resize();



function dataGridCustion(){

    var loadPanel =  {
        enabled: true,
        indicatorSrc: "../resources/img_app/load_img_inner.png",
    };
    var scrolling = {
        mode: "virtual"
    };
    var sorting = {
        mode: "none"
    };
    /* A */
    // main dataGrid
    $("#gridContainerA").dxDataGrid({
        dataSource: mangement,
        showBorders: true,
        loadPanel: loadPanel,
        scrolling: scrolling,
        sorting: sorting,
        columns: [
            {
                dataField: "ModelName",

            }, {
                dataField: "Status",
                alignment: "center",
                cellTemplate: function(el, val) {
                    //el.addClass("grid-edit");
                    // 동적 스티커생성 텍스트가 RUN이면 run 클래스추가
                     el.append('<div class="sticker">' + val.text + '</div>');
                     var thisText = el.text();
                     if(thisText == 'RUN') {
                         el.find('.sticker').addClass('run');
                     } else {
                         el.find('.sticker').removeClass('run');
                     }
                }

            }, {
                dataField: "LearningProgress",
                alignment: "center",
                cellTemplate: function(el, val) {
                    // 데이터 값과 data-nivel을 연결하여 따른 프로그래스바 너비 애니메이션과 % 함수 progressBar() 호출;
                    var pgsData = '';
                        pgsData += '<div class="grid-progress">';
                        pgsData += '<span class="count">' + val.text + '%</span>';
                        pgsData += '<div class="progress-wrap">';
                        pgsData += '<div class="progress-bar" data-nivel="'+ val.text +'">';
                        pgsData += '</div>';
                        pgsData += '</div>';
                        pgsData += '</div>';

                     el.append(pgsData);
                     progressBar();


                }


            }, {
                dataField: "DatasetType",
                alignment: "center",


            }, {
                dataField: "DatasetName",
                alignment: "center",

            }
        ],
    });

    /* B */
    // analytic model :: 01 Data input
    $("#gridContainerB1").dxDataGrid({
       dataSource: database,
       showBorders: true,
       loadPanel: loadPanel,
       scrolling: scrolling,
       sorting: sorting,
       columns: [
           {
               // 체크박스 엘리먼트 ID와 for를 일치시켜 데이터마다 나열하시면 됩니다.
               dataField: "Check",
               alignment: "center",
               cellTemplate: function(el, val) {
                   var checkBox = '';
                       checkBox += '<input class="check" id="chk1" type="checkbox" name="check-set">';
                       checkBox += '<label for="chk1"></label>';

                   el.append(checkBox);
               }

           }, {
               dataField: "FileName",
               alignment: "center",

           }, {
               dataField: "Size",
               alignment: "center"

           }, {
               dataField: "REG_DT",
               alignment: "center",

           }
       ],
   });
    // analytic model :: 02 Data discovery
    $("#gridContainerB2").dxDataGrid({
       dataSource: datadiscovery,
       showBorders: true,
       loadPanel: loadPanel,
       scrolling: scrolling,
       sorting: sorting,
       columns: [
           {
               dataField: "Preview",
               alignment: "center",
               cellTemplate: function(el, val) {
                   // 동적 버튼 생성과, 퍼블리싱단의 공통 팝업 modalUi();호출, 팝업은 팝업ID 와 a태그의 modalLoad클래스 +  href="#ID" 연동 이므로, 셀마다 href와 id를 증감하거나, 하나의 팝업내에서 데이터호출이 변경되도록 개발하시면 될것 같습니다.
                   var viewIco = '';
                       viewIco += '<a href="#" class="" data-toggle="modal" data-target="#previewPop">';
                       viewIco += '<i class="ti-search"></i>';
                       viewIco += '</a>';
                   el.append(viewIco);
                   //modalUi();
               }
           }, {
               dataField: "Name",
               alignment: "center",


           }, {
               dataField: "Type",
               alignment: "center",
               width:150,
               cellTemplate: function(el, val) {
                   // 동적 셀렉트박스 추가 디자인 UI를 위한 niceSelect() 호출
                   var sltAdd = '';
                       sltAdd += '<div class="selects">';
                       sltAdd += '<select class="form-control" name="">';
                       sltAdd += '<option>Nothing</option>';
                       sltAdd += '<option>Nothing Nothing</option>';
                       sltAdd += '<option>Nothing</option>';
                       sltAdd += '</select>';
                       sltAdd += '</div>';
                   el.append(sltAdd);
                   //if($('select').length){
                      // $('select').niceSelect();
                   //}
               }

           }, {
               dataField: "Min",
               alignment: "center",
           }, {
               dataField: "Max",
               alignment: "center",
           }, {
               dataField: "Q1",
               alignment: "center",
           }, {
               dataField: "Q3",
               alignment: "center",
           }, {
               dataField: "Unique value",
               alignment: "center",
           }, {
               dataField: "Missing",
               alignment: "center",
           }, {
               dataField: "Mean",
               alignment: "center",
           }, {
               dataField: "Stddev",
               alignment: "center",
           }, {
               dataField: "Use",
               alignment: "center",
               cellTemplate: function(el, val) {
                   // 스위치박스 동적생성, 스위치박스 UI를 위한 로직
                   var switchBox = '';
                       switchBox += '<label class="switch">';
                       switchBox += '<input type="checkbox" id="btn-switch">';
                       switchBox += '<span class="slider"></span>';
                       switchBox += '</label>';

                   el.append(switchBox);
                   el.each(function(){
                       $(this).find('.switch').on('click', function(){
                           if ($(this).hasClass('on')) {
                               $(this).removeClass('on');
                               $(this).find('input').attr('checked', false);
                               return false;
                           }else {
                               $(this).addClass('on');
                               $(this).find('input').attr('checked', true);
                               return false;
                           }
                       });
                   });
               }
           }, {
               dataField: "Target",
               alignment: "center",
               cellTemplate: function(el, val) {
                   // 체크박스 엘리먼트 ID와 for를 일치시켜 데이터마다 나열하시면 됩니다.
                   var checkBox = '';
                       checkBox += '<input class="check" id="chk1" type="checkbox" name="check-set">';
                       checkBox += '<label for="chk1"></label>';

                   el.append(checkBox);
               }
           }
       ],
    });
    // analytic model :: 03 Feature selection
    $("#gridContainerB3").dxDataGrid({
        dataSource: featureSelection,
        showBorders: true,
        loadPanel: loadPanel,
        scrolling: scrolling,
        sorting: sorting,
        columns: [
            {

                dataField: "선택",
                alignment: "center",
                cellTemplate: function(el, val) {
                    // 체크박스 엘리먼트 ID와 for를 일치시켜 데이터마다 나열하시면 됩니다.
                    var checkBox = '';
                        checkBox += '<input class="check" id="chk1" type="checkbox" name="check-set">';
                        checkBox += '<label for="chk1"></label>';

                    el.append(checkBox);
                }

            }, {
                dataField: "추천 유형",
                alignment: "center",

            }, {
                dataField: "세그먼트 타입",
                alignment: "center"

            }, {
                dataField: "세그먼트 종류",
                alignment: "center",

            }, {
                dataField: "세그먼트명",
                alignment: "center",

            }
        ],
    });
    // analytic model :: 05 Model excution
    $("#gridContainerB4").dxDataGrid({
        dataSource: modelExcution,
        showBorders: true,
        loadPanel: loadPanel,
        scrolling: scrolling,
        sorting: sorting,
        columns: [
            {
                dataField: "추천 유형",
                alignment: "center",

            }, {
                dataField: "세그먼트 타입",
                alignment: "center"

            }, {
                dataField: "세그먼트 종류",
                alignment: "center",

            }, {
                dataField: "세그먼트명",
                alignment: "center",

            }
        ],
    });

	$("#gridContainerB5-1").dxDataGrid({
        dataSource: modelExcution,
        showBorders: true,
        loadPanel: loadPanel,
        scrolling: scrolling,
        sorting: sorting,
        columns: [
            {
                dataField: "Name",
                alignment: "center",

            }, {
                dataField: "Value",
                alignment: "center",

            }, {
                dataField: "Variable Influence",
                alignment: "center"

            }
        ],
    });


	$("#gridContainerB5-2").dxDataGrid({
        dataSource: modelExcution,
        showBorders: true,
        loadPanel: loadPanel,
        scrolling: scrolling,
        sorting: sorting,
        columns: [
            {
                dataField: "Name",
                alignment: "center",

            }, {
                dataField: "Value",
                alignment: "center",

            }, {
                dataField: "Variable Influence",
                alignment: "center"

            }
        ],
    });
	
	$("#gridContainerB5-3").dxDataGrid({
        dataSource: modelExcution,
        showBorders: true,
        loadPanel: loadPanel,
        scrolling: scrolling,
        sorting: sorting,
        columns: [
            {
                dataField: "Name",
                alignment: "center",

            }, {
                dataField: "Value",
                alignment: "center",

            }, {
                dataField: "Variable Influence",
                alignment: "center"

            }
        ],
    });
	
	$("#gridContainerB6").dxDataGrid({
        dataSource: modelExcution,
        showBorders: true,
        loadPanel: loadPanel,
        scrolling: scrolling,
        sorting: sorting,
        columns: [
            {
                dataField: "원본테이블",
                alignment: "center",

            }, {
                dataField: "원본컬럼",
                alignment: "center",

            }, {
                dataField: "대상테이블",
                alignment: "center"

            }, {
                dataField: "대상컬럼",
                alignment: "center"

            },{
                dataField: "연결유형",
                alignment: "center"

            }

        ],
    });

	$("#gridContainerB7").dxDataGrid({
        dataSource: modelExcution,
        showBorders: true,
        loadPanel: loadPanel,
        scrolling: scrolling,
        sorting: sorting,
        columns: [
            {
                dataField: "Pack유형",
                alignment: "center",

            }, {
                dataField: "Pack상태",
                alignment: "center",

            }, {
                dataField: "Pack타입",
                alignment: "center"

            }, {
                dataField: "Pack명",
                alignment: "center"

            }

        ],
    });

	$("#gridContainerB9").dxDataGrid({
        dataSource: modelExcution,
        showBorders: true,
        loadPanel: loadPanel,
        scrolling: scrolling,
        sorting: sorting,
        columns: [
            {
                dataField: "Preference 유형",
                alignment: "center",

            }, {
                dataField: "Preference 상태",
                alignment: "center",

            }, {
                dataField: "Preference 타입",
                alignment: "center"

            }, {
                dataField: "Preference 명",
                alignment: "center"

            }

        ],
    });
	

	$("#gridContainerB10").dxDataGrid({
        dataSource: modelExcution,
        showBorders: true,
        loadPanel: loadPanel,
        scrolling: scrolling,
        sorting: sorting,
        columns: [
            {
                dataField: "Pack 유형",
                alignment: "center",

            }, {
                dataField: "Preference 상태",
                alignment: "center",

            }, {
                dataField: "추천알고리즘",
                alignment: "center"

            }

        ],
    });

	$("#gridContainerB11").dxDataGrid({
        dataSource: modelExcution,
        showBorders: true,
        loadPanel: loadPanel,
        scrolling: scrolling,
        sorting: sorting,
        columns: [
            {
                dataField: "Statistics 유형",
                alignment: "center",

            }, {
                dataField: "Statistics 상태",
                alignment: "center",

            }, {
                dataField: "Pack명",
                alignment: "center"

            }, {
                dataField: "Statistics 구분",
                alignment: "center"

            }, {
                dataField: "Statistics명",
                alignment: "center"

            }, {
                dataField: "반환아이템수",
                alignment: "center"

            }

        ],
    });

	$("#gridContainerB12").dxDataGrid({
        dataSource: preference,
        showBorders: true,
        loadPanel: loadPanel,
        scrolling: scrolling,
        sorting: sorting,
        columns: [
            {
                dataField: "No",
                alignment: "center",

            }, {
                dataField: "선호요소 추천",
                alignment: "center",

            }, {
                dataField: "선호값",
                alignment: "center",
				cellTemplate: function(el, val) {
			   // 체크박스 엘리먼트 ID와 for를 일치시켜 데이터마다 나열하시면 됩니다.
			   var preferenceInput = '';
				   preferenceInput += '<input class="form-control" id="txt01" type="text" name="txt01">';
			   el.append(preferenceInput);
				}

            }, {
                dataField: "변수중요도",
                alignment: "center"

            }

        ],
    });
	
	$("#gridContainerB8").dxDataGrid({
        dataSource: modelExcution,
        showBorders: true,
        loadPanel: loadPanel,
        scrolling: scrolling,
        sorting: sorting,
        columns: [
            {
                dataField: "추천일자",
                alignment: "center",

            }, {
                dataField: "수강신청수",
                alignment: "center",

            }, {
                dataField: "추천신청수",
                alignment: "center"

            }, {
                dataField: "전환율",
                alignment: "center"

            }

        ],
    });



    /* B popup  :: data view */
    $("#gridContainerViewData").dxDataGrid({
        dataSource: viewData,
        showBorders: true,
        loadPanel: loadPanel,
        scrolling: scrolling,
        sorting: sorting,
        columns: [
            {
                dataField: "sepal_length",
                alignment: "center",

            }, {
                dataField: "sepal_width",
                alignment: "center",

            }, {
                dataField: "petal_length",
                alignment: "center"

            }, {
                dataField: "petal_width",
                alignment: "center",

            }, {
                dataField: "variety",
                alignment: "center",

            }
        ],
    });

    /* C */
    $("#gridContainerC").dxDataGrid({
        dataSource: mangement,
        showBorders: true,
        loadPanel: loadPanel,
        scrolling: scrolling,
        sorting: sorting,
        columns: [
            {
                dataField: "ModelName",

            }, {
                dataField: "Status",
                alignment: "center",
                width:50,
                cellTemplate: function(el, val) {
                    //el.addClass("grid-edit");
                    // 동적 스티커생성 텍스트가 RUN이면 run 클래스추가
                     el.append('<div class="sticker">' + val.text + '</div>');
                     var thisText = el.text();
                     if(thisText == 'RUN') {
                         el.find('.sticker').addClass('run');
                     } else {
                         el.find('.sticker').removeClass('run');
                     }
                }

            }, {
                dataField: "LearningProgress",
                alignment: "center",
                cellTemplate: function(el, val) {
                    // 데이터 값과 data-nivel을 연결하여 따른 프로그래스바 너비 애니메이션과 % 함수 progressBar() 호출;
                    var pgsData = '';
                        pgsData += '<div class="grid-progress">';
                        pgsData += '<span>' + val.text + '%</span>';
                        pgsData += '<div class="progress-wrap">';
                        pgsData += '<div class="progress-bar" data-nivel="'+ val.text +'">';
                        pgsData += '</div>';
                        pgsData += '</div>';
                        pgsData += '</div>';

                     el.append(pgsData);
                     progressBar();

                }


            }, {
                dataField: "DatasetType",
                alignment: "center",


            }, {
                dataField: "DatasetName",
                alignment: "center",

            }, {
                dataField: "Edit",
                alignment: "center",
                cellTemplate: function(el, val) {
                    // 동적 버튼 생성과, 퍼블리싱단의 공통 팝업 modalUi();호출, 팝업은 팝업ID 와 a태그의 modalLoad클래스 +  href="#ID" 연동 이므로, 셀마다 href와 id를 증감하거나, 하나의 팝업내에서 데이터호출이 변경되도록 개발하시면 될것 같습니다.
                    var editIco = '';
                        editIco += '<div class="grid-gui-ico">';
                        // editIco += '<a href="#" class="gui file"></a>'; // 일반 첨부파일 아이콘 모양 on 클래스추가시 녹색(첨부된상태 스타일)
                        editIco += '<a href="#dataSelect" class="gui data modalLoad"></a>'; // 데이터베이스 셀렉트 팝업 아이콘 on 클래스추가시 녹색(첨부된상태 스타일)
                        editIco += '<a href="#dataPlay" class="gui play modalLoad"></a>';
                        editIco += '<a href="#dataSchedule" class="gui date modalLoad"></a>';
                        editIco += '<a href="#dataLog" class="gui view modalLoad"></a>';
                        editIco += '<a href="#" class="gui down"></a>';
                        editIco += '<a href="#" class="gui close"></a>';
                        editIco += '</div>';

                    el.append(editIco);
                    modalUi();

                }
            }
        ],
    });

	
	$("#gridContainerC1").dxDataGrid({
       dataSource: database,
       showBorders: true,
       loadPanel: loadPanel,
       scrolling: scrolling,
       sorting: sorting,
       columns: [
           {
               // 체크박스 엘리먼트 ID와 for를 일치시켜 데이터마다 나열하시면 됩니다.
               dataField: "Check",
               alignment: "center",
               cellTemplate: function(el, val) {
                   var checkBox = '';
                       checkBox += '<input class="check" id="chk1" type="checkbox" name="check-set">';
                       checkBox += '<label for="chk1"></label>';

                   el.append(checkBox);
               }

           }, {
               dataField: "추천유형",
               alignment: "center",

           }, {
               dataField: "세그먼트 타입",
               alignment: "center"

           }, {
               dataField: "세그먼트 종류",
               alignment: "center",

           }, {
               dataField: "세그먼트명",
               alignment: "center",

           }
       ],
   });

}
