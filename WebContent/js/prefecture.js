/**
 * 都道府県モーダル表示用
 */

 // ウェブページのDOMが完全に読み込まれた後にコールバック関数を実行するイベントリスナーを追加。
document.addEventListener('DOMContentLoaded', (event) => {

    // IDが myModal の要素を取得し、変数 modal に格納します。これは、モーダルウィンドウ自体を指します。
    const modal = document.getElementById('myModal');


    // IDが openModalBtn の要素を取得し、変数 openModalBtn に格納。これは、モーダルを開くためのボタンを指す。
    const openModalBtn = document.getElementById('openModalBtn');


    // クラス名が close の要素を取得し、その最初の要素を変数 closeModalSpan に格納。これは、モーダルを閉じるための「×」ボタンを指す。
    const closeModalSpan = document.getElementsByClassName('close')[0];


    // openModalBtn がクリックされたときに、モーダルの style.display プロパティを "block" に設定し、モーダルを表示する匿名関数を設定する。
    openModalBtn.onclick = function() {
        modal.style.display = "block";
    }


    // closeModalSpan がクリックされたときに、モーダルの style.display プロパティを "none" に設定し、モーダルを非表示にする匿名関数を設定する。
    closeModalSpan.onclick = function() {
        modal.style.display = "none";
    }


    // ウィンドウ全体がクリックされたときに呼び出される関数を設定する。
    // この関数は、クリックイベントの target がモーダルそのものであるかどうかをチェックし、そうであればモーダルを非表示にする。
    // これは、モーダルの外側をクリックしたときにモーダルを閉じるための処理。
    window.onclick = function(event) {
        if (event.target == modal) {
            modal.style.display = "none";
        }
    }
});

// ウェブページのDOMが完全に読み込まれた後にコールバック関数を実行するイベントリスナーを追加。
document.addEventListener('DOMContentLoaded', function() {


    // クラス名が range_slider の要素をすべて選択し、それらを rangeItems に格納。
    const rangeItems = document.querySelectorAll('.range_slider');


    // rangeItems の各要素に対してループを開始。
    for(let i = 0; i < rangeItems.length; i++) {


        // 現在の rangeItem 要素を変数に格納。
        const rangeItem = rangeItems[i];


        // 現在の rangeItem 要素内にある input 要素（タイプが "range" のもの）を選択し、それを rangeItemInput に格納。
        const rangeItemInput = rangeItem.querySelector('input[type="range"]');


        // rangeItemInput の min 属性の値を取得し、整数に変換して min 変数に格納。
        const min = parseInt(rangeItemInput.getAttribute('min'));

        // 現在の rangeItem 内の range_slider_min クラスを持つ要素を選択し、そのテキスト内容を min に設定。
        rangeItem.querySelector('.range_slider_min').innerText = min;

        // rangeItemInput の max 属性の値を取得し、整数に変換して max 変数に格納。
        const max = parseInt(rangeItemInput.getAttribute('max'));

        // 現在の rangeItem 内の range_slider_max クラスを持つ要素を選択し、そのテキスト内容を max に設定。
        rangeItem.querySelector('.range_slider_max').innerText = max;


        // 現在の rangeItem 内の range_slider_input_current クラスを持つ要素の内部の span 要素を選択し、
        // それを rangeItemCurrent に格納。
        const rangeItemCurrent = rangeItem.querySelector('.range_slider_input_current span');

        // 現在のスライダーの値を表示
        matchCurrent();

        // rangeItemInput の値が変わるたびに matchCurrent 関数を呼び出す input イベントリスナーを追加。
        rangeItemInput.addEventListener('input', function() {
            matchCurrent();
        }, false);


        // matchCurrent 関数を定義。
        function matchCurrent() {

            // 現在のスライダーの値を取得し、整数に変換して current 変数に格納。
            const current = parseInt(rangeItemInput.value);

            // 「現在値から最小値を引いた値」を「最大値から最小値を引いた」値で割り、
            // スライダーの入力範囲内に対して現在値が何％なのか算出した値を格納
            const ratio = ((current - min) / (max - min)) * 100;

            // rangeItemCurrent 要素のテキスト内容を現在のスライダーの値 (current) に設定する。
            rangeItemCurrent.innerText = current;

            // rangeItemCurrent 要素の left スタイルプロパティを ratio に基づいて設定し、スライダー上の適切な位置に配置。
            rangeItemCurrent.style.left = ratio + '%';
        }
    }
}, false);

// document.getElementById('selectall_地方名').addEventListener('change', function() { ... });:
//     id="selectall_地方名" のチェックボックスが変更されたときにイベントリスナーがトリガーされる。
// const checkboxes = document.querySelectorAll('.selectable_各地方の都道府県名');:
//     class="selectable_各地方の都道府県名" のチェックボックスすべてを取得する。
// for (let i = 0; i < checkboxes.length; i++) { checkboxes[i].checked = this.checked; }:
//     ループを使って、すべての selectable_各地方の都道府県名 チェックボックスの状態（checked）を selectall_地方名 チェックボックスの状態に合わせて変更する。

let hokkaido_checkboxes = false;
document.getElementById('selectall_hokkaido').addEventListener('change', function(hokkaido_checkboxes) {
    let checkboxes = document.querySelectorAll('.selectable_hokkaido');
    for (let i = 0; i < checkboxes.length; i++) {
        checkboxes[i].checked = this.checked;
    }
});

let touhoku_checkboxes = false;
document.getElementById('selectall_touhoku').addEventListener('change', function(touhoku_checkboxes) {
    const checkboxes = document.querySelectorAll('.selectable_touhoku');
    for (let i = 0; i < checkboxes.length; i++) {
        checkboxes[i].checked = this.checked;
    }
});

let kantou_checkboxes = false;
document.getElementById('selectall_kantou').addEventListener('change', function(kantou_checkboxes) {
    const checkboxes = document.querySelectorAll('.selectable_kantou');
    for (let i = 0; i < checkboxes.length; i++) {
        checkboxes[i].checked = this.checked;
    }
});

let tyubu_checkboxes = false;
document.getElementById('selectall_tyubu').addEventListener('change', function(tyubu_checkboxes) {
    const checkboxes = document.querySelectorAll('.selectable_tyubu');
    for (let i = 0; i < checkboxes.length; i++) {
        checkboxes[i].checked = this.checked;
    }
});

let kinki_checkboxes = false;
document.getElementById('selectall_kinki').addEventListener('change', function(kinki_checkboxes) {
    const checkboxes = document.querySelectorAll('.selectable_kinki');
    for (let i = 0; i < checkboxes.length; i++) {
        checkboxes[i].checked = this.checked;
    }
});

let tyugoku_checkboxes = false;
document.getElementById('selectall_tyugoku').addEventListener('change', function(tyugoku_checkboxes) {
    const checkboxes = document.querySelectorAll('.selectable_tyugoku');
    for (let i = 0; i < checkboxes.length; i++) {
        checkboxes[i].checked = this.checked;
    }
});

let shikoku_checkboxes = false;
document.getElementById('selectall_shikoku').addEventListener('change', function(shikoku_checkboxes) {
    const checkboxes = document.querySelectorAll('.selectable_shikoku');
    for (let i = 0; i < checkboxes.length; i++) {
        checkboxes[i].checked = this.checked;
    }
});

let kyusyu_checkboxes = false;
document.getElementById('selectall_kyusyu').addEventListener('change', function(kyusyu_checkboxes) {
    const checkboxes = document.querySelectorAll('.selectable_kyusyu');
    for (let i = 0; i < checkboxes.length; i++) {
        checkboxes[i].checked = this.checked;
    }
});

let okinawa_checkboxes = false;
document.getElementById('selectall_okinawa').addEventListener('change', function(okinawa_checkboxes) {
    const checkboxes = document.querySelectorAll('.selectable_okinawa');
    for (let i = 0; i < checkboxes.length; i++) {
        checkboxes[i].checked = this.checked;
    }
});

const submitBtn = document.getElementById('submitBtn');
const selectedItemsDiv = document.getElementById('selected-items');
const selectedItemsDiv2 = document.getElementById('selected-items2');
const selected_prefectureS = document.getElementById('selected_prefectures');

const hokkaidoCheckboxes = document.querySelectorAll(".selectable_hokkaido");
const touhokuCheckboxes = document.querySelectorAll(".selectable_touhoku");
const kantouCheckboxes = document.querySelectorAll(".selectable_kantou");
const tyubuCheckboxes = document.querySelectorAll(".selectable_tyubu");
const kinkiCheckboxes = document.querySelectorAll(".selectable_kinki");
const tyugokuCheckboxes = document.querySelectorAll(".selectable_tyugoku");
const shikokuCheckboxes = document.querySelectorAll(".selectable_shikoku");
const kyusyuCheckboxes = document.querySelectorAll(".selectable_kyusyu");
const okinawaCheckboxes = document.querySelectorAll(".selectable_okinawa");

submitBtn.addEventListener('click', () => {
    let selectedRegions = [];

    hokkaidoCheckboxes.forEach((checkbox) => {
        if (checkbox.checked && checkbox.value === "1") {
            selectedRegions.push("北海道");
        }
    });

    touhokuCheckboxes.forEach((checkbox) => {
        if (checkbox.checked && checkbox.value === "2") {
            selectedRegions.push("青森県");
        } else if (checkbox.checked && checkbox.value === "3") {
            selectedRegions.push("岩手県");
        } else if (checkbox.checked && checkbox.value === "4") {
            selectedRegions.push("宮城県");
        } else if (checkbox.checked && checkbox.value === "5") {
            selectedRegions.push("秋田県");
        } else if (checkbox.checked && checkbox.value === "6") {
            selectedRegions.push("山形県");
        } else if (checkbox.checked && checkbox.value === "7") {
            selectedRegions.push("福島県");
        }
    });

    kantouCheckboxes.forEach((checkbox) => {
        if (checkbox.checked && checkbox.value === "8") {
            selectedRegions.push("茨城県");
        } else if (checkbox.checked && checkbox.value === "9") {
            selectedRegions.push("栃木県");
        } else if (checkbox.checked && checkbox.value === "10") {
            selectedRegions.push("群馬県");
        } else if (checkbox.checked && checkbox.value === "11") {
            selectedRegions.push("埼玉県");
        } else if (checkbox.checked && checkbox.value === "12") {
            selectedRegions.push("千葉県");
        } else if (checkbox.checked && checkbox.value === "13") {
            selectedRegions.push("東京都");
        } else if (checkbox.checked && checkbox.value === "14") {
            selectedRegions.push("神奈川県");
        }
    });

    tyubuCheckboxes.forEach((checkbox) => {
        if (checkbox.checked && checkbox.value === "15") {
            selectedRegions.push("新潟県");
        } else if (checkbox.checked && checkbox.value === "16") {
            selectedRegions.push("富山県");
        } else if (checkbox.checked && checkbox.value === "17") {
            selectedRegions.push("石川県");
        } else if (checkbox.checked && checkbox.value === "18") {
            selectedRegions.push("福井県");
        } else if (checkbox.checked && checkbox.value === "19") {
            selectedRegions.push("山梨県");
        } else if (checkbox.checked && checkbox.value === "20") {
            selectedRegions.push("長野県");
        } else if (checkbox.checked && checkbox.value === "21") {
            selectedRegions.push("岐阜県");
        } else if (checkbox.checked && checkbox.value === "22") {
            selectedRegions.push("静岡県");
        } else if (checkbox.checked && checkbox.value === "23") {
            selectedRegions.push("愛知県");
        }
    });

    kinkiCheckboxes.forEach((checkbox) => {
        if (checkbox.checked && checkbox.value === "24") {
            selectedRegions.push("三重県");
        } else if (checkbox.checked && checkbox.value === "25") {
            selectedRegions.push("滋賀県");
        } else if (checkbox.checked && checkbox.value === "26") {
            selectedRegions.push("京都府");
        } else if (checkbox.checked && checkbox.value === "27") {
            selectedRegions.push("大阪府");
        } else if (checkbox.checked && checkbox.value === "28") {
            selectedRegions.push("兵庫県");
        } else if (checkbox.checked && checkbox.value === "29") {
            selectedRegions.push("奈良県");
        } else if (checkbox.checked && checkbox.value === "30") {
            selectedRegions.push("和歌山県");
        }
    });

    tyugokuCheckboxes.forEach((checkbox) => {
        if (checkbox.checked && checkbox.value === "31") {
            selectedRegions.push("鳥取県");
        } else if (checkbox.checked && checkbox.value === "32") {
            selectedRegions.push("島根県");
        } else if (checkbox.checked && checkbox.value === "33") {
            selectedRegions.push("岡山県");
        } else if (checkbox.checked && checkbox.value === "34") {
            selectedRegions.push("広島県");
        } else if (checkbox.checked && checkbox.value === "35") {
            selectedRegions.push("山口県");
        }
    });

    shikokuCheckboxes.forEach((checkbox) => {
        if (checkbox.checked && checkbox.value === "36") {
            selectedRegions.push("徳島県");
        } else if (checkbox.checked && checkbox.value === "37") {
            selectedRegions.push("香川県");
        } else if (checkbox.checked && checkbox.value === "38") {
            selectedRegions.push("愛媛県");
        } else if (checkbox.checked && checkbox.value === "39") {
            selectedRegions.push("高知県");
        }
    });

    kyusyuCheckboxes.forEach((checkbox) => {
        if (checkbox.checked && checkbox.value === "40") {
            selectedRegions.push("福岡県");
        } else if (checkbox.checked && checkbox.value === "41") {
            selectedRegions.push("佐賀県");
        } else if (checkbox.checked && checkbox.value === "42") {
            selectedRegions.push("長崎県");
        } else if (checkbox.checked && checkbox.value === "43") {
            selectedRegions.push("熊本県");
        } else if (checkbox.checked && checkbox.value === "44") {
            selectedRegions.push("大分県");
        } else if (checkbox.checked && checkbox.value === "45") {
            selectedRegions.push("宮崎県");
        } else if (checkbox.checked && checkbox.value === "46") {
            selectedRegions.push("鹿児島県");
        }
    });

    okinawaCheckboxes.forEach((checkbox) => {
        if (checkbox.checked && checkbox.value === "47") {
            selectedRegions.push("沖縄県");
        }
    });

    const selectedPrefectures = Array.from(document.querySelectorAll('input[name="prefectures"]:checked')).map(checkbox => checkbox.value);

    selectedItemsDiv.textContent = "選択された都道府県: ";
    selectedItemsDiv2.textContent = (selectedRegions.length > 0 ? selectedRegions.join(', ') : "");
    selected_prefectureS.value = selectedPrefectures;
    // selectedItemsDiv.innerHTML = `選択された都道府県: <br>${selectedPrefectures.join(', ')}`;

    /*console.log(selected_prefectureS.value);*/

    const modal = document.getElementById('myModal');
    modal.style.display = 'none';
});