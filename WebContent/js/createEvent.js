/**
 * createEvent.jspに関数するjavascriptファイル
 */

'use strict';


        // 現在の日付を取得
        const today = new Date();

        // 28日後の日付を計算
        const dateAfter28Days = new Date(today);
        dateAfter28Days.setDate(today.getDate() + 28);

        // 180日後の日付を計算
        const dateAfter180Days = new Date(today);
        dateAfter180Days.setDate(today.getDate() + 180);

        function formatDate(date) {
            const year = date.getFullYear();
            const month = (date.getMonth() + 1).toString().padStart(2, '0');
            const day = date.getDate().toString().padStart(2, '0');
            return `${year}-${month}-${day}`;
        }

        function formatDate2(date) {
            const year = date.getFullYear();
            const month = (date.getMonth() + 1).toString().padStart(2, '0');
            const day = date.getDate().toString().padStart(2, '0');
            return `${year}年${month}月${day}日`;
        }


        let formObj = document.getElementById('input');
        let errorMessageObj = document.getElementById('errormessage');
        let regist = document.getElementById('regist');

        /*console.log(selectedDate);
        console.log(formatDate(dateAfter28Days));
        console.log(formatDate(dateAfter180Days));*/

        const Min = document.getElementById('min');
        const Max = document.getElementById('max');
        const Min_minus = document.getElementById('min_minus');
        const Min_plus = document.getElementById('min_plus');
        const Max_minus = document.getElementById('max_minus');
        const Max_plus = document.getElementById('max_plus');

        let count_min = 4;
        let count_max = 5;
        Min.value = count_min;
        Max.value = count_max;

        Min_minus.addEventListener('click', (event) => {
            count_min--;
            if (count_min < 4) {
                count_min = 4;
                event.preventDefault();
            } else {
                Min.value = count_min;
                event.preventDefault();
            }
        });

        Min_plus.addEventListener('click', (event) => {
            count_min++;
            if (count_min > 49) {
                count_min = 49;
                event.preventDefault();
            } else {
                Min.value = count_min;
                event.preventDefault();
            }
        });

        Max_minus.addEventListener('click', (event) => {
            count_max--;
            if (count_max < 5) {
                count_max = 5;
                event.preventDefault();
            } else {
                Max.value = count_max;
                event.preventDefault();
            }
        });

        Max_plus.addEventListener('click', (event) => {
            count_max++;
            if (count_max > 50 ) {
                count_max = 50;
                event.preventDefault();
            }
            Max.value = count_max;
            event.preventDefault();
        });


        let judgeMessageObj = document.getElementById('judgemessage');

        regist.addEventListener('click', (event) => {
            if (!formObj.event_name.value || !formObj.event_summary.value || !formObj.event_day.value || formObj.prefecture.value == 0 || !formObj.detail.value || !formObj.place.value || formObj.switch_2.value == "") {
            	event.preventDefault();
            	errorMessageObj.textContent = '全て入力してください';
            	judgeMessageObj.style.display = "none";
            }  else if (formObj.event_day.value < formatDate(dateAfter28Days) || formObj.event_day.value > formatDate(dateAfter180Days)) {
            	event.preventDefault();
            	errorMessageObj.textContent = '開催日程は、本日の日付から28日後(' + formatDate2(dateAfter28Days) + ')と180日後('+ formatDate2(dateAfter180Days) + ')の間に指定してください';
            } else if (formObj.min.value >= formObj.max.value) {
            	event.preventDefault();
            	errorMessageObj.textContent = '参加最少人数が参加最大人数と同じあるいは超えないように人数の指定をしてください';
            }
        });