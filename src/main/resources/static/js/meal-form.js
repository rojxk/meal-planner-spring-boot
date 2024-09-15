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

    function updateIngredientsList() {
        const list = document.getElementById('ingredientsList');
        list.innerHTML = ingredients.map((ing, index) =>
            `<div>${ing.name} - ${ing.quantity} ${ing.measureText} 
             <button type="button" class="button-main" onclick="removeIngredient(${index})">Remove</button></div>`
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