
document.getElementById('deleteForm').addEventListener('submit', function (event) {
    var confirmDelete = confirm('本当に削除しますか？');
    if (!confirmDelete) {
        event.preventDefault(); // 削除をキャンセル
    }
});