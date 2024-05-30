function sortTable(columnIndex) {
    const table = document.getElementById("employeeTable");
    const tbody = table.querySelector("tbody");
    const rows = Array.from(tbody.querySelectorAll("tr"));

    const isAsc = table.getAttribute("data-sort-order") === "asc";
    table.setAttribute("data-sort-order", isAsc ? "desc" : "asc");

    const sortIcon = isAsc ? "▲" : "▼";
    const sortIcons = table.querySelectorAll(".sort-icon");
    sortIcons.forEach(icon => icon.textContent = "");

    const th = table.querySelectorAll(".sortable");
    th.forEach((header, index) => {
        if (index === columnIndex - 1) {
            header.querySelector(".sort-icon").textContent = sortIcon;
        } else {
            header.querySelector(".sort-icon").textContent = "";
        }
    });

    rows.sort((a, b) => {
        const aText = a.cells[columnIndex - 1].textContent.trim();
        const bText = b.cells[columnIndex - 1].textContent.trim();

        if (!isNaN(aText) && !isNaN(bText)) {
            return isAsc ? aText - bText : bText - aText;
        }

        return isAsc ? aText.localeCompare(bText) : bText.localeCompare(aText);
    });

    rows.forEach(row => tbody.appendChild(row));
}