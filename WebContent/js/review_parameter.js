/**
 * レビュー項目パラメーターのグラフ表示用
 */ // ウェブページのDOMが完全に読み込まれた後にコールバック関数を実行するイベントリスナーを追加。
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