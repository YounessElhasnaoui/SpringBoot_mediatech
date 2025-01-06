// src/main/resources/static/js/main.js

document.addEventListener('DOMContentLoaded', function() {
    // ====== DOM References ======
    const entitySelect = document.getElementById('entitySelect');
    const endpointSelect = document.getElementById('endpointSelect');
    const requestBodyCard = document.getElementById('requestBodyCard');
    const fieldsWrapper = document.getElementById('fieldsWrapper');
    const executeBtn = document.getElementById('executeBtn');
    const errorContainer = document.getElementById('errorContainer');
    const resultContainer = document.getElementById('resultContainer');
    const loadingSpinner = document.getElementById('loadingSpinner');

    // Keep track of current endpoint's path params
    let currentPathParams = [];
    loadingSpinner.style.display = 'none';

    // ====== INIT ======
    populateEntityDropdown();

    // ====== EVENT LISTENERS ======
    entitySelect.addEventListener('change', handleEntityChange);
    endpointSelect.addEventListener('change', handleEndpointChange);
    executeBtn.addEventListener('click', executeRequest);

    // ====== FUNCTIONS ======

    function populateEntityDropdown() {
        entitySelect.innerHTML = '<option value="">-- Select an Entity --</option>';
        const entities = Object.keys(apiMetadata);
        entities.forEach(ent => {
            const option = document.createElement('option');
            option.value = ent;
            option.textContent = ent;
            entitySelect.appendChild(option);
        });
    }

    function handleEntityChange() {
        endpointSelect.innerHTML = '<option value="">-- Select an Endpoint --</option>';
        hideRequestBodyCard();
        clearError();
        clearResult();

        const entityValue = entitySelect.value;
        if (!entityValue) return;
        const endpoints = apiMetadata[entityValue].endpoints;
        endpoints.forEach(ep => {
            const opt = document.createElement('option');
            opt.value = JSON.stringify(ep);
            opt.textContent = ep.name;
            endpointSelect.appendChild(opt);
        });
    }

    function handleEndpointChange() {
        hideRequestBodyCard();
        clearError();
        clearResult();

        const endpointStr = endpointSelect.value;
        if (!endpointStr) return;

        const endpointObj = JSON.parse(endpointStr);
        currentPathParams = endpointObj.pathParams || [];

        // If there are path params, let's insert small input fields for each param
        if (currentPathParams.length > 0) {
            showRequestBodyCard(); // We'll reuse the same card for path param inputs as well
            fieldsWrapper.innerHTML = '';

            // "Path Params" section
            const pathParamHeader = document.createElement('h6');
            pathParamHeader.textContent = "Path Parameters";
            fieldsWrapper.appendChild(pathParamHeader);

            currentPathParams.forEach(pp => {
                const div = document.createElement('div');
                div.className = 'mb-2';
                div.innerHTML = `
          <label class="form-label">${pp}:</label>
          <input class="form-control" type="text" name="${pp}" placeholder="Enter ${pp}..." />
        `;
                fieldsWrapper.appendChild(div);
            });
        }

        // If the endpoint requires a body, we add another section
        if (endpointObj.bodyRequired && endpointObj.fields) {
            showRequestBodyCard();

            // Separate section title if path params also exist
            const bodyHeader = document.createElement('h6');
            bodyHeader.textContent = "Request Body Fields";
            fieldsWrapper.appendChild(bodyHeader);

            endpointObj.fields.forEach(field => {
                const div = document.createElement('div');
                div.className = 'mb-2';
                div.innerHTML = `
          <label class="form-label">${field}:</label>
          <input class="form-control" type="text" name="${field}" placeholder="Enter ${field}..." />
        `;
                fieldsWrapper.appendChild(div);
            });
        }
    }

    function showRequestBodyCard() {
        requestBodyCard.style.display = 'block';
    }

    function hideRequestBodyCard() {
        requestBodyCard.style.display = 'none';
        fieldsWrapper.innerHTML = '';
    }

    async function executeRequest() {
        clearError();
        clearResult();

        const endpointStr = endpointSelect.value;
        if (!endpointStr) {
            showError("Please select an endpoint first.");
            return;
        }

        const endpointObj = JSON.parse(endpointStr);
        let { method, path, bodyRequired } = endpointObj;

        // 1) Collect path params from fields (if any)
        if (currentPathParams.length > 0) {
            currentPathParams.forEach(param => {
                const input = fieldsWrapper.querySelector(`input[name="${param}"]`);
                const userValue = input?.value;
                if (userValue == null || userValue.trim() === '') {
                    showError(`Missing value for path param: ${param}`);
                    throw new Error(`Missing value for param: ${param}`);
                }
                path = path.replace(`{${param}}`, userValue.trim());
            });
        }

        // 2) Prepare fetch options
        const options = { method, headers: {} };

        // 2a) CSRF
        const csrfToken = document.querySelector('meta[name="_csrf"]')?.getAttribute('content') || "";
        const csrfHeader = document.querySelector('meta[name="_csrf_header"]')?.getAttribute('content') || "X-CSRF-TOKEN";
        options.headers[csrfHeader] = csrfToken;

        // 2b) If there's a body
        if (bodyRequired) {
            options.headers["Content-Type"] = "application/json; charset=UTF-8";
            // Gather field inputs
            const bodyInputs = endpointObj.fields || [];
            const formData = {};
            bodyInputs.forEach(field => {
                const fieldInput = fieldsWrapper.querySelector(`input[name="${field}"]`);
                if (fieldInput) {
                    formData[field] = fieldInput.value;
                }
            });
            options.body = JSON.stringify(formData);
        }

        // 3) Show loading spinner
        loadingSpinner.style.display = 'flex';
        executeBtn.disabled = true;

        try {
            const response = await fetch(path, options);
            if (!response.ok) {
                // Attempt to parse the error as JSON
                let errorJson;
                try {
                    errorJson = await response.json();
                } catch(e) {
                    errorJson = { message: await response.text() };
                }
                const status = errorJson.status || response.status;
                const errorMsg = errorJson.message
                    ? `${status} - ${errorJson.message}`
                    : `Error ${status}`;
                throw new Error(errorMsg);
            }

            // Parse successful response
            let data;
            try {
                data = await response.json();
            } catch(e) {
                data = null;
            }
            displayResult(data);

        } catch (err) {
            showError(err.message);
        } finally {
            loadingSpinner.style.display = 'none';
            executeBtn.disabled = false;
        }
    }

    function displayResult(data) {
        if (!data) {
            resultContainer.textContent = "No content in response.";
            return;
        }

        // If it's an array => table
        if (Array.isArray(data)) {
            renderArrayAsTable(data);
        } else if (typeof data === 'object') {
            // Single object => single-row or multi-row
            renderObjectAsTable(data);
        } else {
            // String or something else
            resultContainer.textContent = data;
        }
    }

    function renderArrayAsTable(arr) {
        if (!arr.length) {
            resultContainer.textContent = "Empty array.";
            return;
        }

        const table = document.createElement('table');
        table.className = 'table table-striped table-hover mt-2';

        // Table head
        const thead = document.createElement('thead');
        const headerRow = document.createElement('tr');
        const keys = Object.keys(arr[0]);
        keys.forEach(key => {
            const th = document.createElement('th');
            th.textContent = key;
            headerRow.appendChild(th);
        });
        thead.appendChild(headerRow);
        table.appendChild(thead);

        // Table body
        const tbody = document.createElement('tbody');
        arr.forEach(item => {
            const row = document.createElement('tr');
            keys.forEach(k => {
                const td = document.createElement('td');
                td.textContent = item[k];
                row.appendChild(td);
            });
            tbody.appendChild(row);
        });
        table.appendChild(tbody);

        resultContainer.appendChild(table);
    }

    function renderObjectAsTable(obj) {
        const keys = Object.keys(obj);
        if (!keys.length) {
            resultContainer.textContent = "Empty object.";
            return;
        }

        const table = document.createElement('table');
        table.className = 'table table-bordered mt-2';

        const thead = document.createElement('thead');
        const headerRow = document.createElement('tr');
        keys.forEach(k => {
            const th = document.createElement('th');
            th.textContent = k;
            headerRow.appendChild(th);
        });
        thead.appendChild(headerRow);

        const tbody = document.createElement('tbody');
        const row = document.createElement('tr');
        keys.forEach(k => {
            const td = document.createElement('td');
            td.textContent = obj[k];
            row.appendChild(td);
        });
        tbody.appendChild(row);

        table.appendChild(thead);
        table.appendChild(tbody);
        resultContainer.appendChild(table);
    }

    function showError(msg) {
        errorContainer.style.display = 'block';
        errorContainer.textContent = msg;
    }

    function clearError() {
        errorContainer.style.display = 'none';
        errorContainer.textContent = '';
    }

    function clearResult() {
        resultContainer.innerHTML = '';
    }
});
