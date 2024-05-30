// グローバル変数を定義して、初期状態を保持します
var isAscending = true;

function sortTable(columnIndex) {
    console.log("Sorting table...");
    var table, rows, switching, i, x, y, shouldSwitch;
    table = document.querySelector("table");
    switching = true;
    /* Make a loop that will continue until no switching has been done: */
    while (switching) {
        //start by saying: no switching is done:
        switching = false;
        rows = table.rows;
        /* Loop through all table rows (except the first, which contains table headers): */
        for (i = 1; i < (rows.length - 1); i++) {
            //start by saying there should be no switching:
            shouldSwitch = false;
            /* Get the two elements you want to compare, one from current row and one from the next: */
            x = rows[i].getElementsByTagName("TD")[columnIndex];
            y = rows[i + 1].getElementsByTagName("TD")[columnIndex];
            // ユーザーの意図に応じて、ソートする列を特定します
            if (columnIndex === 1) {
                // 社員IDの場合、数値として比較します
                if (isAscending) {
                    if (parseInt(x.innerHTML) > parseInt(y.innerHTML)) {
                        shouldSwitch = true;
                        break;
                    }
                } else {
                    if (parseInt(x.innerHTML) < parseInt(y.innerHTML)) {
                        shouldSwitch = true;
                        break;
                    }
                }
            } else {
                // それ以外の場合、文字列として比較します
                if (isAscending) {
                    if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
                        shouldSwitch = true;
                        break;
                    }
                } else {
                    if (x.innerHTML.toLowerCase() < y.innerHTML.toLowerCase()) {
                        shouldSwitch = true;
                        break;
                    }
                }
            }
        }
        if (shouldSwitch) {
            /* If a switch has been marked, make the switch and mark that a switch has been done: */
            rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
            switching = true;
        }
    }
    // ソートが完了したら、ソートの方向を反転させます
    isAscending = !isAscending;

    // アイコンのクラスを切り替えて、表示を更新します
    var sortIcons = document.querySelectorAll(".sort-icon");
    sortIcons.forEach(function(icon) {
        // 現在のソート状態に応じて、アイコンの方向を切り替えます
        if (isAscending) {
            icon.innerHTML = "&#9650;"; // 上向き三角アイコン
        } else {
            icon.innerHTML = "&#9660;"; // 下向き三角アイコン
        }
    });
}