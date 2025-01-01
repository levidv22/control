document.addEventListener('DOMContentLoaded', function () {
    const rowsPerPage = 3;
    const table = document.getElementById('savingsTable');
    const tbody = table.querySelector('tbody');
    const rows = Array.from(tbody.querySelectorAll('tr'));
    const pagination = document.getElementById('pagination');
    const maxVisibleButtons = 3; // Número máximo de botones visibles
    const totalPages = Math.ceil(rows.length / rowsPerPage);

    let currentPage = 1;

    function showPage(page) {
        const start = (page - 1) * rowsPerPage;
        const end = start + rowsPerPage;

        rows.forEach((row, index) => {
            row.style.display = (index >= start && index < end) ? '' : 'none';
        });

        updatePagination(page);
    }

    function updatePagination(page) {
        currentPage = page;

        // Limpia los botones actuales
        pagination.innerHTML = '';

        // Botón "anterior"
        const prevButton = document.createElement('li');
        prevButton.className = `page-item ${page === 1 ? 'disabled' : ''}`;
        prevButton.innerHTML = `<button class="page-link">&lt;</button>`;
        prevButton.addEventListener('click', () => {
            if (page > 1)
                showPage(page - 1);
        });
        pagination.appendChild(prevButton);

        // Números de página con rango dinámico
        let startPage = Math.max(1, page - Math.floor(maxVisibleButtons / 2));
        let endPage = Math.min(totalPages, startPage + maxVisibleButtons - 1);

        // Ajustar si estamos cerca del final
        if (endPage - startPage + 1 < maxVisibleButtons) {
            startPage = Math.max(1, endPage - maxVisibleButtons + 1);
        }

        if (startPage > 1) {
            // Botón para la primera página
            const firstPage = document.createElement('li');
            firstPage.className = 'page-item';
            firstPage.innerHTML = `<button class="page-link">1</button>`;
            firstPage.addEventListener('click', () => showPage(1));
            pagination.appendChild(firstPage);

            // Indicador de omisión
            if (startPage > 2) {
                const dots = document.createElement('li');
                dots.className = 'page-item disabled';
                dots.innerHTML = `<button class="page-link">...</button>`;
                pagination.appendChild(dots);
            }
        }

        for (let i = startPage; i <= endPage; i++) {
            const li = document.createElement('li');
            li.className = `page-item ${i === page ? 'active' : ''}`;
            li.innerHTML = `<button class="page-link">${i}</button>`;
            li.addEventListener('click', () => showPage(i));
            pagination.appendChild(li);
        }

        if (endPage < totalPages) {
            // Indicador de omisión
            if (endPage < totalPages - 1) {
                const dots = document.createElement('li');
                dots.className = 'page-item disabled';
                dots.innerHTML = `<button class="page-link">...</button>`;
                pagination.appendChild(dots);
            }

            // Botón para la última página
            const lastPage = document.createElement('li');
            lastPage.className = 'page-item';
            lastPage.innerHTML = `<button class="page-link">${totalPages}</button>`;
            lastPage.addEventListener('click', () => showPage(totalPages));
            pagination.appendChild(lastPage);
        }

        // Botón "siguiente"
        const nextButton = document.createElement('li');
        nextButton.className = `page-item ${page === totalPages ? 'disabled' : ''}`;
        nextButton.innerHTML = `<button class="page-link">&gt;</button>`;
        nextButton.addEventListener('click', () => {
            if (page < totalPages)
                showPage(page + 1);
        });
        pagination.appendChild(nextButton);
    }

    if (totalPages > 1) {
        showPage(1); // Muestra la primera página por defecto
    } else {
        rows.forEach(row => row.style.display = ''); // Muestra todas las filas si hay una sola página
    }
});



// Seleccionar todos los inputs tipo número con el atributo 'min'
const numberInputs = document.querySelectorAll('input[type="number"][min="0"]');

// Agregar evento 'input' a cada campo
numberInputs.forEach(input => {
    input.addEventListener('input', () => {
        // Si el valor es menor que el mínimo permitido (0), restablecerlo a 0
        if (input.value < 0) {
            input.value = 0;
        }
    });
});