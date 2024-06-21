/**
 * コミュニケーションエリア非同期通信、参加者モーダル表示用
 */
'use strict';

/**
* 参加者モーダル表示
*/
// モーダル表示用のボタン(アイコン画像)をすべて取得
const modalBtns = document.querySelectorAll('[data-trigger="btn"]');
// モーダルの内容部分をすべて取得
const modalItems = document.querySelectorAll('[data-trigger="item"]');
// モーダルを閉じるボタンをすべて取得
const modalCloseBtns = document.querySelectorAll('[data-modal="close"]');

// モーダルを表示する
modalBtns.forEach(modalBtn => {
  modalBtn.addEventListener('click', (e) => {
    e.preventDefault();
    const modalBtnNum = e.currentTarget.dataset.modal;
    modalItems.forEach(modalItem => {
    const modalItemNum = modalItem.dataset.modal;
      // 押されたボタンの番号と合うモーダル表示部分があったらモーダル表示用のクラスを追加する
      if (modalItemNum == modalBtnNum) {
        document.querySelector('[data-modal="bg"]').classList.add('is-active');
        document.querySelector('[data-modal="inner"]').classList.add('is-active');
        modalItem.classList.add('is-active');
      }
    });
  });
});

// モーダルを閉じる
modalCloseBtns.forEach(modalCloseBtn => {
  modalCloseBtn.addEventListener('click', (e) => {
    // モーダル表示用のクラスを除去？
    e.currentTarget.closest('[data-modal="box"]').querySelector('[data-modal="bg"]').classList.remove('is-active');
    e.currentTarget.closest('[data-modal="box"]').querySelector('[data-modal="inner"]').classList.remove('is-active');
    const cardModals = e.currentTarget.closest('[data-modal="inner"]').querySelectorAll('[data-trigger="item"]');
    cardModals.forEach(cardModal => {
      cardModal.classList.remove('is-active');
    });
  });
});