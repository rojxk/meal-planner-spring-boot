document.addEventListener('DOMContentLoaded', function() {
    let ingredients = [];

    window.addIngredient = function() {
        const name = document.getElementById('ingredientName').value;
        const quantity = document.getElementById('ingredientQuantity').value;
        const measureSelect = document.getElementById('ingredientMeasure');
        const measureId = measureSelect.value;
        const measureText = measureSelect.options[measureSelect.selectedIndex].text;

        if (name && quantity && measureId) {
            ingredients.push({ name, quantity, measureId, measureText });
            updateIngredientsList();
            clearIngredientInputs();
        } else {
            alert('Please fill all ingredient fields');
        }
    }

    // Function to limit input length
    function limitInputLength(input, maxLength) {
        if (input.value.length > maxLength) {
            input.value = input.value.slice(0, maxLength);
        }
    }

    // Function to allow only alphanumeric and space characters
    function allowAlphanumericAndSpace(input) {
        input.value = input.value.replace(/[^A-Za-z0-9\s]/g, '');
    }

    // Add event listeners for ingredient name and quantity inputs
    const ingredientNameInput = document.getElementById('ingredientName');
    const ingredientQuantityInput = document.getElementById('ingredientQuantity');

    if (ingredientNameInput) {
        ingredientNameInput.addEventListener('input', function() {
            limitInputLength(this,40);
            allowAlphanumericAndSpace(this);
        });
    }

    if (ingredientQuantityInput) {
        ingredientQuantityInput.addEventListener('input', function() {
            limitInputLength(this, 6);
        });
    }



    function updateIngredientsList() {
        const list = document.getElementById('ingredientsList');
        list.innerHTML = ingredients.map((ing, index) =>
            `<div class="ingredient-item">
            <span class="ingredient-text">${ing.name} - ${ing.quantity} ${ing.measureText}</span>
            <button type="button" class="button-remove" onclick="removeIngredient(${index})">Remove</button>
            </div>`
        ).join('');
    }

    window.removeIngredient = function(index) {
        ingredients.splice(index, 1);
        updateIngredientsList();
    }

    function clearIngredientInputs() {
        document.getElementById('ingredientName').value = '';
        document.getElementById('ingredientQuantity').value = '';
        // document.getElementById('ingredientMeasure').value = '';
    }


    document.querySelector('form').addEventListener('submit', function(e) {
        e.preventDefault();

        // Remove any existing ingredient inputs
        this.querySelectorAll('input[name^="ingredient"]').forEach(el => el.remove());

        // Add inputs for ingredients
        ingredients.forEach((ing, index) => {
            const nameInput = document.createElement('input');
            nameInput.type = 'hidden';
            nameInput.name = `ingredient[${index}].name`;
            nameInput.value = ing.name;
            this.appendChild(nameInput);

            const quantityInput = document.createElement('input');
            quantityInput.type = 'hidden';
            quantityInput.name = `ingredient[${index}].quantity`;
            quantityInput.value = ing.quantity;
            this.appendChild(quantityInput);

            const measureInput = document.createElement('input');
            measureInput.type = 'hidden';
            measureInput.name = `ingredient[${index}].measureId`;
            measureInput.value = ing.measureId;
            this.appendChild(measureInput);
        });

        this.submit();
    });
});